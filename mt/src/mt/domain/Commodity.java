package mt.domain;

import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.ObjectMap;

public class Commodity{

	private int coordinateIndex;
	/**
	 * 0: 武器；1: 项链；2: 胸甲；3: 腰带；
	 * 4: 戒指；5: 护腿；6: 口袋；7: 口袋；
	 * 8: 技能书；9: 战宠蛋
	 */
	private int type;
	
	private static IntMap<String> typeName;
	
	private String name;
	private String iconFilePath;
	/**
	 * 属性
	 * 0: 生命；1: 法力；2: 最小物攻；3: 最大物攻；4: 最小魔攻；5: 最大魔攻；
	 * 6: 命中；7: 暴击；8: 闪避；9: 幸运；。。。
	 */
	private ObjectMap<String,Integer> properties;
	
	private IntMap<String> propertyName;
	
	public Commodity(){
		typeName = new IntMap<String>( 11 );
		typeName.put( 0, "武器" );
		typeName.put( 1, "项链" );
		typeName.put( 2, "胸甲" );
		typeName.put( 3, "腰带" );
		typeName.put( 4, "戒指" );
		typeName.put( 5, "护腿" );
		typeName.put( 6, "口袋" );
		typeName.put( 7, "口袋" );
		typeName.put( 8, "技能书" );
		typeName.put( 9, "战宠蛋" );
		//
		propertyName = new IntMap<String>( 11 );
		propertyName.put( 0, "生命" );
		propertyName.put( 1, "法力" );
		propertyName.put( 2, "最小物攻" );
		propertyName.put( 3, "最大物攻" );
		propertyName.put( 4, "最小魔攻" );
		propertyName.put( 5, "最大魔攻" );
		propertyName.put( 6, "命中" );
		propertyName.put( 7, "暴击" );
		propertyName.put( 8, "闪避" );
		propertyName.put( 9, "幸运" );
	}

	public int getCoordinateIndex() {
		return coordinateIndex;
	}

	public void setCoordinateIndex(int coordinateIndex) {
		this.coordinateIndex = coordinateIndex;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ObjectMap<String, Integer> getProperties() {
		return properties;
	}

	public void setProperties(ObjectMap<String, Integer> properties) {
		this.properties = properties;
	}

	public String getIconFilePath() {
		return iconFilePath;
	}

	public void setIconFilePath(String iconFilePath) {
		this.iconFilePath = iconFilePath;
	}

	public String getPropertyName( int key ) {
		return propertyName.get( key );
	}
	
	public static String getTypeName( int key ){
		return typeName.get( key );
	}
	
}
