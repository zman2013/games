package mt.actors;

import mt.resources.ResourcesLoader;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuBox extends WidgetGroup{

	private Image bg;
	
	private TextureRegionDrawable homeButtonDrawable = ResourcesLoader.getMenuButtonDrawable( "home_icon" );
	private TextureRegionDrawable homeButtonActiveDrawable = ResourcesLoader.getMenuButtonDrawable( "home_icon_active" );
	private Image homeButton;
	
	private TextureRegionDrawable fubenButtonDrawable = ResourcesLoader.getMenuButtonDrawable( "fuben_icon" );
	private TextureRegionDrawable fubenButtonActiveDrawable = ResourcesLoader.getMenuButtonDrawable( "fuben_icon_active" );
	private Image fubenButton;
	
	private TextureRegionDrawable fightButtonDrawable = ResourcesLoader.getMenuButtonDrawable( "fight_icon" );
	private TextureRegionDrawable fightButtonActiveDrawable = ResourcesLoader.getMenuButtonDrawable( "fight_icon_active" );
	private Image fightButton;
	
	public MenuBox( ){
		bg = new Image( ResourcesLoader.getMenuButtonDrawable() );
		
		homeButton = new Image( homeButtonDrawable );
		homeButton.setPosition( 15, 0 );
		fubenButton = new Image( fubenButtonDrawable );
		fubenButton.setPosition( 118, 0 );
		fightButton = new Image( fightButtonDrawable );
		fightButton.setPosition( 224, 0 );
		
		addActor( bg );
		addActor( homeButton );
		addActor( fubenButton );
		addActor( fightButton );
		
		initListener();
	}
	
	private void initListener(){
		homeButton.addListener( new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				homeButton.setDrawable( homeButtonActiveDrawable );
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				homeButton.setDrawable( homeButtonDrawable );
			}
		});
		
		fubenButton.addListener( new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				fubenButton.setDrawable( fubenButtonActiveDrawable );
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				fubenButton.setDrawable( fubenButtonDrawable );
			}
		});
		
		fightButton.addListener( new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				fightButton.setDrawable( fightButtonActiveDrawable );
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				fightButton.setDrawable( fightButtonDrawable );
			}
		});
		
	}
	
}
