package com.alessio.engine;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;

import com.alessio.engine.graphics.Layer2D;
import com.alessio.engine.graphics.TextureManager;
import com.alessio.engine.math.Float2;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glViewport;

public abstract class Application extends Activity implements GLSurfaceView.Renderer {
    private Screen screen;
    private ArrayList<Layer2D> layerStack;

    public Application() {
        layerStack = new ArrayList<>();
    }

    public void init() {
        TextureManager.init();
    }

    public void start() { //WIP
        run();
    }

    public void addLayer(Layer2D layer) {
        layerStack.add(layer);
        layer.onInit();
    }

    private void run() { //WIP
            update();
    }

    public void onEvent(MotionEvent e) {
        for(int i = 0; i < layerStack.size(); i++)
            layerStack.get(i).onEvent(e);
    }

    private void render() {
        for(int i = 0; i < layerStack.size(); i++)
            layerStack.get(i).onRender();
    }

    private void update() {
        for(int i = 0; i < layerStack.size(); i++)
            layerStack.get(i).onUpdate();
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        init();
    }

    public void onDrawFrame(GL10 unused) {
        render();
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        glViewport(0, 0, width, height);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screen = new Screen(this, this);
        screen.setRenderer(this);
        setContentView(screen);
    }
}
