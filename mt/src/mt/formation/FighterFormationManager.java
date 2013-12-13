package mt.formation;

import mt.actor.CoordinateActor;
import mt.domain.FighterInfo;
import mt.domain.FighterStatus;
import mt.manager.Manager;
import mt.resources.AbstractCoordinateManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Json;

public class FighterFormationManager extends AbstractCoordinateManager implements Manager{

	private FighterStatus fighterStatus;
	
	public void init() {
		super.actorMap = new IntMap<CoordinateActor>( 5 );
		origin = new Vector2( 29, 42 );
		
		coordinates = new Array<Vector2>();
		coordinates.add( new Vector2(192.5f, 510) );
		coordinates.add( new Vector2(70, 460) );
		coordinates.add( new Vector2(314.5f, 460) );
		coordinates.add( new Vector2(30, 330) );
		coordinates.add( new Vector2(354.5f, 330) );
	}

	@Override
	public void flushData() {
		IntMap<Integer> formation = new IntMap<Integer>();
		for( CoordinateActor actor : actorMap.values() ){
			FighterInfo info = ((HeroActor) actor).getFighterInfo();
			formation.put( info.getFormationIndex(), info.getId() );
		}
		fighterStatus.setFighters( formation );
		new Json().toJson( fighterStatus, Gdx.files.local( FormationResourceLoader.filghterStatusFilePath ) );
	}

	public void setFighterStatus(FighterStatus fighterStatus) {
		this.fighterStatus = fighterStatus;
	}
	
}
