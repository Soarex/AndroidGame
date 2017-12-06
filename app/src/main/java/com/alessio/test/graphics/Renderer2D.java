package com.alessio.test.graphics;

import android.opengl.GLSurfaceView;

import com.alessio.test.R;
import com.alessio.test.utils.DebugTools;
import com.alessio.test.math.Float2;
import com.alessio.test.math.Float3;
import com.alessio.test.math.Float4;
import com.alessio.test.shaders.DefaultShaders;

import java.util.ArrayList;
import java.util.Stack;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES30.*;

class Renderer2D implements GLSurfaceView.Renderer {
    private VertexBuffer vertexBuffer;
    private IndexBuffer indexBuffer;
    private ArrayList<Texture> textures;
    private int indexBase, vertexCount;

    public Renderer2D() {
        indexBase = 0;
        vertexCount = 0;
        textures = new ArrayList<>(32);
    }


    Shader shader;
    Sprite a, b;
    Texture t1, t2;
    public void initialize() {
        vertexBuffer = new VertexBuffer(Vertex.SIZE * 4 * 60000);
        indexBuffer = new IndexBuffer(60000 * 4);
        shader = new Shader(DefaultShaders.VERTEX, DefaultShaders.FRAGMENT);
        t1 = new Texture(R.drawable.texture1);
        t2 = new Texture(R.drawable.texture2);

        a = new Sprite(new Float3(-0.75f, 0, 1), new Float2(0.5f, 0.25f), t1);
        b = new Sprite(new Float3(0.25f, 0, 1), new Float2(0.5f, 0.25f), t2);
    }

    public void submit(Sprite sprite) {
        int textureId = -1;
        if(sprite.texture != null) {
            for (int i = 0; i < textures.size(); i++)
                if (textures.get(i).getHandle() == sprite.texture.getHandle())
                    textureId = i;

            if(textureId == -1) {
                textureId = textures.size();
                textures.add(sprite.texture);
            }
        }

        Vertex vertex = new Vertex();
        vertex.position = sprite.position;
        vertex.color = sprite.color;
        vertex.textureCoordinates = new Float2(0, 0);
        vertex.textureId = textureId;
        vertexBuffer.add(vertex);

        vertex.position = new Float3(sprite.position.x + sprite.size.x, sprite.position.y, sprite.position.z);
        vertex.color = sprite.color;
        vertex.textureCoordinates = new Float2(1, 0);
        vertex.textureId = textureId;
        vertexBuffer.add(vertex);

        vertex.position = new Float3(sprite.position.x + sprite.size.x, sprite.position.y + sprite.size.y, sprite.position.z);
        vertex.color = sprite.color;
        vertex.textureCoordinates = new Float2(1, 1);
        vertex.textureId = textureId;
        vertexBuffer.add(vertex);

        vertex.position = new Float3(sprite.position.x, sprite.position.y + sprite.size.y, sprite.position.z);
        vertex.color = sprite.color;
        vertex.textureCoordinates = new Float2(0, 1);
        vertex.textureId = textureId;
        vertexBuffer.add(vertex);

        int[] indices = {
                indexBase + 0, indexBase + 1, indexBase + 2,
                indexBase + 2, indexBase + 3, indexBase + 0
        };

        indexBuffer.add(indices);
        indexBase += 4;
        vertexCount += 6;
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        initialize();
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        DebugTools.check();
    }

    public void onDrawFrame(GL10 unused) {
        glClear(GL_COLOR_BUFFER_BIT);
        submit(a);
        submit(b);

        indexBuffer.bind();
        vertexBuffer.bind();
        shader.bind();

        shader.setSamplers(textures.size());
        for (int i = 0; i < textures.size(); i++){
            glActiveTexture((GL_TEXTURE0 + i));
            textures.get(i).bind();
        }

        glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0);
        DebugTools.check();

        vertexCount = 0;
        indexBase = 0;
        textures.clear();
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        glViewport(0, 0, width, height);
        DebugTools.check();
    }
}
