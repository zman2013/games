package mt;
import mt.actors.domain.FighterInfo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;


public class HeroDataGenerator extends ApplicationAdapter{

	public static void main(
	        String[] args )
	    {
	        new LwjglApplication( new HeroDataGenerator(), "", 100, 100, true );
	    }

	public void create() {
		Array<FighterInfo> fighterInfos = new Array<FighterInfo>();
		fighterInfos.add( new FighterInfo( "assets/images/border/5.png", "assets/images/fighter/2.png", 30, 30, 0.5f ) );
		fighterInfos.add( new FighterInfo( "assets/images/border/2.png", "assets/images/fighter/2.png", 345, 30, 0.5f ) );
		fighterInfos.add( new FighterInfo( "assets/images/border/3.png", "assets/images/fighter/2.png", 70, 160, 0.5f ) );
		fighterInfos.add( new FighterInfo( "assets/images/border/4.png", "assets/images/fighter/2.png", 305, 160, 0.5f ) );
		fighterInfos.add( new FighterInfo( "assets/images/border/2.png", "assets/images/fighter/1.png", 190, 210, 0.5f ) );
		
		Json json = new Json();
		String jsonAsString = json.prettyPrint( fighterInfos );
		FileHandle fh = Gdx.files.local( "assets/data/player/hero.data" );
		fh.writeString( jsonAsString, false );
	}
}
