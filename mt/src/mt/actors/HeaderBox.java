package mt.actors;

import mt.resources.FontLoader;
import mt.resources.ResourcesLoader;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class HeaderBox extends WidgetGroup{

	private BitmapFont bitmapFont;
	
	private Image levelIcon;
	
	private Image expBar11, expBar12;
	private Image expBar21, expBar22;
	
	public HeaderBox( ){
		Image bg = new Image( ResourcesLoader.getHeaderBoxDrawable() );
		//font
		bitmapFont = FontLoader.getBitmapFont();
		levelIcon = new Image( ResourcesLoader.getLevel1Drawable() );
		levelIcon.setScale( 0.8f );
		levelIcon.setPosition( 55, 139 );
		
		//bar
		expBar11 = new Image( ResourcesLoader.getExpBar11Drawable() );
		expBar11.setPosition( 82, 108 );
		expBar11.setScaleX( 0.9f );
		expBar11.setScaleY( 0.5f );
		expBar12 = new Image( ResourcesLoader.getExpBar12Drawable() );
		expBar12.setPosition( 82, 108 );
		expBar12.setScaleX( 0 );
		expBar12.setScaleY( 0.5f );
		expBar21 = new Image( ResourcesLoader.getExpBar21Drawable() );
		expBar21.setPosition( 82, 65 );
		expBar21.setScaleX( 0.55f );
		expBar21.setScaleY( 0.6f );
		expBar22 = new Image( ResourcesLoader.getExpBar22Drawable() );
		expBar22.setPosition( 82, 65 );
		expBar22.setScaleX( 0 );
		expBar22.setScaleY( 0.8f );
		
		addActor( bg );
		addActor( levelIcon );
		addActor( expBar11 );
		addActor( expBar12 );
		addActor( expBar21 );
		addActor( expBar22 );
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		//player name
		bitmapFont.draw( batch, "xiaoying", getX()+100, getY()+134 );
		//player level
		bitmapFont.draw( batch, "等级 : "+2, getX()+260, getY()+120 );
		//stone count
		bitmapFont.draw( batch, ""+300, getX()+420, getY()+115 );
		//experience
		bitmapFont.draw( batch, "经验", getX()+14, getY()+104 );
		//physical power
		bitmapFont.draw( batch, "体力", getX()+14, getY()+70 );
		bitmapFont.draw( batch, 120+"/"+120, getX()+216, getY()+70 );
		//money
		bitmapFont.draw( batch, ""+25600, getX()+416, getY()+70 );
		//exp bar
		expBar12.setScaleX( 500/1000.0f );
		expBar22.setScaleX( 100/300.0f );
	}

	
	
}
