package mt.resources;

import mt.actor.CoordinateActor;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;

public abstract class AbstractCoordinateManager {

	protected Array<Vector2> coordinates;
	
	protected IntMap<CoordinateActor> actorMap;
	
	/**
	 * actor�����ģ�����������������½ǵ�ƫ�����ꡣ
	 */
	protected Vector2 origin;
	
	public AbstractCoordinateManager(){ init(); }
	
	/**
	 * init actorMap & origin & cooridates.
	 */
	abstract protected void init();
	
	/**
	 * ��ָ������λ�����actor
	 * @param index
	 * @param actor
	 */
	public void add( int index, CoordinateActor actor ){
		actorMap.put( index, actor );
	}
	
	public void updateCoordinate( CoordinateActor target ){
		int formationIndex = target.getCoordinateIndex();
		Rectangle rect = target.getRectangle();
		for( int i = 0; i < coordinates.size; i ++ ){
			if( i != formationIndex ){
				Vector2 coor = coordinates.get(i);
				if( rect.contains( coor.x+origin.x, coor.y+origin.y ) ){
					target.setPosition( coor.x, coor.y );
					
					CoordinateActor actor = actorMap.get( i );
					//���Ŀ��λ�ô���ս�裬��������λ��
					if( actor != null ){
						coor = coordinates.get( formationIndex );
						actor.setPosition( coor.x, coor.y );
						actorMap.put( formationIndex, actor );
						actor.setCoordinateIndex( formationIndex );
					}else{
						//Ŀ��λ�ò�����ս���أ�ֱ���ƶ�
						actorMap.remove( formationIndex );
					}
					actorMap.put( i, target );
					target.setCoordinateIndex( i );
					
					return;
				}
			}
		}
		//�ƶ����ɹ�������ԭ��λ��
		Vector2 coor = coordinates.get( formationIndex );
		target.setPosition( coor.x, coor.y );
	}
	
	//����fighterΪ���ϲ���ʾ��
	public void setFront( CoordinateActor target ){
		//-2��Ϊ��returnActor��returnActor��Ҫʼ����ʾ�����ϲ�
		target.setZIndex( target.getStage().getActors().size-2 );
	}
	
	public Array<Vector2> getCoordinates() {
		return coordinates;
	}
	
	public Vector2 getCoordinate( int index ){
		return coordinates.get( index );
	}

}
