package mt.formation;

import mt.actor.CoordinateActor;
import mt.resources.AbstractCoordinateManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;

public class SkillFormationManager extends AbstractCoordinateManager{

	public void init() {
		actorMap = new IntMap<CoordinateActor>(6);
		origin = new Vector2( 25, 25 );
		
		coordinates = new Array<Vector2>();
		coordinates.add( new Vector2(180, 440) );
		coordinates.add( new Vector2(248.8f, 440) );
		coordinates.add( new Vector2(160, 380) );
		coordinates.add( new Vector2(268.8f, 380) );
		coordinates.add( new Vector2(140, 320) );
		coordinates.add( new Vector2(288.8f, 320) );
	}
	
}
