package mt.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * �����¼���йؿ������ꡢ����Ľ��ȡ�
 * ��������Թؿ�������������ʶ��
 * ���磺�������Ϊ1����ֻ��ʾ��һ���ؿ����������Ϊ2������ʾǰ�����ؿ���
 * @author zman
 *
 */
public class BarrierManager {

	private Array<Vector2> barrierCoordinateArray;
	
	private int taskProgress = 17;
	
	/**
	 * ��ǰ��ѡ�йؿ��������š�
	 */
	private int focusIndex;
	
	public BarrierManager(){
		barrierCoordinateArray = new Array<Vector2>( 17 );
		barrierCoordinateArray.add( new Vector2(496, 141) );
		barrierCoordinateArray.add( new Vector2(410, 206) );
		barrierCoordinateArray.add( new Vector2(233, 186) );
		barrierCoordinateArray.add( new Vector2(  74, 243) );
		barrierCoordinateArray.add( new Vector2(107, 378) );
		barrierCoordinateArray.add( new Vector2(220, 356) );
		barrierCoordinateArray.add( new Vector2(352, 390) );
		barrierCoordinateArray.add( new Vector2(526, 438) );
		barrierCoordinateArray.add( new Vector2(591, 548) );
		barrierCoordinateArray.add( new Vector2(528, 604) );
		barrierCoordinateArray.add( new Vector2(418, 637) );
		barrierCoordinateArray.add( new Vector2(297, 624) );
		barrierCoordinateArray.add( new Vector2(144, 622) );
		barrierCoordinateArray.add( new Vector2(370, 543) );
		barrierCoordinateArray.add( new Vector2(246, 512) );
		barrierCoordinateArray.add( new Vector2(156, 545) );
		barrierCoordinateArray.add( new Vector2(669, 253) );
	}

	public Array<Vector2> getBarrierCoordinateArray() {
		return barrierCoordinateArray;
	}
	
	public Vector2 getCoordinate( int index ){
		return barrierCoordinateArray.get( index );
	}

	public int getTaskProgress() {
		return taskProgress;
	}

	public void setTaskProgress(int taskProgress) {
		this.taskProgress = taskProgress;
	}

	public int getFocusIndex() {
		return focusIndex;
	}

	public void setFocusIndex(int focusIndex) {
		this.focusIndex = focusIndex;
	}
	
}
