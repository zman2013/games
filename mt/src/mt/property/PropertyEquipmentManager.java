package mt.property;

import mt.actor.CoordinateActor;
import mt.domain.Commodity;
import mt.domain.FighterInfo;
import mt.resources.AbstractCoordinateManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Json;

public class PropertyEquipmentManager extends AbstractCoordinateManager{
	
	private FighterInfo fighterInfo;
	
	private EquipmentDetailActor detailActor;
	
	protected void init() {
		actorMap = new IntMap<CoordinateActor>( 8 );
		origin = new Vector2(32, 32);
		
		coordinates = new Array<Vector2>();
		coordinates.add( new Vector2(50, 200) );
		coordinates.add( new Vector2(150, 200) );
		coordinates.add( new Vector2(250, 200) );
		coordinates.add( new Vector2(350, 200) );
		coordinates.add( new Vector2(50, 100) );
		coordinates.add( new Vector2(150, 100) );
		coordinates.add( new Vector2(250, 100) );
		coordinates.add( new Vector2(350, 100) );
	}

	public void showDetail(Commodity commodity) {
		//-2因为有returnActor，returnActor需要始终显示在最上层
		detailActor.setCommodity( commodity );
		detailActor.setZIndex( detailActor.getStage().getActors().size-2 );
		detailActor.setVisible( true );
	}

	/**
	 * 卸下装备
	 * @param commodity
	 */
	public void disboard(Commodity commodity) {
		int commodityCoordinateIndex = commodity.getCoordinateIndex();
		//将卸下的物品放入背包
		Json json = new Json();
		FileHandle bagFileHandle = Gdx.files.local("assets/data/player/bag.data");
		@SuppressWarnings("unchecked")
		Array<Commodity> commodities = json.fromJson( Array.class, Commodity.class, bagFileHandle );
		if( commodities.size < 48 ){
			boolean[] cells =  new boolean[48];
			//标识已被占用的格子为true
			for( int i = 0; i < commodities.size; i ++ ){
				cells[commodities.get(i).getCoordinateIndex()] = true;
			}
			//找到空闲格子
			for( int i = 0; i < 48; i ++ ){
				if( cells[i] == false ){
					commodity.setCoordinateIndex( i );
					commodities.add( commodity );
					json.toJson( commodities, Array.class, Commodity.class, bagFileHandle );
					break;
				}
			}
		}else{
			//背包已满，物品丢失
			//todo
		}
		//将装备对应的actor从stage移走
		EquipmentActor actor = (EquipmentActor) actorMap.remove( commodityCoordinateIndex );
		actor.remove();
		//将装备从fighter身上卸下
		Array<Commodity> equips = fighterInfo.getEquipments();
		for( int i = 0; i < equips.size; i ++ ){
			if( commodity.getCoordinateIndex() == equips.get(i).getCoordinateIndex() ){
				equips.removeIndex( i );
				break;
			}
		}
	}
	
	public void setDetailActor(EquipmentDetailActor detailActor) {
		this.detailActor = detailActor;
	}

	public void setFighterInfo(FighterInfo fighterInfo) {
		this.fighterInfo = fighterInfo;
	}
	
}
