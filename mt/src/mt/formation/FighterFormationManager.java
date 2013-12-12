package mt.formation;

import mt.actor.CoordinateActor;
import mt.resources.AbstractCoordinateManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;

public class FighterFormationManager extends AbstractCoordinateManager{

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
	
}
