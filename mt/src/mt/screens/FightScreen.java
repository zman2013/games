package mt.screens;

import mt.actors.Fighter;
import mt.resources.ResourcesLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class FightScreen extends AbstractScreen{

	private Drawable bgDrawable1;
	private Drawable bgDrawable2;
	
	private Fighter fighter;
	private Fighter boss;
	
	public FightScreen(){
		super();
		bgDrawable1 = ResourcesLoader.getDrawable( "assets/images/fight_background/data.dat_000017.jpg" );
		bgDrawable2 = ResourcesLoader.getDrawable( "assets/images/fight_background/data.dat_000017.jpg" );
	
		fighter = new Fighter( 2, 1, Fighter.HERO, 0.5f, 215, 100 );
		boss = new Fighter( 4, 2, Fighter.MONSTER, 1, 160, 400 );
		fighter.setEnemy( boss );
		boss.setEnemy( fighter );
		
	}

	private Batch spriteBatch;
	private static float HEIGHT = 768;
	@Override
	public void resize(int width, int height) {
		super.resize( width, height );
		stage.clear();
		
		stage.addActor( fighter );
		stage.addActor( boss );
		spriteBatch = stage.getSpriteBatch();
		
		bg1Y = 0;
		bg2Y = HEIGHT;
		
		stage.addListener( new InputListener(){

			@Override
			public boolean keyUp(InputEvent event, int keycode) {
				if( keycode == Input.Keys.LEFT ){
					fighter.attack();
				}else{
					boss.attack();
				}
				return true;
			}
		});
	}

	private float bg1Y;
	private float bg2Y;
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		
//		moveBackground( delta );
		
		spriteBatch.begin();
		bgDrawable1.draw( spriteBatch, 0, bg1Y, stage.getWidth(), HEIGHT );
		bgDrawable2.draw( spriteBatch, 0, bg2Y, stage.getWidth(), HEIGHT );
		spriteBatch.end();
		
		stage.act( delta );
		stage.draw();
	}
	
	@SuppressWarnings("unused")
	private void moveBackground(float delta){
		bg1Y -= 1;
		bg2Y -= 1;
		if( bg1Y <= -HEIGHT ){
			bg1Y = HEIGHT;
		}
		if( bg2Y <= -HEIGHT ){
			bg2Y = HEIGHT;
		}
	}
	
	
}
