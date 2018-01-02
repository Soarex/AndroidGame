package com.alessio.engine.graphics.shaders;

import android.util.Log;

import com.alessio.engine.utils.DebugTools;

import static android.opengl.GLES30.*;

public abstract class Shader {
    protected int program;
    protected String vertexSource, fragmentSource;

    public Shader(String vertexSource, String fragmentSource) {
        this.vertexSource = vertexSource;
        this.fragmentSource = fragmentSource;
    }

    public void init() {
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

        glDeleteShader(vertexShaderHandle);
        glDeleteShader(fragmentShaderHandle);

        setUpAttributes();

        DebugTools.check();
    }

    public abstract void setUpAttributes();

    public void bind() {
        glUseProgram(program);
    }
}
