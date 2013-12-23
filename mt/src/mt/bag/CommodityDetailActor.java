package mt.bag;

import mt.domain.Commodity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entry;

public class CommodityDetailActor extends Image{
	
	private Commodity commodity;
	private BagResourceLoader loader;
	private BagManager manager;
	
	private Drawable commodityDrawable;
	
	private Drawable buttonDrawable;
	private Drawable defaultButtonDrawable;
	private Drawable activeButtonDrawable;
	
	private BitmapFont font;
	
	private CommodityDetailActor self;

	public CommodityDetailActor( BagResourceLoader loader, BagManager manager ){
		super( loader.getDetailBgDrawable() );
		self = this;
		this.manager = manager;
		
		setWidth( 240 );
		setHeight( 520 );
		setY( 145 );
		
		defaultButtonDrawable = loader.getButtonDrawable();
		activeButtonDrawable = loader.getActiveButtonDrawable();
		buttonDrawable = defaultButtonDrawable;
		font = loader.getFont();
		
		this.loader = loader;
		//95%的不透明度
		setColor( 1, 1, 1, 0.95f );
		//listener
		initListener();
		//
		setVisible( false );
	}
	
	private Rectangle buttonRectangle = new Rectangle( 74, 24, 90, 38 );
	private void initListener() {
		addListener( new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if( buttonRectangle.contains( x, y ) ){
					buttonDrawable = activeButtonDrawable;
				}
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				buttonDrawable = defaultButtonDrawable;
				if( buttonRectangle.contains( x, y ) ){
					manager.abandon( commodity );
				}
				self.setVisible( false );
			}
		});
	}

	public void setCommodity( Commodity commodity ){
		if( this.commodity == commodity ){
			return;
		}
		this.commodity = commodity;
		commodityDrawable = loader.getDrawable( commodity.getIconFilePath() );
		if( commodity.getCoordinateIndex() % 6 < 3 ){
			setX( 240 );
		}else{
			setX( 0 );
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		float x = getX();
		if( commodityDrawable != null ){
			float y = 630;
			font.draw( batch, commodity.getName()+" ("+Commodity.getTypeName(commodity.getType())+")", x+80, y );
			ObjectMap<String, Integer> properties = commodity.getProperties();
			y -= 20;
			for( Entry<String, Integer> entry : properties.entries() ){
				font.draw( batch, commodity.getPropertyName(Integer.parseInt(entry.key))+":"+entry.value, x+100, y );
				y -= 18;
			}
		}
		
		//button
		x = x + (getWidth()-buttonDrawable.getMinWidth())/2;
		float y = getY()-20;
		buttonDrawable.draw( batch, x, y, buttonDrawable.getMinWidth(), buttonDrawable.getMinHeight() );
		font.draw( batch, "丢弃", x+58, y+74 );
	}
	
	
	
}
