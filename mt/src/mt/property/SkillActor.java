package mt.property;

import mt.actor.CoordinateActor;
import mt.formation.SkillInfo;
import mt.resources.AbstractResourceLoader;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SkillActor extends Image implements CoordinateActor{

	private SkillInfo info;
	
	private PropertySkillManager manager;
	
	public SkillActor( SkillInfo info, AbstractResourceLoader loader, PropertySkillManager manager ){
		super( loader.getDrawable( info.getIconFilePath() ) );
		this.info = info;
		this.manager = manager;
		
		Vector2 coor = manager.getCoordinate( info.getFormationIndex() );
		setPosition( coor.x, coor.y );
		
		initListener();
	}
	
	private void initListener() {
		addListener( new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				manager.showDetail( info );
			}
		});
	}
	

	//following functions useless for this class
	public int getCoordinateIndex() {return 0;}
	public void setCoordinateIndex(int index) {}
	public Rectangle getRectangle() {return null;}
	//above functions useless for this class

	public SkillInfo getSkilInfo() {
		return info;
	}

}
