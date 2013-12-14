package mt.resources;

import mt.domain.Commodity;
import mt.domain.FighterInfo;
import mt.domain.FighterStatus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

public class DataAccessor {

	private static Json json;
	
	private static String bagFilePath = "assets/data/player/bag.data";
	
	private static String fighterFilePathPrefix = "assets/data/fighter/";
	
	private static String fighterStatusFilePath = "assets/data/player/fighter_status.data";
	
	@SuppressWarnings("unchecked")
	public static Array<Commodity> loadBagData(){
		if( json == null ){
			json = new Json();
		}
		FileHandle fh = Gdx.files.internal( bagFilePath );
		return json.fromJson( Array.class, Commodity.class, fh);
	}
	
	public static void writeBagData(Array<Commodity> commodities){
		if( json == null ){
			json = new Json();
		}
		FileHandle fh = Gdx.files.local( bagFilePath );
		json.toJson( commodities, Array.class, Commodity.class, fh );
	}

	public static void writeFighterData(FighterInfo fighterInfo) {
		if( json == null ){
			json = new Json();
		}
		FileHandle fh = Gdx.files.local( fighterFilePathPrefix+fighterInfo.getId() );
		json.toJson( fighterInfo, FighterInfo.class, fh );
	}

	public static FighterInfo loadFighterData(int id) {
		if( json == null ){
			json = new Json();
		}
		FileHandle fh = Gdx.files.internal( fighterFilePathPrefix+id );
		return json.fromJson( FighterInfo.class, fh );
	}

	public static FighterStatus loadFighterStatus() {
		if( json == null ){
			json = new Json();
		}
		FileHandle fh = Gdx.files.internal( fighterStatusFilePath );
		return json.fromJson( FighterStatus.class, fh );
	}

	public static void flushFighterStatus(FighterStatus fighterStatus) {
		if( json == null ){
			json = new Json();
		}
		FileHandle fh = Gdx.files.local( fighterStatusFilePath );
		json.toJson( fighterStatus, FighterStatus.class, fh );
	}
}
