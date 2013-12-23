package mt.property;

import mt.actor.CoordinateActor;
import mt.domain.FighterInfo;
import mt.formation.SkillInfo;
import mt.manager.Manager;
import mt.resources.AbstractCoordinateManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Json;

public class PropertySkillManager extends AbstractCoordinateManager implements Manager{
	
	private FighterInfo fighterInfo;
	
	private SkillDetailActor detailActor;
	
	public PropertySkillManager(PropertyDataAccessor dataAccessor) {
		super();
	}

	protected void init() {
		actorMap = new IntMap<CoordinateActor>( 8 );
		origin = new Vector2(32, 32);
		
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

	@Override
	public void flushData() {
		Array<SkillInfo> infos = new Array<SkillInfo>();
		for( CoordinateActor actor : actorMap.values() ){
			infos.add( ((SkillActor)actor).getSkilInfo() );
		}
		fighterInfo.setSkillInfos( infos );
		FileHandle fileHandle = Gdx.files.local( "assets/data/fighter/"+fighterInfo.getId() );
		String jsonString = new Json().prettyPrint( fighterInfo  );
		fileHandle.writeString( jsonString, false );
	}

	public void setFighterInfo(FighterInfo fighterInfo) {
		actorMap.clear();
		this.fighterInfo = fighterInfo;
	}

	public void showDetail(SkillInfo info) {
		detailActor.setZIndex( detailActor.getStage().getActors().size-1 );
		detailActor.setSkillInfo( info );
		detailActor.setVisible( true );
	}

	public void abandon(SkillInfo skillInfo) {
		int index = skillInfo.getFormationIndex();
		((SkillActor)actorMap.get( index )).remove();
		actorMap.remove( index );
		Array<SkillInfo> skillInfos = fighterInfo.getSkillInfos();
		for( int i = 0; i < skillInfos.size; i ++ ){
			if( skillInfos.get(i).getFormationIndex() == index ){
				skillInfos.removeIndex( i );
				return;
			}
		}
	}

	public void setDetailActor(SkillDetailActor skillDetailActor) {
		this.detailActor = skillDetailActor;
	}
	
}
