package mt.fight;

import com.badlogic.gdx.utils.Array;


/**
 * 管理整个战斗过程
 * @author zman
 *
 */
public class FightManager {
	
	private Array<Fighter> heros;
	
	private Array<Fighter> enemies;
	
	public FightManager( Array<Fighter> heros, Array<Fighter> enemies ){
		this.heros = heros;
		this.enemies = enemies;
	}
	
	/**
	 * 更新所有fighter的战斗状态
	 * @param delta  
	 */
	public void updateFighting( float delta ) {
		for( Fighter fighter : heros ){
			fighter.attack( delta );
//			return;
		}
		for( int i = 0; i < enemies.size; i ++ ){
			enemies.get( i ).attack( delta );
		}
	}
	
	public void updateWalking(){
		for( Fighter fighter : heros ){
			fighter.walk();
		}
	}

	public Array<Fighter> getHeros() {
		return heros;
	}

	public void setHeros(Array<Fighter> heros) {
		this.heros = heros;
	}

	public Array<Fighter> getEnemies() {
		return enemies;
	}

	public void setEnemies(Array<Fighter> enemies) {
		this.enemies = enemies;
	}

	
}
