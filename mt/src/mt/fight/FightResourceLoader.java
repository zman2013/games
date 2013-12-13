package mt.fight;

import mt.domain.FighterInfo;
import mt.resources.ResourceUtil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;

/**
 * 加载所有资源
 * @author zman
 *
 */
public class FightResourceLoader {
	
	private String bottomSlateFilePath = "assets/images/border/data.dat_000493.png";
	
	private Drawable backgroundDrawable;
	
	private TextureRegion bottomSlateRegion;
	
	private ObjectMap<String, TextureRegion> fighterRegionMap = new ObjectMap<>( 20 );
	
	private Array<Fighter> heros = new Array<Fighter>( 5 );
	private Array<Fighter> enemies = new Array<Fighter>( 6 );
	
	public FightResourceLoader( int barrierIndex ){
		Json json = new Json();
		String barrierDataFile = "assets/data/barrier/"+barrierIndex+".data";
		BarrierInfo barrierInfo = json.fromJson( BarrierInfo.class, Gdx.files.internal( barrierDataFile ) );
		String heroDataFile = "assets/data/player/hero.data";
		@SuppressWarnings("unchecked")
		Array<FighterInfo> heroInfos = json.fromJson( Array.class, FighterInfo.class, Gdx.files.internal( heroDataFile ) );
		
		ObjectSet<String> fighterFilePaths = new ObjectSet<String>();
		for( FighterInfo fighterInfo : barrierInfo.getEnemyInfos() ){
			fighterFilePaths.add( fighterInfo.getBorderFilePath() );
			fighterFilePaths.add( fighterInfo.getFighterFilePath() );
		}
		for( FighterInfo fighterInfo : heroInfos ){
			fighterFilePaths.add( fighterInfo.getBorderFilePath() );
			fighterFilePaths.add( fighterInfo.getFighterFilePath() );
		}
		//init background & bottom slate resources
		loadResources( barrierInfo.getBackgroundFilePath(), fighterFilePaths );
		backgroundDrawable = getBackgroundDrawable();
		bottomSlateRegion = getBottomSlateRegion();
		//init hero fighters
		TextureRegion borderRegion, fighterRegion;
		for( FighterInfo info : heroInfos ){
			borderRegion = get( info.getBorderFilePath() );
			fighterRegion = get( info.getFighterFilePath() );
			Fighter fighter = new Fighter( bottomSlateRegion, borderRegion, fighterRegion, info );
			heros.add( fighter );
		}
		//init enemy fighters
		for( FighterInfo info : barrierInfo.getEnemyInfos() ){
			borderRegion = get( info.getBorderFilePath() );
			fighterRegion = get( info.getFighterFilePath() );
			Fighter fighter = new Fighter( bottomSlateRegion, borderRegion, fighterRegion, info );
			enemies.add( fighter );
		}
	}
	
	public void loadResources( String backgroundFilePath, ObjectSet<String> fighterFilePaths ){
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

	public TextureRegion get( String filePath ){
		return this.fighterRegionMap.get( filePath );
	}

	public Drawable getBackgroundDrawable() {
		return backgroundDrawable;
	}

	public TextureRegion getBottomSlateRegion() {
		return bottomSlateRegion;
	}

	public Array<Fighter> getHeros() {
		return heros;
	}

	public Array<Fighter> getEnemies() {
		return enemies;
	}
}
