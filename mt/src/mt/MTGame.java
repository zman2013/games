package mt;

import mt.screens.AbstractScreen;
import mt.screens.HomeScreen;

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
		setScreen( HomeScreen.class );
	}
	
	public void setScreen(  Class<? extends AbstractScreen> screenClass  ) {
		super.setScreen( getScreen( screenClass ) );
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
