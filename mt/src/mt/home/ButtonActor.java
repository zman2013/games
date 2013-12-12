package mt.home;

import mt.screens.AbstractScreen;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class ButtonActor extends Image{
	
	private AbstractScreen screen;
	
	private Class<? extends AbstractScreen> screenClass;

	private Drawable defaultDrawable;
	private Drawable activeDrawable;
	private BitmapFont font;
	
	private String text;
	
	public ButtonActor( AbstractScreen screen, Class<? extends AbstractScreen> screenClass, String text, HomeResourceLoader resourceLoader ){
		super( resourceLoader.getButtonDrawable() );
		
		this.screen = screen;
		
		this.screenClass = screenClass;
		
		this.defaultDrawable = resourceLoader.getButtonDrawable();
		this.activeDrawable = resourceLoader.getActiveButtonDrawable();
		this.text = text;
		font = resourceLoader.getFont();
		
		initListener();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		font.draw( batch, text, getX()+90, getY()+50 );
		
	}

	private void initListener() {
		addListener( new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				setDrawable( activeDrawable );
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				screen.getGame().setScreen( screenClass );
				setDrawable( defaultDrawable );
			}
		});
	}
	
	
}
