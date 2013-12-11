package mt.actor.shared;

import mt.screens.AbstractScreen;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class ReturnActor extends Image{

	public ReturnActor( Drawable drawable, float stageWidth, final AbstractScreen screen, final Class<? extends AbstractScreen> screenClass ){
		super( drawable );
		
		setPosition( stageWidth-getWidth(), 0 );
		
		addListener( new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				screen.getGame().setScreen( screenClass );
			}
		});
	}
}
