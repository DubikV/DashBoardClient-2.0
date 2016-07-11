package ua.com.avatlantik.dubyk.i.dashboardclient;

import android.os.AsyncTask;

import ua.com.avatlantik.dubyk.i.dashboardclient.dto.DataDTO;

/**
 * Created by i.dubyk on 11.07.2016.
 */
public class DownloadData extends AsyncTask<Void, Void, DataDTO> {

   protected DataDTO doInBackground(Void... params) {
//
//        RestTemplate template = new RestTemplate();
//        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//
//        return template.getForObject(Constants.URL.GET_REMIND, RemindDTO.class);
       return null;
   }

    @Override
    protected void onPostExecute(DataDTO remindDTO) {

//        Toolbar toolbar = (Toolbar) view.getRootView().findViewById(R.id.toolbar);
//        toolbar.setTitle(getString(R.string.app_name) + ": " + getString(R.string.nav_salesUgk_ua));
//
//        NavigationView navigationView = (NavigationView) view.getRootView().findViewById(R.id.nav_view);
//        navigationView.getMenu().performIdentifierAction(R.id.nav_salesUgk, 0);

    }
}
