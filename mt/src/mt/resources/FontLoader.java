package mt.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * This class will be deprecated. Use ResouceUtil instead.
 * @author zman
 *
 */
public class FontLoader {

	private static BitmapFont bitmapFont;
	
	public static BitmapFont getBitmapFont(){
		if( bitmapFont != null ){
			return bitmapFont;
		}
		bitmapFont = new BitmapFont( Gdx.files.internal( "assets/font/home_screen.fnt" ), Gdx.files.internal( "assets/font/home_screen.png" ), false );
	
		return bitmapFont;
	}
}
