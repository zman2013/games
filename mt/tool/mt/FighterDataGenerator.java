package mt;
import mt.domain.Commodity;
import mt.domain.FighterInfo;
import mt.formation.SkillInfo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;


public class FighterDataGenerator extends ApplicationAdapter{

	public static void main(
	        String[] args )
	    {
	        new LwjglApplication( new FighterDataGenerator(), "", 100, 100, true );
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
		
		Array<FighterInfo> fighterInfos = new Array<FighterInfo>();
		fighterInfos.add( new FighterInfo( 0, 0, "assets/images/border/5.png", "assets/images/fighter/156.png",
				"assets/images/border/101.png", "assets/images/fighter/data.dat_000156.png",(byte)1, MathUtils.random(1.7f, 4), 
				30, 30, 0.5f, skillInfos, equipments ) );
		fighterInfos.add( new FighterInfo( 1, 1, "assets/images/border/2.png", "assets/images/fighter/157.png",
				"assets/images/border/102.png", "assets/images/fighter/data.dat_000157.png",(byte)1, MathUtils.random(1.7f, 4), 
				345, 30, 0.5f, skillInfos, equipments ) );
		fighterInfos.add( new FighterInfo( 2, 2, "assets/images/border/3.png", "assets/images/fighter/158.png",
				"assets/images/border/104.png", "assets/images/fighter/data.dat_000158.png",(byte)1, MathUtils.random(1.7f, 4), 
				70, 160, 0.5f, skillInfos, equipments ) );
		fighterInfos.add( new FighterInfo( 3, 3, "assets/images/border/4.png", "assets/images/fighter/159.png",
				"assets/images/border/101.png", "assets/images/fighter/data.dat_000159.png",(byte)1, MathUtils.random(1.7f, 4), 
				305, 160, 0.5f, skillInfos, equipments ) );
		fighterInfos.add( new FighterInfo( 4, 4, "assets/images/border/2.png", "assets/images/fighter/161.png",
				"assets/images/border/104.png", "assets/images/fighter/data.dat_000161.png",(byte)1, MathUtils.random(1.7f, 4), 
				190, 210, 0.5f, skillInfos, equipments ) );
		
		
		
		Json json = new Json();
		for( int i = 0; i < 5; i ++ ){
			json.toJson( fighterInfos.get(i), Gdx.files.local("assets/data/fighter/"+i) );
		}
	}
}
