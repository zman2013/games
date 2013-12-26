package mt;

import mt.fight.FightDataAccessor;
import mt.fight.FightResourceLoader;
import mt.fight.result.FightFailureScreen;
import mt.screens.AbstractScreen;
import mt.screens.BagScreen;
import mt.screens.FightScreen;
import mt.screens.FighterListScreen;
import mt.screens.HomeScreen;
import mt.screens.MapScreen;
import mt.screens.PropertyScreen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ObjectMap;

public class MTGame extends Game{

	private ObjectMap<Class<? extends AbstractScreen>, Screen> screenCache = new ObjectMap<Class<? extends AbstractScreen>, Screen>();
	
	@Override
	public void create() {
		Gdx.app.setLogLevel( Application.LOG_DEBUG );
		setScreen( MapScreen.class );
	}
	
	public void setScreen(  Class<? extends AbstractScreen> screenClass  ) {
//		super.setScreen( getScreen( screenClass ) );
		
		FightFailureScreen screen = new FightFailureScreen();
		screen.setGame( this );
		screen.setDataAccessor( new FightDataAccessor() );
		screen.setResourceLoader( new FightResourceLoader() );
		super.setScreen( screen );
	}

	/**
	 * Check if the specific screen is stored in the screenCache,
	 * if true, return it. else create new instance then return it.
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public Screen getScreen( Class<? extends AbstractScreen> screenClass ) {
		if( screenCache.containsKey( screenClass ) ){
			return screenCache.get( screenClass );
		}else{
			AbstractScreen screen;
			try {
				screen = screenClass.newInstance();
			} catch (InstantiationException e) {
				//todo
				e.printStackTrace();
				return null;
			} catch (IllegalAccessException e) {
				//todo
				e.printStackTrace();
				return null;
			}
			screen.setGame( this );
			screenCache.put( screenClass, screen );
			return screen;
		}
	}

	
}
