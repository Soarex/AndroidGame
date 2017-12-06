package com.alessio.test.graphics;

import com.alessio.test.math.Float2;
import com.alessio.test.math.Float3;
import com.alessio.test.math.Float4;

public class Sprite {
    public Float3 position;
    public Float2 size;
    public Float4 color;
    public Texture texture;

    public Sprite() {
        position = new Float3();
        size = new Float2(1, 1);
        color = new Float4(0, 0, 0, 1);
        texture = null;
    }

    public Sprite(Float3 position, Float2 size, Float4 color) {
        this.position = position;
        this.size = size;
        this.color = color;
        texture = null;
    }

    public Sprite(Float3 position, Float2 size, Texture texture) {
        this.position = position;
        this.size = size;
        this.color = new Float4(0, 0, 0, 0);
        this.texture = texture;
    }
}
