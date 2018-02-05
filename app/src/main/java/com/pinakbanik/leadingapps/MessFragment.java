package com.pinakbanik.leadingapps;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


public class MessFragment extends Fragment {

    private SQLiteHandler db;
    String name;
    String idd;
    private ProgressDialog pDialog;
    long date2;
    SimpleDateFormat sdf;
    String dateString;
    ConnectionD	cd;
    ListView listView;
    EditText etx1;
    String it;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mess,
                container, false);
        db = new SQLiteHandler(getActivity());
        HashMap<String, String> user = db.getUserDetails();
        name = user.get("username");
        idd = user.get("idd");
        date2 = System.currentTimeMillis();
        sdf = new SimpleDateFormat("MMM MM dd, yyyy :mm a");
        cd = new ConnectionD(getActivity());
        dateString = sdf.format(date2);
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);


         listView = (ListView) view.findViewById(R.id.listView);

        final String[] items = { "Admission Requirment", "Admission Date And Oriantation Of Spring",
                "Admission Date And Oriantation Of Summer", "Admission Date And Oriantation Of Fall",
                "How To Pay Tution Fee","How To Collect Admission Form","Default","Default","Default","Other Facilities" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, items);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                it=items[arg2].toString();

                LayoutInflater inflater = getActivity().getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.rank_dialog, null);
                etx1 = (EditText) alertLayout.findViewById(R.id.rep);
                AlertDialog.Builder alert = new AlertDialog.Builder( getActivity(), R.style.AppCompatAlertDialogStyle);
                alert.setTitle("Report");
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
                                addMsg();
                                dialog.dismiss();
                                showDialog();
                                pDialog.setMessage("Please wait...");
                            }else{
                                AlertDialog.Builder builder =
                                        new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);

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
                });
                AlertDialog dialog = alert.create();
                dialog.show();



            }

        });

        return view;
    }


    private void addMsg(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_MSG,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideDialog();
                        Toast.makeText(getActivity(),response,Toast.LENGTH_LONG).show();
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
                            Toast.makeText(getActivity(),"Oops. Timeout error!",Toast.LENGTH_LONG).show();

                        }
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("name",name);
                params.put("id",idd);
                params.put("comment", etx1.getText().toString().trim());
                params.put("date",dateString);
                params.put("caty",it);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
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


}
