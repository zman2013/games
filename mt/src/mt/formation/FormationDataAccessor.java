package mt.formation;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap.Entry;

import mt.domain.FighterInfo;
import mt.domain.FighterStatus;
import mt.resources.DataAccessor;

public class FormationDataAccessor {

	private FighterInfo playerInfo;
	/**
	 * 出征战宠信息
	 */
	private Array<FighterInfo> fighterInfos;
	
	private FighterStatus fighterStatus;
	
	public FighterInfo getPlayerInfo() {
		return playerInfo;
	}
	public Array<FighterInfo> getFighterInfos() {
		return fighterInfos;
	}
	
	public FighterStatus getFighterStatus() {
		return fighterStatus;
	}
	/**
	 * 在加载screen时，load一次数据
	 */
	public void load(){
		if( fighterInfos == null ){
			fighterInfos = new Array<FighterInfo>( 5 );
		}else{
			fighterInfos.clear();
		}
		playerInfo = DataAccessor.loadFighterData(0);
		fighterStatus = DataAccessor.getFighterStatus();
		for( Entry<String, Integer> entry : fighterStatus.getFighters().entries() ){
			fighterInfos.add( DataAccessor.loadFighterData(entry.value) );
		}
	}
	public void flushFighterStatus(FighterStatus fighterStatus) {
		this.fighterStatus = fighterStatus;
		DataAccessor.flushFighterStatus( fighterStatus );
	}
	public void flushPlayer(FighterInfo playerInfo) {
		this.playerInfo = playerInfo;
		DataAccessor.flushFighterInfo( playerInfo );
	}
	public void flushFighters(){
		for( FighterInfo info : fighterInfos ){
			DataAccessor.flushFighterInfo( info );
		}
	}
	
	
}
