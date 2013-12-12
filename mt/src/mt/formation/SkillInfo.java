package mt.formation;

public class SkillInfo {

	/**
	 * 默认为-1：未装备。
	 * 0：左上; 1：右上；2：左中；3：右中；4：左下；5：右下。
	 */
	private int formationIndex;
	
	private String iconFilePath;
	
	public SkillInfo(){}
	
	public SkillInfo( int formationIndex, String iconFilePath){
		this.formationIndex = formationIndex;
		this.iconFilePath = iconFilePath;
	}

	public int getFormationIndex() {
		return formationIndex;
	}

	public void setFormationIndex(int formationIndex) {
		this.formationIndex = formationIndex;
	}

	public String getIconFilePath() {
		return iconFilePath;
	}

	public void setIconFilePath(String iconFilePath) {
		this.iconFilePath = iconFilePath;
	}
	
	
}
