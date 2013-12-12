package mt.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

import mt.actor.shared.ReturnActor;
import mt.actors.domain.FighterInfo;
import mt.formation.HeroActor;
import mt.formation.SkillActor;
import mt.formation.SkillInfo;
import mt.property.PropertyResourceLoader;
import mt.property.PropertySkillManager;

public class PropertyScreen extends AbstractScreen{

	private Drawable bgDrawable;
	
	private TextureRegion skillPlaceHolderRegion;
	
	private BitmapFont font;
	
	private FighterInfo fighterInfo;
	
	private Array<Vector2> skillCoors;
	
	public PropertyScreen(){
		super();
		//test begin
		fighterInfo = new FighterInfo();
		fighterInfo.setBorderFilePath( "assets/images/border/5.png" );
		fighterInfo.setFighterFilePath( "assets/images/fighter/156.png" );
		stage.addListener( new ClickListener(){
			public void clicked(InputEvent event, float x2, float y2) {
			}
		});
		//test end
		
		PropertyResourceLoader loader = new PropertyResourceLoader( fighterInfo );
		bgDrawable = loader.getBgDrawable();
		font = loader.getFont();
		skillPlaceHolderRegion = loader.getSkillPlaceHolderRegion();
		
		HeroActor fighter = new HeroActor( fighterInfo, loader, null );
		fighter.setPosition( 50, 590 );
		stage.addActor( fighter );
		
		ReturnActor returnActor = new ReturnActor( loader.getReturnDrawable(), stage.getWidth(), this, HomeScreen.class );
		stage.addActor( returnActor );
		
		PropertySkillManager skillManager = new PropertySkillManager();
		//add skills
		skillCoors = skillManager.getCoordinates();
		Array<SkillInfo> skillInfos = fighterInfo.getSkillInfos();
		for( SkillInfo info : skillInfos ){
			SkillActor skill = new SkillActor( info, loader, skillManager);
		}
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
		//装备上
		batch.draw( skillPlaceHolderRegion, 50, 200, skillPlaceHolderRegion.getRegionWidth(), skillPlaceHolderRegion.getRegionHeight() );
		batch.draw( skillPlaceHolderRegion, 150, 200, skillPlaceHolderRegion.getRegionWidth(), skillPlaceHolderRegion.getRegionHeight() );
		batch.draw( skillPlaceHolderRegion, 250, 200, skillPlaceHolderRegion.getRegionWidth(), skillPlaceHolderRegion.getRegionHeight() );
		batch.draw( skillPlaceHolderRegion, 350, 200, skillPlaceHolderRegion.getRegionWidth(), skillPlaceHolderRegion.getRegionHeight() );
		//装备下
		batch.draw( skillPlaceHolderRegion, 50, 100, skillPlaceHolderRegion.getRegionWidth(), skillPlaceHolderRegion.getRegionHeight() );
		batch.draw( skillPlaceHolderRegion, 150, 100, skillPlaceHolderRegion.getRegionWidth(), skillPlaceHolderRegion.getRegionHeight() );
		batch.draw( skillPlaceHolderRegion, 250, 100, skillPlaceHolderRegion.getRegionWidth(), skillPlaceHolderRegion.getRegionHeight() );
		batch.draw( skillPlaceHolderRegion, 350, 100, skillPlaceHolderRegion.getRegionWidth(), skillPlaceHolderRegion.getRegionHeight() );
		batch.end();

		stage.act( delta );
		stage.draw();
	}
	
	
}
