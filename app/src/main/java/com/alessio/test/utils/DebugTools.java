package com.alessio.test.utils;

import android.util.Log;

import static android.opengl.GLES20.GL_NO_ERROR;
import static android.opengl.GLES20.glGetError;

public class DebugTools {
    public static void clear() {
        while(glGetError() != GL_NO_ERROR);
    }

    public static void check() {
        int errorCode;
        while((errorCode = glGetError()) != GL_NO_ERROR)
            Log.e("Opengl Error",  Integer.toString(errorCode));
    }
}