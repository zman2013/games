package mt.actors;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class HomeButton extends AbstractImage{
	
	private TextureRegionDrawable defaultDrawable;
	private TextureRegionDrawable activeDrawable;

	public HomeButton( TextureRegionDrawable defaultDrawable, TextureRegionDrawable activeDrawable ){
		super( defaultDrawable );
		
		this.defaultDrawable = defaultDrawable;
		this.activeDrawable = activeDrawable;
		
		initListener();
	}

	private void initListener() {
		addListener( new ClickListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				setDrawable( activeDrawable );
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				setDrawable( defaultDrawable );
			}
			
		});
	}

}
