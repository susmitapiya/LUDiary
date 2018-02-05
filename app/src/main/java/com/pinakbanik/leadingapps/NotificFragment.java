package com.pinakbanik.leadingapps;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class NotificFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
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
    private SQLiteHandler db;
    String idd;
    int j;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notific,
                container, false);
        cdd = new ConnectionDetector(getActivity().getApplicationContext());
        isInternetPresent = cdd.isConnectingToInternet();
        db = new SQLiteHandler(getActivity());
        HashMap<String, String> user = db.getUserDetails();
        idd = user.get("idd");
        list = (ListView) view.findViewById(R.id.list3);
        list.setDivider(null);
        show=(TextView)view.findViewById(R.id.sh) ;
        show.setVisibility(View.GONE);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) this);
        JSON_URL = "http://akashjoy.com/leadingapps/anslist.php?id="+idd+"&limit=";
        JSON_URL = JSON_URL.replaceAll(" ", "%20");
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
                                                    new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);

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



        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();

            }
        });






        return view;
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
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);

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
                    String msg = c.getString("msg").toString();
                    String ans = c.getString("ans").toString();
                    String date = c.getString("date").toString();
                    String cate = c.getString("caty").toString();
                    contacts2.setId(id);
                    contacts2.setName("My Question:"+msg );
                    contacts2.setCaty(cate);
                    contacts2.setMsg("Admin Answer:"+ans);
                    contacts2.setDate(date);
                    _Contacts2.add(contacts2);

                }
                mCustomAdapter2 = new CustomAdapter2(getActivity(), _Contacts2);
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


}
