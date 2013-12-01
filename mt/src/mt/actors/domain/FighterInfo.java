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
	//位置
	private float x, y;
	//目标 索引
	private int targetIndex;
	//
	private boolean isWalking;
	//
	private final float originX, originY;
	
	public FighterInfo( int borderIndex, int heroIndex, float originX, float originY ){
		this.borderIndex = borderIndex;
		this.heroIndex = heroIndex;
		this.originX = originX;
		this.originY = originY;
		this.x = originX;
		this.y = originY;
	}
	

	public boolean isWalking() {
		return isWalking;
	}

	public void setWalking(boolean isWalking) {
		this.isWalking = isWalking;
	}



	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
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


	public float getOriginX() {
		return originX;
	}


	public float getOriginY() {
		return originY;
	}
	
}
