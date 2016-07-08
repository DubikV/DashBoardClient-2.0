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

import ua.com.avatlantik.dubyk.i.dashboardclient.Constants.ConstantsForms;
import ua.com.avatlantik.dubyk.i.dashboardclient.Modules.Module_ReadWrite_Data;
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

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close){

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
                    toolbar.setTitle(getString(R.string.app_name) + ": " + getString(R.string.nav_salesUgk_ua));
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new TabFragmentSalesUGK()).commit();
                }

                if (item.getItemId() == R.id.nav_salesMoney) {
                    toolbar.setTitle(getString(R.string.app_name) + ": " + getString(R.string.nav_salesMoney_ua));
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new TabFragmentSalesMoney()).commit();
                }


                if (item.getItemId() == R.id.nav_info) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new InfoFragment()).commit();

                }

                if (item.getItemId() == R.id.nav_settings) {
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


}
