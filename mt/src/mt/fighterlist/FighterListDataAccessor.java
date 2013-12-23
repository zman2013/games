package mt.fighterlist;

import mt.domain.FighterInfo;
import mt.domain.FighterStatus;
import mt.resources.DataAccessor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

public class FighterListDataAccessor {
	
	private String fighterStatusFilePath = "assets/data/player/fighter_status.data";

	private Json json;
	
	private FighterStatus fighterStatus;
	
	private Array<FighterInfo> fighterInfos;
	
	private Array<FighterInfo> candidateInfos;
	
	public Array<FighterInfo> loadFighterInfos(){
		if( json == null ){
			json = new Json();
		}
		fighterInfos = new Array<FighterInfo>(5);
		//加载出征战宠的信息
		for( Integer id : fighterStatus.getFighters().values() ){
			FighterInfo fighterInfo = json.fromJson( FighterInfo.class, Gdx.files.internal( "assets/data/fighter/"+id ) );
			fighterInfos.add( fighterInfo );
		}
		return fighterInfos;
	}
	
	public Array<FighterInfo> loadCandidateInfos(){
		if( json == null ){json = new Json();}
		candidateInfos = new Array<FighterInfo>(10);
		//加载出征战宠的信息
		for( Integer id : fighterStatus.getCandidates() ){
			FighterInfo fighterInfo = json.fromJson( FighterInfo.class, Gdx.files.internal( "assets/data/fighter/"+id ) );
			candidateInfos.add( fighterInfo );
		}
		return candidateInfos;
	}
	
	
	public FighterStatus loadFighterStatus() {
		if( json == null ){json = new Json();}
		fighterStatus = json.fromJson( FighterStatus.class, Gdx.files.internal( fighterStatusFilePath ) );
		return fighterStatus;
	}

	public FighterStatus getFighterStatus() {
		if( fighterStatus == null ){
			loadFighterStatus();
		}
		return fighterStatus;
	}

	public Array<FighterInfo> getFighterInfos() {
		if( fighterInfos == null ){
			loadFighterInfos();
		}
		return fighterInfos;
	}
	
	public Array<FighterInfo> getCandidateInfos(){
		if( candidateInfos == null ){
			loadCandidateInfos();
		}
		return candidateInfos;
	}

	public void flushFighterStatus(FighterStatus fighterStatus) {
		this.fighterStatus = fighterStatus;
		DataAccessor.flushFighterStatus( fighterStatus );
	}
	
}
