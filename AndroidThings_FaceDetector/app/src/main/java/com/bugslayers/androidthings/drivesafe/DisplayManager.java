package com.bugslayers.androidthings.drivesafe;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.android.things.contrib.driver.ssd1306.BitmapHelper;
import com.google.android.things.contrib.driver.ssd1306.Ssd1306;
import java.io.IOException;

public class DisplayManager {

    private static final String TAG = DisplayManager.class.getName();

    private Ssd1306 display;

    public DisplayManager() {}

    public void init() {
        try {
            display = new Ssd1306("I2C1");
            display.setContrast(100);
            Log.d(TAG, "Display initialized!");
        }
        catch(IOException ioe) {
            Log.e(TAG, "Error initialing the display");
            ioe.printStackTrace();
        }
    }

    public void setImage(Resources res, int resId) {
        display.clearPixels();
        Bitmap bmp = BitmapFactory.decodeResource(res, resId);
        BitmapHelper.setBmpData(display, 0,0, bmp, true);

        try {
            display.show();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
