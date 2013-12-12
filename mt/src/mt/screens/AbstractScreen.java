package mt.screens;

import mt.MTGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class AbstractScreen extends ScreenAdapter{

	protected MTGame game;
	
	protected Stage stage;
	
	protected Batch batch = new SpriteBatch();
	
	protected static float SCALE = 0.8f;
	
	public AbstractScreen( ){
		this.stage = new Stage();
	}
	
	public void init(){}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		stage.setViewport( width, height );
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

		stage.act( delta );
		stage.draw();
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor( stage );
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	public MTGame getGame() {
		return game;
	}

	public void setGame(MTGame game) {
		this.game = game;
	}
	
}
