package mt.deprecated;

import mt.resources.FontLoader;
import mt.resources.ResourcesLoader;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class HeroSelectBar extends Image{
	//bg
	private Drawable heroBar;
	private Drawable heroBarActive;
	private Drawable heroBarLocked;
	//sub widget
	private Drawable heroWidget;
	private Drawable lock;
	private Drawable checkBox;
	private Drawable boxChecked;
	//font
	private BitmapFont font;
	
	public HeroSelectBar( int heroIndex, int borderIndex, boolean locked, boolean checked ){
		heroBar = ResourcesLoader.getDrawable( "assets/images/list_page/hero_bar.png" );
		heroBarActive = ResourcesLoader.getDrawable( "assets/images/list_page/hero_bar_active.png" );
		heroBarLocked = ResourcesLoader.getDrawable( "assets/images/list_page/hero_bar_locked.png" );
		//
		lock = ResourcesLoader.getDrawable( "assets/images/list_page/lock.png" );
		checkBox = ResourcesLoader.getDrawable( "assets/images/list_page/check_box.png" );
		boxChecked = ResourcesLoader.getDrawable( "assets/images/list_page/box_checked.png" );
		
		heroWidget = new HeroWidget( heroIndex, borderIndex );
		
		font = FontLoader.getBitmapFont();
		
		this.locked = locked;
		this.checked = checked;
		if( locked ){
			setDrawable( heroBarLocked );
		}else{
			setDrawable( heroBar );
			initListener();
		}
		
	}

	private void initListener() {
		addListener( new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				setDrawable( heroBarActive );
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				if( x>=500 & x<=540 & y>=30 & y<=70 ){
					checked = !checked;
				}
				setDrawable( heroBar );
			}
		});
		
	}

	private boolean locked;
	private boolean checked;
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		heroWidget.draw( batch, getX()-10, getY(), 100, 100 );
		
		font.draw( batch, "гЂал", getX()+130, getY()+80 );
		font.draw( batch, "1000", getX()+130, getY()+40 );
		font.draw( batch, "100", getX()+250, getY()+40 );
		
		if( locked ){
			lock.draw( batch, getX()+500, getY()+30, 40, 40 );
		}else if( checked ){
			boxChecked.draw( batch, getX()+500, getY()+30, 52, 49 );
		}else{
			checkBox.draw( batch, getX()+503, getY()+30, 42, 38 );
		}
	}
	
	
}
