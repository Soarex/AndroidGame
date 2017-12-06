package com.alessio.test.math;

public class Float3 {
    public float x, y, z;

    public Float3() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Float3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float[] toArray() {
        float[] res = new float[3];
        res[0] = x;
        res[1] = y;
        res[2] = z;
        return res;
    }
}
