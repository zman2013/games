package mt.screens;

import mt.actors.AbstractImage;
import mt.actors.HeaderBox;
import mt.actors.HomeButtonGroup;
import mt.actors.MenuBox;
import mt.actors.TeamBox;
import mt.resources.ResourcesLoader;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class HomeScreen extends AbstractScreen{

	
	public HomeScreen(Game game) {
		super(game);
	}

	private static float SCALE = 0.8f;
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
		stage.clear();
		
		Image bg = new Image( ResourcesLoader.getBgDrawable() );
		bg.setFillParent( true );
		stage.addActor( bg );
		
		HeaderBox headerBox = new HeaderBox( );
		headerBox.setScale( SCALE );
		headerBox.setY( height - 180*AbstractImage.SCALE );
		
		TeamBox teamBox = new TeamBox( );
		teamBox.setScale( SCALE );
		teamBox.setY( height - (180 + 325)*AbstractImage.SCALE );
		
		HomeButtonGroup homeButtonGroup = new HomeButtonGroup( );
		homeButtonGroup.setScale( 0.7f );
		homeButtonGroup.setPosition(  width/2f-90, 80 );
		
		MenuBox menuBox = new MenuBox( );
		menuBox.setScale( SCALE );

		stage.addActor( headerBox );
		stage.addActor( teamBox );
		stage.addActor( homeButtonGroup );
		stage.addActor( menuBox );
		
	}
	
	
	
}
