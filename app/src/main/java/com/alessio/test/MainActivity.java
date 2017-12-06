package com.alessio.test;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.alessio.test.graphics.Screen;

public class MainActivity extends Activity {
    private GLSurfaceView screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screen = new Screen(this);
        setContentView(screen);
    }
}
