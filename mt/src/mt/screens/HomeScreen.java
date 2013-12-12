package mt.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import mt.actor.shared.ReturnActor;
import mt.home.ButtonActor;
import mt.home.HomeManager;
import mt.home.HomeResourceLoader;

public class HomeScreen extends AbstractScreen{

	private HomeManager homeManager;
	
	private Drawable bgDrawable;
	
	public HomeScreen(){
		super();
		
		homeManager = new HomeManager();
		
		HomeResourceLoader resourceLoader = new HomeResourceLoader();
		bgDrawable = resourceLoader.getBgDrawable();
		
		initButtons( resourceLoader );
		
		stage.addActor( new ReturnActor( resourceLoader.getReturnDrawable(), stage.getWidth(), this, MapScreen.class ) );
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		
		batch.begin();
		bgDrawable.draw( batch, 0, 0, stage.getWidth(), stage.getHeight() );
		batch.end();
		
		stage.act( delta );
		stage.draw();
	}



	private void initButtons(HomeResourceLoader resourceLoader) {
		Vector2 coordinate;
		//shop button
		Actor actor = new ButtonActor( this, FormationScreen.class, "’Û–Õ", resourceLoader );
		coordinate = homeManager.getShopButtonCoordinate();
		actor.setPosition( coordinate.x, coordinate.y );
		stage.addActor( actor );		
		//formation button
		actor = new ButtonActor( this, null, "…ÃµÍ", resourceLoader );
		coordinate = homeManager.getFormationButtonCoordinate();
		actor.setPosition( coordinate.x, coordinate.y );
		stage.addActor( actor );
		//fighter button
		actor = new ButtonActor( this, FighterListScreen.class, "’Ω≥Ë", resourceLoader );
		coordinate = homeManager.getFighterButtonCoordinate();
		actor.setPosition( coordinate.x, coordinate.y );
		stage.addActor( actor );
		//player button
		actor = new ButtonActor( this, null, "”¢–€", resourceLoader );
		coordinate = homeManager.getPlayerButtonCoordinate();
		actor.setPosition( coordinate.x, coordinate.y );
		stage.addActor( actor );
		
	}
}
