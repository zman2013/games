package mt;
import mt.actors.domain.FighterInfo;
import mt.formation.SkillInfo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;


public class HeroDataGenerator extends ApplicationAdapter{

	public static void main(
	        String[] args )
	    {
	        new LwjglApplication( new HeroDataGenerator(), "", 100, 100, true );
	    }

	public void create() {
		Array<SkillInfo> skillInfos = new Array<SkillInfo>( 8 );
		skillInfos.add( new SkillInfo( 0, "assets/images/skills/icon/1.png") );
		skillInfos.add( new SkillInfo( 1, "assets/images/skills/icon/2.png") );
		skillInfos.add( new SkillInfo( 2, "assets/images/skills/icon/3.png") );
		skillInfos.add( new SkillInfo( 3, "assets/images/skills/icon/4.png") );
		skillInfos.add( new SkillInfo( 4, "assets/images/skills/icon/5.png") );
		skillInfos.add( new SkillInfo( 5, "assets/images/skills/icon/6.png") );
		
		Array<FighterInfo> fighterInfos = new Array<FighterInfo>();
		fighterInfos.add( new FighterInfo( 0, "assets/images/border/5.png", "assets/images/fighter/156.png",
				"assets/images/border/101.png", "assets/images/fighter/data.dat_000156.png",(byte)1, MathUtils.random(1.7f, 4), 30, 30, 0.5f, skillInfos ) );
		fighterInfos.add( new FighterInfo( 1, "assets/images/border/2.png", "assets/images/fighter/157.png",
				"assets/images/border/102.png", "assets/images/fighter/data.dat_000157.png",(byte)1, MathUtils.random(1.7f, 4), 345, 30, 0.5f, skillInfos ) );
		fighterInfos.add( new FighterInfo( 2, "assets/images/border/3.png", "assets/images/fighter/158.png",
				"assets/images/border/104.png", "assets/images/fighter/data.dat_000158.png",(byte)1, MathUtils.random(1.7f, 4), 70, 160, 0.5f, skillInfos ) );
		fighterInfos.add( new FighterInfo( 3, "assets/images/border/4.png", "assets/images/fighter/159.png",
				"assets/images/border/101.png", "assets/images/fighter/data.dat_000159.png",(byte)1, MathUtils.random(1.7f, 4), 305, 160, 0.5f, skillInfos ) );
		fighterInfos.add( new FighterInfo( 4, "assets/images/border/2.png", "assets/images/fighter/161.png",
				"assets/images/border/104.png", "assets/images/fighter/data.dat_000161.png",(byte)1, MathUtils.random(1.7f, 4), 190, 210, 0.5f, skillInfos ) );
		
		
		
		Json json = new Json();
		String jsonAsString = json.prettyPrint( fighterInfos );
		FileHandle fh = Gdx.files.local( "assets/data/player/hero.data" );
		fh.writeString( jsonAsString, false );
	}
}
