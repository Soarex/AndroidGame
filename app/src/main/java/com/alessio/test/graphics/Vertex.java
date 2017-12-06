package com.alessio.test.graphics;

import com.alessio.test.math.Float2;
import com.alessio.test.math.Float3;
import com.alessio.test.math.Float4;

public class Vertex {
    public Float3 position;
    public Float4 color;
    public Float2 textureCoordinates;
    public float textureId;

    public static final int SIZE = 4 * 3 + 4 * 4 + 2 * 4 + 4;

    public Vertex() {

    }

    public Vertex(Float3 position, Float4 color, Float2 textureCoordinates, float textureId) {
        this.position = position;
        this.color = color;
        this.textureCoordinates = textureCoordinates;
        this.textureId = textureId;
    }

    public int getSize() {
        return SIZE;
    }

    public float[] toFloatArray() {
        float[] res = new float[10];

        res[0] = position.x;
        res[1] = position.y;
        res[2] = position.z;

        res[3] = color.x;
        res[4] = color.y;
        res[5] = color.z;
        res[6] = color.w;

        res[7] = textureCoordinates.x;
        res[8] = textureCoordinates.y;

        res[9] = textureId;

        return res;
    }
}
