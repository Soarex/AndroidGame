package com.alessio.test.graphics;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class Screen extends GLSurfaceView {
    private Renderer2D renderer;

    public Screen(Context context) {
        super(context);
        setEGLContextClientVersion(3);
        renderer = new Renderer2D();
        setRenderer(renderer);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
