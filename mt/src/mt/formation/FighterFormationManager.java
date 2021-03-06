package mt.formation;

import mt.actor.CoordinateActor;
import mt.domain.FighterInfo;
import mt.domain.FighterStatus;
import mt.manager.Manager;
import mt.resources.AbstractCoordinateManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.ObjectMap;

public class FighterFormationManager extends AbstractCoordinateManager implements Manager{

	private FormationDataAccessor dataAccessor;
	
	public FighterFormationManager( FormationDataAccessor dataAccessor ){
		super();
		this.dataAccessor = dataAccessor;
	}
	
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
		ObjectMap<String, Integer> formation = new ObjectMap<String, Integer>();
		for( CoordinateActor actor : actorMap.values() ){
			FighterInfo info = ((HeroActor) actor).getFighterInfo();
			formation.put( String.valueOf(info.getFormationIndex()), info.getId() );
		}
		FighterStatus fighterStatus = dataAccessor.getFighterStatus();
		fighterStatus.setFighters( formation );
		dataAccessor.flushFighterStatus( fighterStatus );
		dataAccessor.flushFighters();
	}

	@Override
	protected void moveFormation(CoordinateActor target, int formationIndex) {
		FighterInfo info = ((HeroActor) target).getFighterInfo();
		ObjectMap<String,Integer> map = dataAccessor.getFighterStatus().getFighters();
		map.remove( String.valueOf(info.getFormationIndex()) );
		map.put( String.valueOf(formationIndex), info.getId() );
	}

	@Override
	protected void switchFormation(CoordinateActor from, CoordinateActor to) {
		FighterInfo fromInfo = ((HeroActor) from).getFighterInfo();
		FighterInfo toInfo = ((HeroActor) to).getFighterInfo();
		
		ObjectMap<String,Integer> map = dataAccessor.getFighterStatus().getFighters();
		map.put( String.valueOf(from.getCoordinateIndex()),  toInfo.getId() );
		map.put( String.valueOf(to.getCoordinateIndex()), fromInfo.getId() );
	}

	
}
