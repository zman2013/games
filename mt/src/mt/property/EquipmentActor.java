package mt.property;

import mt.actor.CoordinateActor;
import mt.domain.Commodity;
import mt.resources.AbstractResourceLoader;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class EquipmentActor extends Image implements CoordinateActor{

	private Commodity commodity;
	
	private PropertyEquipmentManager manager;
	
	private AbstractResourceLoader loader;
	
	public EquipmentActor( Commodity info, AbstractResourceLoader loader, PropertyEquipmentManager manager ){
		super( loader.getDrawable( info.getIconFilePath() ) );
		this.loader = loader;
		this.commodity = info;
		this.manager = manager;
		
		Vector2 coor = manager.getCoordinate( info.getCoordinateIndex() );
		setPosition( coor.x, coor.y );
		
		initListener();
	}
	
	private void initListener() {
		addListener( new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				manager.showDetail( commodity );
			}
		});
	}
	
	public Commodity getCommodity() {
		return commodity;
	}

	//following functions useless for this class
	public int getCoordinateIndex() {return 0;}
	public void setCoordinateIndex(int index) {}
	public Rectangle getRectangle() {return null;}
	//above functions useless for this class

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
		setDrawable( loader.getDrawable( commodity.getIconFilePath() ) );
	}

}
