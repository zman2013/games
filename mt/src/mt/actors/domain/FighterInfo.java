package mt.actors.domain;

public class FighterInfo {

	private int borderIndex;
	
	private int heroIndex;
	//������
	private int minMeleeAttack=10, maxMeleeAttack=100;
	//��������
	private int minSpellAttack=10, maxSpellAttack=100;
	//Ѫ��
	private int hp = 1000;
	
	//λ�á�������Ϣ
	//��С
	private float width, height;
	//λ��
	private float x, y;
	//Ŀ�� ����
	private int targetIndex;
	//
	private boolean isWalking;
	
	public FighterInfo( int borderIndex, int heroIndex, float width, float height ){
		this.borderIndex = borderIndex;
		this.heroIndex = heroIndex;
		this.width = width;
		this.height = height;
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

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public int getTargetIndex() {
		return targetIndex;
	}

	public void setTargetIndex(int targetIndex) {
		this.targetIndex = targetIndex;
	}
	
}
