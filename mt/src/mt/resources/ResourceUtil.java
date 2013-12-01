package mt.resources;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * skin works with assetManager.
 */
public class ResourceUtil {

	private static Skin skin;
	
	private static AssetManager assetManager;
	
	public static Skin getSkin(){
		if( skin == null ){
			skin = new Skin();
		}
		return skin;
	}
	
	public static AssetManager getAssetManager(){
		if( assetManager == null ){
			assetManager = new AssetManager();
		}
		return assetManager;
	}
}
