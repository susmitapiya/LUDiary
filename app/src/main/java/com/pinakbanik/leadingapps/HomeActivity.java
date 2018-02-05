package com.pinakbanik.leadingapps;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private Toolbar toolbar;
    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_Search= "search";
    private static final String TAG_Review = "rivew";
    private static final String TAG_Remind = "remind";
    private static final String TAG_Admin = "admin";
    public static String CURRENT_TAG = TAG_Search;
    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    private Handler mHandler;
    private SQLiteHandler db;
    String uniq;
    private TextView txtName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        db = new SQLiteHandler(HomeActivity.this);

        File database2=getApplicationContext().getDatabasePath("pihash2.db");

        if (database2.exists()) {
            SQLiteDatabase database = getApplicationContext().openOrCreateDatabase("pihash2.db",this.MODE_PRIVATE, null);
            String query = "select " +"id"+ " from uniapp2";
            Cursor c = database.rawQuery(query, null);
            int a= c.getCount();
            if (a>0) {
                HashMap<String, String> user = db.getUserDetails();
                uniq= user.get("uniq");
                if (uniq.equals("1")) {
                    Intent i = new Intent(HomeActivity.this, ContractActivity.class);
                    startActivity(i);
                    finish();
                }else if (uniq.equals("3")){
                    Intent i = new Intent(HomeActivity.this, AdminlistActivity.class);
                    startActivity(i);
                    finish();
                } else{
                    db.deleteUsers();
                }
            }

        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);


        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        // load nav menu header data


        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_Search;
            loadHomeFragment();
        }
        txtName.setText("Wellcome Apps");

    }



    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
//        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            // show or hide the fab button
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }


        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                InfoFragment infoFragment= new InfoFragment();
                return infoFragment;

            case 1:
                // home
               UserContractFragment userContractFragment= new UserContractFragment();
                return userContractFragment;

            case 2:
                // home
                ReminderHomeFragment reminderHomeFragment= new ReminderHomeFragment();
                return reminderHomeFragment;


            case 3:
                // home
                AdminFragment adminFragment= new AdminFragment();
                return adminFragment;

            default:
                 return new InfoFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_search:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_Search;
                        break;

                    case R.id.nav_review:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_Review;
                        break;


                    case R.id.nav_remind:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_Remind;
                        break;

                    case R.id.nav_admin:
                       navItemIndex = 3;
                        CURRENT_TAG = TAG_Admin;
                       break;
                    case R.id.waiver:
                      Intent i=new Intent(HomeActivity.this,waiver.class);
                        startActivity(i);
                        break;
                    case R.id.inf:
                        Intent intent=new Intent(HomeActivity.this,Teachers.class);
                        startActivity(intent);
                        break;
                    case R.id.history:
                        Intent in=new Intent(HomeActivity.this,History.class);
                        startActivity(in);
                        break;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

       if(id == R.id.action_exit){
            onBackPressed();
            drawer.closeDrawers();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}

