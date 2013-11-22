package mt;

import mt.screens.HomeScreen;

import com.badlogic.gdx.Game;

public class MTGame extends Game{

	@Override
	public void create() {
		setScreen( new HomeScreen(this) );
	}

	
}
