package mt.screens;

import mt.actors.sub.HeroWidget;
import mt.resources.ResourcesLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class FightScreen extends AbstractScreen{

	private Drawable bgDrawable1;
	private Drawable bgDrawable2;
	private Drawable bossGbDrawable;
	
	private Image hero;
	
	public FightScreen(){
		super();
		bgDrawable1 = ResourcesLoader.getDrawable( "assets/images/fight_background/data.dat_000017.jpg" );
		bgDrawable2 = ResourcesLoader.getDrawable( "assets/images/fight_background/data.dat_000017.jpg" );
		bossGbDrawable = ResourcesLoader.getDrawable( "assets/images/fight_background/data.dat_000445.png" );
	
		HeroWidget heroWidget = new HeroWidget( 160, 1 );
		hero = new Image( heroWidget );
		hero.setPosition( 215, 100 );
	}

	private SpriteBatch spriteBatch;
	private static float HEIGHT = 768;
	@Override
	public void resize(int width, int height) {
		super.resize( width, height );
		stage.clear();
		
		stage.addActor( hero );
		spriteBatch = stage.getSpriteBatch();
		
		bg1Y = 0;
		bg2Y = HEIGHT;
	}

	private float bg1Y;
	private float bg2Y;
	private float timeCount = 0;
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		
		moveBackground( delta );
		
		spriteBatch.begin();
		bgDrawable1.draw( spriteBatch, 0, bg1Y, stage.getWidth(), HEIGHT );
		bgDrawable2.draw( spriteBatch, 0, bg2Y, stage.getWidth(), HEIGHT );
		spriteBatch.end();
		
		stage.act( delta );
		stage.draw();
	}
	
	private float heroWalk = 0;
	private void moveBackground(float delta){
		timeCount += delta;
		bg1Y -= 1;
		bg2Y -= 1;
		if( bg1Y <= -HEIGHT ){
			bg1Y = HEIGHT;
		}
		if( bg2Y <= -HEIGHT ){
			bg2Y = HEIGHT;
		}
		
		heroWalk += delta;
		if(  heroWalk < 0.5 ){
			hero.setScale( 1.01f );
			hero.setX( 214 );
			hero.setY( hero.getY() + 2 );
		}else if( heroWalk < 1.5 ){
			hero.setScale( 1 );
			hero.setX( 215 );
			hero.setY( hero.getY() - 1 );
		}else{
			hero.setPosition( 215, 100 );
			heroWalk = 0;
		}
	}
	
	
}
