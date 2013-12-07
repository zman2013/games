package mt.actors.domain;

public class FighterInfo {

	private int borderIndex;
	
	private int heroIndex;
	//物理攻击
	private int minMeleeAttack=10, maxMeleeAttack=100;
	//法术攻击
	private int minSpellAttack=10, maxSpellAttack=100;
	//血量
	private int hp = 1000;
	
	//位置、动作信息
	//大小, no use currently
//	private float width, height;
	//目标 索引
	private int targetIndex;
	//
	private boolean isWalking;
	
	public FighterInfo( int borderIndex, int heroIndex ){
		this.borderIndex = borderIndex;
		this.heroIndex = heroIndex;
	}
	

	public boolean isWalking() {
		return isWalking;
	}

	public void setWalking(boolean isWalking) {
		this.isWalking = isWalking;
	}

	public int getBorderIndex() {
		return borderIndex;
	}

	public int getHeroIndex() {
		return heroIndex;
	}

	public int getMinMeleeAttack() {
		return minMeleeAttack;
	}

	public int getMaxMeleeAttack() {
		return maxMeleeAttack;
	}

	public int getMinSpellAttack() {
		return minSpellAttack;
	}

	public int getMaxSpellAttack() {
		return maxSpellAttack;
	}

	public int getHp() {
		return hp;
	}

	public int getTargetIndex() {
		return targetIndex;
	}

	public void setTargetIndex(int targetIndex) {
		this.targetIndex = targetIndex;
	}
	
	public void bleeding( int value ){
		hp -= value;
	}
	
	public int randomMeleeAttach(){
		return (int) (minMeleeAttack+Math.random()*(maxMeleeAttack-minMeleeAttack));
	}
}
