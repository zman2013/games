package mt.actors;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.repeat;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import mt.actor.skill.MeleeAttack;
import mt.actor.skill.SkillPool;
import mt.actors.domain.FighterInfo;
import mt.resources.ResourceUtil;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Fighter extends Image{
	
	public static byte HERO = 1;
	public static byte MONSTER = -1;
	
	private FighterInfo fighterInfo;
	
	private Fighter enemy;
	
	//英雄所属势力：-1：上，1：下
	private int camp;

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
	
	private float originX, originY;
	public Fighter( int borderIndex, int heroIndex, byte camp, float scale, float x, float y ){
		fighterInfo = new FighterInfo( borderIndex, heroIndex );
		setScale( scale );
		initResource( borderIndex, heroIndex );
		setPosition( x, y );
		originX = x;
		originY = y;
		this.camp = camp;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		
		if( fighterInfo.getHp() < 0 ){
			remove();
		}
	}
	
	private float previousRotation = 0;
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		float x = getX();
		float y = getY();
		float scaleX = getScaleX();
		float scaleY = getScaleY();
		float rotation = getRotation();
		
		if (scaleX == 1 && scaleY == 1 && rotation == 0){
			batch.draw(bottomSlateTextureRegion, x, y, bottomSlateWidth, bottomSlateHeight);
			batch.draw(borderTextureRegion, x+11, y+18, borderWidth, borderHeight);
			batch.draw(heroTextureRegion, x-11, y+35, heroWidth, heroHeight);
		}else {
			if( rotation != previousRotation ){
				float deltaRotation = rotation - previousRotation;
				previousRotation = rotation;
				borderOffset.rotate( deltaRotation );
				heroOffset.rotate( deltaRotation );
			}
			//draw, 以originX, originY作为原点进行旋转
			batch.draw(bottomSlateTextureRegion, x, y, 0, 0, bottomSlateWidth, bottomSlateHeight, scaleX, scaleY, rotation);
			batch.draw(borderTextureRegion, x+borderOffset.x*scaleX, y+borderOffset.y*scaleY, 0, 0, borderWidth, borderHeight, scaleX, scaleY, rotation);
			batch.draw(heroTextureRegion, x+heroOffset.x*scaleX, y+heroOffset.y*scaleY, 0, 0, heroWidth, heroHeight, scaleX, scaleY, rotation);
		}
		
	}
	
	private Vector2 borderOffset = new Vector2( 11, 18 );
	private Vector2 heroOffset = new Vector2( -11, 35 );
	
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

	public void attack(){
		//1 find target
		findTarget();
		//2 attack
		attackAction();
		MeleeAttack attack = SkillPool.getMeleeAttack();
		attack.setFighter( this );
		attack.setEnemy( enemy );
		getStage().addActor( attack );
		enemy.beingAttacked( attack );
	}
	
	private void findTarget() {
		//todo
	}
	
	public void beingAttacked( MeleeAttack attack ){
		addAction( 
				sequence( 
						Actions.moveTo( getX()-10, getY()+5, 0.2f, Interpolation.swingIn )
						,Actions.moveTo( originX, originY, 0.2f, Interpolation.swingOut )
				)
		);
		int damage = fighterInfo.randomMeleeAttach();
		fighterInfo.bleeding( damage );
		attack.setDamage( damage );
		System.out.println( damage+":"+fighterInfo.getHp() );
	}

	private void attackAction(){
		addAction( 
				sequence( 
						Actions.moveTo( getX(), getY()+20*camp, 1, Interpolation.elasticOut)
						,Actions.moveTo( originX, originY, 0.5f )
				)
		);
	}
	
	public void walk(){
		addAction(
				repeat( 10,
					sequence(
							repeat( 40, moveBy( 0, 1 ) )
							,repeat( 40, moveBy( 0, -1 ) )
					)
				)
		);
	}

	public Fighter getEnemy() {
		return enemy;
	}

	public void setEnemy(Fighter enemy) {
		this.enemy = enemy;
	}

}
