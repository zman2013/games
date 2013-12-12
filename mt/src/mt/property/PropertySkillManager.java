package mt.property;

import mt.actor.CoordinateActor;
import mt.resources.AbstractCoordinateManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;

public class PropertySkillManager extends AbstractCoordinateManager{
	
	protected void init() {
		actorMap = new IntMap<CoordinateActor>( 8 );
		origin = new Vector2(25, 25);
		
		coordinates = new Array<Vector2>();
		coordinates.add( new Vector2(50, 450) );
		coordinates.add( new Vector2(150, 450) );
		coordinates.add( new Vector2(250, 450) );
		coordinates.add( new Vector2(350, 450) );
		coordinates.add( new Vector2(50, 350) );
		coordinates.add( new Vector2(150, 350) );
		coordinates.add( new Vector2(250, 350) );
		coordinates.add( new Vector2(350, 350) );
	}
	
}
