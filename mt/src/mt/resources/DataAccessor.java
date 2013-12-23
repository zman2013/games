package mt.resources;

import mt.domain.Commodity;
import mt.domain.FighterInfo;
import mt.domain.FighterStatus;
import mt.fight.BarrierInfo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

public class DataAccessor {

	private static Json json;
	
	private static String bagFilePath = "assets/data/player/bag.data";
	
	private static String fighterFilePathPrefix = "assets/data/fighter/";
	
	private static String fighterStatusFilePath = "assets/data/player/fighter_status.data";
	
	private static String barrierProgressFilePath = "assets/data/player/barrier_progress.data";
	
	private static String barrierInfoFilePathPrefix = "assets/data/barrier/";
	
	private static Array<Commodity> commodities;
	private static FighterStatus fighterStatus;
	//关卡进度
	private static int barrierProgress;
	
	/**
	 * 背包数据和fighterStatus只在class加载时初始化一次，后面不会再重新load。
	 * 因为后面的flush方法会自动更新其为最新的状态。
	 * 注意：当切换战宠时（property页面），fighterInfo需要重新load。
	 */
	static{
		json = new Json();
		loadBagData();
		loadFighterStatus();
		loadBarrierStatus();
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
	
	public static int getBarrierProgress(){
		return barrierProgress;
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
	
	public static FighterInfo loadFighterData(int id) {
		FileHandle fh = Gdx.files.internal( fighterFilePathPrefix+id );
		return  json.fromJson( FighterInfo.class, fh );
	}

	private static FighterStatus loadFighterStatus() {
		FileHandle fh = Gdx.files.internal( fighterStatusFilePath );
		fighterStatus = json.fromJson( FighterStatus.class, fh );
		return fighterStatus;
	}
	
	private static int loadBarrierStatus() {
		FileHandle fh = Gdx.files.internal( barrierProgressFilePath );
		barrierProgress = json.fromJson( Integer.class, fh );
		return barrierProgress;
	}
	
	public static BarrierInfo loadBarrierInfo( int id ) {
		FileHandle fh = Gdx.files.internal( barrierInfoFilePathPrefix+id );
		return json.fromJson( BarrierInfo.class, fh );
	}

	//flush
	public static void flushFighterStatus(FighterStatus fighterStatus) {
		DataAccessor.fighterStatus = fighterStatus;
		FileHandle fh = Gdx.files.local( fighterStatusFilePath );
		fh.writeString( json.prettyPrint(fighterStatus), false );
	}
	
	public static void flushBagData(Array<Commodity> commodities){
		DataAccessor.commodities = commodities;
		FileHandle fh = Gdx.files.local( bagFilePath );
		fh.writeString( json.prettyPrint(commodities), false );
	}

	public static void flushFighterInfo(FighterInfo fighterInfo) {
		FileHandle fh = Gdx.files.local( fighterFilePathPrefix+fighterInfo.getId() );
		fh.writeString( json.prettyPrint(fighterInfo), false );
	}
	
	public static void flushBarrierProgress( int barrierProgress ) {
		DataAccessor.barrierProgress = barrierProgress;
		FileHandle fh = Gdx.files.local( barrierProgressFilePath );
		fh.writeString( json.prettyPrint(barrierProgress), false );
	}

	public static void flushBarrierInfo(BarrierInfo barrierInfo) {
		FileHandle fh = Gdx.files.local( barrierInfoFilePathPrefix+barrierInfo.getId() );
		fh.writeString( json.prettyPrint(barrierInfo), false );
	}
}
