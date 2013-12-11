package mt.actor.skill;

import java.util.HashMap;
import java.util.Map;

import mt.fight.Fighter;
import mt.resources.ResourceUtil;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

public class MeleeAttack extends Image{
	
	private static float duration = 0.1f;
	
	private Fighter fighter;
	
	private Fighter enemy;

	private Animation animation;
	
	private Map<TextureRegion, Drawable> cache;
	
	private TextureRegion bleedingRegion;
	
	private BitmapFont font;
	
	public MeleeAttack(){
		initResources();
		TextureRegion region = animation.getKeyFrame( 0 );
		setWidth( region.getRegionWidth() );
		setHeight( region.getRegionHeight() );
	}
	
	public void init() {
		setFighter( null );
		setEnemy( null );
	}
	
	private float stateTime;
	private float threshold = duration*4;
	@Override
	public void act(float delta) {
		super.act(delta);
		
		stateTime += delta;
		float stateTimeTmp = interpolate(stateTime, threshold);
		TextureRegion region = animation.getKeyFrame( stateTimeTmp, false );
		Drawable drawable = cache.get( region );
		setDrawable( drawable );
		setScale( fighter.getScaleX()*1.5f );
		setPosition( fighter.getX()-40*fighter.getScaleX(), fighter.getY() );
		
//	    check if complete
		if( stateTime > threshold ){
			setDrawable( null );
		}
		if( stateTime > threshold*1.5f ){
			stateTime = 0;
			remove();
		}
	} 
	
	

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		float x = enemy.getX();
		float y = enemy.getY();
		float width = bleedingRegion.getRegionWidth();
		float height = bleedingRegion.getRegionHeight();
		float scaleX = enemy.getScaleX();
		float scaleY = enemy.getScaleY();
		float rotation = enemy.getRotation();
		batch.draw( bleedingRegion, x-60*scaleX, y+20*scaleY, 0, 0, width, height, scaleX, scaleY, rotation );
		
		font.draw( batch, String.valueOf(damage), x+100*scaleX, y+100*scaleY );
		
	}

	private void initResources() {
		AssetManager assetManager = ResourceUtil.getAssetManager();
		String meleeAttackAtlasPath = "assets/images/skills/melee_attack/melee_attack.atlas";
		String bleedingFilePath = "assets/images/skills/melee_attack/data.dat_000480.png";
		String fontFilePath = "assets/font/home_screen.fnt";
		assetManager.load( meleeAttackAtlasPath, TextureAtlas.class );
		assetManager.load( bleedingFilePath, Texture.class );
		assetManager.load( fontFilePath, BitmapFont.class );
		assetManager.finishLoading();
		
		Skin skin = ResourceUtil.getSkin();
		skin.add( "meleeAttackBleeding", assetManager.get( bleedingFilePath, Texture.class ) );
		bleedingRegion = skin.getRegion( "meleeAttackBleeding" );
		
		TextureAtlas textureAtlas = assetManager.get( meleeAttackAtlasPath, TextureAtlas.class );
		Array<AtlasRegion> keyFrames = textureAtlas.findRegions( "melee_attack" );
		
		font = assetManager.get( fontFilePath, BitmapFont.class );
		
		cache = new HashMap<TextureRegion, Drawable>();
		for( TextureRegion region : keyFrames ){
			cache.put( region, new TextureRegionDrawable( region ) );
		}
		animation = new Animation( duration, keyFrames );
	}
	
	/**
	 * if value > threshold, then return 1.
	 * @param value
	 * @param threshold
	 * @return
	 */
	private float interpolate( float value, float threshold ){
		double piece = threshold/10;
		if( value < piece*2 ){
			return 0;
		}else if( value < piece*7 ){
			return duration;
		}else if( value < piece*9 ){
			return duration*2;
		}else{
			return duration*3;
		}
	}
	
	
	public void setFighter(Fighter fighter) {
		this.fighter = fighter;
	}

	public void setEnemy(Fighter enemy) {
		this.enemy = enemy;
	}

	private int damage;
	public void setDamage(int damage) {
		this.damage = damage;
	}

	public Fighter getFighter() {
		return fighter;
	}

}
