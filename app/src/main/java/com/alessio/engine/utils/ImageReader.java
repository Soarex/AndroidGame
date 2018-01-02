package com.alessio.engine.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.alessio.engine.Context;

public class ImageReader {
    public static Bitmap readImage(int resourceId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        return BitmapFactory.decodeResource(Context.getContext().getResources(), resourceId, options);
    }
}
