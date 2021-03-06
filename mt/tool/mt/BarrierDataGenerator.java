package mt;
import mt.domain.Commodity;
import mt.domain.FighterInfo;
import mt.fight.BarrierInfo;
import mt.formation.SkillInfo;
import mt.resources.DataAccessor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;


public class BarrierDataGenerator extends ApplicationAdapter{

	public static void main(
	        String[] args )
	    {
	        new LwjglApplication( new BarrierDataGenerator(), "", 100, 100, true );
	    }
	
	public void create() {
		Array<SkillInfo> skillInfos = new Array<SkillInfo>( 8 );
		skillInfos.add( new SkillInfo( 0, "assets/images/skills/icon/1.png") );
		skillInfos.add( new SkillInfo( 1, "assets/images/skills/icon/2.png") );
		skillInfos.add( new SkillInfo( 2, "assets/images/skills/icon/3.png") );
		skillInfos.add( new SkillInfo( 3, "assets/images/skills/icon/4.png") );
		skillInfos.add( new SkillInfo( 4, "assets/images/skills/icon/5.png") );
		skillInfos.add( new SkillInfo( 5, "assets/images/skills/icon/6.png") );
		
		Array<Commodity> equipments = new Array<Commodity>();
		ObjectMap<String, Integer> properties = new ObjectMap<String, Integer>( 5 );
		properties.put( "0", 100 );
		properties.put( "1", 100 );
		for( int i = 0; i < 8; i ++ ){
			Commodity equipment = new Commodity();
			equipment.setCoordinateIndex( i );
			equipment.setName( "猎龙枪"+i );
			equipment.setType( 1 );
			equipment.setProperties( properties );
			equipment.setIconFilePath( "assets/images/commodity/equipment/1.png" );
			equipments.add( equipment );
		}
		
		Array<FighterInfo> enemyInfos = new Array<FighterInfo>();
		enemyInfos.add( new FighterInfo( 301, 0, "assets/images/border/5.png", "assets/images/fighter/301.png", 
				"assets/images/border/101.png", "assets/images/fighter/data.dat_000302.png", (byte)-1, MathUtils.random(1.7f, 4), 135, 500, 1, skillInfos,
				equipments) );
		enemyInfos.add( new FighterInfo( 302, 1, "assets/images/border/2.png", "assets/images/fighter/302.png",
				"assets/images/border/102.png", "assets/images/fighter/data.dat_000303.png", (byte)-1, MathUtils.random(1.7f, 4), 25, 600, 0.5f, skillInfos,
				equipments) );
		enemyInfos.add( new FighterInfo( 303, 2, "assets/images/border/3.png", "assets/images/fighter/303.png",
				"assets/images/border/103.png", "assets/images/fighter/data.dat_000304.png", (byte)-1, MathUtils.random(1.7f, 4), 350, 600, 0.5f, skillInfos,
				equipments) );
		enemyInfos.add( new FighterInfo( 304, 3, "assets/images/border/4.png", "assets/images/fighter/304.png",
				"assets/images/border/104.png", "assets/images/fighter/data.dat_000305.png", (byte)-1, MathUtils.random(1.7f, 4), 32, 420, 0.5f, skillInfos,
				equipments) );
		enemyInfos.add( new FighterInfo( 305, 4, "assets/images/border/2.png", "assets/images/fighter/305.png",
				"assets/images/border/105.png", "assets/images/fighter/data.dat_000306.png", (byte)-1, MathUtils.random(1.7f, 4), 343, 420, 0.5f, skillInfos,
				equipments) );
		enemyInfos.add( new FighterInfo( 306, 5, "assets/images/border/3.png", "assets/images/fighter/306.png",
				"assets/images/border/106.png", "assets/images/fighter/data.dat_000307.png", (byte)-1, MathUtils.random(1.7f, 4), 190, 380, 0.5f, skillInfos,
				equipments) );
		
		
		Array<Integer> fighterIds = new Array<Integer>();
		for( FighterInfo info : enemyInfos ){
			fighterIds.add( info.getId() );
			DataAccessor.flushFighterInfo( info );
		}
		
		BarrierInfo barrierInfo = new BarrierInfo();
		barrierInfo.setId( 0 );
		barrierInfo.setBackgroundFilePath( "assets/images/fight_background/data.dat_000017.jpg" );
		barrierInfo.setFighterIds( fighterIds );
	
		DataAccessor.flushBarrierInfo( barrierInfo );
	}
}
