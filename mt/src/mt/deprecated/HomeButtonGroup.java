package mt.deprecated;

import mt.MTGame;
import mt.resources.FontLoader;
import mt.resources.ResourcesLoader;
import mt.screens.TeamScreen;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
	
	private MTGame game;
	
	public HomeButtonGroup( MTGame game ){
		this.game = game;
		
		bitmapFont = FontLoader.getBitmapFont();
		
		defaultButton = ResourcesLoader.getHomeButtonDrawable();
		activeButton = ResourcesLoader.getHomeButtonActiveDrawable();
		
		heroButton = new Image( defaultButton );
		heroButton.setY( 225 );
		upgradeButton = new Image( defaultButton );
		upgradeButton.setY( 150 );
		evolveButton = new Image( defaultButton );
		evolveButton.setY( 75 );
		awardButton = new Image( defaultButton );
		
		initListeners();
		
		addActor( heroButton );
		addActor( upgradeButton );
		addActor( evolveButton );
		addActor( awardButton );
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		bitmapFont.draw( batch, "Ó¢ÐÛ", getX()+60, getY()+194 );
		bitmapFont.draw( batch, "Éý¼¶", getX()+60, getY()+141 );
		bitmapFont.draw( batch, "½ø»¯", getX()+60, getY()+88 );
		bitmapFont.draw( batch, "½±Àø", getX()+60, getY()+35 );
	}



	private void initListeners( ) {
		initHeroButtonListener( );
	}

	private void initHeroButtonListener() {
		heroButton.addListener( new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				heroButton.setDrawable( activeButton );
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				heroButton.setDrawable( defaultButton );
				game.setScreen( game.getScreen( TeamScreen.class ));
			}
		});
	}

}
