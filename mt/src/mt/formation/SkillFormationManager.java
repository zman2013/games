package mt.formation;

import mt.actor.CoordinateActor;
import mt.domain.FighterInfo;
import mt.manager.Manager;
import mt.resources.AbstractCoordinateManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;

public class SkillFormationManager extends AbstractCoordinateManager implements Manager{

	private FormationDataAccessor dataAccessor;
	
	public SkillFormationManager(FormationDataAccessor dataAccessor) {
		super();
		
		this.dataAccessor = dataAccessor;
	}

	public void init() {
		actorMap = new IntMap<CoordinateActor>(8);
		origin = new Vector2( 25, 25 );
		
		coordinates = new Array<Vector2>();
		coordinates.add( new Vector2(180, 440) );
		coordinates.add( new Vector2(248.8f, 440) );
		coordinates.add( new Vector2(160, 380) );
		coordinates.add( new Vector2(268.8f, 380) );
		coordinates.add( new Vector2(140, 320) );
		coordinates.add( new Vector2(288.8f, 320) );
		//只显示前6个技能，在阵型页面只能配置前6个技能
		coordinates.add( new Vector2(-140, -320) );
		coordinates.add( new Vector2(-140, -320) );
	}

	@Override
	public void flushData() {
		Array<SkillInfo> infos = new Array<SkillInfo>();
		for( CoordinateActor actor : actorMap.values() ){
			infos.add( ((SkillActor)actor).getInfo() );
		}
		FighterInfo playerInfo = dataAccessor.getPlayerInfo();
		playerInfo.setSkillInfos( infos );
		dataAccessor.flushPlayer( playerInfo );
	}

}
