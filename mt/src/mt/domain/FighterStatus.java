package mt.domain;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;

public class FighterStatus {

	/**
	 * 出征的战宠, 阵型ID:战宠ID
	 */
	private IntMap<Integer> fighters;
	
	/**
	 * 未出征的战宠, 战宠ID
	 */
	private Array<Integer> candidates;
	
	public IntMap<Integer> getFighters() {
		return fighters;
	}

	public void setFighters(IntMap<Integer> fighters) {
		this.fighters = fighters;
	}

	public Array<Integer> getCandidates() {
		return candidates;
	}

	public void setCandidates(Array<Integer> candidates) {
		this.candidates = candidates;
	}

	public Array<Integer> getAll(){
		Array<Integer> all = new Array<Integer>();
		//0：表示palyer
		all.add( 0 );
		for( Integer id : fighters.values() ){
			all.add( id );
		}
		all.addAll( candidates );
		return all;
	}
}
