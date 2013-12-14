package mt.actor;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * 需要拖动并依附于固定坐标的actor需要实现此接口，改接口与AbstractCoordinateManager配合使用。
 * @author zman
 *
 */
public interface CoordinateActor{
	
	int getCoordinateIndex();
	
	void setCoordinateIndex( int index );
	
	Rectangle getRectangle();
	
	void setPosition( float x, float y );
	
	void setZIndex( int zIndex );
	
	Stage getStage();
	
	boolean remove();
}
