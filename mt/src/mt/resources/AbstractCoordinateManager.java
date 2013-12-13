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
	 * actor的中心，即：中心相对于左下角的偏移坐标。
	 */
	protected Vector2 origin;
	
	public AbstractCoordinateManager(){ init(); }
	
	/**
	 * init actorMap & origin & cooridates.
	 */
	abstract protected void init();
	
	/**
	 * 在指定索引位置添加actor
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
					//如果目标位置存在战宠，交换两者位置
					if( actor != null ){
						coor = coordinates.get( formationIndex );
						actor.setPosition( coor.x, coor.y );
						actorMap.put( formationIndex, actor );
						actor.setCoordinateIndex( formationIndex );
					}else{
						//目标位置不存在战宠呢，直接移动
						actorMap.remove( formationIndex );
					}
					actorMap.put( i, target );
					target.setCoordinateIndex( i );
					
					return;
				}
			}
		}
		//移动不成功，返回原来位置
		Vector2 coor = coordinates.get( formationIndex );
		target.setPosition( coor.x, coor.y );
	}
	
	//设置fighter为最上层显示。
	public void setFront( CoordinateActor target ){
		//-2因为有returnActor，returnActor需要始终显示在最上层
		target.setZIndex( target.getStage().getActors().size-2 );
	}
	
	public Array<Vector2> getCoordinates() {
		return coordinates;
	}
	
	public Vector2 getCoordinate( int index ){
		return coordinates.get( index );
	}

}
