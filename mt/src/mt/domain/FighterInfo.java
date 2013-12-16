package mt.domain;

import mt.formation.SkillInfo;

import com.badlogic.gdx.utils.Array;


public class FighterInfo implements Cloneable{
	/**
	 * 战宠编号，0表示player
	 */
	private int id;
	/**
	 * 默认为-1：未出征。
	 * 0：上; 1：左中；2：右中；3：左下；4：右下。
	 */
	private int formationIndex;
	//名字
	private String name="哀木涕";
	//等级
	private int level=1;
	//当前经验
	private int currentExperience=12;

	private String borderFilePath, smallBorderFilePath;
	
	private String fighterFilePath, smallFighterFilePath;
	//血量
	private int hp = 1000;
	//法力
	private int magic = 1000;
	//物理攻击
	private int minMeleeAttack=10, maxMeleeAttack=100;
	//法术攻击
	private int minSpellAttack=10, maxSpellAttack=100;
	
	//物防 魔防
	private int melleDefense=100, spellDefense=100;
	//命中 暴击 闪避 运气
	private int hitScore=20, crit=20, doage=20, luck=20;
	
	//攻击间隔，最小为为1.7s，目前攻击一次用时1.5s。
	private float attackInterval = 2;
	
	
	//英雄所属势力：-1：上，1：下
	private byte camp;
	
	//位置: (不可改变)
	private float x, y;
	
	private float scale;
	
	//拥有技能
	private Array<SkillInfo> skillInfos;
	
	//装备
	private Array<Commodity> equipments;
	
	public FighterInfo(){}
	
	public FighterInfo( int id, int formationIndex, String borderFilePath, String fighterFilePath, 
			String smallBorderFilePath, String smallFighterFilePath, byte camp, float attackInterval, 
			float x, float y, float scale, Array<SkillInfo> skillInfos, Array<Commodity> equipments ){
		this.id = id;
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
		this.skillInfos = skillInfos;
		this.equipments = equipments;
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

	public int getMagic() {
		return magic;
	}

	public void setMagic(int magic) {
		this.magic = magic;
	}

	public int getMelleDefense() {
		return melleDefense;
	}

	public void setMelleDefense(int melleDefense) {
		this.melleDefense = melleDefense;
	}

	public int getSpellDefense() {
		return spellDefense;
	}

	public void setSpellDefense(int spellDefense) {
		this.spellDefense = spellDefense;
	}

	public int getHitScore() {
		return hitScore;
	}

	public void setHitScore(int hitScore) {
		this.hitScore = hitScore;
	}

	public int getCrit() {
		return crit;
	}

	public void setCrit(int crit) {
		this.crit = crit;
	}

	public int getDoage() {
		return doage;
	}

	public void setDoage(int doage) {
		this.doage = doage;
	}

	public int getLuck() {
		return luck;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	public Array<SkillInfo> getSkillInfos() {
		return skillInfos;
	}

	public void setSkillInfos(Array<SkillInfo> skillInfos) {
		this.skillInfos = skillInfos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Array<Commodity> getEquipments() {
		return equipments;
	}

	public void setEquipments(Array<Commodity> equipments) {
		this.equipments = equipments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCurrentExperience() {
		return currentExperience;
	}

	public void setCurrentExperience(int currentExperience) {
		this.currentExperience = currentExperience;
	}

	@Override
	public FighterInfo clone(){
		//todo
		try {
			return (FighterInfo) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}


}
