package mt.formation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;

import mt.actors.domain.FighterInfo;
import mt.resources.AbstractResourceLoader;

public class FormationResourceLoader extends AbstractResourceLoader {

	private String bgFilePath = "assets/images/home_images/bg.jpg";
	private String headerFilePath = "assets/images/list_page/list_header.png";
	//空位图片
	private String fighterPlaceHolderFilePath = "assets/images/border/fighter_place_holder.png";
	private String skillPlaceHolderFilePath = "assets/images/skills/icon/0.png";
	//加号图片
	private String plusFilePath = "assets/images/border/plus.png";
	
	private String fighterFilePath = "assets/data/player/hero.data";
	private String skillFilePath = "assets/data/player/skill.data";
	
	private String fontFilePath = "assets/font/font.fnt";
	
	private Array<FighterInfo> fighterInfos;
	private Array<SkillInfo> skillInfos;
	
	public FormationResourceLoader(){ init(); }
	
	@Override
	protected ObjectMap<String, Class<?>> initResourceMap() {
		ObjectMap<String, Class<?>> resourceMap = new ObjectMap<String, Class<?>>();
		resourceMap.put( bgFilePath, Texture.class );
		resourceMap.put( headerFilePath, Texture.class );
		resourceMap.put( fighterPlaceHolderFilePath, Texture.class );
		resourceMap.put( skillPlaceHolderFilePath, Texture.class );
		resourceMap.put( plusFilePath, Texture.class );
		resourceMap.put( fontFilePath, BitmapFont.class );
		
		fighterInfos = loadFighterInfos( fighterFilePath );
		for( FighterInfo info : fighterInfos ){
			resourceMap.put( info.getBorderFilePath(), Texture.class );
			resourceMap.put( info.getFighterFilePath(), Texture.class );
		}
		
		skillInfos = loadSkillInfos( skillFilePath );
		for( SkillInfo info : skillInfos ){
			resourceMap.put( info.getIconFilePath(), Texture.class );
		}
		
		return resourceMap;
	}

	private Json json = new Json();
	@SuppressWarnings("unchecked")
	private Array<SkillInfo> loadSkillInfos(String skillFilePath) {
		//加载装备技能的信息
		return json.fromJson( Array.class, SkillInfo.class, Gdx.files.internal( skillFilePath ) );
	}

	@SuppressWarnings("unchecked")
	private Array<FighterInfo> loadFighterInfos(String fighterFilePath) {
		//加载出征战宠的信息
		return json.fromJson( Array.class, FighterInfo.class, Gdx.files.internal( fighterFilePath ) );
	}

	public Array<FighterInfo> getFighterInfos() { return fighterInfos; }
	public Array<SkillInfo> getSkillInfos(){ return skillInfos; }
	public Drawable getBgDrawable(){ return getDrawable(bgFilePath); }
	public TextureRegion getHeaderRegion(){ return getTextureRegion(headerFilePath); }
	public TextureRegion getFighterPlaceHolderRegion(){ return getTextureRegion(fighterPlaceHolderFilePath); }
	public TextureRegion getSkillPlaceHolderRegion(){ return getTextureRegion(skillPlaceHolderFilePath); }
	public Drawable getPlusDrawable(){ return getDrawable(plusFilePath); }
	public BitmapFont getFont(){ return getFont(fontFilePath); }
}
