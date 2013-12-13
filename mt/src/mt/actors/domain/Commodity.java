package mt.actors.domain;

import com.badlogic.gdx.utils.IntMap;

public class Commodity{

	private int coordinateIndex;
	/**
	 * 0: ������1: ������2: �ؼף�3: ������
	 * 4: ��ָ��5: ���ȣ�6: �ڴ���7: �ڴ���
	 * 8: �����飻9: ս�走
	 */
	private int type;
	
	private static IntMap<String> typeName;
	
	private String name;
	private String iconFilePath;
	/**
	 * ����
	 * 0: ������1: ������2: ��С�﹥��3: ����﹥��4: ��Сħ����5: ���ħ����
	 * 6: ���У�7: ������8: ���ܣ�9: ���ˣ�������
	 */
	private IntMap<Integer> properties;
	
	private static IntMap<String> propertyName;
	
	public Commodity(){
		typeName = new IntMap<String>( 10 );
		typeName.put( 0, "����" );
		typeName.put( 1, "����" );
		typeName.put( 2, "�ؼ�" );
		typeName.put( 3, "����" );
		typeName.put( 4, "��ָ" );
		typeName.put( 5, "����" );
		typeName.put( 6, "�ڴ�" );
		typeName.put( 7, "�ڴ�" );
		typeName.put( 8, "������" );
		typeName.put( 9, "ս�走" );
		//
		propertyName = new IntMap<String>( 10 );
		propertyName.put( 0, "����" );
		propertyName.put( 1, "����" );
		propertyName.put( 2, "��С�﹥" );
		propertyName.put( 3, "����﹥" );
		propertyName.put( 4, "��Сħ��" );
		propertyName.put( 5, "���ħ��" );
		propertyName.put( 6, "����" );
		propertyName.put( 7, "����" );
		propertyName.put( 8, "����" );
		propertyName.put( 9, "����" );
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

	public IntMap<Integer> getProperties() {
		return properties;
	}

	public void setProperties(IntMap<Integer> properties) {
		this.properties = properties;
	}

	public String getIconFilePath() {
		return iconFilePath;
	}

	public void setIconFilePath(String iconFilePath) {
		this.iconFilePath = iconFilePath;
	}

	public static String getPropertyName( int key ) {
		return propertyName.get( key );
	}
	
	public static String getTypeName( int key ){
		return typeName.get( key );
	}
	
}
