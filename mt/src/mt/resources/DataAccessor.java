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
	
	private static Array<Commodity> commodities;
	private static FighterStatus fighterStatus;
	
	/**
	 * 背包数据和fighterStatus只在class加载时初始化一次，后面不会再重新load。
	 * 因为后面的flush方法会自动更新其为最新的状态。
	 * 注意：当切换战宠时（property页面），fighterInfo需要重新load。
	 */
	static{
		json = new Json();
		loadBagData();
		loadFighterStatus();
	}
	
	
	public static Array<Commodity> getCommodities() {
		return commodities;
	}

	public static void setCommodities(Array<Commodity> commodities) {
		DataAccessor.commodities = commodities;
	}

	public static FighterStatus getFighterStatus() {
		return fighterStatus;
	}

	public static void setFighterStatus(FighterStatus fighterStatus) {
		DataAccessor.fighterStatus = fighterStatus;
	}

	@SuppressWarnings("unchecked")
	private static Array<Commodity> loadBagData(){
		FileHandle fh = Gdx.files.internal( bagFilePath );
		commodities = json.fromJson( Array.class, Commodity.class, fh);
		return commodities;
	}
	
	public static void flushBagData(Array<Commodity> commodities){
		DataAccessor.commodities = commodities;
		FileHandle fh = Gdx.files.local( bagFilePath );
		json.toJson( commodities, Array.class, Commodity.class, fh );
	}

	public static void flushFighterInfo(FighterInfo fighterInfo) {
		FileHandle fh = Gdx.files.local( fighterFilePathPrefix+fighterInfo.getId() );
		json.toJson( fighterInfo, FighterInfo.class, fh );
	}

	public static FighterInfo loadFighterData(int id) {
		FileHandle fh = Gdx.files.internal( fighterFilePathPrefix+id );
		return  json.fromJson( FighterInfo.class, fh );
	}

	private static FighterStatus loadFighterStatus() {
		FileHandle fh = Gdx.files.internal( fighterStatusFilePath );
		fighterStatus = json.fromJson( FighterStatus.class, fh );
		return fighterStatus;
	}

	public static void flushFighterStatus(FighterStatus fighterStatus) {
		DataAccessor.fighterStatus = fighterStatus;
		FileHandle fh = Gdx.files.local( fighterStatusFilePath );
		json.toJson( fighterStatus, FighterStatus.class, fh );
	}
}
