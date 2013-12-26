package mt.fight;

import mt.domain.FighterInfo;
import mt.resources.AbstractResourceLoader;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * 加载所有资源
 * @author zman
 *
 */
public class FightResourceLoader extends AbstractResourceLoader{
	
	private String bottomSlateFilePath = "assets/images/border/data.dat_000493.png";
	private String fightFailureBgFilePath = "assets/images/home_images/bg.jpg";
	private String fightFailureFilePath = "assets/images/fight/failure.png";
	private String fightSuccessFilePath = "assets/images/fight/success.png";
	
	public FightResourceLoader(){ init(); }
	
	@Override
	protected ObjectMap<String, Class<?>> initResourceMap() {
		ObjectMap<String, Class<?>> resourceFilePathMap = new ObjectMap<String, Class<?>>();
		resourceFilePathMap.put( bottomSlateFilePath, Texture.class );
		resourceFilePathMap.put( fightFailureFilePath, Texture.class );
		resourceFilePathMap.put( fightSuccessFilePath, Texture.class );
		resourceFilePathMap.put( fightFailureBgFilePath, Texture.class );
		return resourceFilePathMap;
	}
	
	public void loadResource( String bgFilePath, Array<FighterInfo> enemyInfos, Array<FighterInfo> heroInfos ){
		ObjectMap<String, Class<?>> resourceFilePathMap = new ObjectMap<String, Class<?>>();
		resourceFilePathMap.put( bgFilePath, Texture.class );
		for( FighterInfo fighterInfo : enemyInfos ){
			resourceFilePathMap.put( fighterInfo.getBorderFilePath(), Texture.class );
			resourceFilePathMap.put( fighterInfo.getFighterFilePath(), Texture.class );
		}
		for( FighterInfo fighterInfo : heroInfos ){
			resourceFilePathMap.put( fighterInfo.getBorderFilePath(), Texture.class );
			resourceFilePathMap.put( fighterInfo.getFighterFilePath(), Texture.class );
		}
		
		loadResource( resourceFilePathMap );
	}

	public TextureRegion getBottomSlateRegion() {return getTextureRegion( bottomSlateFilePath );}
	public Drawable getFightFailureDrawable(){return getDrawable( fightFailureFilePath ); }
	public Drawable getFightSuccessDrawable(){return getDrawable( fightSuccessFilePath ); }
	public Drawable getFightFailureBgDrawable(){return getDrawable( fightFailureBgFilePath ); }
}
