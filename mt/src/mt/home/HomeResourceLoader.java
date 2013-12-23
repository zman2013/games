package mt.home;

import mt.resources.ResourceUtil;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;


public class HomeResourceLoader{

	private Drawable activeButtonDrawable;
	private Drawable bgDrawable;
	private Drawable buttonDrawable;
	private Drawable returnDrawable;
	
	private BitmapFont font;
	
	public HomeResourceLoader(){
		String bgFilePath = "assets/images/home_images/bg.jpg";
		String buttonFilePath = "assets/images/home_images/home_button.png";
		String activeButtonFilePath = "assets/images/home_images/home_button_active.png";
		String fontFilePath = "assets/font/font.fnt";
		String returnFilePath = "assets/images/shared/return_button.png";
		
		AssetManager assetManager = ResourceUtil.getAssetManager();
		assetManager.load( bgFilePath, Texture.class );
		assetManager.load( buttonFilePath, Texture.class );
		assetManager.load( activeButtonFilePath, Texture.class );
		assetManager.load( fontFilePath, BitmapFont.class );
		assetManager.load( returnFilePath, Texture.class );
		assetManager.finishLoading();
		
		Texture bgTexture = assetManager.get( bgFilePath );
		Texture buttonTexture = assetManager.get( buttonFilePath );
		Texture activeButtonTexture = assetManager.get( activeButtonFilePath );
		font = assetManager.get( fontFilePath, BitmapFont.class );
		Texture returnTexture = assetManager.get( returnFilePath );
		
		Skin skin = ResourceUtil.getSkin();
		skin.add( bgFilePath, bgTexture );
		skin.add( buttonFilePath, buttonTexture );
		skin.add( activeButtonFilePath, activeButtonTexture );
		skin.add( returnFilePath, returnTexture );
		
		bgDrawable = skin.getDrawable( bgFilePath );
		bgDrawable.setMinWidth( bgTexture.getWidth() );
		bgDrawable.setMinHeight( bgTexture.getHeight() );
		buttonDrawable = skin.getDrawable( buttonFilePath );
		buttonDrawable.setMinWidth( buttonTexture.getWidth() );
		buttonDrawable.setMinHeight( buttonTexture.getHeight() );
		activeButtonDrawable = skin.getDrawable( activeButtonFilePath );
		activeButtonDrawable.setMinWidth( activeButtonTexture.getWidth() );
		activeButtonDrawable.setMinHeight( activeButtonTexture.getHeight() );
		returnDrawable = skin.getDrawable( returnFilePath );
		returnDrawable.setMinWidth( returnTexture.getWidth() );
		returnDrawable.setMinHeight( returnTexture.getHeight() );
	}

	public Drawable getButtonDrawable() {
		return buttonDrawable;
	}

	public Drawable getActiveButtonDrawable() {
		return activeButtonDrawable;
	}

	public Drawable getBgDrawable() {
		return bgDrawable;
	}

	public BitmapFont getFont() {
		return font;
	}

	public Drawable getReturnDrawable() {
		return returnDrawable;
	}
	
	
}
