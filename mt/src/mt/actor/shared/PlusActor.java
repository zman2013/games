package mt.actor.shared;

import mt.listener.PlusActorClickListener;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class PlusActor extends Image{

	private PlusActorClickListener clickListener;
	private int index;
	private Vector2 originPosition = new Vector2();
	
	public PlusActor( Drawable plusDrawable, float x, float y ){
		super( plusDrawable );
		originPosition.set( x, y );
		
		this.addAction( Actions.forever( Actions.sequence( Actions.delay(1), Actions.fadeOut(2), Actions.fadeIn(1) ) ) );
		
		initListener();
	}

	private void initListener() {
		addListener( new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				if( clickListener != null ){
					clickListener.clickedPlusActorButton( commodityType, index );
				}
			}
		});
	}
	
	private int commodityType;
	public void setClickListener( PlusActorClickListener listener, int commodityType, int index, Vector2 offset ){
		this.commodityType = commodityType;
		this.clickListener = listener;
		this.index = index;
		setPosition( originPosition.x+offset.x, originPosition.y+offset.y );
	}
}
