package com.pinakbanik.leadingapps;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Teachers extends AppCompatActivity {
    Button a;
    Button b;
    Button c;
    Button d;
    Button e;
    Button f;
    Button g;
    Button h;
    Button i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);
        a=(Button)findViewById(R.id.button2);
        b=(Button)findViewById(R.id.button3);
        c=(Button)findViewById(R.id.button4);
        d=(Button)findViewById(R.id.button5);
        e=(Button)findViewById(R.id.button6);
        f=(Button)findViewById(R.id.button7);
        g=(Button)findViewById(R.id.button8);
        h=(Button)findViewById(R.id.button9);
        i=(Button)findViewById(R.id.button10);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lus.ac.bd/faculty-of-cse/"));
                startActivity(i);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lus.ac.bd/faculty-of-bua/"));
                startActivity(i);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lus.ac.bd/faculty-of-english/"));
                startActivity(i);
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lus.ac.bd/faculty-of-law/"));
                startActivity(i);
            }
        });

        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lus.ac.bd/faculty-of-architecture/"));
                startActivity(i);
            }
        });
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lus.ac.bd/faculty-list-of-civil/"));
                startActivity(i);
            }
        });
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lus.ac.bd/faculty-of-eee/"));
                startActivity(i);
            }
        });
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lus.ac.bd/faculty-of-islamic-studies/"));
                startActivity(i);
            }
        });
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lus.ac.bd/faculty-of-public-health/"));
                startActivity(i);
            }
        });

    }
}
