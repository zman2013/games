package mt.actors;

import mt.actors.domain.FighterInfo;
import mt.resources.ResourceUtil;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Fighter extends Image{

	private FighterInfo fighterInfo;
	//resources
	//最下石板
	private Drawable bottomSlateDrawable;
	//边框
	private Drawable borderDrawable;
	//英雄
	private Drawable heroDrawable;
	
	public Fighter( int borderIndex, int heroIndex, float width, float height, float scale ){
		fighterInfo = new FighterInfo( borderIndex, heroIndex, width, height );
		setScale( scale );
		initResource( borderIndex, heroIndex );
		
		walkSteps = 0;
	}

	
	private float heroWalkTime = 0;
	@Override
	public void act(float delta) {
		super.act(delta);
		
		heroWalk( delta );
	}
	
	/**
	 * 走一0.5s，停1s
	 * @param delta
	 */
	private float walkSteps = -1;
	private void heroWalk( float delta ) {
		if( walkSteps == -1 ){
			return;
		}
		heroWalkTime += delta;
		if(  heroWalkTime < 0.5 ){
			if( !fighterInfo.isWalking() ){
				fighterInfo.setX( fighterInfo.getX() - 1 );
			}
			fighterInfo.setWalking( true );
			fighterInfo.setY( fighterInfo.getY() + 2 );
		}else if( heroWalkTime < 1.5 ){
			if( fighterInfo.isWalking() ){
				fighterInfo.setX( fighterInfo.getX() + 1 );
			}
			fighterInfo.setWalking( false );
			fighterInfo.setY( fighterInfo.getY() - 1 );
		}else{
			setPosition( 215, 100 );
			heroWalkTime = 0;
			walkSteps++;
			if( walkSteps == 4 ){
				walkSteps = -1;
			}
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		float x = fighterInfo.getX();
		float y = fighterInfo.getY();
		bottomSlateDrawable.draw(batch, x, y, bottomSlateWidth, bottomSlateHeight);
		borderDrawable.draw( batch, x+(11*getScaleX()), y+(18*getScaleY()), borderWidth, borderHeight );
		heroDrawable.draw( batch, x-(11*getScaleX()), y+(35*getScaleY()), heroWidth, heroHeight );
	}
	
	private float bottomSlateWidth, bottomSlateHeight;
	private float borderWidth, borderHeight;
	private float heroWidth, heroHeight;
	
	private void initResource(int borderIndex, int heroIndex ) {
		AssetManager assetManager = ResourceUtil.getAssetManager();
		String bottomSlateFilePath = "assets/images/border/data.dat_000493.png";
		String borderFilePath = "assets/images/border/"+borderIndex+".png";
		String heroFilePath = "assets/images/heros/"+heroIndex+".png";
		assetManager.load( bottomSlateFilePath, Texture.class );
		assetManager.load( borderFilePath, Texture.class );
		assetManager.load( heroFilePath, Texture.class );
		assetManager.finishLoading();

		Texture bottomSlateTexture = assetManager.get( bottomSlateFilePath );
		Texture borderTexture = assetManager.get( borderFilePath );
		Texture heroTexture = assetManager.get( heroFilePath );
		
		bottomSlateWidth = bottomSlateTexture.getWidth()*getScaleX();
		bottomSlateHeight = bottomSlateTexture.getHeight()*getScaleY();
		borderWidth = borderTexture.getWidth()*getScaleX();
		borderHeight = borderTexture.getHeight()*getScaleY();
		heroWidth = heroTexture.getWidth()*getScaleX();
		heroHeight = heroTexture.getHeight()*getScaleY();
		
		Skin skin = ResourceUtil.getSkin();
		skin.add( bottomSlateFilePath, bottomSlateTexture );
		skin.add( borderFilePath, borderTexture );
		skin.add( heroFilePath, heroTexture );
		
		bottomSlateDrawable = skin.getDrawable( bottomSlateFilePath );
		borderDrawable = skin.getDrawable( borderFilePath );
		heroDrawable = skin.getDrawable( heroFilePath );
	}

	@Override
	public void setPosition(float x, float y) {
		fighterInfo.setX( x );
		fighterInfo.setY( y );
	}
	
	
}
