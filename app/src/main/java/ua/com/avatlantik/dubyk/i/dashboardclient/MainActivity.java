package ua.com.avatlantik.dubyk.i.dashboardclient;

import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.MenuItem;

import ua.com.avatlantik.dubyk.i.dashboardclient.Constants.ConstantsForms;
import ua.com.avatlantik.dubyk.i.dashboardclient.Modules.Module_ReadWrite_Data;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        module_readWrite_data = new Module_ReadWrite_Data(this);

        res = getResources();

        module_readWrite_data.readDataFromMemory();

        initToolbar();

        initNavigationView();

        initTabs();

        initMail();

        setDisplaySizeInConstants();

        setTextLoginToBar();

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

    private void initMail() {

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    }
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();


                if (item.getItemId() == R.id.nav_salesUgk) {
                    toolbar.setTitle(getString(R.string.app_name) + ": " + getString(R.string.nav_salesUgk_ua));
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new TabFragmentSalesUGK()).commit();
                }

                if (item.getItemId() == R.id.nav_salesMoney) {
                    toolbar.setTitle(getString(R.string.app_name) + ": " + getString(R.string.nav_salesMoney_ua));
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new TabFragmentSalesMoney()).commit();
                }


//                if (item.getItemId() == R.id.nav_salesMoney) {
//                    toolbar.setTitle(getString(R.string.app_name) + ": " + getString(R.string.nav_salesMoney_ua));
//                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
//                    xfragmentTransaction.replace(R.id.containerView,new TabFragmentMoney()).commit();
//                }

                if (item.getItemId() == R.id.nav_info) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new InfoFragment()).commit();

                }

                if (item.getItemId() == R.id.nav_settings) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new SettingsFragment()).commit();

                }

                if (item.getItemId() == R.id.nav_exit) {
                    System.exit(0);

                }
                return false;

//                switch (item.getItemId()) {
//                    case R.id.nav_salesUgk:
//                        toolbar.setTitle(getString(R.string.app_name) + ": " + getString(R.string.nav_salesUgk_ua));
//                        showNotificationTab();
//                        break;
//                    case R.id.nav_salesMoney:
//                        toolbar.setTitle(getString(R.string.app_name) + ": " + getString(R.string.nav_salesMoney_ua));
//                        showNotificationTab();
//                        break;
//                    case R.id.nav_margin:
//                        toolbar.setTitle(getString(R.string.app_name) + ": " + getString(R.string.nav_margin_ua));
//                        break;
//                    case R.id.nav_stocks:
//                        toolbar.setTitle(getString(R.string.app_name) + ": " + getString(R.string.nav_stocks_ua));
//                        break;
//                    case R.id.nav_settings:
//                        toolbar.setTitle(getString(R.string.action_settings_ua));
//                        break;
//                    case R.id.nav_loadData:
//                        toolbar.setTitle(getString(R.string.nav_loaddata_ua));
//                        break;
//                    case R.id.nav_info:
//                        toolbar.setTitle(getString(R.string.nav_info_ua));
//                        break;
//                    case R.id.nav_exit:
//                        toolbar.setTitle(getString(R.string.nav_exit_ua));
//                        break;
//                }
//                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//                drawer.closeDrawer(GravityCompat.START);
//                return true;
            }

        });
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

    private void setTextLoginToBar() {

//        TextView text_nav_heared_login = (TextView) findViewById(R.id.text_nav_heared_login);
//
//        if (settingsUser.getUserLogin() != null){
//            text_nav_heared_login.setText(settingsUser.getUserLogin());
//        }
   }


}
