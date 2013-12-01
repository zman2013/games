package mt.actors;

import mt.actors.domain.FighterInfo;
import mt.resources.ResourceUtil;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Fighter extends Image{

	private FighterInfo fighterInfo;
	//resources
	//最下石板
	private TextureRegion bottomSlateTextureRegion;
	private Drawable bottomSlateDrawable;
	//边框
	private TextureRegion borderTextureRegion;
	private Drawable borderDrawable;
	//英雄
	private TextureRegion heroTextureRegion;
	private Drawable heroDrawable;
	
	public Fighter( int borderIndex, int heroIndex, float scale, float originX, float originY ){
		fighterInfo = new FighterInfo( borderIndex, heroIndex, originX, originY );
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
			setPosition( fighterInfo.getOriginX(), fighterInfo.getOriginY() );
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
		float scaleX = getScaleX();
		float scaleY = getScaleY();
		float rotation = getRotation();
		
		if (scaleX == 1 && scaleY == 1 && rotation == 0){
			batch.draw(bottomSlateTextureRegion, x, y, bottomSlateWidth, bottomSlateHeight);
			batch.draw(borderTextureRegion, x+11, y+18, borderWidth, borderHeight);
			batch.draw(heroTextureRegion, x-11, y+35, heroWidth, heroHeight);
		}else {
			batch.draw(bottomSlateTextureRegion, x, y, 0, 0, bottomSlateWidth, bottomSlateHeight, scaleX, scaleY, rotation);
			batch.draw(borderTextureRegion, x+11*scaleX, y+18*scaleY, 0, 0, borderWidth, borderHeight, scaleX, scaleY, rotation);
			batch.draw(heroTextureRegion, x-11*scaleX, y+35*scaleY, 0, 0, heroWidth, heroHeight, scaleX, scaleY, rotation);
		}
		
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
		
		bottomSlateWidth = bottomSlateTexture.getWidth();
		bottomSlateHeight = bottomSlateTexture.getHeight();
		borderWidth = borderTexture.getWidth();
		borderHeight = borderTexture.getHeight();
		heroWidth = heroTexture.getWidth();
		heroHeight = heroTexture.getHeight();
		
		Skin skin = ResourceUtil.getSkin();
		skin.add( bottomSlateFilePath, bottomSlateTexture );
		skin.add( borderFilePath, borderTexture );
		skin.add( heroFilePath, heroTexture );
		
		bottomSlateDrawable = skin.getDrawable( bottomSlateFilePath );
		borderDrawable = skin.getDrawable( borderFilePath );
		heroDrawable = skin.getDrawable( heroFilePath );
		
		bottomSlateTextureRegion = ((TextureRegionDrawable)bottomSlateDrawable).getRegion();
		borderTextureRegion = ((TextureRegionDrawable)borderDrawable).getRegion();
		heroTextureRegion = ((TextureRegionDrawable)heroDrawable).getRegion();
	}

	@Override
	public void setPosition(float x, float y) {
		fighterInfo.setX( x );
		fighterInfo.setY( y );
	}
	
	
}
