package com.pinakbanik.leadingapps;


import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AlarmService extends IntentService {
    private static final String TAG = "Trac User";
    String uniq;
    String id;
    private SQLiteHandler db;
    HashMap<String, String> user;



    public static String URL= "http://akashjoy.com/leadingapps/usernofic.php";
    public static String URL2= "http://akashjoy.com/leadingapps/adminnotific.php";
    public AlarmService() {
        super("AlarmService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
            db = new SQLiteHandler(this);

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
                    id= user.get("idd");
                    // Log.i(TAG,id);
                    Check2();
                }else if (uniq.equals("3")) {
                    id= user.get("idd");
                    // Log.i(TAG,id);
                    Check();

                }else
                {
                    db.deleteUsers();
                }
            }

        }

    }


    private void Check2(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(!response.equals("")){
                            sendNotification(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("id",id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }


    private void Check(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(!response.equals("")){
                            sendNotification(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("id",id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }


    private void sendNotification(String messageBody) {

        int icon = R.drawable.ic_launcher;

        int mNotificationId = 001;

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext());
        Notification notification = mBuilder.setSmallIcon(icon).setTicker("Apps Name").setWhen(0)
                .setAutoCancel(true)
                .setContentTitle("Apps Name")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(messageBody))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ic_launcher))
                .setContentText(messageBody).build();

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(mNotificationId, notification);
    }





}