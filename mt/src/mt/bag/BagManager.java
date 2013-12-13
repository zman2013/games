package mt.bag;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Json;

import mt.actor.CoordinateActor;
import mt.domain.Commodity;
import mt.manager.Manager;
import mt.resources.AbstractCoordinateManager;

public class BagManager extends AbstractCoordinateManager implements Manager{

	private CommodityDetailActor detailActor;
	
	@Override
	protected void init() {
		actorMap = new IntMap<>( 48 );
		origin = new Vector2( 32, 32 );
		
		coordinates = new Array<Vector2>();
		//八行六列，48个格子
		for( int i = 0; i < 8; i ++ ){
			for( int j = 0; j < 6; j ++ ){
				coordinates.add( new Vector2( 45+j*65, 600-65*i ) );
			}
		}
	}
	
	public void setDetailActor(CommodityDetailActor detailActor) {
		this.detailActor = detailActor;
	}

	public void showDetail(Commodity commodity) {
		//-2因为有returnActor，returnActor需要始终显示在最上层
		detailActor.setCommodity( commodity );
		detailActor.setZIndex( detailActor.getStage().getActors().size-2 );
		detailActor.setVisible( true );
	}
	
	public void hideDetail() {
		detailActor.setVisible( false );
	}
	
	/**
	 * 丢弃物品
	 * @param commodity
	 */
	public void abandon( Commodity commodity ) {
		int coordinateIndex = commodity.getCoordinateIndex();
		CommodityActor actor = (CommodityActor) actorMap.get( coordinateIndex );
		actor.remove();
		actorMap.remove( coordinateIndex );
	}
	
	/**
	 * 把数据输出到文件中。
	 * 在返回上一次菜单时，会被调用。
	 */
	public void flushData(){
		Array<Commodity> commodities = new Array<Commodity>();
		for( CoordinateActor actor : actorMap.values() ){
			commodities.add( ((CommodityActor)actor).getCommodity() );
		}
		FileHandle fileHandle = Gdx.files.local("assets/data/player/bag.data");
		String jsonString = new Json().prettyPrint( commodities  );
		fileHandle.writeString( jsonString, false );
	}
}
