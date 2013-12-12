package mt.actors.domain;


public class FighterInfo {
	/**
	 * Ĭ��Ϊ-1��δ������
	 * 0����; 1�����У�2�����У�3�����£�4�����¡�
	 */
	private int formationIndex;

	private String borderFilePath, smallBorderFilePath;
	
	private String fighterFilePath, smallFighterFilePath;
	//������
	private int minMeleeAttack=10, maxMeleeAttack=100;
	//��������
	private int minSpellAttack=10, maxSpellAttack=100;
	//�����������СΪΪ1.7s��Ŀǰ����һ����ʱ1.5s��
	private float attackInterval = 2;
	//Ѫ��
	private int hp = 1000;
	
	//Ӣ������������-1���ϣ�1����
	private byte camp;
	//λ��: (���ɸı�)
	private float x, y;
	
	private float scale;
	
	public FighterInfo(){}
	
	public FighterInfo( int formationIndex, String borderFilePath, String fighterFilePath, String smallBorderFilePath, String smallFighterFilePath, byte camp, float attackInterval, float x, float y, float scale ){
		this.formationIndex = formationIndex;
		this.borderFilePath = borderFilePath;
		this.fighterFilePath = fighterFilePath;
		this.smallBorderFilePath = smallBorderFilePath;
		this.smallFighterFilePath = smallFighterFilePath;
		this.camp = camp;
		this.attackInterval = attackInterval;
		this.x = x;
		this.y = y;
		this.scale = scale;
	}
	
	public String getBorderFilePath() {
		return borderFilePath;
	}

	public String getFighterFilePath() {
		return fighterFilePath;
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

	public void bleeding( int value ){
		hp -= value;
	}
	
	public int randomMeleeAttach(){
		return (int) (minMeleeAttack+Math.random()*(maxMeleeAttack-minMeleeAttack));
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getScale() {
		return scale;
	}


	public byte getCamp() {
		return camp;
	}

	public void setBorderFilePath(String borderFilePath) {
		this.borderFilePath = borderFilePath;
	}

	public void setFighterFilePath(String fighterFilePath) {
		this.fighterFilePath = fighterFilePath;
	}

	public void setMinMeleeAttack(int minMeleeAttack) {
		this.minMeleeAttack = minMeleeAttack;
	}

	public void setMaxMeleeAttack(int maxMeleeAttack) {
		this.maxMeleeAttack = maxMeleeAttack;
	}

	public void setMinSpellAttack(int minSpellAttack) {
		this.minSpellAttack = minSpellAttack;
	}

	public void setMaxSpellAttack(int maxSpellAttack) {
		this.maxSpellAttack = maxSpellAttack;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setCamp(byte camp) {
		this.camp = camp;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public float getAttackInterval() {
		return attackInterval;
	}

	public void setAttackInterval(float attackInterval) {
		this.attackInterval = attackInterval;
	}

	public String getSmallBorderFilePath() {
		return smallBorderFilePath;
	}

	public void setSmallBorderFilePath(String smallBorderFilePath) {
		this.smallBorderFilePath = smallBorderFilePath;
	}

	public String getSmallFighterFilePath() {
		return smallFighterFilePath;
	}

	public void setSmallFighterFilePath(String smallFighterFilePath) {
		this.smallFighterFilePath = smallFighterFilePath;
	}

	public int getFormationIndex() {
		return formationIndex;
	}

	public void setFormationIndex(int formationIndex) {
		this.formationIndex = formationIndex;
	}

}
