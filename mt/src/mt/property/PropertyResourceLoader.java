package mt.property;

import mt.domain.Commodity;
import mt.domain.FighterInfo;
import mt.formation.SkillInfo;
import mt.resources.AbstractResourceLoader;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.ObjectMap;

public class PropertyResourceLoader extends AbstractResourceLoader{

	private String bgFilePath = "assets/images/home_images/bg.jpg";
	private String detailBgFilePath = "assets/images/commodity/data.dat_000456.png";
	
	private String plusFilePath = "assets/images/border/plus.png";
	
	private String buttonFilePath = "assets/images/shared/data.dat_000454.png";
	private String activeButtonFilePath = "assets/images/shared/data.dat_000455.png";
	
	private String fontFilePath = "assets/font/font.fnt";
	
	private String skillPlaceHolderFilePath = "assets/images/skills/icon/0.png";
	
	private String arrowFilePath = "assets/images/shared/arrow.png";
	
	
	public PropertyResourceLoader(){ 
		init(); 
	}
	
	@Override
	protected ObjectMap<String, Class<?>> initResourceMap() {
		ObjectMap<String, Class<?>> resourceMap = new ObjectMap<String, Class<?>>();
		resourceMap.put( bgFilePath, Texture.class );
		resourceMap.put( fontFilePath, BitmapFont.class );
		resourceMap.put( skillPlaceHolderFilePath, Texture.class );
		resourceMap.put( detailBgFilePath, Texture.class );
		resourceMap.put( buttonFilePath, Texture.class );
		resourceMap.put( activeButtonFilePath, Texture.class );
		resourceMap.put( arrowFilePath, Texture.class );
		resourceMap.put( plusFilePath, Texture.class );
		
		return resourceMap;
	}
	
	public void loadFighterInfo( FighterInfo fighterInfo ){
		ObjectMap<String, Class<?>> resourceMap = new ObjectMap<String, Class<?>>();
		resourceMap.put( fighterInfo.getBorderFilePath(), Texture.class );
		resourceMap.put( fighterInfo.getFighterFilePath(), Texture.class );
		//技能
		for( SkillInfo info : fighterInfo.getSkillInfos() ){
			resourceMap.put( info.getIconFilePath(), Texture.class );
		}
		//装备
		for( Commodity equipment : fighterInfo.getEquipments() ){
			resourceMap.put( equipment.getIconFilePath(), Texture.class );
		}
		loadResource( resourceMap );
	}
	
	public Drawable getBgDrawable(){ return getDrawable( bgFilePath ); }
	public Drawable getDetailBgDrawable(){ return getDrawable( detailBgFilePath ); }
	public BitmapFont getFont(){ return getFont( fontFilePath ); }
	public TextureRegion getSkillPlaceHolderRegion(){ return getTextureRegion(skillPlaceHolderFilePath); }
	public Drawable getButtonDrawable(){ return getDrawable( buttonFilePath ); }
	public Drawable getActiveButtonDrawable(){ return getDrawable( activeButtonFilePath ); }
	public TextureRegion getArrowRegoin(){ return getTextureRegion(arrowFilePath); }
	public Drawable getPlusDrawable(){ return getDrawable(plusFilePath); }
	
}
