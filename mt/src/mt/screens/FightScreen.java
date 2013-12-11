package mt.screens;

import mt.fight.FightManager;
import mt.fight.FightResourceLoader;
import mt.fight.Fighter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

public class FightScreen extends AbstractScreen{
	
	private int barrierIndex;
	
	private FightResourceLoader fightResourceLoader;
	
	private FightManager fightManager;
	
	private Drawable bgDrawable;
	private float bgHeight;
	
	public FightScreen(){
		super();
		//to do, should read from file or somewhere
		this.barrierIndex = 0;
		
		initResources( barrierIndex );
		
		Array<Fighter> heros = fightResourceLoader.getHeros();
		Array<Fighter> enemies = fightResourceLoader.getEnemies();
		fightManager = new FightManager( heros, enemies );
		addFighters( heros, enemies );
		
		initListeners();
	}
	
	private void initListeners() {
		stage.addListener( new InputListener(){
			public boolean keyUp(InputEvent event, int keycode) {
				if( Input.Keys.F == keycode ){
					fighting = true;
					return true;
				}else if( Input.Keys.S == keycode ){
					fighting = false;
					return true;
				}else if( Input.Keys.W == keycode ){
					walking = true;
					return true;
				}
				return false;
			}
		});
	}

	private void addFighters(Array<Fighter> heros, Array<Fighter> enemies) {
		for( Fighter fighter : heros ){
			fighter.setEnemies( enemies );
			fighter.setHeros( heros );
			stage.addActor( fighter );
		}
		for( Fighter fighter : enemies ){
			fighter.setEnemies( heros );
			fighter.setHeros( enemies );
			stage.addActor( fighter );
		}
	}

	private void initResources( int barrierIndex ) {
		fightResourceLoader = new FightResourceLoader( barrierIndex );
		bgDrawable = fightResourceLoader.getBackgroundDrawable();
		bgHeight = bgDrawable.getMinHeight();
		bg2Y = bgHeight;
	}
	
	private boolean fighting = false;
	private boolean walking = false;
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		
		if( fighting ){
			fightManager.updateFighting( delta );
		}
		if( walking ){
			moveBackground();
			fightManager.updateWalking();
		}
		
		batch.begin();
		bgDrawable.draw( batch, 0, bg1Y, stage.getWidth(), bgHeight );
		bgDrawable.draw( batch, 0, bg2Y, stage.getWidth(), bgHeight );
		batch.end();

		stage.act( delta );
		stage.draw();
	}
	
	private int movementCount;
	private float bg1Y;
	private float bg2Y;
	private void moveBackground(){
		bg1Y -= 1;
		bg2Y -= 1;
		if( bg1Y <= -bgHeight ){
			bg1Y = bgHeight;
		}
		if( bg2Y <= -bgHeight ){
			bg2Y = bgHeight;
		}
		movementCount++;
		if( movementCount >= stage.getHeight()/2 ){
			System.out.println( movementCount );
			movementCount = 0;
			walking = false;
		}
	}
	
}
