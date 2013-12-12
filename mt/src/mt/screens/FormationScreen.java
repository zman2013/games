package mt.screens;

import mt.actors.domain.FighterInfo;
import mt.formation.FormationManager;
import mt.formation.FormationResourceLoader;
import mt.formation.HeroActor;
import mt.formation.PlusActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

public class FormationScreen extends AbstractScreen{

	private Drawable bgDrawable;
	private TextureRegion headerRegion;
	private TextureRegion placeHolderRegion;
	
	private Array<Vector2> placeHolderCoordinates;
	
	public FormationScreen(){
		super();
		
		FormationResourceLoader resourceLoader = new FormationResourceLoader();
		bgDrawable = resourceLoader.getBgDrawable();
		headerRegion = resourceLoader.getHeaderRegion();
		placeHolderRegion = resourceLoader.getPlaceHolderRegion();
		Drawable plusDrawable = resourceLoader.getPlusDrawable();
		
		FormationManager manager = new FormationManager();
		placeHolderCoordinates = manager.getPlaceHolderCoordinates();
		for( Vector2 coor : placeHolderCoordinates ){
			PlusActor actor = new PlusActor( plusDrawable, coor.x, coor.y );
			stage.addActor( actor );
		}
		
		Array<FighterInfo> fighterInfos = resourceLoader.getFighterInfos();
		for( int i = 0; i < fighterInfos.size; i ++ ){
			FighterInfo info = fighterInfos.get( i );
			HeroActor fighter = new HeroActor( info, resourceLoader, manager );
			manager.putFight( i, fighter );
			Vector2 coor = placeHolderCoordinates.get( info.getFormationIndex() );
			fighter.setPosition( coor.x, coor.y );
			stage.addActor( fighter );
		}
	}

	

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

		batch.begin();
		bgDrawable.draw( batch, 0, 0, stage.getWidth(), stage.getHeight() );
		batch.draw( headerRegion, 0, 650 );
		for( Vector2 coor : placeHolderCoordinates ){
			batch.draw( placeHolderRegion, coor.x, coor.y, 0, 0, placeHolderRegion.getRegionWidth(), placeHolderRegion.getRegionHeight(), 0.5f, 0.5f, 0);
		}
		batch.end();
		
		stage.act( delta );
		stage.draw();
	}
	
	
}
