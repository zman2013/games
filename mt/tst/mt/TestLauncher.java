package mt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class TestLauncher {

	public static void main(
	        String[] args )
	    {
	    	
	        // create the listener that will receive the application events
	        ApplicationListener listener = new TestRotation();

	        // define the window's title
	        String title = "Test";

	        // define the window's size
	        int width = 512, height = 700;

	        // whether to use OpenGL ES 2.0
	        boolean useOpenGLES2 = true;

	        // create the game
	        new LwjglApplication( listener, title, width, height, useOpenGLES2 );
	    }
}
