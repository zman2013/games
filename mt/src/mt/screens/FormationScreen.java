package mt.screens;

import mt.actor.shared.ReturnActor;
import mt.domain.FighterInfo;
import mt.formation.FighterFormationManager;
import mt.formation.FormationResourceLoader;
import mt.formation.HeroActor;
import mt.formation.PlusActor;
import mt.formation.SkillActor;
import mt.formation.SkillFormationManager;
import mt.formation.SkillInfo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

public class FormationScreen extends AbstractScreen{

	private Drawable bgDrawable;
	private TextureRegion headerRegion;
	private TextureRegion fighterPlaceHolderRegion;
	private TextureRegion skillPlaceHolderRegion;
	
	private Drawable returnDrawable;
	private BitmapFont font;
	
	private Array<Vector2> fighterPlaceHolderCoordinates;
	private Array<Vector2> skillPlaceHolderCoordinates;
	
	public FormationScreen(){
		super();
		
		FormationResourceLoader resourceLoader = new FormationResourceLoader();
		bgDrawable = resourceLoader.getBgDrawable();
		headerRegion = resourceLoader.getHeaderRegion();
		fighterPlaceHolderRegion = resourceLoader.getFighterPlaceHolderRegion();
		skillPlaceHolderRegion = resourceLoader.getSkillPlaceHolderRegion();
		font = resourceLoader.getFont();
		//add place holders
		FighterFormationManager fighterFormationManager = new FighterFormationManager();
		fighterFormationManager.setFighterStatus( resourceLoader.getFighterStatus() );
		Drawable plusDrawable = resourceLoader.getPlusDrawable();
		fighterPlaceHolderCoordinates = fighterFormationManager.getCoordinates();
		for( Vector2 coor : fighterPlaceHolderCoordinates ){
			PlusActor actor = new PlusActor( plusDrawable, coor.x, coor.y );
			stage.addActor( actor );
		}
		//add fighters
		Array<FighterInfo> fighterInfos = resourceLoader.getFighterInfos();
		for( FighterInfo info : fighterInfos  ){
			HeroActor fighter = new HeroActor( info, resourceLoader, fighterFormationManager );
			fighterFormationManager.add( info.getFormationIndex(), fighter );
			Vector2 coor = fighterPlaceHolderCoordinates.get( info.getFormationIndex() );
			fighter.setPosition( coor.x, coor.y );
			stage.addActor( fighter );
		}
		//add skills
		SkillFormationManager skillFormationManager = new SkillFormationManager();
		skillFormationManager.setPlayerInfo( resourceLoader.getPlayerInfo() );
		skillPlaceHolderCoordinates = skillFormationManager.getCoordinates();
		Array<SkillInfo> skillInfos = resourceLoader.getSkillInfos();
		for( SkillInfo info : skillInfos ){
			SkillActor skill = new SkillActor( info, resourceLoader, skillFormationManager );
			skillFormationManager.add( info.getFormationIndex(), skill );
			skill.setScale( 0.8f );
			stage.addActor( skill );
		}
		//add return actor
		returnDrawable = resourceLoader.getReturnDrawable();
		ReturnActor returnActor = new ReturnActor( returnDrawable, stage.getWidth(), this, HomeScreen.class,  fighterFormationManager, skillFormationManager );
		stage.addActor( returnActor );
	}

	

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

		batch.begin();
		bgDrawable.draw( batch, 0, 0, stage.getWidth(), stage.getHeight() );
		batch.draw( headerRegion, 0, 650 );
		for( Vector2 coor : fighterPlaceHolderCoordinates ){
			batch.draw( fighterPlaceHolderRegion, coor.x, coor.y, 0, 0, fighterPlaceHolderRegion.getRegionWidth(), fighterPlaceHolderRegion.getRegionHeight(), 0.5f, 0.5f, 0);
		}
		for( Vector2 coor : skillPlaceHolderCoordinates ){
			batch.draw( skillPlaceHolderRegion, coor.x, coor.y, 0, 0, skillPlaceHolderRegion.getRegionWidth(), skillPlaceHolderRegion.getRegionHeight(), 0.8f, 0.8f, 0);
		}
		font.draw( batch, "阵型", 212, 698 );
		batch.end();
		
		stage.act( delta );
		stage.draw();
	}
	
	
}
