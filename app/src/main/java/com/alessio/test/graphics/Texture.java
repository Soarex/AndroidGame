package com.alessio.test.graphics;

import android.graphics.Bitmap;
import android.opengl.GLUtils;

import com.alessio.test.utils.DebugTools;
import com.alessio.test.utils.ImageReader;

import static android.opengl.GLES30.*;

public class Texture {
    private int textureHandle;

    public Texture(int resourceId) {
        Bitmap bitmap = ImageReader.readImage(resourceId);
        int[] temp = new int[1];
        glGenTextures(1, temp, 0);
        textureHandle = temp[0];

        glBindTexture(GL_TEXTURE_2D, textureHandle);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        GLUtils.texImage2D(GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();
        DebugTools.check();
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, textureHandle);
        DebugTools.check();
    }

    public int getHandle() {
        return textureHandle;
    }
}
