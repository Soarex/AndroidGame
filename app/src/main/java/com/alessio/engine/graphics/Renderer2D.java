package com.alessio.engine.graphics;

import android.util.Log;

import com.alessio.engine.utils.DebugTools;
import com.alessio.engine.math.Float2;
import com.alessio.engine.math.Float3;

import java.util.ArrayList;

import static android.opengl.GLES30.*;

public class Renderer2D {
    private VertexBuffer vertexBuffer;
    private IndexBuffer indexBuffer;
    private ArrayList<Texture> textures;
    private int indexBase, vertexCount;

    public Renderer2D() {
        indexBase = 0;
        vertexCount = 0;
        textures = new ArrayList<>(32);
        vertexBuffer = new VertexBuffer(Vertex.SIZE * 4 * 60000);
        indexBuffer = new IndexBuffer(60000 * 4);
    }

    public void init() {
        vertexBuffer.init();
        indexBuffer.init();
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_BLEND);
        glDepthFunc(GL_LESS);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        DebugTools.check();
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

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        indexBuffer.bind();
        vertexBuffer.bind();

        for (int i = 0; i < textures.size(); i++){
            glActiveTexture((GL_TEXTURE0 + i));
            textures.get(i).bind();
        }

        glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0);
        DebugTools.check();

        vertexCount = 0;
        indexBase = 0;
        vertexBuffer.clear();
        indexBuffer.clear();
        textures.clear();
    }
}
