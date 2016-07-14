package ua.com.avatlantik.dubyk.i.dashboardclient;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import ua.com.avatlantik.dubyk.i.dashboardclient.dto.DataDTO;
import ua.com.avatlantik.dubyk.i.dashboardclient.dto.SalesUGKDTO;

/**
 * Created by i.dubyk on 11.07.2016.
 */
public class DownloadData extends AsyncTask<String, Integer, String> {

    private HttpURLConnection urlConnection = null;
    private BufferedReader reader = null;
    private String resultJson = "";
    private MainActivity mainActivity;
    private ProgressDialog progressDialog;
    private String nameData;
    private DataDTO dataDTO;


    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public String getNameData() {
        return nameData;
    }

    public void setNameData(String nameData) {
        this.nameData = nameData;
    }

    @Override
    protected void onPreExecute() {
        // обновляем пользовательский интерфейс сразу после выполнения задачи
        super.onPreExecute();

        progressDialog = new ProgressDialog(mainActivity);
        progressDialog.setCancelable(true);
        progressDialog.setMessage(mainActivity.getString(R.string.dowload_data_from_server));
        progressDialog.show();

    }

    @Override
    protected String doInBackground(String... url) {
        String content = null;
        try{
            content = downloadData(url[0]);
        }catch (IOException e){}

        return content;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
       // progressDialog.setMessage(mainActivity.getString(R.string.treatment_data));
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        progressDialog.cancel();
        mainActivity.setToastToActivity(result);

    }

    public String downloadData(String urlData) throws IOException {
    // получаем данные с внешнего ресурса
    try

    {
        URL url = new URL(urlData);

        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setUseCaches(false);
        urlConnection.connect();
        int status = urlConnection.getResponseCode();

        switch (status) {
            case 400:
                return mainActivity.getString(R.string.error_invalid_query);
            case 401:
                return mainActivity.getString(R.string.error_authorisation_error);
            case 404:
                return mainActivity.getString(R.string.error_data_not_found);
            case 200:
            case 201:
                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder buffer = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                reader.close();
                resultJson = buffer.toString();
        }
    } catch (MalformedURLException e) {
        e.printStackTrace();
        return mainActivity.getString(R.string.error_retrieving_data);
    } catch (IOException e) {
        e.printStackTrace();
        return mainActivity.getString(R.string.error_retrieving_data);
    }

    return parseDataJson(resultJson);
  }
    private String parseDataJson(String strJson){

        dataDTO = DataDTO.getInstance();

        if (nameData.equals("salesUGK")) {

            return parseSalesUGK(strJson);
        }

        return "";
    }

    private String parseSalesUGK(String strJson){

        String result;

        JSONObject dataJsonObj = null;
        try {

            dataJsonObj = new JSONObject(strJson);

            ArrayList<SalesUGKDTO> array = new ArrayList<>();

            JSONArray SalesUGKDTOarray = dataJsonObj.getJSONArray("salesUGK");

            for (int i = 0; i < SalesUGKDTOarray.length(); i++) {

                JSONObject SalesUGK = SalesUGKDTOarray.getJSONObject(i);

                array.add(new SalesUGKDTO(SalesUGK.getString("typeData"), SalesUGK.getInt("numberDay"), SalesUGK.getInt("value")));
            }

            dataDTO.setSalesUGKDTO(array);

            result = mainActivity.getString(R.string.finish_dowload_data);

        } catch (JSONException e) {
            e.printStackTrace();
            result = mainActivity.getString(R.string.error_processing_data);
        }

        return result;
    }

}
