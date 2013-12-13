package mt.fight;

import mt.domain.FighterInfo;

import com.badlogic.gdx.utils.Array;

public class BarrierInfo {

	private String backgroundFilePath;
	
	private Array<FighterInfo> enemyInfos;
	
	public String getBackgroundFilePath() {
		return backgroundFilePath;
	}

	public void setBackgroundFilePath(String backgroundFilePath) {
		this.backgroundFilePath = backgroundFilePath;
	}

	public Array<FighterInfo> getEnemyInfos() {
		return enemyInfos;
	}

	public void setEnemyInfos(Array<FighterInfo> enemyInfos) {
		this.enemyInfos = enemyInfos;
	}
	
}
