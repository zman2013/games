package mt.screens;

import mt.actors.Fighter;
import mt.fight.FightManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

public class FightScreen extends AbstractScreen{
	
	@SuppressWarnings("unused")
	private int barrierIndex;
	
	private FightManager fightManager;
	
	private Drawable bgDrawable;
	private float bgHeight;
	
	public FightScreen(){
		super();
	}
	
	public void init( int barrierIndex ){
		this.barrierIndex = barrierIndex;
		
		initResources( barrierIndex );
		
		addFighters( fightManager.getHeros(), fightManager.getEnemies() );
	}

	private void addFighters(Array<Fighter> heros, Array<Fighter> enemies) {
		for( Fighter fighter : heros ){
			stage.addActor( fighter );
		}
		for( Fighter fighter : enemies ){
			stage.addActor( fighter );
		}
	}

	private void initResources( int barrierIndex ) {
		fightManager = new FightManager( barrierIndex );
		bgDrawable = fightManager.getBackgroundDrawable();
		bgHeight = bgDrawable.getMinHeight();
		bg2Y = bgHeight;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		
		batch.begin();
		bgDrawable.draw( batch, 0, bg1Y, stage.getWidth(), bgHeight );
		bgDrawable.draw( batch, 0, bg2Y, stage.getWidth(), bgHeight );
		batch.end();

		stage.act( delta );
		stage.draw();
	}
	
	private float bg1Y;
	private float bg2Y;
	@SuppressWarnings("unused")
	private void moveBackground(float delta){
		bg1Y -= 1;
		bg2Y -= 1;
		if( bg1Y <= -bgHeight ){
			bg1Y = bgHeight;
		}
		if( bg2Y <= -bgHeight ){
			bg2Y = bgHeight;
		}
	}
	
}
