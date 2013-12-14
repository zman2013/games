package mt.property;

import mt.domain.Commodity;
import mt.domain.FighterInfo;
import mt.domain.FighterStatus;
import mt.resources.DataAccessor;

import com.badlogic.gdx.utils.Array;

public class PropertyDataAccessor {

	/**
	 * 背包中的物品
	 */
	private Array<Commodity> commodities;
	
	/**
	 * 当前显示的fighterInfo
	 */
	private FighterInfo fighterInfo;
	
	/**
	 * 
	 * @return
	 */
	private FighterStatus fighterStatus;
	
	/**
	 * 加载最新的数据
	 */
	public void sync( int fighterId ){
		loadFighterStatus();
		loadCommodities();
		loadFighterInfo(fighterId);
	}

	public FighterStatus getFighterStatus() {
		return fighterStatus;
	}

	public void setFighterStatus(FighterStatus fighterStatus) {
		this.fighterStatus = fighterStatus;
	}

	public Array<Commodity> getCommodities() {
		if( commodities == null ){
			commodities = loadCommodities();
		}
		return commodities;
	}

	public void setCommodities(Array<Commodity> commodities) {
		this.commodities = commodities;
	}

	public FighterInfo getFighterInfo() {
		return fighterInfo;
	}

	public void setFighterInfo(FighterInfo fighterInfo) {
		this.fighterInfo = fighterInfo;
	}
	
	public FighterInfo loadFighterInfo( int id ){
		this.fighterInfo = DataAccessor.loadFighterData(id);
		return fighterInfo;
	}
	
	public Array<Commodity> loadCommodities() {
		this.commodities = DataAccessor.loadBagData();
		return commodities;
	}
	
	public void flushCommodities( Array<Commodity> commodities ){
		this.commodities = commodities;
		DataAccessor.writeBagData(commodities);
	}
	
	public void flushFighterInfo( FighterInfo fighterInfo ){
		this.fighterInfo = fighterInfo;
		DataAccessor.writeFighterData(fighterInfo);
	}
	
	public FighterStatus loadFighterStatus(){
		this.fighterStatus = DataAccessor.loadFighterStatus();
		return fighterStatus;
	}
	
	public void flushFighterStatus( FighterStatus fighterStatus ){
		this.fighterStatus = fighterStatus;
		DataAccessor.flushFighterStatus( fighterStatus );
	}
}
