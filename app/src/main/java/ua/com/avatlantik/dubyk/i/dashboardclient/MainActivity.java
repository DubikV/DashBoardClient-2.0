package ua.com.avatlantik.dubyk.i.dashboardclient;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import ua.com.avatlantik.dubyk.i.dashboardclient.Constants.ConstantsForms;
import ua.com.avatlantik.dubyk.i.dashboardclient.Modules.Module_GetURL;
import ua.com.avatlantik.dubyk.i.dashboardclient.Modules.Module_ReadWrite_Data;
import ua.com.avatlantik.dubyk.i.dashboardclient.Settings.SettingConnect;
import ua.com.avatlantik.dubyk.i.dashboardclient.Settings.SettingsUser;
import ua.com.avatlantik.dubyk.i.dashboardclient.adapter.TabFragmentSalesMoney;
import ua.com.avatlantik.dubyk.i.dashboardclient.adapter.TabFragmentSalesUGK;
import ua.com.avatlantik.dubyk.i.dashboardclient.fragment.InfoFragment;
import ua.com.avatlantik.dubyk.i.dashboardclient.fragment.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_main;

    private Resources res;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Module_ReadWrite_Data module_readWrite_data;
    private Module_GetURL module_getURL;
    private SettingConnect settingConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        module_readWrite_data = new Module_ReadWrite_Data(this);
        module_getURL = new Module_GetURL(this);
        settingConnect = SettingConnect.getInstance();

        res = getResources();

        module_readWrite_data.readDataFromMemory();


        initToolbar();

        initNavigationView();

        initTabs();

        setDisplaySizeInConstants();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        module_readWrite_data.saveDataToMemory();

    }

    @Override
    protected void onStop() {
        super.onStop();

        module_readWrite_data.saveDataToMemory();

    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name)+": "+getString(R.string.nav_salesUgk_ua));
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                int id = menuItem.getItemId();

                if (id == R.id.action_loadData) {
                 downloadData();
                 return true;
                }
                return false;

            }
        });

        toolbar.inflateMenu(R.menu.main);


    }

    private void initTabs() {

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new TabFragmentSalesUGK()).commit();

   }

    private void initNavigationView() {

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close){


            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                return super.onOptionsItemSelected(item);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                 /* hide keyboard */
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);


                TextView text_nav_heared_login = (TextView) findViewById(R.id.text_nav_heared_login);

                SettingsUser settingsUser = SettingsUser.getInstance();

                if (settingsUser.getUserLogin() != null){

                text_nav_heared_login.setText(settingsUser.getUserLogin());

                }

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

        //Initializing NavigationView
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();

                item.setChecked(true);

                if (item.getItemId() == R.id.nav_salesUgk) {
                    onNavigationItemSelectedSalesUGK();
                }

                if (item.getItemId() == R.id.nav_salesMoney) {
                    onNavigationItemSelectedMoney();
                }

                if (item.getItemId() == R.id.nav_margin) {
                    onNavigationItemSelectedMargin();
                }

                if (item.getItemId() == R.id.nav_stocks) {
                    onNavigationItemSelectedStocks();
                }


                if (item.getItemId() == R.id.nav_info) {
                    setToolbarText(getString(R.string.nav_info_ua));
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new InfoFragment()).commit();

                }

                if (item.getItemId() == R.id.nav_settings) {
                    setToolbarText(getString(R.string.action_settings_ua));
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new SettingsFragment()).commit();

                }

                if (item.getItemId() == R.id.nav_exit) {

                    SetOnExitApp();

                }
                return false;
            }

        });
    }

    private void onNavigationItemSelectedSalesUGK(){

        if (settingConnect.isAvtoDownload()) {

            if(module_getURL.getCheckConnektion()) {

                String url = module_getURL.getGetURL("salesUGK");
                DownloadData downloadData = new DownloadData();
                downloadData.setMainActivity(this);
                downloadData.setNameData("salesUGK");
                downloadData.setOpenStart(true);
                downloadData.execute(url);

            }

        }else {
            setToolbarText(getString(R.string.app_name) + ": " + getString(R.string.nav_salesUgk_ua));
            FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
            xfragmentTransaction.replace(R.id.containerView, new TabFragmentSalesUGK()).commit();
        }
    }

    private void onNavigationItemSelectedMoney(){

        if (settingConnect.isAvtoDownload()) {

            if(module_getURL.getCheckConnektion()) {

                String url = module_getURL.getGetURL("money");
                DownloadData downloadData = new DownloadData();
                downloadData.setMainActivity(this);
                downloadData.setNameData("salesUGK");
                downloadData.setOpenStart(true);
                downloadData.execute(url);

            }

        }else {
            setToolbarText(getString(R.string.app_name) + ": " + getString(R.string.nav_salesMoney_ua));
            FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
            xfragmentTransaction.replace(R.id.containerView,new TabFragmentSalesMoney()).commit();
        }
    }

    private void onNavigationItemSelectedMargin(){

        if (settingConnect.isAvtoDownload()) {

            if(module_getURL.getCheckConnektion()) {

                String url = module_getURL.getGetURL("money");
                DownloadData downloadData = new DownloadData();
                downloadData.setMainActivity(this);
                downloadData.setNameData("salesUGK");
                downloadData.setOpenStart(true);
                downloadData.execute(url);

            }

        }else {
            setToolbarText(getString(R.string.app_name) + ": " + getString(R.string.nav_margin_ua));
            FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
            xfragmentTransaction.replace(R.id.containerView,new TabFragmentSalesMoney()).commit();
        }
    }

    private void onNavigationItemSelectedStocks(){

        if (settingConnect.isAvtoDownload()) {

            if(module_getURL.getCheckConnektion()) {

                String url = module_getURL.getGetURL("money");
                DownloadData downloadData = new DownloadData();
                downloadData.setMainActivity(this);
                downloadData.setNameData("salesUGK");
                downloadData.setOpenStart(true);
                downloadData.setIdItemSelected(R.id.nav_salesUgk);
                downloadData.execute(url);

            }

        }else {
            setToolbarText(getString(R.string.app_name) + ": " + getString(R.string.nav_stocks_ua));
            FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
            xfragmentTransaction.replace(R.id.containerView,new TabFragmentSalesMoney()).commit();
        }
    }



    private void SetOnExitApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.title_Exit));
        builder.setMessage(getString(R.string.questions_Exit));

        builder.setPositiveButton(getString(R.string.questions_Exit_answer_yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                System.exit(0);

            }
        });

        builder.setNegativeButton(getString(R.string.questions_Exit_answer_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void setDisplaySizeInConstants() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        ConstantsForms.DISPLAY_WIDHT = size.x;
        ConstantsForms.DISPLAY_HEIGHT = size.y;
    }

    @Override
    public void onBackPressed() {
        try{
            drawerLayout.closeDrawers();
        }catch (Exception e){
            super.onBackPressed();
        }
    }

    public void downloadData(){

        if(module_getURL.getCheckConnektion()) {

                String url = module_getURL.getGetURL("salesUGK");
                DownloadData downloadData = new DownloadData();
                downloadData.setMainActivity(this);
                downloadData.setNameData("salesUGK");
                downloadData.setOpenStart(true);
                downloadData.setIdItemSelected(R.id.nav_salesUgk);
                downloadData.execute(url);

        }

    }

    public void setToolbarText(String text){

        toolbar.setTitle(text);

    }

    public void setToastToActivity(String textToast){

        Toast.makeText(getApplicationContext(), textToast, Toast.LENGTH_LONG).show();

    }


}
