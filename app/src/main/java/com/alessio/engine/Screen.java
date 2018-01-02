package com.alessio.engine;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import com.alessio.engine.math.Float2;

public class Screen extends GLSurfaceView {
    public Application a;

    public Screen(Context context, Application a) {
        super(context);
        setEGLContextClientVersion(3);
        this.a = a;
    }

    public boolean onTouchEvent(MotionEvent e) {
        a.onEvent(e);
        return true;
    }
}
