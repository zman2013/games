package mt.property;

import mt.actor.CoordinateActor;
import mt.domain.Commodity;
import mt.domain.FighterInfo;
import mt.listener.EquipmentDetailActorClickListener;
import mt.resources.AbstractCoordinateManager;
import mt.screens.PropertyScreen;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;

public class PropertyEquipmentManager extends AbstractCoordinateManager implements EquipmentDetailActorClickListener{
	
	private PropertyScreen screen;
	
	private EquipmentDetailActor detailActor;
	
	private BagWidget bagWidget;
	
	private PropertyDataAccessor dataAccessor;
	
	public PropertyEquipmentManager(PropertyDataAccessor dataAccessor) {
		super();
		
		this.dataAccessor = dataAccessor;
	}

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
		detailActor.addClickListener( this, "卸下" );
		detailActor.setCommodity( commodity );
		detailActor.setZIndex( detailActor.getStage().getActors().size-1 );
		detailActor.setVisible( true );
	}

	/**
	 * 卸下装备
	 * @param commodity
	 */
	public void clickedEquipDetailButton(Commodity commodity) {
		FighterInfo fighterInfo = dataAccessor.getFighterInfo();
		int commodityCoordinateIndex = commodity.getCoordinateIndex();
		//将卸下的物品放入背包
		Array<Commodity> commodities = dataAccessor.getCommodities();
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
					//放入背包
					bagWidget.refreshUI();
					dataAccessor.flushCommodities(commodities);
					break;
				}
			}
		}else{
			//背包已满，物品丢失
			//todo
		}
		//将装备对应的actor从stage移走
		actorMap.remove( commodityCoordinateIndex ).remove();
		//将装备从fighter身上卸下
		Array<Commodity> equips = fighterInfo.getEquipments();
		for( int i = 0; i < equips.size; i ++ ){
			if( commodity.getCoordinateIndex() == equips.get(i).getCoordinateIndex() ){
				equips.removeIndex( i );
				break;
			}
		}
		dataAccessor.flushFighterInfo(fighterInfo);
		//更新displaying fighter info的属性
		screen.removeEquipment( commodity );
	}
	
	public void setDetailActor(EquipmentDetailActor detailActor) {
		this.detailActor = detailActor;
	}

	public void put(int formationIndexInProperty, PropertyResourceLoader loader, Commodity commodity) {
		EquipmentActor actor = (EquipmentActor) actorMap.get( formationIndexInProperty );
		if( actor == null ){
			actor = new EquipmentActor( commodity, loader, this );
			detailActor.getStage().addActor( actor );
		}
		actor.setCommodity( commodity );
		actorMap.put( commodity.getCoordinateIndex(), actor );
	}

	public void setBagWidget(BagWidget bagWidget) {
		this.bagWidget = bagWidget;
	}
	
	public void setScreen( PropertyScreen screen ){
		this.screen = screen;
	}

}
