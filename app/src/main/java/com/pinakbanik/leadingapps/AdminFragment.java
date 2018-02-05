package com.pinakbanik.leadingapps;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class AdminFragment extends Fragment {
    private static final String TAG ="Signin" ;
    EditText username;
    EditText pass;
    private ProgressDialog pDialog;
    ConnectionD	cd;
    private SQLiteHandler db;
    String uniq;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        View view = inflater.inflate(R.layout.fragment_admin,
                container, false);


        cd = new ConnectionD(getActivity());
        db = new SQLiteHandler(getActivity());
        username=(EditText)view.findViewById(R.id.userName);
        pass=(EditText)view.findViewById(R.id.password);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);



        view.findViewById(R.id.signin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cd.isConnectingToInternet()) {
                    if (username.getText().toString().trim().length() > 0 && pass.getText().toString().trim().length() > 0) {
                        showDialog();
                        pDialog.setMessage("Please wait...");
                        loginUser();
                    }else{
                        Toast.makeText(getActivity(), "Please complete your details", Toast.LENGTH_LONG).show();
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



        return view;
    }


    private void loginUser(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_ADLOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideDialog();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            String error = jObj.getString("error");

                            // Check for error node in json
                            if (error.equals("1")) {
                                // Now store the user in SQLite
                                String uid = jObj.getString("uid");
                                String name = jObj.getString("username");
                                Toast.makeText(getActivity(),"Sign in successful",Toast.LENGTH_LONG).show();
                                db.addUser(uid,"3",name,name);
                                Intent i = new Intent(getActivity(), AdminlistActivity.class);
                                startActivity(i);
                                getActivity().finish();

                            } else {
                                Toast.makeText(getActivity(),error,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }


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
                            Toast.makeText(getContext(),
                                    "Oops. Timeout error!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("username", username.getText().toString().trim());
                params.put("password", pass.getText().toString().trim());
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

