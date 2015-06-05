package com.example.bear.loader;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import android.os.Handler;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.example.bear.loader.R.layout.layout;


public class MainActivity extends ActionBarActivity {
    public static ProgressBar p;
    public static List<Integer> numbers;
    public static Boolean isBuilt = false;
    public static Context ctx;
    public static ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup our variables
        lv= (ListView) findViewById(R.id.listViewVar);
        ctx = getApplicationContext();
        numbers = new ArrayList<>();
        p = (ProgressBar) findViewById(R.id.ourbar);
        p.setMax(100);
        Button a = (Button) findViewById(R.id.A);
        Button b = (Button) findViewById(R.id.B);
        Button c = (Button) findViewById(R.id.C);

        a.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        createList();
                    }
                });


        b.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        populateList();
                    }
                });

        c.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        clearList();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void update() {
        ArrayAdapter<Integer> adapter;
        adapter = new ArrayAdapter<Integer>(MainActivity.this, R.layout.layout, MainActivity.numbers);
        MainActivity.lv.setAdapter(adapter);
    }

    public void createList() {
        // Launch our thread for writing data
        fileWrite fw = new fileWrite();
        Thread write = new Thread(fw);
        write.start();
    }

    public void populateList() {
        // Load file values from numbers.txt"
        if(isBuilt == false) {
            Toast.makeText(
                    getBaseContext(),
                    "File must be created before it can load",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        // Launch our thread for reading data
        final Handler h = new Handler();
        fileRead fr = new fileRead();
        new Thread(new Runnable() {

            // Standard function we want to run through our thread
            @Override
            public void run() {
                new fileRead().run();
                h.post(new Runnable() {

                            //The function we want to happen ONLY when the thread is done
                           @Override
                           public void run() {
                               update();
                           }
                       });
            }
        }).start();
    }

    public void clearList() {
        Integer[] blank = {};
        ArrayAdapter<Integer> adapter;
        adapter = new ArrayAdapter<Integer>(MainActivity.this, R.layout.layout, blank);
        ListView lv = (ListView) findViewById(R.id.listViewVar); // Code here
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
