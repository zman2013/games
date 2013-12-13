package mt.screens;

import mt.actor.shared.ReturnActor;
import mt.domain.Commodity;
import mt.domain.FighterInfo;
import mt.formation.HeroActor;
import mt.formation.SkillActor;
import mt.formation.SkillInfo;
import mt.property.EquipmentActor;
import mt.property.EquipmentDetailActor;
import mt.property.PropertyEquipmentManager;
import mt.property.PropertyResourceLoader;
import mt.property.PropertySkillManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

public class PropertyScreen extends AbstractScreen{

	private Drawable bgDrawable;
	
	private TextureRegion skillPlaceHolderRegion;
	
	private BitmapFont font;
	
	private FighterInfo fighterInfo;
	
	private Array<Vector2> skillCoors, equipmentCoors;
	
	public PropertyScreen(){
		super();
		fighterInfo = new Json().fromJson(FighterInfo.class, Gdx.files.internal("assets/data/fighter/0") );
		
		PropertyResourceLoader loader = new PropertyResourceLoader( fighterInfo );
		bgDrawable = loader.getBgDrawable();
		font = loader.getFont();
		skillPlaceHolderRegion = loader.getSkillPlaceHolderRegion();
		
		HeroActor fighter = new HeroActor( fighterInfo, loader, null );
		fighter.setPosition( 50, 590 );
		stage.addActor( fighter );
		
		PropertySkillManager skillManager = new PropertySkillManager();
		skillManager.setFighterInfo( fighterInfo );
		//add skills
		skillCoors = skillManager.getCoordinates();
		for( SkillInfo info : fighterInfo.getSkillInfos() ){
			SkillActor skill = new SkillActor( info, loader, skillManager);
			skillManager.add( info.getFormationIndex(), skill );
			stage.addActor( skill );
		}
		//add equipment
		PropertyEquipmentManager equipManager = new PropertyEquipmentManager();
		equipManager.setFighterInfo( fighterInfo );
		equipmentCoors = equipManager.getCoordinates();
		for( Commodity equip : fighterInfo.getEquipments() ){
			EquipmentActor actor = new EquipmentActor( equip, loader, equipManager );
			equipManager.add( equip.getCoordinateIndex(), actor );
			stage.addActor( actor );
		}
		//add detail actor
		EquipmentDetailActor detailActor = new EquipmentDetailActor( loader, equipManager );
		detailActor.setVisible( false );
		equipManager.setDetailActor( detailActor );
		stage.addActor( detailActor );
		
		//return actor
		ReturnActor returnActor = new ReturnActor( loader.getReturnDrawable(), stage.getWidth(), this, HomeScreen.class, skillManager );
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
