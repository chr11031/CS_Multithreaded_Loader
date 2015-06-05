package com.example.bear.loader;


import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Handler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Class created to be laucnhed as a thread
 * to help the main process with file reading
 * into a mainActivity static variable
 */
public class fileRead implements Runnable {
    // Dynamic file location
    private String file;

    /**
     * Constructor, tell us where we are reading from
     * @param var the value of the file/file path
     */
    public fileRead(String var) {
        file = var;
    }

    /**
     * run, standard runnable implementation
     * to progressively read in values from
     * the file, increment the progress bar
     */
    @Override
    public void run() {
        MainActivity.numbers.clear();

        try {
            FileInputStream fin = MainActivity.ctx.openFileInput(file);
            int i = 0;
            while ((i = fin.read()) != -1) {

                // Add the read in variable, sleep, increment progress
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