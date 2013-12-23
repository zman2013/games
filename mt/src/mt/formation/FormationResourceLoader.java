package mt.formation;

import mt.domain.FighterInfo;
import mt.resources.AbstractResourceLoader;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

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
		
		return resourceMap;
	}
	
	public void loadFighterResource( Array<FighterInfo> fighterInfos, Array<SkillInfo> skillInfos ){
		ObjectMap<String, Class<?>> resourceMap = new ObjectMap<String, Class<?>>();
		for( FighterInfo info : fighterInfos ){
			resourceMap.put( info.getBorderFilePath(), Texture.class );
			resourceMap.put( info.getFighterFilePath(), Texture.class );
		}
		
		for( SkillInfo info : skillInfos ){
			resourceMap.put( info.getIconFilePath(), Texture.class );
		}
		
		loadResource( resourceMap );
	}

	public Drawable getBgDrawable(){ return getDrawable(bgFilePath); }
	public TextureRegion getHeaderRegion(){ return getTextureRegion(headerFilePath); }
	public TextureRegion getFighterPlaceHolderRegion(){ return getTextureRegion(fighterPlaceHolderFilePath); }
	public TextureRegion getSkillPlaceHolderRegion(){ return getTextureRegion(skillPlaceHolderFilePath); }
	public Drawable getPlusDrawable(){ return getDrawable(plusFilePath); }
	public BitmapFont getFont(){ return getFont(fontFilePath); }

}
