package mt.fight;

import mt.domain.FighterInfo;
import mt.domain.FighterStatus;
import mt.resources.DataAccessor;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap.Entry;

public class FightDataAccessor {
	
	private BarrierInfo barrierInfo;

	private Array<FighterInfo> fighterInfos = new Array<FighterInfo>();
	
	private Array<FighterInfo> enemyInfos = new Array<FighterInfo>();
	
	public void load( int barrierId ){
		fighterInfos.clear();
		enemyInfos.clear();
		FighterStatus fighterStatus = DataAccessor.getFighterStatus();
		for( Entry<String,Integer> entry : fighterStatus.getFighters().entries() ){
			fighterInfos.add( DataAccessor.loadFighterData( entry.value ) );
		}
		barrierInfo = DataAccessor.loadBarrierInfo( barrierId );
		for( Integer id : barrierInfo.getFighterIds() ){
			enemyInfos.add( DataAccessor.loadFighterData( id ) );
		}
	}

	public Array<FighterInfo> getFighterInfos() {
		return fighterInfos;
	}

	public Array<FighterInfo> getEnemyInfos() {
		return enemyInfos;
	}

	public BarrierInfo getBarrierInfo() {
		return barrierInfo;
	}
	
	
}
