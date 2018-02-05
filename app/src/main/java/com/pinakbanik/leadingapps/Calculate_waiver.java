package com.pinakbanik.leadingapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Calculate_waiver extends AppCompatActivity {
    EditText ssc,hsc;
    ImageView submitImage;
    TextView outputText;

    String StrSSC,StrHSC,result="";

    String dept="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_waiver);
        setTitle("weaver");


        Bundle bundle = getIntent().getExtras();

        if(bundle!=null)
        {
            dept = bundle.getString("DEPT");
        }


        ssc= (EditText) findViewById(R.id.ssc);
        hsc= (EditText) findViewById(R.id.hsc);
        submitImage= (ImageView) findViewById(R.id.submitImage);
        outputText= (TextView) findViewById(R.id.outputText);


        Toast.makeText(getApplicationContext(),dept,Toast.LENGTH_SHORT).show();



        submitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hideSoftKeyboard();

                StrSSC = ssc.getText().toString();
                StrHSC = hsc.getText().toString();

                double ss = Double.parseDouble(StrSSC);
                double hs = Double.parseDouble(StrHSC);

                int sss = (int) Math.floor(ss);
                int hhh = (int) Math.floor(hs);

                // Toast.makeText(getApplicationContext(),sss+"  "+hhh,Toast.LENGTH_SHORT).show();


                if (StrSSC.equals("") || StrHSC.equals("") || (StrSSC.equals("") &&
                        StrHSC.equals("")) || sss > 5 || hhh > 5) {

                    outputText.setText("Invalid Input");
                } else {

                    double s = Double.parseDouble(StrSSC);
                    double h = Double.parseDouble(StrHSC);

                    Double cgpa = s + h;

                    int total_res = (int) Math.floor(cgpa);
                    if (dept.equals("LLB")) {


                        if (total_res < 6) {
                            result = "You are not qualified";
                        } else if (total_res == 6) {
                            result = "Waiver=0%\n Total=2,96,100";
                        } else if (total_res == 7) {
                            result = "Waiver=10%\n Total=2,72,790";
                        } else if (total_res == 8) {
                            result = "Waiver=15%\n Total=2,61,135";
                        } else if (total_res == 9) {
                            result = "Waiver=30%\n Total=2,26,170";
                        } else if (total_res == 10) {
                            result = "Waiver=50%\n Total=1,79,550\n " +
                                    "if 1 golden in either=60%\n Total=1,56,240\n" +
                                    " if both in golden=75%\n Total=1,21,275";
                        }
                        else {
                            result = "Invalid Input";
                        }


                    }
                  else  if (dept.equals("BBA")) {


                        if (total_res < 6) {
                            result = "You are not qualified";
                        } else if (total_res == 6) {
                            result = "Waiver=0%\n Total=3,46,500";
                        } else if (total_res == 7) {
                            result = "Waiver=10%\n Total=3,18,150";
                        } else if (total_res == 8) {
                            result = "Waiver=15%\n Total=3,03,975";
                        } else if (total_res == 9) {
                            result = "Waiver=30%\n Total=2,61,450";
                        } else if (total_res == 10) {
                            result = "Waiver=50%\n Total=2,04,750\n if 1 golden in either=60%\n Total=1,76,400\n if both in golden=75%\n Total=1,33,875";
                        }
                        else {
                            result = "Invalid Input";
                        }


                    }
                    else  if (dept.equals("BBA")) {


                        if (total_res < 6) {
                            result = "You are not qualified";
                        } else if (total_res == 6) {
                            result = "Waiver=0%\n Total=3,46,500";
                        } else if (total_res == 7) {
                            result = "Waiver=10%\n Total=3,18,150";
                        } else if (total_res == 8) {
                            result = "Waiver=15%\n Total=3,03,975";
                        } else if (total_res == 9) {
                            result = "Waiver=30%\n Total=2,61,450";
                        } else if (total_res == 10) {
                            result = "Waiver=50%\n Total=2,04,750\n if 1 golden in either=60%\n Total=1,76,400\n if both in golden=75%\n Total=1,33,875";
                        }
                        else {
                            result = "Invalid Input";
                        }


                    }
                    else  if (dept.equals("English")) {


                        if (total_res < 6) {
                            result = "You are not qualified";
                        } else if (total_res == 6) {
                            result = "Waiver=0%\n Total=2,61,400";
                        } else if (total_res == 7) {
                            result = "Waiver=10%\n Total=2,41,560";
                        } else if (total_res == 8) {
                            result = "Waiver=15%\n Total=2,31,640";
                        } else if (total_res == 9) {
                            result = "Waiver=30%\n Total=2,01,880";
                        } else if (total_res == 10) {
                            result = "Waiver=50%\n Total=1,62,200\n if 1 golden in either=60%\n Total=1,42,360\n if both in golden=75%\n Total=1,12,600";
                        }
                        else {
                            result = "Invalid Input";
                        }


                    }
                    else  if (dept.equals("EEE")) {


                        if (total_res < 6) {
                            result = "You are not qualified";
                        } else if (total_res == 6) {
                            result = "Waiver=0%\n Total=3,95,500";
                        } else if (total_res == 7) {
                            result = "Waiver=10%\n Total=3,63,600";
                        } else if (total_res == 8) {
                            result = "Waiver=15%\n Total=3,47,900";
                        } else if (total_res == 9) {
                            result = "Waiver=30%\n Total=3,00,800";
                        } else if (total_res == 10) {
                            result = "Waiver=50%\n Total=2,38,000\n if 1 golden in either=60%\n Total=2,06,600\n if both in golden=75%\n Total=1,59,500";
                        }
                        else {
                            result = "Invalid Input";
                        }


                    }
                    else  if (dept.equals("CSE")) {


                        if (total_res < 6) {
                            result = "You are not qualified";
                        } else if (total_res == 6) {
                            result = "Waiver=0%\n Total=3,63,800";
                        } else if (total_res == 7) {
                            result = "Waiver=10%\n Total=3,34,920";
                        } else if (total_res == 8) {
                            result = "Waiver=15%\n Total=3,20,480";
                        } else if (total_res == 9) {
                            result = "Waiver=30%\n Total=2,77,160";
                        } else if (total_res == 10) {
                            result = "Waiver=50%\n Total=2,19,400\n if 1 golden in either=60%\n Total=1,90,520\n if both in golden=75%\n Total=1,47,200";
                        }
                        else {
                            result = "Invalid Input";
                        }


                    }

                    else  if (dept.equals("Architecture")) {


                        if (total_res < 6) {
                            result = "You are not qualified";
                        } else if (total_res == 6) {
                            result = "Waiver=0%\n Total=5,25,600";
                        } else if (total_res == 7) {
                            result = "Waiver=10%\n Total=4,82,400";
                        } else if (total_res == 8) {
                            result = "Waiver=15%\n Total=4,60,260";
                        } else if (total_res == 9) {
                            result = "Waiver=30%\n Total=3,94,920";
                        } else if (total_res == 10) {
                            result = "Waiver=50%\n Total=3,07,800\n if 1 golden in either=60%\n Total=2,64,240\n if both in golden=75%\n Total=1,98,900";
                        }
                        else {
                            result = "Invalid Input";
                        }


                    }
                    else  if (dept.equals("Civil Engg")) {


                        if (total_res < 6) {
                            result = "You are not qualified";
                        } else if (total_res == 6) {
                            result = "Waiver=0%\n Total=4,19,400";
                        } else if (total_res == 7) {
                            result = "Waiver=10%\n Total=3,84,960";
                        } else if (total_res == 8) {
                            result = "Waiver=15%\n Total=3,67,740";
                        } else if (total_res == 9) {
                            result = "Waiver=30%\n Total=3,16,080";
                        } else if (total_res == 10) {
                            result = "Waiver=50%\n Total=2,47,200\n if 1 golden in either=60%\n Total=2,12,760\n if both in golden=75%\n Total=1,61,100";
                        }
                        else {
                            result = "Invalid Input";
                        }


                    }
                    else  if (dept.equals("Civil Engg")) {


                        if (total_res < 6) {
                            result = "You are not qualified";
                        } else if (total_res == 6) {
                            result = "Waiver=0%\n Total=4,19,400";
                        } else if (total_res == 7) {
                            result = "Waiver=10%\n Total=3,84,960";
                        } else if (total_res == 8) {
                            result = "Waiver=15%\n Total=3,67,740";
                        } else if (total_res == 9) {
                            result = "Waiver=30%\n Total=3,16,080";
                        } else if (total_res == 10) {
                            result = "Waiver=50%\n Total=2,47,200\n if 1 golden in either=60%\n Total=2,12,760\n if both in golden=75%\n Total=1,61,100";
                        }
                        else {
                            result = "Invalid Input";
                        }


                    }

                    else  if (dept.equals("Islamic Study")) {


                        if (total_res < 6) {
                            result = "You are not qualified";
                        } else if (total_res == 6) {
                            result = "Waiver=0%\n Total=75,600";
                        } else if (total_res == 7) {
                            result = "Waiver=10%\n Total=74,340";
                        } else if (total_res == 8) {
                            result = "Waiver=15%\n Total=73,710";
                        } else if (total_res == 9) {
                            result = "Waiver=30%\n Total=71,820";
                        } else if (total_res == 10) {
                            result = "Waiver=50%\n Total=69,300\n if 1 golden in either=60%\n Total=68,040\n if both in golden=75%\n Total=66,150";
                        }
                        else {
                            result = "Invalid Input";
                        }


                    }


                    else  if (dept.equals("Tourism & Hospitality Management")) {


                        if (total_res < 6) {
                            result = "You are not qualified";
                        } else if (total_res == 6) {
                            result = "Waiver=0%\n Total=3,11,000";
                        } else if (total_res == 7) {
                            result = "Waiver=10%\n Total=2,85,800";
                        } else if (total_res == 8) {
                            result = "Waiver=15%\n Total=2,73,200";
                        } else if (total_res == 9) {
                            result = "Waiver=30%\n Total=2,35,400";
                        } else if (total_res == 10) {
                            result = "Waiver=50%\n Total=1,85,000\n if 1 golden in either=60%\n Total=1,59,800\n if both in golden=75%\n Total=1,22,000";
                        }
                        else {
                            result = "Invalid Input";
                        }


                    }



                    outputText.setText(result);
                }

            }
        });
    }

    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);


    }

}