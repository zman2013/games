package mt.formation;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SkillActor extends Image{

	private SkillActor skillActor;
	
	private SkillInfo info;
	
	private SkillFormationManager manager;
	
	public SkillActor( SkillInfo info, FormationResourceLoader loader, SkillFormationManager manager ){
		super( loader.getDrawable( info.getIconFilePath() ) );
		this.skillActor = this;
		this.info = info;
		this.manager = manager;
		
		setScale( 0.8f );
		
		initListener();
	}
	
	private Vector2 startDragPosition = new Vector2();
	private Vector2 draggingPosition = new Vector2();
	private void initListener() {
		addListener( new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				manager.setFrontSkill( skillActor );
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
				manager.updateSkillFormation( skillActor );
			}
		});
	}
	public SkillInfo getInfo() {
		return info;
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
	
}
