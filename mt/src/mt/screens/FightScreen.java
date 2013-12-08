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
	
	private Fighter fighter, fighter2, fighter3, fighter4, fighter5;
	private Fighter boss, enemy1, enemy2, enemy3, enemy4, enemy5;
	
	public FightScreen(){
		super();
		bgDrawable1 = ResourcesLoader.getDrawable( "assets/images/fight_background/data.dat_000017.jpg" );
		bgDrawable2 = ResourcesLoader.getDrawable( "assets/images/fight_background/data.dat_000017.jpg" );
	
		fighter = new Fighter( 2, 1, Fighter.HERO, 0.5f, 30, 30 );
		fighter2 = new Fighter( 2, 1, Fighter.HERO, 0.5f, 345, 30 );
		fighter3 = new Fighter( 2, 1, Fighter.HERO, 0.5f, 70, 160 );
		fighter4 = new Fighter( 2, 1, Fighter.HERO, 0.5f, 305, 160 );
		fighter5 = new Fighter( 2, 1, Fighter.HERO, 0.5f, 190, 210 );
		boss = new Fighter( 4, 2, Fighter.MONSTER, 1, 135, 500 );
		enemy1 = new Fighter( 4, 2, Fighter.MONSTER, 0.5f, 25, 600 );
		enemy2 = new Fighter( 4, 2, Fighter.MONSTER, 0.5f, 350, 600 );
		enemy3 = new Fighter( 4, 2, Fighter.MONSTER, 0.5f, 32, 420 );
		enemy4 = new Fighter( 4, 2, Fighter.MONSTER, 0.5f, 343, 420 );
		enemy5 = new Fighter( 4, 2, Fighter.MONSTER, 0.5f, 190, 380 );
		fighter.setEnemy( boss );
		boss.setEnemy( fighter );
		
		stage.clear();
		
		stage.addActor( fighter );
		stage.addActor( fighter2 );
		stage.addActor( fighter3 );
		stage.addActor( fighter4 );
		stage.addActor( fighter5 );
		stage.addActor( boss );
		stage.addActor( enemy1 );
		stage.addActor( enemy2 );
		stage.addActor( enemy3 );
		stage.addActor( enemy4 );
		stage.addActor( enemy5 );
		spriteBatch = stage.getSpriteBatch();
		stage.addListener( new InputListener(){

			@Override
			public boolean keyUp(InputEvent event, int keycode) {
				if( keycode == Input.Keys.LEFT ){
					fighter.attack();
				}else if( keycode == Input.Keys.RIGHT  ){
					boss.attack();
				}else if( keycode == Input.Keys.A ){
					System.out.println( "fighter x:"+fighter.getX() +";y:"+fighter.getY() );
					System.out.println( "fighter2 x:"+fighter2.getX() +";y:"+fighter2.getY() );
					System.out.println( "fighter3 x:"+fighter3.getX() +";y:"+fighter3.getY() );
					System.out.println( "fighter4 x:"+fighter4.getX() +";y:"+fighter4.getY() );
					System.out.println( "fighter5 x:"+fighter5.getX() +";y:"+fighter5.getY() );
				}
				return true;
			}
		});
		
		bg1Y = 0;
		bg2Y = HEIGHT;
	}

	private Batch spriteBatch;
	private static float HEIGHT = 768;
	@Override
	public void resize(int width, int height) {
		super.resize( width, height );
		stage.setViewport( width, height );
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
