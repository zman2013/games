package mt.formation;

public class SkillInfo {

	/**
	 * Ĭ��Ϊ-1��δװ����
	 * 0������; 1�����ϣ�2�����У�3�����У�4�����£�5�����¡�
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
