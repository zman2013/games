package mt.formation;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class PlusActor extends Image{

	private Vector2 offset = new Vector2( 29, 42 );
	
	public PlusActor( Drawable plusDrawable, float x, float y ){
		super( plusDrawable );
		setPosition( x+offset.x, y+offset.y );
		
		this.addAction( Actions.forever( Actions.sequence( Actions.delay(1), Actions.fadeOut(2), Actions.fadeIn(1) ) ) );
		
		initListener();
	}

	private void initListener() {
		//todo
	}
}
