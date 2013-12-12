package mt.deprecated;

import mt.resources.ResourcesLoader;
import mt.screens.AbstractScreen;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class TeamScreen extends AbstractScreen{

	public TeamScreen( ) {
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
		
		TeamListBox teamList = new TeamListBox( stage );
		teamList.setScale( SCALE );
		teamList.setY( 70 );
		
		MenuBox menuBox = new MenuBox( game );
		menuBox.setScale( SCALE );
		
		stage.addActor( headerBox );
		stage.addActor( teamList );
		stage.addActor( menuBox );
	
	}
	
	

}
