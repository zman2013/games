package mt.screens;

import mt.actors.AbstractImage;
import mt.actors.HeaderBox;
import mt.actors.HomeButton;
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
		
		HeaderBox headerBox = new HeaderBox( width, height, SCALE );
		headerBox.setY( height - 180*AbstractImage.SCALE );
		
		TeamBox teamBox = new TeamBox( ResourcesLoader.getTeamBoxDrawable() );
		teamBox.setY( height - (180 + 325)*AbstractImage.SCALE );
		
		HomeButton heroButton = new HomeButton( ResourcesLoader.getHomeButtonDrawable(), ResourcesLoader.getHomeButtonActiveDrawable() );
		heroButton.setPosition( width/2 - (410/2)*AbstractImage.SCALE, height - (180 + 325 + 70)*AbstractImage.SCALE );
		HomeButton upgradeButton = new HomeButton( ResourcesLoader.getHomeButtonDrawable(), ResourcesLoader.getHomeButtonActiveDrawable() );
		upgradeButton.setPosition( width/2 - (410/2)*AbstractImage.SCALE, height - (180 + 325 + 140)*AbstractImage.SCALE );
		HomeButton revolutionButton = new HomeButton( ResourcesLoader.getHomeButtonDrawable(), ResourcesLoader.getHomeButtonActiveDrawable() );
		revolutionButton.setPosition( width/2 - (410/2)*AbstractImage.SCALE, height - (180 + 325 + 210)*AbstractImage.SCALE );
		HomeButton awardButton = new HomeButton( ResourcesLoader.getHomeButtonDrawable(), ResourcesLoader.getHomeButtonActiveDrawable() );
		awardButton.setPosition( width/2 - (410/2)*AbstractImage.SCALE, height - (180 + 325 + 280)*AbstractImage.SCALE );
		
		MenuBox menuBox = new MenuBox( ResourcesLoader.getMenuButtonDrawable() );

		stage.addActor( headerBox );
		stage.addActor( teamBox );
		stage.addActor( heroButton );
		stage.addActor( upgradeButton );
		stage.addActor( revolutionButton );
		stage.addActor( awardButton );
		stage.addActor( menuBox );
		
	}
	
	
	
}
