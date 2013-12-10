package mt.actors;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.repeat;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import mt.actor.skill.MeleeAttack;
import mt.actor.skill.SkillPool;
import mt.actors.domain.FighterInfo;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Fighter extends Image{
	
	private FighterInfo fighterInfo;
	
	private Fighter enemy;
	
	//resources
	//最下石板
	private TextureRegion bottomSlateTextureRegion;
	//边框
	private TextureRegion borderTextureRegion;
	//英雄
	private TextureRegion fighterTextureRegion;
	
	private float originX, originY;
//	public Fighter( int borderIndex, int heroIndex, byte camp, float scale, float x, float y ){
//		fighterInfo = new FighterInfo( borderIndex, heroIndex );
//		setScale( scale );
//		initResource( borderIndex, heroIndex );
//		setPosition( x, y );
//		originX = x;
//		originY = y;
//		this.camp = camp;
//		
//		setWidth( heroWidth );
//		setHeight( heroHeight );
//	}

	public Fighter(TextureRegion bottomSlateRegion, TextureRegion borderRegion,
			TextureRegion fighterRegion, FighterInfo info) {
		this.fighterInfo = info;
		setScale( info.getScale() );
		setPosition( info.getX(), info.getY() );
		this.bottomSlateTextureRegion = bottomSlateRegion;
		this.borderTextureRegion = borderRegion;
		this.fighterTextureRegion = fighterRegion;
	}

	private Vector2 startDragPosition = new Vector2();
	private Vector2 draggingPosition = new Vector2();
	boolean initListener = false;
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if( !initListener ){
			initListener = true;
			addListener( new ClickListener(){

				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					startDragPosition.set( x, y );
					localToStageCoordinates( startDragPosition );
					return true;
				}

				@Override
				public void touchDragged(InputEvent event, float x, float y,
						int pointer) {
					draggingPosition.set( x, y );
					localToStageCoordinates( draggingPosition );
					setX( getX() + (draggingPosition.x-startDragPosition.x) );
					setY( getY() + (draggingPosition.y-startDragPosition.y) );
					startDragPosition.set( draggingPosition.x, draggingPosition.y );
				}
			});
		}
		
		if( fighterInfo.getHp() < 0 ){
			remove();
		}
	}
	
	private Vector2 borderOffset = new Vector2( 11, 18 );
	private Vector2 heroOffset = new Vector2( -11, 35 );
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
			batch.draw(bottomSlateTextureRegion, x, y, bottomSlateTextureRegion.getRegionWidth(), bottomSlateTextureRegion.getRegionHeight() );
			batch.draw(borderTextureRegion, x+borderOffset.x, y+borderOffset.y, borderTextureRegion.getRegionWidth(), borderTextureRegion.getRegionHeight() );
			batch.draw(fighterTextureRegion, x+heroOffset.x, y+heroOffset.y, fighterTextureRegion.getRegionWidth(), fighterTextureRegion.getRegionHeight() );
		}else {
			if( rotation != previousRotation ){
				float deltaRotation = rotation - previousRotation;
				previousRotation = rotation;
				borderOffset.rotate( deltaRotation );
				heroOffset.rotate( deltaRotation );
			}
			//draw, 以originX, originY作为原点进行旋转
			batch.draw(bottomSlateTextureRegion, x, y, 0, 0, bottomSlateTextureRegion.getRegionWidth(), bottomSlateTextureRegion.getRegionHeight(), scaleX, scaleY, rotation);
			batch.draw(borderTextureRegion, x+borderOffset.x*scaleX, y+borderOffset.y*scaleY, 0, 0, borderTextureRegion.getRegionWidth(), borderTextureRegion.getRegionHeight(), scaleX, scaleY, rotation);
			batch.draw(fighterTextureRegion, x+heroOffset.x*scaleX, y+heroOffset.y*scaleY, 0, 0, fighterTextureRegion.getRegionWidth(), fighterTextureRegion.getRegionHeight(), scaleX, scaleY, rotation);
		}
		
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
//		System.out.println( damage+":"+fighterInfo.getHp() );
	}

	private void attackAction(){
		addAction( 
				sequence( 
						Actions.moveTo( getX(), getY()+20*fighterInfo.getCamp(), 1, Interpolation.elasticOut)
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
