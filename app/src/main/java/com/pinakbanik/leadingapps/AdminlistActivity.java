package com.pinakbanik.leadingapps;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminlistActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private Toolbar mToolbar;
    ListView list;
    private CustomAdapter2 mCustomAdapter2;
    ConnectionDetector cdd;
    Boolean isInternetPresent = false;
    TextView show;
    private ArrayList<Contacts2> _Contacts2 = new ArrayList<Contacts2>();
    String JSON_URL;
    String myJSON;
    private static final String TAG_RESULTS="result";
    JSONArray peoples = null;
    private SwipeRefreshLayout swipeRefreshLayout;
    int j;
    ArrayList<String> id_array = new ArrayList<String>();
    ArrayList<String> msg_array = new ArrayList<String>();
    ArrayList<String> cate_array = new ArrayList<String>();
    String idd;
    String msg;
    String cate;
    TextView tx1;
    EditText etx1;
    ConnectionD	cd;
    private ProgressDialog pDialog;
    long date2;
    SimpleDateFormat sdf;
    String dateString;
    private SQLiteHandler db;
    public AlarmManager alarmManager;
    Intent alarmIntent;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlist);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Admin");
        setSupportActionBar(mToolbar);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        db = new SQLiteHandler(this);
        cdd = new ConnectionDetector(this.getApplicationContext());
        isInternetPresent = cdd.isConnectingToInternet();
        cd = new ConnectionD(this);
        list = (ListView) findViewById(R.id.list3);
        list.setDivider(null);
        show=(TextView)findViewById(R.id.sh) ;
        show.setVisibility(View.GONE);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) this);
        JSON_URL = "http://akashjoy.com/leadingapps/msglist.php?limit=";
        date2 = System.currentTimeMillis();
        sdf = new SimpleDateFormat("MMM MM dd, yyyy h:mm a");
        dateString = sdf.format(date2);

        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        if (isInternetPresent) {
                                            if (mCustomAdapter2 == null) {
                                                show.setVisibility(View.GONE);
                                                j = 10;
                                                getJSON(JSON_URL + j);
                                            } else {
                                                show.setVisibility(View.GONE);
                                                j = j + 1;
                                                mCustomAdapter2.clearData();
                                                mCustomAdapter2.notifyDataSetChanged();
                                                getJSON(JSON_URL + j);
                                            }
                                        } else {
                                            AlertDialog.Builder builder =
                                                    new AlertDialog.Builder(AdminlistActivity.this, R.style.AppCompatAlertDialogStyle);

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

                                    }
                                }
        );






        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idd=id_array.get(position);
                msg=msg_array.get(position);
                cate=cate_array.get(position);
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.rank_dialog, null);
                etx1 = (EditText) alertLayout.findViewById(R.id.rep);
                tx1 = (TextView) alertLayout.findViewById(R.id.rank_dialog_text1);
                tx1.setText(msg);
                AlertDialog.Builder alert = new AlertDialog.Builder(AdminlistActivity.this, R.style.AppCompatAlertDialogStyle);
                alert.setTitle("Answer");
                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);
                // disallow cancel of AlertDialog on click of back button and outside touch
                alert.setCancelable(false);
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setPositiveButton("Send", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (cd.isConnectingToInternet()) {
                            if(etx1.getText().toString().trim().length() > 0){
                                Ans();
                                dialog.dismiss();
                                showDialog();
                                pDialog.setMessage("Please wait...");
                            }else{
                                AlertDialog.Builder builder =
                                        new AlertDialog.Builder(AdminlistActivity.this, R.style.AppCompatAlertDialogStyle);

                                builder.setTitle("Message");
                                builder.setMessage("Complete your details.");
                                builder.setCancelable(true);
                                builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                builder.show();
                            }

                        }else{
                            AlertDialog.Builder builder =
                                    new AlertDialog.Builder(AdminlistActivity.this, R.style.AppCompatAlertDialogStyle);

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

                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();

            }
        });
        nofic();

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();

            }
        });
    }


    private void nofic() {

        if (isInternetPresent) {
            alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmIntent = new Intent(AdminlistActivity.this, Alarm.class);
            pendingIntent = PendingIntent.getBroadcast(AdminlistActivity.this,1, alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),1000, pendingIntent);
            // Toast.makeText(HomeActivity.this,"latlng"+latitude+""+longitude, Toast.LENGTH_SHORT).show();



        }else {
            Log.i("ContractActivity", "No Internet.");
        }
    }




    @Override
    public void onRefresh() {
        if (isInternetPresent ) {
            if (mCustomAdapter2== null) {
                show.setVisibility(View.GONE);
                j=10;
                getJSON(JSON_URL+j);

            }else{
                show.setVisibility(View.GONE);
                j=j+1;
                mCustomAdapter2.clearData();
                mCustomAdapter2.notifyDataSetChanged();
                getJSON(JSON_URL+j);
            }
        }else{

        }
    }


    private void getJSON(String url) {
        class GetJSON extends AsyncTask<String, Void, String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }
                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                myJSON=s;
                showList();

            }
        }
        GetJSON gj = new GetJSON();
        gj.execute(url);


    }


    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            int arraylength = peoples.length();
            if (arraylength > 0) {

                for(int i=0;i<peoples.length();i++){
                    JSONObject c = peoples.getJSONObject(i);
                    Contacts2 contacts2 = new Contacts2();
                    String id = c.getString("id").toString();
                    String name = c.getString("name").toString();
                    String msg = c.getString("msg").toString();
                    String datee = c.getString("datee").toString();
                    String cate = c.getString("caty").toString();
                    contacts2.setId(id);
                    contacts2.setName(name);
                    contacts2.setMsg(cate);
                    contacts2.setCaty("Question:"+msg);
                    contacts2.setDate(datee);
                    id_array.add(id);
                    msg_array.add(msg);
                    cate_array.add(cate);
                    _Contacts2.add(contacts2);

                }
                mCustomAdapter2 = new CustomAdapter2(AdminlistActivity.this, _Contacts2);
                list.setAdapter(mCustomAdapter2);
                swipeRefreshLayout.setRefreshing(false);
            }else{
                swipeRefreshLayout.setRefreshing(false);
                show.setVisibility(View.VISIBLE);
                show.setText("No Data Found");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    private void Ans(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_ANS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideDialog();
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof NetworkError) {
                        } else if (error instanceof ServerError) {
                        } else if (error instanceof AuthFailureError) {
                        } else if (error instanceof ParseError) {
                        } else if (error instanceof NoConnectionError) {
                        } else if (error instanceof TimeoutError) {
                            Toast.makeText(AdminlistActivity.this,"Oops. Timeout error!",Toast.LENGTH_LONG).show();

                        }
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("id",idd);
                params.put("comment",msg);
                params.put("ans",etx1.getText().toString().trim());
                params.put("date",dateString);
                params.put("caty",cate);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(AdminlistActivity.this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
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

        if (id == R.id.action_logout) {
            if (cd.isConnectingToInternet()) {
                db.deleteUsers();
                Intent i = new Intent(AdminlistActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }else{
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(AdminlistActivity.this, R.style.AppCompatAlertDialogStyle);

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU)
            return true;
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        finish();

    }



}
