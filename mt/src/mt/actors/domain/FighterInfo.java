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
	//��С, no use currently
//	private float width, height;
	//Ŀ�� ����
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
