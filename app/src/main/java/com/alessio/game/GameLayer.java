package com.alessio.game;

import android.util.Log;
import android.view.MotionEvent;

import com.alessio.engine.events.Event;
import com.alessio.engine.graphics.Layer2D;
import com.alessio.engine.graphics.Renderer2D;
import com.alessio.engine.graphics.Sprite;
import com.alessio.engine.graphics.Texture;
import com.alessio.engine.graphics.TextureManager;
import com.alessio.engine.graphics.shaders.DefaultShader;
import com.alessio.engine.math.Float2;
import com.alessio.engine.math.Float3;

public class GameLayer extends Layer2D {
    private Sprite redBrick, whiteBrick;

    public GameLayer() {
        super(new Renderer2D(), new DefaultShader());
    }

    public void onInit() {
        super.onInit();
        redBrick = new Sprite(new Float3(-0.75f, 0, 0.7f), new Float2(0.5f, 0.25f),
                TextureManager.add(new Texture(R.drawable.texture1), "Red"));

        whiteBrick = new Sprite(new Float3(0.25f, 0, 1), new Float2(0.5f, 0.25f),
                TextureManager.add(new Texture(R.drawable.texture2), "White"));
    }

    public void onRender() {
        shader.bind();
        renderer.submit(redBrick);
        renderer.submit(whiteBrick);

        renderer.render();
    }

    public void onUpdate() {

    }

    public void onEvent(Event e) {

    }

    public void onEvent(MotionEvent e) {
        redBrick.position.x = (e.getX(0) / 1080 * 2) - 1;
        redBrick.position.y = (e.getY(0) / 1900 * -2) + 1;

        if (e.getPointerCount() > 1) {
            whiteBrick.position.x = (e.getX(1) / 1080 * 2) - 1;
            whiteBrick.position.y = (e.getY(1) / 1900 * -2) + 1;
        }
    }
}
