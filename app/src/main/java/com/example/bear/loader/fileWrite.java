package com.example.bear.loader;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by bear on 6/4/15.
 */
public class fileWrite implements Runnable {

    @Override
    public void run() {
        String FileName = "numbers.txt";

        try {
            FileOutputStream fos = MainActivity.ctx.openFileOutput(FileName, Context.MODE_PRIVATE);
            for(Integer output = 1; output < 11; output++) {
                Thread.sleep(250);
                MainActivity.p.setProgress(output * 10);
                fos.write(output.byteValue());
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
