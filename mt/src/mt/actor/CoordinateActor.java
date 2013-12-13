package mt.actor;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * ��Ҫ�϶��������ڹ̶������actor��Ҫʵ�ִ˽ӿڣ��Ľӿ���AbstractCoordinateManager���ʹ�á�
 * @author zman
 *
 */
public interface CoordinateActor {
	
	int getCoordinateIndex();
	
	void setCoordinateIndex( int index );
	
	Rectangle getRectangle();
	
	void setPosition( float x, float y );
	
	void setZIndex( int zIndex );
	
	Stage getStage();
	
}
