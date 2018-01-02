package com.alessio.engine.graphics;

import com.alessio.engine.utils.DebugTools;

import java.nio.FloatBuffer;

import static android.opengl.GLES30.*;

public class VertexBuffer {
    private int handle;
    private int size;
    private int offset;

    public VertexBuffer(int size) {
        offset = 0;
        this.size = size;

    }

    public void init() {
        int[] temp = new int[1];
        glGenBuffers(1, temp, 0);
        handle = temp[0];

        glBindBuffer(GL_ARRAY_BUFFER, handle);
        glBufferData(GL_ARRAY_BUFFER, size, null, GL_STATIC_DRAW);
        DebugTools.check();
    }

    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, handle);
        DebugTools.check();
    }

    public void add(Vertex vertex) {
        bind();
        glBufferSubData(GL_ARRAY_BUFFER, offset, vertex.getSize(), FloatBuffer.wrap(vertex.toFloatArray()));
        offset += vertex.getSize();
        DebugTools.check();
    }

    public void clear() {
        offset = 0;
    }

    public void delete() {
        int[] temp = new int[1];
        temp[0] = handle;
        glDeleteBuffers(1, temp, 0);
        DebugTools.check();
    }
}
