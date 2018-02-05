package com.pinakbanik.leadingapps;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class waiver extends AppCompatActivity {
    ListView listView;
    ArrayList<String> items;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiver);


        listView=(ListView)findViewById(R.id.listView);

        items=new ArrayList<>();
        items.add("LLB");
        items.add("BBA");
        items.add("English");
        items.add("EEE");
        items.add("CSE");
        items.add("Architecture");
        items.add("Civil Engg");
        items.add("Islamic Study");
        items.add("Tourism & Hospitality Management");
        items.add("ccc");


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,

                items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text=listView.getItemAtPosition(position).toString();

                Intent i=new Intent (waiver.this,Calculate_waiver.class);
                String name = items.get(position);

                if (name.equals("LLB")) {
                    i.putExtra("DEPT", "LLB");
                }
                else if (name.equals("BBA")) {
                    i.putExtra("DEPT", "BBA");
                }
                else if (name.equals("English")) {
                    i.putExtra("DEPT", "English");
                }
                else if (name.equals("EEE")) {
                    i.putExtra("DEPT", "EEE");
                }
                else if (name.equals("CSE")) {
                    i.putExtra("DEPT", "CSE");
                }
                else if (name.equals("Architecture")) {
                    i.putExtra("DEPT", "Architecture");
                }
                else if (name.equals("Civil Engg")) {
                    i.putExtra("DEPT", "Civil Engg");
                }
                else if (name.equals("Islamic Study")) {
                    i.putExtra("DEPT", "Islamic Study");
                }

                else if (name.equals("Tourism & Hospitality Management")) {
                    i.putExtra("DEPT", "Tourism & Hospitality Management");
                }
                else if (name.equals("ccc")) {
                    i.putExtra("DEPT", "ccc");
                }


                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        MenuItem searchItem =menu.findItem(R.id.listView_search);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView((MenuItem) searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> templist=new ArrayList<>();
                for (String temp:items){
                    if(temp.toLowerCase().contains(newText.toLowerCase())){
                        templist.add(temp);
                    }
                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(waiver.this,android.R.layout.simple_list_item_1,templist);
                listView.setAdapter(adapter);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


}

