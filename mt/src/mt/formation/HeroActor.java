package mt.formation;

import mt.actor.CoordinateActor;
import mt.actors.domain.FighterInfo;
import mt.resources.AbstractResourceLoader;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class HeroActor extends Image implements CoordinateActor{
	
	private FighterInfo fighterInfo;
	
	private Drawable borderDrawable;
	private float borderWidth, borderHeight;
	private TextureRegion fighterRegion;
	private float fighterWidth, fighterHeight;
	private float fighterOffsetX, fighterOffsetY;
	
	private FighterFormationManager manager;
	private HeroActor fighter;

	public HeroActor( FighterInfo fighterInfo, AbstractResourceLoader resourceLoader, FighterFormationManager manager ){
		fighter = this;
		this.fighterInfo = fighterInfo;
		this.manager = manager;
		
		initResource( resourceLoader );
		
		setWidth( borderWidth );
		setHeight( borderHeight );
		setScale( 0.5f );
		setDrawable( borderDrawable );
		
		initListener();
	}
	
	private Vector2 startDragPosition = new Vector2();
	private Vector2 draggingPosition = new Vector2();
	private void initListener() {
		addListener( new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if( manager != null ){
					manager.setFront( fighter );
				}
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
				if( manager != null ){
					manager.updateCoordinate( fighter );
				}
			}
		});
	}

	

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		float scaleX = getScaleX();
		float scaleY = getScaleY();
		float x = getX()+fighterOffsetX*scaleX;
		float y = getY()+fighterOffsetY*scaleY;
		
		batch.draw( fighterRegion, x, y, 0, 0, fighterWidth, fighterHeight, scaleX, scaleY, 0 );
	}
	
	private void initResource( AbstractResourceLoader resourceLoader ) {
		borderDrawable = resourceLoader.getDrawable( fighterInfo.getBorderFilePath() );
		borderWidth = borderDrawable.getMinWidth();
		borderHeight = borderDrawable.getMinHeight();
		
		fighterRegion = resourceLoader.getTextureRegion( fighterInfo.getFighterFilePath() );
		fighterWidth = fighterRegion.getRegionWidth();
		fighterHeight = fighterRegion.getRegionHeight();
		//根据战宠的图片大小调整战宠的相对位置
		fighterOffsetX = (borderWidth - fighterWidth)/2;
		fighterOffsetY = (borderHeight - fighterHeight)/2;
	}
	
	private Rectangle rectangle;
	public Rectangle getRectangle(){
		if( rectangle == null ){
			rectangle = new Rectangle();
		}
		rectangle.setPosition( getX(), getY() );
		rectangle.setWidth( borderWidth * getScaleX() );
		rectangle.setHeight( borderHeight * getScaleY() );
		return rectangle;
	}



	public FighterInfo getFighterInfo() {
		return fighterInfo;
	}



	@Override
	public int getCoordinateIndex() {
		return fighterInfo.getFormationIndex();
	}



	@Override
	public void setCoordinateIndex(int index) {
		fighterInfo.setFormationIndex( index );
	}
	
	
}
