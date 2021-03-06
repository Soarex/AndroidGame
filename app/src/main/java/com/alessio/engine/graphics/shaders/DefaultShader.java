package com.alessio.engine.graphics.shaders;

import com.alessio.engine.graphics.Vertex;
import com.alessio.engine.utils.DebugTools;

import static android.opengl.GLES30.*;

public class DefaultShader extends Shader {
    private static final String VERTEX = "#version 300 es \n" +
            "" +
            "layout(location = 0) in vec3 position; \n" +
            "layout(location = 1) in vec4 color; \n" +
            "layout(location = 2) in vec2 textureCoordinates; \n" +
            "layout(location = 3) in float textureId; \n" +
            "" +
            "out vec2 textureCoord; \n" +
            "out float texId; \n" +
            "out vec4 colorOut; \n" +
            "" +
            "void main() { \n" +
            "   textureCoord = textureCoordinates; \n" +
            "   texId = textureId; \n" +
            "   colorOut = color; \n" +
            "   gl_Position = vec4(position, 1.0); \n" +
            "}";

    private static final String FRAGMENT = "#version 300 es \n" +
            "" +
            "in vec2 textureCoord; \n" +
            "in float texId; \n" +
            "in vec4 colorOut; \n" +
            "out vec4 finalColor; \n" +
            "" +
            "uniform sampler2D textures[16];" +
            "" +
            "void main() { \n" +
            "   int i = int(texId);" +
            "   switch(i){\n" +
            "       case -1: finalColor = colorOut; break;\n" +
            "       case  0: finalColor = texture(textures[ 0], textureCoord); break;\n" +
            "       case  1: finalColor = texture(textures[ 1], textureCoord); break;\n" +
            "       case  2: finalColor = texture(textures[ 2], textureCoord); break;\n" +
            "       case  3: finalColor = texture(textures[ 3], textureCoord); break;\n" +
            "       case  4: finalColor = texture(textures[ 4], textureCoord); break;\n" +
            "       case  5: finalColor = texture(textures[ 5], textureCoord); break;\n" +
            "       case  6: finalColor = texture(textures[ 6], textureCoord); break;\n" +
            "       case  7: finalColor = texture(textures[ 7], textureCoord); break;\n" +
            "       case  8: finalColor = texture(textures[ 8], textureCoord); break;\n" +
            "       case  9: finalColor = texture(textures[ 9], textureCoord); break;\n" +
            "       case 10: finalColor = texture(textures[10], textureCoord); break;\n" +
            "       case 11: finalColor = texture(textures[11], textureCoord); break;\n" +
            "       case 12: finalColor = texture(textures[12], textureCoord); break;\n" +
            "       case 13: finalColor = texture(textures[13], textureCoord); break;\n" +
            "       case 14: finalColor = texture(textures[14], textureCoord); break;\n" +
            "       case 15: finalColor = texture(textures[15], textureCoord); break;\n" +
            "       default: finalColor = vec4(1, 0, 1, 1);\n" +
            "   } \n" +
            "}";

    private int samplerArrayLoc;

    public DefaultShader() {
        super(VERTEX, FRAGMENT);
    }

    public void setUpAttributes() {
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

        setSamplers(16);

        DebugTools.check();
    }

    public void setSamplers(int quantity) {
        bind();
        int samplers[] = new int[quantity];

        for(int i = 0; i < quantity; i++)
            samplers[i] = i;

        glUniform1iv(samplerArrayLoc, quantity, samplers, 0);
    }
}
