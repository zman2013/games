package mt.property;

import mt.screens.PropertyScreen;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ArrowActor extends Image{
	
	private TextureRegion region;
	
	private PropertyScreen screen;
	
	/**
	 * 表示下一个的箭头 or 表示上一个的箭头
	 */
	private boolean next;
	
	public ArrowActor( TextureRegion region, PropertyScreen screen, boolean next ){
		this.region = region;
		this.screen = screen;
		this.next = next;
		
		setWidth( region.getRegionWidth() );
		setHeight( region.getRegionHeight() );
		
		if( next ){
			setPosition( 450, 375 );
		}else{
			setPosition( 0, 375 );
		}
		
		initListener();
	}

	private void initListener() {
		addListener( new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				screen.changeFighter( next );
			}
		});
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		if( next ){
			batch.draw( region, getX(), getY() );
		}else{
			batch.draw( region, getX(), getY(), 15, 27, getWidth(), getHeight(), 1, 1, 180 );
		}
	}
	
	

}
