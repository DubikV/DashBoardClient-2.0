package ua.com.avatlantik.dubyk.i.dashboardclient.Modules;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import ua.com.avatlantik.dubyk.i.dashboardclient.Constants.ConstantsGlobal;
import ua.com.avatlantik.dubyk.i.dashboardclient.MainActivity;
import ua.com.avatlantik.dubyk.i.dashboardclient.R;
import ua.com.avatlantik.dubyk.i.dashboardclient.Settings.SettingConnect;
import ua.com.avatlantik.dubyk.i.dashboardclient.Settings.SettingsUser;


public class Module_GetURL {
    private MainActivity mainActivity;
    private SettingsUser settingsUser;
    private SettingConnect settingConnect;


    public Module_GetURL() {
        settingsUser = SettingsUser.getInstance();
        settingConnect = SettingConnect.getInstance();
    }

    public Module_GetURL(MainActivity mainActivity) {
        settingsUser = SettingsUser.getInstance();
        settingConnect = SettingConnect.getInstance();
        this.mainActivity = mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public String getGetURL(String dataName){

        String textURL;
        if(dataName.equals("checkConnection")){
            return "http://" + settingConnect.getAdressServer() + ConstantsGlobal.HTTPSERVICE_1C_NAME + "/" + dataName;
        }else {
            return "http://" + settingConnect.getAdressServer() + ConstantsGlobal.HTTPSERVICE_1C_NAME + "/" + dataName + "?login=" + getusrlogin() + "&password=" + settingsUser.getUserPassword();
        }

    }

    private String getusrlogin(){
        String usrLog = settingsUser.getUserLogin();
        String arrStr[] = usrLog.split(" ");
        String usrlogin ="";
        for (int i =0; i< arrStr.length; i++){
            if(i==arrStr.length-1){
                try {
                    usrlogin = usrlogin+ URLEncoder.encode(arrStr[i], "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    usrlogin = usrlogin+ arrStr[i];
                }
                continue;
            }
            try {
                usrlogin = usrlogin+ URLEncoder.encode(arrStr[i], "UTF-8")+"%20";
            } catch (UnsupportedEncodingException e) {
                usrlogin = usrlogin+arrStr[i]+"%20";
            }
        }

        return usrlogin;
    }


    public boolean getCheckConnektion() {

       if (getCheckEnternet()) {



           //try {
//                URL url = new URL(urlString);
//
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setRequestMethod("GET");
//                urlConnection.setUseCaches(false);
//                urlConnection.setConnectTimeout(1000);
//                urlConnection.connect();
//                int status = urlConnection.getResponseCode();
//
//                switch (status) {
//                    case 200:
//                    case 201:
                       return true;
//                }
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//                mainActivity.setToastToActivity(mainActivity.getString(R.string.error_connection_server));
//                return false;
//            } catch (IOException e) {
//                e.printStackTrace();
//                mainActivity.setToastToActivity(mainActivity.getString(R.string.error_connection_server));
//                return false;
//            }
//            mainActivity.setToastToActivity(mainActivity.getString(R.string.error_connection_server));
//            return false;
        }
      return false;
    }

    public boolean getCheckEnternet() {

            final ConnectivityManager conMgr = (ConnectivityManager)mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();

              if (activeNetwork != null && activeNetwork.isConnected()) {
                  return true;
              } else {
                  mainActivity.setToastToActivity(mainActivity.getString(R.string.error_internet_connecting));
                 return false;
              }
    }
}
