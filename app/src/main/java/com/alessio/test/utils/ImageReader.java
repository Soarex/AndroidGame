package com.alessio.test.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.alessio.test.App;

public class ImageReader {
    public static Bitmap readImage(int resourceId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        return BitmapFactory.decodeResource(App.getAppContext().getResources(), resourceId, options);
    }
}
