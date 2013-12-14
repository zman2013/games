package mt;
import mt.domain.FighterStatus;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Json;


public class FighterStatusGenerator extends ApplicationAdapter{

	public static void main(
	        String[] args )
	    {
	        new LwjglApplication( new FighterStatusGenerator(), "", 100, 100, true );
	    }

	public void create() {
		
		FighterStatus status = new FighterStatus();
		
		IntMap<Integer> fighters = new IntMap<Integer>(4);
		fighters.put( 0, 0 );
		fighters.put( 1, 1 );
		fighters.put( 2, 2 );
		fighters.put( 3, 3 );
		
		Array<Integer> candidates = new Array<Integer>(10);
//		candidates.add( 0 ); candidates.add( 0 ); candidates.add( 0 );
//		candidates.add( 1 ); candidates.add( 1 ); candidates.add( 1 );
//		candidates.add( 2 ); candidates.add( 2 ); candidates.add( 3 );
//		candidates.add( 4 );
		
		status.setFighters( fighters );
		status.setCandidates( candidates );
		
		Json json = new Json();
		String jsonAsString = json.prettyPrint( status );
		FileHandle fh = Gdx.files.local( "assets/data/player/fighter_status.data" );
		fh.writeString( jsonAsString, false );
	}
}
