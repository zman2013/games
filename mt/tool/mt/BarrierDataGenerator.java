package mt;
import mt.actors.domain.FighterInfo;
import mt.fight.BarrierInfo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;


public class BarrierDataGenerator extends ApplicationAdapter{

	public static void main(
	        String[] args )
	    {
	        new LwjglApplication( new BarrierDataGenerator(), "", 100, 100, true );
	    }
	
	public void create() {
		Array<FighterInfo> enemyInfos = new Array<FighterInfo>();
		enemyInfos.add( new FighterInfo( "assets/images/border/5.png", "assets/images/fighter/301.png", 
				"assets/images/border/101.png", "assets/images/fighter/data.dat_000302.png", (byte)-1, MathUtils.random(1.7f, 4), 135, 500, 1 ) );
		enemyInfos.add( new FighterInfo( "assets/images/border/2.png", "assets/images/fighter/302.png",
				"assets/images/border/102.png", "assets/images/fighter/data.dat_000303.png", (byte)-1, MathUtils.random(1.7f, 4), 25, 600, 0.5f ) );
		enemyInfos.add( new FighterInfo( "assets/images/border/3.png", "assets/images/fighter/303.png",
				"assets/images/border/103.png", "assets/images/fighter/data.dat_000304.png", (byte)-1, MathUtils.random(1.7f, 4), 350, 600, 0.5f ) );
		enemyInfos.add( new FighterInfo( "assets/images/border/4.png", "assets/images/fighter/304.png",
				"assets/images/border/104.png", "assets/images/fighter/data.dat_000305.png", (byte)-1, MathUtils.random(1.7f, 4), 32, 420, 0.5f ) );
		enemyInfos.add( new FighterInfo( "assets/images/border/2.png", "assets/images/fighter/305.png",
				"assets/images/border/105.png", "assets/images/fighter/data.dat_000306.png", (byte)-1, MathUtils.random(1.7f, 4), 343, 420, 0.5f ) );
		enemyInfos.add( new FighterInfo( "assets/images/border/3.png", "assets/images/fighter/306.png",
				"assets/images/border/106.png", "assets/images/fighter/data.dat_000307.png", (byte)-1, MathUtils.random(1.7f, 4), 190, 380, 0.5f ) );
		
		BarrierInfo barrierInfo = new BarrierInfo();
		barrierInfo.setBackgroundFilePath( "assets/images/fight_background/data.dat_000017.jpg" );
		barrierInfo.setEnemyInfos( enemyInfos );
		
		Json json = new Json();
		String jsonAsString = json.prettyPrint( barrierInfo );
		FileHandle fh = Gdx.files.local( "assets/data/barrier/0.data" );
		fh.writeString( jsonAsString, false );
	}
}
