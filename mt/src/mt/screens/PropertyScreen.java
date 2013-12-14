package mt.screens;

import mt.actor.shared.PlusActor;
import mt.actor.shared.ReturnActor;
import mt.domain.Commodity;
import mt.domain.FighterInfo;
import mt.formation.HeroActor;
import mt.formation.SkillInfo;
import mt.property.ArrowActor;
import mt.property.BagWidget;
import mt.property.EquipmentActor;
import mt.property.EquipmentDetailActor;
import mt.property.PropertyDataAccessor;
import mt.property.PropertyEquipmentManager;
import mt.property.PropertyResourceLoader;
import mt.property.PropertySkillManager;
import mt.property.SkillActor;
import mt.property.SkillDetailActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

public class PropertyScreen extends AbstractScreen{

	private Drawable bgDrawable;
	
	private TextureRegion skillPlaceHolderRegion;
	
	private Drawable plusDrawable;
	
	private BitmapFont font;
	
	private FighterInfo fighterInfo;
	private Array<Integer> allFighters;
	/**
	 * 当前显示的fighter在队伍中的索引。
	 */
	private int currentFighterIndex=0;
	
	private Array<Vector2> skillCoors, equipmentCoors;
	
	private PropertySkillManager skillManager;
	private PropertyEquipmentManager equipManager;
	private EquipmentDetailActor equipDetailActor;
	private SkillDetailActor skillDetailActor;
	private ReturnActor returnActor;
	private ArrowActor nextArrowActor;
	private ArrowActor previousArrowActor;
	
	private BagWidget bagWidget;
	private Array<PlusActor> plusActors = new Array<PlusActor>( 16 );
	private Vector2 plusActorOffset = new Vector2( 13, 17 );
	
	private PropertyDataAccessor dataAccessor;
	private PropertyResourceLoader resourceLoader;
	public PropertyScreen(){
		super();
		
		dataAccessor = new PropertyDataAccessor();
		resourceLoader = new PropertyResourceLoader();
		
		bgDrawable = resourceLoader.getBgDrawable();
		font = resourceLoader.getFont();
		skillPlaceHolderRegion = resourceLoader.getSkillPlaceHolderRegion();
		plusDrawable = resourceLoader.getPlusDrawable();
				
		//init arrow actor
		TextureRegion arrowRegion = resourceLoader.getArrowRegoin();
		nextArrowActor = new ArrowActor( arrowRegion, this, true );
		previousArrowActor = new ArrowActor( arrowRegion, this, false );
				
		skillManager = new PropertySkillManager( dataAccessor );
		equipManager = new PropertyEquipmentManager( dataAccessor );
		skillCoors = skillManager.getCoordinates();
		equipmentCoors = equipManager.getCoordinates();
		
		returnActor = new ReturnActor( resourceLoader.getReturnDrawable(), stage.getWidth(), this, HomeScreen.class, skillManager );
		skillDetailActor = new SkillDetailActor( resourceLoader, skillManager );
		equipDetailActor = new EquipmentDetailActor( resourceLoader );
		
		//bag widget
		bagWidget = new BagWidget( resourceLoader, equipManager, dataAccessor );
		bagWidget.setEquipDetailActor( equipDetailActor );
		equipManager.setBagWidget( bagWidget );
		
		//plus actor
		for( Vector2 coor : skillCoors ){
			plusActors.add( new PlusActor( plusDrawable, coor.x, coor.y) );
		}
		for( Vector2 coor : equipmentCoors ){
			plusActors.add( new PlusActor( plusDrawable, coor.x, coor.y) );
		}
		
	}
	
	
	
	@Override
	public void show() {
		super.show();
		stage.clear();
		
		dataAccessor.sync( currentFighterIndex );
		
		//不改变fighter
		changeFighter( null );
	}



