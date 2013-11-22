package mt.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class AbstractScreen extends ScreenAdapter{

	protected Game game;
	
	protected Stage stage;
	
	public AbstractScreen( Game game ){
		this.game = game;
		this.stage = new Stage( 0, 0, true );
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport( width, height );
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor( stage );
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
	
}
