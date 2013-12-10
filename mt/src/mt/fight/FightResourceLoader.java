package mt.fight;

import mt.resources.ResourceUtil;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;

public class FightResourceLoader {
	
	private String bottomSlateFilePath = "assets/images/border/data.dat_000493.png";
	
	private Drawable backgroundDrawable;
	
	private TextureRegion bottomSlateRegion;
	
	private ObjectMap<String, TextureRegion> fighterRegionMap = new ObjectMap<>( 20 );
	
	public FightResourceLoader( String backgroundFilePath, ObjectSet<String> fighterFilePaths ){
		AssetManager assetManager = ResourceUtil.getAssetManager();
		assetManager.load( backgroundFilePath, Texture.class );
		assetManager.load( bottomSlateFilePath, Texture.class );
		for( String filePath : fighterFilePaths ){
			assetManager.load( filePath, Texture.class );
		}
		assetManager.finishLoading();
		
		Skin skin = ResourceUtil.getSkin();
		//init background drawable
		Texture texture = assetManager.get( backgroundFilePath );
		skin.add( backgroundFilePath, texture);
		backgroundDrawable = skin.getDrawable( backgroundFilePath  );
		backgroundDrawable.setMinWidth( texture.getWidth() );
		backgroundDrawable.setMinHeight( texture.getHeight() );
		//init bottom slate 
		texture = assetManager.get( this.bottomSlateFilePath );
		skin.add( bottomSlateFilePath, texture );
		bottomSlateRegion = skin.getRegion( bottomSlateFilePath );
		//init fighter drawable
		TextureRegion region;
		for( String filePath : fighterFilePaths ){
			texture = assetManager.get( filePath );
			skin.add( filePath, texture );
			region = skin.getRegion( filePath );
			fighterRegionMap.put( filePath, region );
		}
	}

	public Drawable getBackgroundDrawable() {
		return backgroundDrawable;
	}

	public TextureRegion getBottomSlateRegion() {
		return bottomSlateRegion;
	}
	
	public TextureRegion get( String filePath ){
		return this.fighterRegionMap.get( filePath );
	}

}
