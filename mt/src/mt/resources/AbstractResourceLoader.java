package mt.resources;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entry;

/**
 * Abstract Resource loader class, sub-class needs to invoke init() and  implement the initResourceFilePathSet() function.
 * @author zman
 *
 */
public abstract class AbstractResourceLoader {
	
	//shared resource
	private String returnFilePath = "assets/images/shared/return_button.png";

	private Skin skin = ResourceUtil.getSkin();
	
	public void init(){
		
		ObjectMap<String, Class<?>> resourceMap = initResourceMap();
		loadResource( resourceMap );
		
	}

	protected void loadResource(ObjectMap<String, Class<?>> resourceMap) {
		resourceMap.put( returnFilePath, Texture.class );
		
		AssetManager manager = ResourceUtil.getAssetManager();
		for( Entry<String, Class<?>> entry : resourceMap.entries() ){
			manager.load( entry.key, entry.value );
		}
		manager.finishLoading();
		
		for( Entry<String, Class<?>> entry : resourceMap.entries() ){
			Object object = manager.get( entry.key, entry.value );
			skin.add( entry.key, object );
		}
	}

	protected abstract ObjectMap<String, Class<?>> initResourceMap();
	
	public Drawable getDrawable( String key ){
		Texture texture = skin.get( key, Texture.class );
		Drawable drawable = skin.getDrawable( key ); 
		drawable.setMinWidth( texture.getWidth() );
		drawable.setMinHeight( texture.getHeight() );
		return drawable;
		
	}
	public TextureRegion getTextureRegion( String key ){ return skin.getRegion( key ); }

	public Drawable getReturnDrawable(){ return skin.getDrawable( returnFilePath ); }
	
	public BitmapFont getFont( String key ){ return skin.getFont( key ); }
	
	public Texture getTexture( String key ){ return skin.get( key, Texture.class ); }
}
