package mt.property;

import mt.domain.Commodity;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class CommodityActor extends Image {

	private Commodity commodity;
	private BagWidget bagWidget;
	
	
	
	public CommodityActor(Commodity commodity, PropertyResourceLoader loader,
			BagWidget bagWidget, float x, float y ) {
		super( loader.getDrawable( commodity.getIconFilePath() ) );
		setPosition( x, y );
		
		this.commodity = commodity;
		this.bagWidget = bagWidget;
		
		initListener();
	}

	private void initListener() {
		addListener( new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				bagWidget.showDetail( commodity );
			}
		});
	}

	public Commodity getCommodity() {
		return commodity;
	}

}
