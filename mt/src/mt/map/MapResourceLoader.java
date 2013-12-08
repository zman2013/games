package mt.map;

import mt.resources.ResourceUtil;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class MapResourceLoader {
	
	private Drawable mapDrawable;
	private Drawable barrierDrawable;
	private Drawable focusDrawable;

	public MapResourceLoader(){
		String mapFilePath = "assets/images/map/global_map.png";
		String barrierFilePath = "assets/images/map/barrier.png";
		String focusFilePath = "assets/images/map/focusIcon.png";
		
		AssetManager assetManager = ResourceUtil.getAssetManager();
		assetManager.load( mapFilePath, Texture.class );
		assetManager.load( barrierFilePath, Texture.class );
		assetManager.load( focusFilePath, Texture.class );
		assetManager.finishLoading();
		
		Texture globalMapTexture = assetManager.get( mapFilePath );
		Texture barrierTexture = assetManager.get( barrierFilePath );
		Texture focusTexture = assetManager.get( focusFilePath );
		
		Skin skin = ResourceUtil.getSkin();
		skin.add( "globalMap", globalMapTexture );
		skin.add( "barrier", barrierTexture );
		skin.add( "focusIcon", focusTexture );
		
		mapDrawable = skin.getDrawable( "globalMap" );
		barrierDrawable = skin.getDrawable( "barrier" );
		barrierDrawable.setMinWidth( barrierTexture.getWidth() );
		barrierDrawable.setMinHeight( barrierTexture.getHeight() );
		focusDrawable = skin.getDrawable( "focusIcon" );
		focusDrawable.setMinWidth( focusTexture.getWidth() );
		focusDrawable.setMinHeight( focusTexture.getHeight() );
	}

	public Drawable getMapDrawable() {
		return mapDrawable;
	}

	public Drawable getBarrierDrawable() {
		return barrierDrawable;
	}

	public Drawable getFocusDrawable() {
		return focusDrawable;
	}
	
}
