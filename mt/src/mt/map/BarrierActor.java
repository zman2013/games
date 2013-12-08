package mt.map;

import mt.screens.MapScreen;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class BarrierActor extends Image{
	
	private int barrierIndex;
	
	private MapScreen screen;

	public BarrierActor( int barrierIndex, MapScreen screen ){
		super( screen.getResourceLoader().getBarrierDrawable() );
		
		this.barrierIndex = barrierIndex;
		this.screen = screen;
		
		initListener();
	}

	private void initListener() {
		final BarrierManager barrierManager = screen.getBarrierManager();
		addListener( new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if( barrierIndex != barrierManager.getFocusIndex() ){
					barrierManager.setFocusIndex( barrierIndex );
					Vector2 position = barrierManager.getCoordinate( barrierIndex );
					screen.setFocusPosition( position.x+5, position.y+70 );
				}else{
					//�л����ؿ�ս��ҳ��
					System.out.println( "barrier "+barrierIndex );
				}
			}
		});
	}

	
/*//������
   //��δ�������Ϊ�ؿ�����϶��¼�������ʾ�϶����������ꡣ
	private Vector2 previousDragPoint = new Vector2();
	private Vector2 currectDragPoint = new Vector2();
	private void initListener() {
		addListener( new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				previousDragPoint.set( x, y );
				localToStageCoordinates( previousDragPoint );
				return true;
			}
			public void touchDragged(InputEvent event, float x, float y,
					int pointer) {
				currectDragPoint.set( x, y );
				localToStageCoordinates( currectDragPoint );
				setX( getX() + (currectDragPoint.x-previousDragPoint.x) );
				setY( getY() + (currectDragPoint.y-previousDragPoint.y) );
				previousDragPoint.set( currectDragPoint.x, currectDragPoint.y );
			}
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println( "barrier index:"+barrierIndex+";"+getX()+":"+getY() );
			}
			
			
		});
	}
*/
}
