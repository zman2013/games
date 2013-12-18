package mt.fight;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.repeat;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import mt.actor.skill.MeleeAttack;
import mt.actor.skill.SkillPool;
import mt.domain.FighterInfo;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class Fighter extends Image{
	
	protected FighterInfo fighterInfo;
	
	@SuppressWarnings("unused")
	private Array<Fighter> heros, enemies;
	
	//resources
	//最下石板
	private TextureRegion bottomSlateTextureRegion;
	//边框
	private TextureRegion borderTextureRegion;
	//英雄
	private TextureRegion fighterTextureRegion;
	//是否正在被攻击, 0: 表示没有被攻击，1:表示正被一个敌人攻击，2:表示正被两个敌人攻击
	private byte beingAttacked;
	
	public Fighter(TextureRegion bottomSlateRegion, TextureRegion borderRegion,
			TextureRegion fighterRegion, FighterInfo info) {
		this.fighterInfo = info;
		setScale( info.getScale() );
		setPosition( info.getX(), info.getY() );
		this.bottomSlateTextureRegion = bottomSlateRegion;
		this.borderTextureRegion = borderRegion;
		this.fighterTextureRegion = fighterRegion;
		
		heroOffset.x = borderOffset.x+(borderRegion.getRegionWidth()-fighterRegion.getRegionWidth())/2;
		heroOffset.y = borderOffset.y+(borderRegion.getRegionHeight()-fighterRegion.getRegionHeight())/2;
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
		
		if( fighterInfo.getHp() < 0 && beingAttacked == 0 ){
			remove();
		}
	}
	
	private Vector2 borderOffset = new Vector2( 10, 18 );
	private Vector2 heroOffset = new Vector2();
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
	
	private float delay;
	private boolean fighting = false;
	public void attack( float delta ){
		//1正在攻击中
		if( fighting ){
			return;
		}
		//2 寻找有效目标
		Fighter enemy = findTarget();
		if( enemy == null ){
			return;
		}
		//3 等待攻击事件间隔
		delay += delta;
		if( delay < fighterInfo.getAttackInterval() )
			return;
		//4 检查是否已经死亡
		if( fighterInfo.getHp() <= 0 ){
			return;
		}
		fighting = true;
		attackAction();
		MeleeAttack attack = SkillPool.getMeleeAttack();
		attack.setFighter( this );
		attack.setEnemy( enemy );
		getStage().addActor( attack );
		enemy.beingAttacked( attack );
	}
	
	private Fighter findTarget() {
		//todo
		for( Fighter fighter : enemies ){
			if( fighter.fighterInfo.getHp() > 0 ){
				return fighter;
			}
		}
		return null;
	}
	
	public void beingAttacked( MeleeAttack attack ){
		beingAttacked ++;
		int damage = attack.getFighter().getFighterInfo().randomMeleeAttach();
		fighterInfo.bleeding( damage );
		attack.setDamage( damage );
		addAction( 
				sequence( 
						Actions.moveTo( getX()-10, getY()+5, 0.2f, Interpolation.swingIn )
						,Actions.moveTo( fighterInfo.getX(), fighterInfo.getY(), 0.2f, Interpolation.swingOut )
						,new Action(){
							public boolean act(float delta) {
								beingAttacked--;
								return true;
							}
						}
				)
		);
	}

	private void attackAction(){
		addAction( 
				sequence( 
						Actions.moveTo( getX(), getY()+20*fighterInfo.getCamp(), 1, Interpolation.elasticOut)
						,Actions.moveTo( fighterInfo.getX(), fighterInfo.getY(), 0.5f )
						, new Action(){
							public boolean act(float delta) {
								fighting = false;
								delay = 0;
								return true;
							}
						}
				)
		);
	}
	
	private boolean walking = false;
	public void walk(){
		if( walking ){
			return;
		}
		walking = true;
		addAction(
				sequence(
						repeat( MathUtils.random( 10 ), moveBy( 0, -1 ) )
						,repeat( 
								5, sequence(
										repeat( 38, moveBy( 0, 1 ) )
										,repeat( 37, moveBy( 0, -1 ) )
									)
						)
						,Actions.moveTo( fighterInfo.getX(), fighterInfo.getY(), 0.1f )
						,new Action(){
							public boolean act(float delta) {
								walking = false;
								return true;
							}
						}
				)
		);
	}

	public void setEnemies(Array<Fighter> enemies) {
		this.enemies = enemies;
	}
	
	public void setHeros( Array<Fighter> heros ){
		this.heros = heros;
	}

	public FighterInfo getFighterInfo() {
		return fighterInfo;
	}

	
}
