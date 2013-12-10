package mt;
import mt.actors.domain.FighterInfo;
import mt.fight.BarrierInfo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.files.FileHandle;
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
		enemyInfos.add( new FighterInfo( "assets/images/border/5.png", "assets/images/fighter/1.png", 135, 500, 1 ) );
		enemyInfos.add( new FighterInfo( "assets/images/border/2.png", "assets/images/fighter/2.png", 25, 600, 0.5f ) );
		enemyInfos.add( new FighterInfo( "assets/images/border/3.png", "assets/images/fighter/2.png", 350, 600, 0.5f ) );
		enemyInfos.add( new FighterInfo( "assets/images/border/4.png", "assets/images/fighter/2.png", 32, 420, 0.5f ) );
		enemyInfos.add( new FighterInfo( "assets/images/border/2.png", "assets/images/fighter/2.png", 343, 420, 0.5f ) );
		enemyInfos.add( new FighterInfo( "assets/images/border/3.png", "assets/images/fighter/1.png", 190, 380, 0.5f ) );
		
		BarrierInfo barrierInfo = new BarrierInfo();
		barrierInfo.setBackgroundFilePath( "assets/images/fight_background/data.dat_000017.jpg" );
		barrierInfo.setEnemyInfos( enemyInfos );
		
		Json json = new Json();
		String jsonAsString = json.prettyPrint( barrierInfo );
		FileHandle fh = Gdx.files.local( "assets/data/barrier/0.data" );
		fh.writeString( jsonAsString, false );
	}
}
