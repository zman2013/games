package mt.screens;

import mt.actor.shared.ReturnActor;
import mt.map.BarrierActor;
import mt.map.BarrierManager;
import mt.map.MapResourceLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

public class MapScreen extends AbstractScreen{
	//resources
	private Drawable mapDrawable;
	private Drawable focusDrawable;
	private Vector2 focusPosition = new Vector2();
	
	private Camera camera;
	
	private MapResourceLoader resourceLoader;
	
	private BarrierManager barrierManager = new BarrierManager();
	
	public MapScreen(){
		super();
		camera = stage.getCamera();
		
		resourceLoader = new MapResourceLoader();
		mapDrawable = resourceLoader.getMapDrawable();
		focusDrawable = resourceLoader.getFocusDrawable();
		
		
		initListener();
		
		initBarriers();
		
		stage.addActor( new ReturnActor( resourceLoader.getReturnDrawable(), stage.getWidth() ) );
	}
	
	private void initBarriers() {
		Array<Vector2> barrierCoordinateArray = barrierManager.getBarrierCoordinateArray();
		for( int i = 0; i <= barrierManager.getTaskProgress(); i ++ ){
			BarrierActor barrier = new BarrierActor( i, this );
			Vector2 coordinate = barrierCoordinateArray.get( i );
			barrier.setPosition( coordinate.x, coordinate.y );
			stage.addActor( barrier );
		}
	}

	private Vector2 previousDragPoint = new Vector2();
	private Vector2 currectDragPoint = new Vector2();
	private void initListener() {
		//add drag listener for global map
		stage.addListener( new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				previousDragPoint.set( x, y );
				stage.stageToScreenCoordinates( previousDragPoint );
				return true;
			}
			public void touchDragged(InputEvent event, float x, float y,
					int pointer) {
				currectDragPoint.set( x, y );
				stage.stageToScreenCoordinates( currectDragPoint );
				float delta = previousDragPoint.x-currectDragPoint.x;
				
				Vector2 origin = new Vector2( 0, 0 );
				stage.stageToScreenCoordinates( origin );
				Vector3 cameraPosition = camera.position;
				//右移
				if( origin.x < 0 &&  delta < 0 ){
					//边界限定，不可溢出边界
					if( cameraPosition.x + delta < 240 ){
						cameraPosition.x = 240;
					}else{
						camera.position.add( delta, 0, 0 );
					}
				}else{
					origin.x = 800;
					stage.stageToScreenCoordinates( origin );
					//左移
					if( origin.x > 480 && delta > 0 ){
						//边界限定，不可溢出边界
						if( cameraPosition.x + delta > 560 ){
							cameraPosition.x = 560;
						}else{
							camera.position.add( delta, 0, 0 );
						}
					}
				}
				previousDragPoint.set( currectDragPoint );
			}
		});
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

		batch.begin();
		mapDrawable.draw( batch, 0, 0, mapDrawable.getMinWidth(), mapDrawable.getMinHeight() );
		batch.end();
		
		stage.act( delta );
		stage.draw();
		
		batch.begin();
		focusDrawable.draw( batch, focusPosition.x, focusPosition.y, focusDrawable.getMinWidth(), focusDrawable.getMinHeight() );
		batch.end();
	}

	public void setFocusPosition( float x, float y ) {
		this.focusPosition.set( x, y );
	}

	public MapResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	public BarrierManager getBarrierManager() {
		return barrierManager;
	}
	
}
