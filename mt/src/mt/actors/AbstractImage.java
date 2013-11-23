package mt.actors;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AbstractImage extends Image{
	
	public static float SCALE = 0.8f;

	public AbstractImage( TextureRegionDrawable drawable ){
		super( drawable );
		setScale( SCALE );
	}
}