	/**
	 * 切换fighter
	 * next：null不变，true下一个，false上一个.
	 * 溢出界的情况不存在，在最初初始化箭头时就控制住。
	 */
	public void changeFighter( Boolean next ){
		//clear stage
		stage.clear();
		//添加按钮
		for( int i = 0; i < skillCoors.size; i ++ ){
			PlusActor plusActor = plusActors.get( i );
			plusActor.setClickListener( bagWidget, 8, i, plusActorOffset );
			stage.addActor( plusActor );
		}
		for( int i = 0; i < equipmentCoors.size; i ++ ){
			PlusActor plusActor = plusActors.get( skillCoors.size+i );
			plusActor.setClickListener( bagWidget, i, i, plusActorOffset );
			stage.addActor( plusActor );
		}
		//change fighter
		allFighters = dataAccessor.getFighterStatus().getAll();
		if( next != null ){
			if( next ){
				//无越界情况，再添加arrow时已经限制。
				currentFighterIndex = currentFighterIndex + 1;
			}else if( !next ){
				//无越界情况，再添加arrow时已经限制。
				currentFighterIndex = currentFighterIndex - 1;
			}
		}
		fighterInfo = dataAccessor.loadFighterInfo(  allFighters.get( currentFighterIndex ) );
		//添加arrow
		if( currentFighterIndex<(allFighters.size-1)  ){
			stage.addActor( nextArrowActor );
		}
		if( currentFighterIndex > 0 ){
			stage.addActor( previousArrowActor );
		}
		
		resourceLoader.loadFighterInfo( fighterInfo );
		//add fighter
		HeroActor fighter = new HeroActor( fighterInfo, resourceLoader, null );
		fighter.setPosition( 50, 590 );
		stage.addActor( fighter );
		//add skills		
		skillManager.setFighterInfo( fighterInfo );
		for( SkillInfo info : fighterInfo.getSkillInfos() ){
			SkillActor skill = new SkillActor( info, resourceLoader, skillManager);
			skillManager.add( info.getFormationIndex(), skill );
			stage.addActor( skill );
		}
		//add skill detail actor
		skillDetailActor.setVisible( false );
		skillManager.setDetailActor( skillDetailActor );
		stage.addActor( skillDetailActor );
		//add equipment
		for( Commodity equip : fighterInfo.getEquipments() ){
			EquipmentActor actor = new EquipmentActor( equip, resourceLoader, equipManager );
			equipManager.add( equip.getCoordinateIndex(), actor );
			stage.addActor( actor );
		}
		//add equip detail actor
		equipDetailActor.setVisible( false );
		equipManager.setDetailActor( equipDetailActor );
		stage.addActor( equipDetailActor );
		//add bag widget
		stage.addActor( bagWidget );
		//return actor
		stage.addActor( returnActor );
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		
		batch.begin();
		bgDrawable.draw( batch, 0, 0, stage.getWidth(), stage.getHeight() );
		//属性左
		font.draw( batch, "生命:"+fighterInfo.getHp(), 170, 700 );
		font.draw( batch, "法力:"+fighterInfo.getMagic(), 170, 682 );
		font.draw( batch, "最小物攻:"+fighterInfo.getMinMeleeAttack(), 170, 664 );
		font.draw( batch, "最大物攻:"+fighterInfo.getMaxMeleeAttack(), 170, 646 );
		font.draw( batch, "最小魔攻:"+fighterInfo.getMinSpellAttack(), 170, 628 );
		font.draw( batch, "最大魔攻:"+fighterInfo.getMaxSpellAttack(), 170, 610 );
		//属性右
		font.draw( batch, "物防:"+fighterInfo.getMelleDefense(), 310, 700 );
		font.draw( batch, "魔防:"+fighterInfo.getSpellDefense(), 310, 682 );
		font.draw( batch, "命中:"+fighterInfo.getHitScore(), 310, 664 );
		font.draw( batch, "暴击:"+fighterInfo.getCrit(), 310, 646 );
		font.draw( batch, "闪避:"+fighterInfo.getDoage(), 310, 628 );
		font.draw( batch, "运气:"+fighterInfo.getLuck(), 310, 610 );
		//属性上
		font.draw( batch, "哀木涕", 75, 730 );
		font.draw( batch, "等级:", 175, 730 );
		font.draw( batch, "经验:", 275, 730 );
		//技能
		font.draw( batch, "技能", 40, 550 );
		
		//技能上
		for( Vector2 coor : skillCoors ){
			batch.draw( skillPlaceHolderRegion, coor.x, coor.y, skillPlaceHolderRegion.getRegionWidth(), skillPlaceHolderRegion.getRegionHeight() );
		}
		//装备
		font.draw( batch, "装备", 40, 300 );
		for( Vector2 coor : equipmentCoors ){
			batch.draw( skillPlaceHolderRegion, coor.x, coor.y, skillPlaceHolderRegion.getRegionWidth(), skillPlaceHolderRegion.getRegionHeight() );
		}
		batch.end();

		stage.act( delta );
		stage.draw();
	}
	
	
}
