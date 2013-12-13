package mt.actor.shared;

import mt.manager.Manager;
import mt.screens.AbstractScreen;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class ReturnActor extends Image{
	
	/**
	 * 
	 * @param drawable
	 * @param stageWidth
	 * @param screen
	 * @param screenClass
	 * @param managers: 如果当前页面需要保存数据，managers不应为空。
	 */
	public ReturnActor( Drawable drawable, float stageWidth, final AbstractScreen screen, final Class<? extends AbstractScreen> screenClass, final Manager... managers ){
		super( drawable );
		
		setPosition( stageWidth-getWidth(), 0 );
		
		addListener( new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				for( Manager manager : managers ){
					manager.flushData();
				}
				screen.getGame().setScreen( screenClass );
			}
		});
	}
}
