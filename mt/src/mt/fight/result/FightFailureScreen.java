package mt.fight.result;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import mt.actor.shared.ReturnActor;
import mt.fight.FightResourceLoader;
import mt.screens.AbstractScreen;
import mt.screens.MapScreen;

/**
 * before setted as current screen, need to be setted resourceLoader.
 * @author zman
 *
 */
public class FightFailureScreen extends AbstractScreen{

	private Drawable bgDrawable;
	private Drawable failureDrawable;
	
	private FightResourceLoader resourceLoader;
	
	private ReturnActor returnActor;
	
	public FightFailureScreen(){}

	@Override
	public void show() {
		super.show();
		stage.clear();
		
		bgDrawable = resourceLoader.getFightFailureBgDrawable();
		failureDrawable = resourceLoader.getFightFailureDrawable();
		returnActor = new ReturnActor( resourceLoader.getReturnDrawable(), stage.getWidth(), this, MapScreen.class );
		
		stage.addActor( returnActor );
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

		batch.begin();
		bgDrawable.draw( batch, 0, 0, stage.getWidth(), stage.getHeight() );
		failureDrawable.draw( batch, (stage.getWidth()-failureDrawable.getMinWidth())/2, 500, failureDrawable.getMinWidth(), failureDrawable.getMinHeight() );
		batch.end();
		
		stage.act( delta );
		stage.draw();
		
		
	}

	public void setResourceLoader(FightResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
	
	
	
}
