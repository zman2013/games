package mt.fight;

import com.badlogic.gdx.utils.Array;

public class BarrierInfo {
	
	private int id;

	private String backgroundFilePath;
	
	private Array<Integer> fighterIds;
	
	public String getBackgroundFilePath() {
		return backgroundFilePath;
	}

	public void setBackgroundFilePath(String backgroundFilePath) {
		this.backgroundFilePath = backgroundFilePath;
	}

	public Array<Integer> getFighterIds() {
		return fighterIds;
	}

	public void setFighterIds(Array<Integer> fighterIds) {
		this.fighterIds = fighterIds;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
