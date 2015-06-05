package com.example.bear.loader;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Class meant to run as a thread
 * to assist the main process for
 * file writing purposes
 */
public class fileWrite implements Runnable {
    // Dynamic file location
    private String file;

    /**
     * Constructor, say where we are writing to
     * @param var the value of the filename/file path
     */
    public fileWrite(String var) {
        file = var;
    }

    /**
     * Run, the functions of creating a file
     * populated with the first 10 nonnegative
     * numbers.
     */
    @Override
    public void run() {
        String FileName = file;

        try {
            FileOutputStream fos = MainActivity.ctx.openFileOutput(FileName, Context.MODE_PRIVATE);
            for(Integer output = 1; output < 11; output++) {

                // Sleep, write out to the file, increment the progress bar
                Thread.sleep(250);
                fos.write(output.byteValue());
                MainActivity.p.setProgress(output * 10);
            }
            fos.close();
            MainActivity.isBuilt = true;
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
