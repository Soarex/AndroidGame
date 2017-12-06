package com.alessio.test.graphics;

import android.util.Log;

import com.alessio.test.utils.DebugTools;

import static android.opengl.GLES30.*;

public class Shader {
    private int program;
    private int samplerArrayLoc;

    public Shader(String vertexSource, String fragmentSource) {
        int vertexShaderHandle, fragmentShaderHandle;
        vertexShaderHandle = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShaderHandle, vertexSource);
        glCompileShader(vertexShaderHandle);

        int[] res = new int[1];
        glGetShaderiv(vertexShaderHandle, GL_COMPILE_STATUS, res, 0);
        if (res[0] != GL_TRUE)
            Log.e("Shader error: ", glGetShaderInfoLog(vertexShaderHandle));

        fragmentShaderHandle = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShaderHandle, fragmentSource);
        glCompileShader(fragmentShaderHandle);

        glGetShaderiv(fragmentShaderHandle, GL_COMPILE_STATUS, res, 0);
        if (res[0] != GL_TRUE)
            Log.e("Shader error: ", glGetShaderInfoLog(fragmentShaderHandle));

        program = glCreateProgram();
        glAttachShader(program, vertexShaderHandle);
        glAttachShader(program, fragmentShaderHandle);
        glLinkProgram(program);

        glGetProgramiv(program, GL_LINK_STATUS, res, 0);
        if (res[0] != GL_TRUE)
            Log.e("Program error: ", glGetProgramInfoLog(program));

        glUseProgram(program);

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        glEnableVertexAttribArray(3);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE, 0);
        glVertexAttribPointer(1, 4, GL_FLOAT, false, Vertex.SIZE, 3 * 4);
        glVertexAttribPointer(2, 2, GL_FLOAT, false, Vertex.SIZE, 3 * 4 + 4 * 4);
        glVertexAttribPointer(3, 1, GL_FLOAT, false, Vertex.SIZE, 3 * 4 + 4 * 4 + 4 * 2);
        samplerArrayLoc = glGetUniformLocation(program, "textures");

        glDeleteShader(vertexShaderHandle);
        glDeleteShader(fragmentShaderHandle);

        DebugTools.check();
    }

    public void bind() {
        glUseProgram(program);
    }

    public void setSamplers(int quantity) {
        bind();
        int samplers[] = new int[quantity];

        for(int i = 0; i < quantity; i++)
            samplers[i] = i;

        glUniform1iv(samplerArrayLoc, quantity, samplers, 0);
    }
}
