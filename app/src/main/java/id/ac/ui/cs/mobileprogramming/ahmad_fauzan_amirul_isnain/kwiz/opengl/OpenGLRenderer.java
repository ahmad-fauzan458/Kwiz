package id.ac.ui.cs.mobileprogramming.ahmad_fauzan_amirul_isnain.kwiz.opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLRenderer implements GLSurfaceView.Renderer {
    private float greenValue = 0.3f;
    private float blueValue = 0.3f;
    private static final double FLASH_DURATION_MS = 2000;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0, 0.3f, 0.3f, 1f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClearColor(0, greenValue, blueValue, 1f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        greenValue = (float) ((Math.sin(System.currentTimeMillis()
                * 2 * Math.PI / FLASH_DURATION_MS) * 0.5 ));
        blueValue = (float) ((Math.sin(System.currentTimeMillis()
                * 2 * Math.PI / FLASH_DURATION_MS) * 0.5 ) + 0.1);
    }
}
