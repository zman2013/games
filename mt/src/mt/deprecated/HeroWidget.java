package mt.deprecated;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import mt.resources.ResourcesLoader;

public class HeroWidget extends BaseDrawable{

	private Drawable bgDrawable;
	
	private Drawable heroDrawable;
	
	private Drawable borderDrawable;
	
	public HeroWidget( int heroIndex, int borderIndex ){
		bgDrawable = ResourcesLoader.getBorderIconDrawable( 0 );
		heroDrawable = ResourcesLoader.getHeroIconDrawable( heroIndex );
		borderDrawable =  ResourcesLoader.getBorderIconDrawable( borderIndex );
	}
	
	@Override
	public void draw( Batch batch, float x, float y, float width,
			float height ) {
		bgDrawable.draw( batch, x, y, width, height );
		heroDrawable.draw( batch, x, y, width, height );
		borderDrawable.draw( batch, x, y, width, height );
	}

	@Override
	public float getMinWidth() {
		return 100;
	}

	@Override
	public float getMinHeight() {
		return 100;
	}
	
	

	
}
