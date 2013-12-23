package mt;

import mt.domain.Commodity;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;

public class CommodityDataGenerator extends ApplicationAdapter {

	public static void main(
	        String[] args )
	    {
	        new LwjglApplication( new CommodityDataGenerator(), "", 100, 100, true );
	    }
	
	public void create() {
		Array<Commodity> commodities = new Array<Commodity>();
		ObjectMap<String, Integer> properties = new ObjectMap<String, Integer>( 5 );
		properties.put( "0", 100 );
		properties.put( "1", 100 );
		for( int i = 0; i < 20; i ++ ){
			Commodity commodity = new Commodity();
			commodity.setCoordinateIndex( i );
			commodity.setName( "猎龙枪"+i );
			commodity.setType( 0 );
			commodity.setProperties( properties );
			commodity.setIconFilePath( "assets/images/commodity/equipment/1.png" );
			commodities.add( commodity );
		}
		
		Json json = new Json();
		String jsonAsString = json.prettyPrint( commodities );
		FileHandle fh = Gdx.files.local( "assets/data/player/bag.data" );
		fh.writeString( jsonAsString, false );
	}
}
