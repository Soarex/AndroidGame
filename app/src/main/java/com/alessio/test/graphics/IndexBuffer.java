package com.alessio.test.graphics;

import com.alessio.test.utils.DebugTools;

import java.nio.IntBuffer;

import static android.opengl.GLES30.*;

public class IndexBuffer {
    private int handle;
    private int size;
    private int offset;

    public IndexBuffer(int size) {
        offset = 0;
        this.size = size;

        int[] temp = new int[1];
        glGenBuffers(1, temp, 0);
        handle = temp[0];

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, handle);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, size, null, GL_STATIC_DRAW);
        DebugTools.check();
    }

    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, handle);
        DebugTools.check();
    }

    public void add(int[] indices) {
        bind();
        glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, offset, indices.length * 4, IntBuffer.wrap(indices));
        offset += indices.length * 4;
        DebugTools.check();
    }

    public void delete() {
        int[] temp = new int[1];
        temp[0] = handle;
        glDeleteBuffers(1, temp, 0);
        DebugTools.check();
    }
}
