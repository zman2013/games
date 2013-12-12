package mt.formation;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;

public class SkillFormationManager {

	private Array<Vector2> placeHolderCoordinates;
	
	private IntMap<SkillActor> skillActorMap = new IntMap<SkillActor>( 6 );
	
	public SkillFormationManager() {
		placeHolderCoordinates = new Array<Vector2>();
		placeHolderCoordinates.add( new Vector2(180, 440) );
		placeHolderCoordinates.add( new Vector2(248.8f, 440) );
		placeHolderCoordinates.add( new Vector2(160, 380) );
		placeHolderCoordinates.add( new Vector2(268.8f, 380) );
		placeHolderCoordinates.add( new Vector2(140, 320) );
		placeHolderCoordinates.add( new Vector2(288.8f, 320) );
	}
	
	public void putFight( int index, SkillActor skillActor ){
		skillActorMap.put( index, skillActor );
	}
	
	public void updateSkillFormation( SkillActor skillActor ){
		int formationIndex = skillActor.getInfo().getFormationIndex();
		Rectangle rect = skillActor.getRectangle();
		for( int i = 0; i < placeHolderCoordinates.size; i ++ ){
			if( i != formationIndex ){
				Vector2 coor = placeHolderCoordinates.get(i);
				if( rect.contains( coor.x+25, coor.y+25 ) ){
					skillActor.setPosition( coor.x, coor.y );
					
					SkillActor actor = skillActorMap.get( i );
					//如果目标位置存在战宠，交换两者位置
					if( actor != null ){
						coor = placeHolderCoordinates.get( formationIndex );
						actor.setPosition( coor.x, coor.y );
						skillActorMap.put( formationIndex, actor );
						actor.getInfo().setFormationIndex( formationIndex );
					}else{
						//目标位置不存在战宠呢，直接移动
						skillActorMap.remove( formationIndex );
					}
					skillActorMap.put( i, skillActor );
					skillActor.getInfo().setFormationIndex( i );
					
					return;
				}
			}
		}
		//移动不成功，返回原来位置
		Vector2 coor = placeHolderCoordinates.get( formationIndex );
		skillActor.setPosition( coor.x, coor.y );
	}
	
	//设置fighter为最上层显示。
	public void setFrontSkill( SkillActor skillActor ){
		for( SkillActor actor : skillActorMap.values() ){
			actor.setZIndex( 20 );
		}
		skillActor.setZIndex( 21 );
	}
	
	public Array<Vector2> getPlaceHolderCoordinates() {
		return placeHolderCoordinates;
	}

}
