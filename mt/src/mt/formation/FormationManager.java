package mt.formation;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;

public class FormationManager {

	private Array<Vector2> placeHolderCoordinates;
	
	private IntMap<HeroActor> fighterMap = new IntMap<HeroActor>( 5 );
	
	public FormationManager() {
		placeHolderCoordinates = new Array<Vector2>();
		placeHolderCoordinates.add( new Vector2(190, 510) );
		placeHolderCoordinates.add( new Vector2(70, 460) );
		placeHolderCoordinates.add( new Vector2(305, 460) );
		placeHolderCoordinates.add( new Vector2(30, 330) );
		placeHolderCoordinates.add( new Vector2(345, 330) );
	}
	
	public void putFight( int index, HeroActor fighter ){
		fighterMap.put( index, fighter );
	}
	
	public void updateFighterFormation( HeroActor fighter ){
		int formationIndex = fighter.getFighterInfo().getFormationIndex();
		Rectangle rect = fighter.getRectangle();
		for( int i = 0; i < placeHolderCoordinates.size; i ++ ){
			if( i != formationIndex ){
				Vector2 coor = placeHolderCoordinates.get(i);
				if( rect.contains( coor.x+29, coor.y+42 ) ){
					fighter.setPosition( coor.x, coor.y );
					
					HeroActor actor = fighterMap.get( i );
					//如果目标位置存在战宠，交换两者位置
					if( actor != null ){
						coor = placeHolderCoordinates.get( formationIndex );
						actor.setPosition( coor.x, coor.y );
						fighterMap.put( formationIndex, actor );
						actor.getFighterInfo().setFormationIndex( formationIndex );
					}else{
						//目标位置不存在战宠呢，直接移动
						fighterMap.remove( formationIndex );
					}
					fighterMap.put( i, fighter );
					fighter.getFighterInfo().setFormationIndex( i );
					
					return;
				}
			}
		}
		//移动不成功，返回原来位置
		Vector2 coor = placeHolderCoordinates.get( formationIndex );
		fighter.setPosition( coor.x, coor.y );
	}
	
	//设置fighter为最上层显示。
	public void setFront( HeroActor fighter ){
		for( HeroActor actor : fighterMap.values() ){
			actor.setZIndex( 10 );
		}
		fighter.setZIndex( 11 );
	}
	
	public Array<Vector2> getPlaceHolderCoordinates() {
		return placeHolderCoordinates;
	}
	
	
}
