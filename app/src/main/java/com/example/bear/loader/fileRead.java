package com.example.bear.loader;


import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Handler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by bear on 6/4/15.
 */
public class fileRead implements Runnable {

    @Override
    public void run() {
        MainActivity.numbers.clear();

        try {
            FileInputStream fin = MainActivity.ctx.openFileInput("numbers.txt");
            int i = 0;
            while ((i = fin.read()) != -1) {
                MainActivity.numbers.add(i);
                Thread.sleep(250);
                MainActivity.p.setProgress(i * 10);
            }
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}