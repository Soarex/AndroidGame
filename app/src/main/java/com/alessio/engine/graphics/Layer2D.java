package com.alessio.engine.graphics;


import android.view.MotionEvent;

import com.alessio.engine.events.Event;
import com.alessio.engine.graphics.shaders.Shader;

public abstract class Layer2D {
    protected Renderer2D renderer;
    protected Shader shader;

    public Layer2D(Renderer2D renderer, Shader shader) {
        this.renderer = renderer;
        this.shader = shader;
    }

    public void onInit() {
        renderer.init();
        shader.init();
    }

    public abstract void onRender();
    public abstract void onEvent(Event e);
    public abstract void onEvent(MotionEvent e);
    public abstract void onUpdate();
}
