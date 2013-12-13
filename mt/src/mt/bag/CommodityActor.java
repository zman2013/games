package mt.bag;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import mt.actor.CoordinateActor;
import mt.actors.domain.Commodity;
import mt.resources.AbstractCoordinateManager;
import mt.resources.AbstractResourceLoader;

public class CommodityActor extends Image implements CoordinateActor{

	private CommodityActor commodityActor;
	
	private Commodity commodity;
	
	private BagManager manager;
	
	public CommodityActor( Commodity info, AbstractResourceLoader loader, AbstractCoordinateManager manager ){
		super( loader.getDrawable( info.getIconFilePath() ) );
		this.commodityActor = this;
		this.commodity = info;
		this.manager = (BagManager) manager;
		
		Vector2 coor = manager.getCoordinate( info.getCoordinateIndex() );
		setPosition( coor.x, coor.y );
		
		initListener();
	}
	
	private Vector2 startDragPosition = new Vector2();
	private Vector2 draggingPosition = new Vector2();
	private void initListener() {
		addListener( new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				manager.setFront( commodityActor );
				startDragPosition.set( x, y );
				localToStageCoordinates( startDragPosition );
				return true;
			}
			public void touchDragged(InputEvent event, float x, float y,
					int pointer) {
				draggingPosition.set( x, y );
				localToStageCoordinates( draggingPosition );
				setX( getX() + (draggingPosition.x-startDragPosition.x) );
				setY( getY() + (draggingPosition.y-startDragPosition.y) );
				startDragPosition.set( draggingPosition.x, draggingPosition.y );
			}
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				manager.updateCoordinate( commodityActor );
				manager.showDetail( commodity );
			}
		});
	}
	
	private Rectangle rectangle;
	public Rectangle getRectangle(){
		if( rectangle == null ){
			rectangle = new Rectangle();
		}
		rectangle.setPosition( getX(), getY() );
		rectangle.setWidth( getWidth() * getScaleX() );
		rectangle.setHeight( getHeight() * getScaleY() );
		return rectangle;
	}
	@Override
	public int getCoordinateIndex() {
		return commodity.getCoordinateIndex();
	}
	@Override
	public void setCoordinateIndex(int index) {
		commodity.setCoordinateIndex( index );
	}

}
