package mt.screens;

import mt.actor.shared.ReturnActor;
import mt.bag.BagManager;
import mt.bag.BagResourceLoader;
import mt.bag.CommodityActor;
import mt.bag.CommodityDetailActor;
import mt.domain.Commodity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

/**
 * 背包
 * @author zman
 *
 */
public class BagScreen extends AbstractScreen{

	private Drawable bgDrawable;
	private Texture cellTexture;
	private BitmapFont font;
	
	private Array<Vector2> coordinates;
	
	private Array<Commodity> commodities;
	
	private BagManager manager;
	
	public BagScreen(){
		super();
		
		manager = new BagManager();
		//get coordinates
		coordinates = manager.getCoordinates();
	}
	
	@Override
	public void show() {
		super.show();
		stage.clear();
		
		//load resource
		BagResourceLoader loader = new BagResourceLoader();
		bgDrawable = loader.getBgDrawable();
		cellTexture = loader.getCellTexture();
		font = loader.getFont();
		Drawable returnDrawable = loader.getReturnDrawable();
		
		//add commodities
		commodities = loader.getCommodities();
		for( Commodity commodity : commodities ){
			CommodityActor actor = new CommodityActor( commodity, loader, manager);
			stage.addActor( actor );
			manager.add( commodity.getCoordinateIndex(), actor );
		}
		
		//init commodity detail actor
		CommodityDetailActor detailActor = new CommodityDetailActor( loader, manager );
		detailActor.setVisible( false );
		manager.setDetailActor( detailActor );
		stage.addActor( detailActor );
		
		//add returnActor
		ReturnActor returnActor = new ReturnActor( returnDrawable, stage.getWidth(), this, HomeScreen.class, manager );
		stage.addActor( returnActor );
		returnActor.setZIndex( 100 );
	}



	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

		batch.begin();
		bgDrawable.draw( batch, 0, 0, stage.getWidth(), stage.getHeight() );
		for( Vector2 coor : coordinates ){
			batch.draw( cellTexture, coor.x, coor.y );
		}
		font.draw( batch, "背包", 225, 700 );
		batch.end();
		
		stage.act( delta );
		stage.draw();
	}
	
	
	
}
