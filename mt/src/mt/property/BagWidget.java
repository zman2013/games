package mt.property;

import mt.domain.Commodity;
import mt.domain.FighterInfo;
import mt.listener.EquipmentDetailActorClickListener;
import mt.listener.PlusActorClickListener;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;

/**
 * 在property页面装备装备或学习技能时，用于显示拥有的装备和技能书。
 * @author zman
 *
 */
public class BagWidget extends Group implements EquipmentDetailActorClickListener, PlusActorClickListener{

	private Drawable bgDrawable;
	private TextureRegion cellBgRegion;
	
	private PropertyResourceLoader loader;
	
	private EquipmentDetailActor equipDetailActor;
	
	private PropertyEquipmentManager equipManager;
	
	private Image bgImage;
	
	private int currentCommodityType;
	private IntMap<CommodityActor> commodityActors = new IntMap<CommodityActor>( 18 );
	
	private PropertyDataAccessor dataAccessor;
	
	public BagWidget( PropertyResourceLoader loader, PropertyEquipmentManager equipManager, PropertyDataAccessor dataAccessor ){
		this.dataAccessor = dataAccessor;
		this.loader = loader;
		this.equipManager = equipManager;
		bgDrawable = loader.getDetailBgDrawable();
		cellBgRegion = loader.getSkillPlaceHolderRegion();
		
		bgImage = new Image( bgDrawable );
		addActor( bgImage );
		
		setVisible( false );
		
		initListener();
	}

	private Rectangle rect = new Rectangle(45, 40, 380, 190);
	private void initListener() {
		bgImage.addListener( new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				if( !rect.contains(x, y) ){
					setVisible( false );
				}
			}
		});
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		int index = 0;
		for( int i = 0; i < 3; i ++ ){
			for( int j = 0; j < 6; j ++ ){
				if(  index >= commodityCountWithSameType ){
					batch.draw( cellBgRegion, 45+j*65, getY()+170-65*i );
				}
			}
		}
	}

	/**
	 * 当前选定的装备格子或技能格子在property页面的索引号。
	 */
	private int formationIndexInProperty;
	private int commodityCountWithSameType = 0;
	public void clickedPlusActorButton( int commodityType, int formationIndexInProperty ){
		
		this.currentCommodityType = commodityType;
		this.formationIndexInProperty = formationIndexInProperty;
		
		if( commodityType == 8 ){
			setY( 50 );
		}else{
			setY( 310 );
		}
		
		refreshUI();
		
		setVisible( true );
	}

	public void refreshUI() {
		clearChildren();
		commodityActors.clear();
		addActor( bgImage  );
		
		commodityCountWithSameType = 0;
		for( Commodity commodity : dataAccessor.getCommodities() ){
			//todo 目前只显示前18个同类物品
			if( commodityCountWithSameType >= 18 ){
				break;
			}
			if( commodity.getType() == currentCommodityType ){
				int i = commodityCountWithSameType/6, j = commodityCountWithSameType % 6;
				CommodityActor actor = new CommodityActor( commodity, loader, this, 45+j*65, 170-65*i );
				commodityActors.put(commodity.getCoordinateIndex(), actor);
				addActor( actor  );
				commodityCountWithSameType++;
			}
		}
	}

	/**
	 * 显示装备的详细信息
	 * @param commodity
	 */
	public void showDetail(Commodity commodity) {
		String detailButtonText = "装备";
		if( commodity.getType() == 8 ){
			detailButtonText = "学习";
		}
		equipDetailActor.addClickListener( this, detailButtonText );
		equipDetailActor.setCommodity( commodity );
		equipDetailActor.setZIndex( equipDetailActor.getStage().getActors().size-2 );
		equipDetailActor.setVisible( true );
	}

	/**
	 * 装上指定装备
	 * @param commodity: 将要装上的装备
	 */
	public void clickedEquipDetailButton(Commodity commodity ) {
		Array<Commodity> commodities = dataAccessor.getCommodities();
		FighterInfo fighterInfo = dataAccessor.getFighterInfo();
		Array<Commodity> equips = fighterInfo.getEquipments();
		//从背包拿出新装备
		int newEquiptIndexInBag = 0;
		for( int i = 0; i < commodities.size; i ++ ){
			if( commodities.get(i).getCoordinateIndex() == commodity.getCoordinateIndex() ){
				newEquiptIndexInBag = i;
				commodities.removeIndex( i );
				commodityActors.remove( commodity.getCoordinateIndex() ).remove();
				break;
			}
		}
		//先卸下旧装备，放入背包
		for( int i = 0; i < equips.size; i ++ ){
			Commodity oldEquip = equips.get( i );
			if( oldEquip.getCoordinateIndex() == formationIndexInProperty ){
				equips.removeIndex( i );
				oldEquip.setCoordinateIndex( commodity.getCoordinateIndex() );
				commodities.set( newEquiptIndexInBag, oldEquip );
				break;
			}
		}
		//装上新装备
		commodity.setCoordinateIndex( formationIndexInProperty );
		equipManager.put( formationIndexInProperty, loader, commodity );
		equips.add( commodity );
		//flush data into files
		dataAccessor.flushCommodities( commodities );
		dataAccessor.flushFighterInfo( fighterInfo );
	}
	
	public void setEquipDetailActor(EquipmentDetailActor equipDetailActor) {
		this.equipDetailActor = equipDetailActor;
	}

}
