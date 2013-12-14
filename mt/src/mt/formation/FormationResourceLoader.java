package mt.formation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entry;

import mt.domain.FighterInfo;
import mt.domain.FighterStatus;
import mt.resources.AbstractResourceLoader;

public class FormationResourceLoader extends AbstractResourceLoader {

	private String bgFilePath = "assets/images/home_images/bg.jpg";
	private String headerFilePath = "assets/images/list_page/list_header.png";
	//空位图片
	private String fighterPlaceHolderFilePath = "assets/images/border/fighter_place_holder.png";
	private String skillPlaceHolderFilePath = "assets/images/skills/icon/0.png";
	//加号图片
	private String plusFilePath = "assets/images/border/plus.png";
	
	public static String playerFilePath = "assets/data/fighter/0";
	public static String filghterStatusFilePath = "assets/data/player/fighter_status.data";
	
	private String fontFilePath = "assets/font/font.fnt";
	
	private FighterStatus fighterStatus;
	private Array<FighterInfo> fighterInfos;
	/**
	 * 主角信息
	 */
	private FighterInfo playerInfo;
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
		
		fighterInfos = loadFighterInfos( filghterStatusFilePath );
		for( FighterInfo info : fighterInfos ){
			resourceMap.put( info.getBorderFilePath(), Texture.class );
			resourceMap.put( info.getFighterFilePath(), Texture.class );
		}
		
		skillInfos = loadSkillInfos( playerFilePath );
		for( SkillInfo info : skillInfos ){
			resourceMap.put( info.getIconFilePath(), Texture.class );
		}
		
		return resourceMap;
	}

	private Json json = new Json();
	private Array<SkillInfo> loadSkillInfos(String playerFilePath) {
		playerInfo = json.fromJson( FighterInfo.class, Gdx.files.internal( playerFilePath ) );
		return playerInfo.getSkillInfos();
	}

	private Array<FighterInfo> loadFighterInfos(String fighterStatusFilePath) {
		Array<FighterInfo> infos = new Array<FighterInfo>(5);
		fighterStatus = json.fromJson( FighterStatus.class, Gdx.files.internal( fighterStatusFilePath ) );
		//加载出征战宠的信息
		for( Entry<String, Integer> entry : fighterStatus.getFighters().entries() ){
			FighterInfo fighterInfo = json.fromJson( FighterInfo.class, Gdx.files.internal( "assets/data/fighter/"+entry.value ) );
			fighterInfo.setFormationIndex( Integer.parseInt(entry.key) );
			infos.add( fighterInfo );
		}
		return infos;
	}

	public Array<FighterInfo> getFighterInfos() { return fighterInfos; }
	public Array<SkillInfo> getSkillInfos(){ return skillInfos; }
	public Drawable getBgDrawable(){ return getDrawable(bgFilePath); }
	public TextureRegion getHeaderRegion(){ return getTextureRegion(headerFilePath); }
	public TextureRegion getFighterPlaceHolderRegion(){ return getTextureRegion(fighterPlaceHolderFilePath); }
	public TextureRegion getSkillPlaceHolderRegion(){ return getTextureRegion(skillPlaceHolderFilePath); }
	public Drawable getPlusDrawable(){ return getDrawable(plusFilePath); }
	public BitmapFont getFont(){ return getFont(fontFilePath); }

	public FighterStatus getFighterStatus() {
		return fighterStatus;
	}

	public FighterInfo getPlayerInfo() {
		return playerInfo;
	}
	
}
