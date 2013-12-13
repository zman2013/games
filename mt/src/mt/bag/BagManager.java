package mt.bag;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;

import mt.actors.domain.Commodity;
import mt.resources.AbstractCoordinateManager;

public class BagManager extends AbstractCoordinateManager{

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
	
}
