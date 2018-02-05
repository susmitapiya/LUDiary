package com.pinakbanik.leadingapps;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.util.HashMap;

public class ContractActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
    private SQLiteHandler db;
    HashMap<String, String> user;
    String version;
    ConnectionD	cd;
    ConnectionDetector cdd;
    Boolean isInternetPresent = false;
    // index to identify current nav menu item
    public static int navItemIndex = 0;
    // tags used to attach the fragments
    private static final String TAG_Search= "search";
    private static final String TAG_Search2= "mess";
    private static final String TAG_Review = "notific";
    public static String CURRENT_TAG = TAG_Search;
    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;
    private Handler mHandler;
    String name;
    String email;
    public AlarmManager alarmManager;
    Intent alarmIntent;
    PendingIntent pendingIntent;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cd = new ConnectionD(ContractActivity.this);
        cdd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cdd.isConnectingToInternet();
        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.email);


        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_title2);
        db = new SQLiteHandler(this);
        user = db.getUserDetails();
        name=user.get("username");
        email= user.get("email");


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_Search;
            loadHomeFragment();
        }

        nofic();


    }

    private void nofic() {

        if (isInternetPresent) {
            alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmIntent = new Intent(ContractActivity.this, Alarm.class);
            pendingIntent = PendingIntent.getBroadcast(ContractActivity.this,1, alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),1000, pendingIntent);
            // Toast.makeText(HomeActivity.this,"latlng"+latitude+""+longitude, Toast.LENGTH_SHORT).show();



        }else {
            Log.i("ContractActivity", "No Internet.");
        }
    }





    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */


    private void loadNavHeader() {
        // name, website
        txtName.setText(name);
        txtWebsite.setText(email);


    }


    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

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
                MessFragment messsMessFragment = new MessFragment();
                return messsMessFragment;

            case 2:
                if (cd.isConnectingToInternet()) {
                    // home
                    NotificFragment notificFragment = new NotificFragment();
                    return notificFragment;
                }else{

                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(ContractActivity.this, R.style.AppCompatAlertDialogStyle);

                    builder.setTitle("Message");
                    builder.setMessage("Please turn on your internet connection!");
                    builder.setCancelable(true);
                    builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }




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

                    case R.id.nav_search2:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_Search2;
                        break;

                    case R.id.nav_review:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_Review;
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
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            if (cd.isConnectingToInternet()) {
                AlertDialog.Builder builder2 =
                        new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);

                builder2.setTitle("Message");
                builder2.setMessage("Are you sure you want to sign out?");
                builder2.setCancelable(true);
                builder2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                                new ResultCallback<Status>() {
                                    @Override
                                    public void onResult(Status status) {
                                        db.deleteUsers();
                                        Intent i = new Intent(ContractActivity.this, HomeActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                });

                    }

                })
                        .setNegativeButton("No", null)
                        .show();
            }else{
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(ContractActivity.this, R.style.AppCompatAlertDialogStyle);

                builder.setTitle("Message");
                builder.setMessage("Please turn on your internet connection!");
                builder.setCancelable(true);
                builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
            return true;
        }else if(id == R.id.action_exit){
            onBackPressed();
            drawer.closeDrawers();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
