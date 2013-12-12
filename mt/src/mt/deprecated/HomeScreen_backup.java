package mt.deprecated;

import mt.resources.ResourcesLoader;
import mt.screens.AbstractScreen;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class HomeScreen_backup extends AbstractScreen{

	
	public HomeScreen_backup() {
		super();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		stage.clear();
		
		Image bg = new Image( ResourcesLoader.getBgDrawable() );
		bg.setFillParent( true );
		stage.addActor( bg );
		
		HeaderBox headerBox = new HeaderBox( );
		headerBox.setScale( SCALE );
		headerBox.setY( height - 180*AbstractScreen.SCALE );
		
		TeamBox teamBox = new TeamBox( );
		teamBox.setScale( SCALE );
		teamBox.setY( height - (180 + 325)*AbstractScreen.SCALE );
		
		HomeButtonGroup homeButtonGroup = new HomeButtonGroup( game );
		homeButtonGroup.setScale( 0.7f );
		homeButtonGroup.setPosition(  width/2f-90, 80 );
		
		MenuBox menuBox = new MenuBox( game  );
		menuBox.setScale( SCALE );

		stage.addActor( headerBox );
		stage.addActor( teamBox );
		stage.addActor( homeButtonGroup );
		stage.addActor( menuBox );
		
	}
	
	
	
}
