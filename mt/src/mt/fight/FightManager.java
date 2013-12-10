package mt.fight;

import mt.actors.Fighter;
import mt.actors.domain.FighterInfo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectSet;

public class FightManager {
	
	private Drawable backgroundDrawable;
	
	private TextureRegion bottomSlateRegion;

	private Array<Fighter> heros = new Array<Fighter>( 5 );
	private Array<Fighter> enemies = new Array<Fighter>( 6 );
	
	public FightManager( int barrierIndex ){
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
		FightResourceLoader resourceLoader = new FightResourceLoader( barrierInfo.getBackgroundFilePath(), fighterFilePaths );
		backgroundDrawable = resourceLoader.getBackgroundDrawable();
		bottomSlateRegion = resourceLoader.getBottomSlateRegion();
		//init hero fighters
		TextureRegion borderRegion, fighterRegion;
		for( FighterInfo info : heroInfos ){
			borderRegion = resourceLoader.get( info.getBorderFilePath() );
			fighterRegion = resourceLoader.get( info.getFighterFilePath() );
			Fighter fighter = new Fighter( bottomSlateRegion, borderRegion, fighterRegion, info );
			heros.add( fighter );
		}
		//init enemy fighters
		for( FighterInfo info : barrierInfo.getEnemyInfos() ){
			borderRegion = resourceLoader.get( info.getBorderFilePath() );
			fighterRegion = resourceLoader.get( info.getFighterFilePath() );
			Fighter fighter = new Fighter( bottomSlateRegion, borderRegion, fighterRegion, info );
			enemies.add( fighter );
		}
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
