package mt.actors;

import mt.resources.FontLoader;
import mt.resources.ResourcesLoader;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class HomeButtonGroup extends WidgetGroup{
	
	private BitmapFont bitmapFont;
	
	private Image heroButton;
	private Image upgradeButton;
	private Image evolveButton;
	private Image awardButton;
	
	private TextureRegionDrawable defaultButton;
	private TextureRegionDrawable activeButton;
	
	public HomeButtonGroup( ){
		bitmapFont = FontLoader.getBitmapFont();
		
		defaultButton = ResourcesLoader.getHomeButtonDrawable();
		activeButton = ResourcesLoader.getHomeButtonActiveDrawable();
		
		heroButton = new Image( defaultButton );
		heroButton.setY( 225 );
		addListener( heroButton );
		upgradeButton = new Image( defaultButton );
		upgradeButton.setY( 150 );
		addListener( upgradeButton );
		evolveButton = new Image( defaultButton );
		evolveButton.setY( 75 );
		addListener( evolveButton );
		awardButton = new Image( defaultButton );
		addListener( awardButton );
		
		addActor( heroButton );
		addActor( upgradeButton );
		addActor( evolveButton );
		addActor( awardButton );
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		bitmapFont.draw( batch, "Ó¢ÐÛ", getX()+60, getY()+194 );
		bitmapFont.draw( batch, "Éý¼¶", getX()+60, getY()+141 );
		bitmapFont.draw( batch, "½ø»¯", getX()+60, getY()+88 );
		bitmapFont.draw( batch, "½±Àø", getX()+60, getY()+35 );
	}



	private void addListener( final Image actor ) {
		actor.addListener( new ClickListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				actor.setDrawable( activeButton );
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				actor.setDrawable( defaultButton );
			}
			
		});
	}

}
