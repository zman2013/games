package mt;
import mt.actors.domain.FighterInfo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;


public class CandidateDataGenerator extends ApplicationAdapter{

	public static void main(
	        String[] args )
	    {
	        new LwjglApplication( new CandidateDataGenerator(), "", 100, 100, true );
	    }

	public void create() {
		Array<FighterInfo> fighterInfos = new Array<FighterInfo>();
		fighterInfos.add( new FighterInfo( -1, "assets/images/border/5.png", "assets/images/fighter/156.png",
				"assets/images/border/101.png", "assets/images/fighter/data.dat_000156.png",(byte)1, MathUtils.random(1.7f, 4), 30, 30, 0.5f ) );
		fighterInfos.add( new FighterInfo( -1, "assets/images/border/2.png", "assets/images/fighter/157.png",
				"assets/images/border/102.png", "assets/images/fighter/data.dat_000157.png",(byte)1, MathUtils.random(1.7f, 4), 345, 30, 0.5f ) );
		fighterInfos.add( new FighterInfo( -1, "assets/images/border/3.png", "assets/images/fighter/158.png",
				"assets/images/border/104.png", "assets/images/fighter/data.dat_000158.png",(byte)1, MathUtils.random(1.7f, 4), 70, 160, 0.5f ) );
		fighterInfos.add( new FighterInfo( -1, "assets/images/border/4.png", "assets/images/fighter/159.png",
				"assets/images/border/101.png", "assets/images/fighter/data.dat_000159.png",(byte)1, MathUtils.random(1.7f, 4), 305, 160, 0.5f ) );
		fighterInfos.add( new FighterInfo( -1, "assets/images/border/2.png", "assets/images/fighter/161.png",
				"assets/images/border/104.png", "assets/images/fighter/data.dat_000161.png",(byte)1, MathUtils.random(1.7f, 4), 190, 210, 0.5f ) );
		
		Json json = new Json();
		String jsonAsString = json.prettyPrint( fighterInfos );
		FileHandle fh = Gdx.files.local( "assets/data/player/candidate.data" );
		fh.writeString( jsonAsString, false );
	}
}
