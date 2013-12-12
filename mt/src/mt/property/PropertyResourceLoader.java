package mt.property;

import mt.actors.domain.FighterInfo;
import mt.formation.SkillInfo;
import mt.resources.AbstractResourceLoader;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class PropertyResourceLoader extends AbstractResourceLoader{

	private String bgFilePath = "assets/images/home_images/bg.jpg";
	
	private String fontFilePath = "assets/font/font.fnt";
	
	private String skillPlaceHolderFilePath = "assets/images/skills/icon/0.png";
	
	private FighterInfo fighterInfo;
	
	public PropertyResourceLoader(FighterInfo fighterInfo){ 
		this.fighterInfo = fighterInfo;
		init(); 
	}
	
	@Override
	protected ObjectMap<String, Class<?>> initResourceMap() {
		ObjectMap<String, Class<?>> resourceMap = new ObjectMap<String, Class<?>>();
		resourceMap.put( bgFilePath, Texture.class );
		resourceMap.put( fontFilePath, BitmapFont.class );
		resourceMap.put( skillPlaceHolderFilePath, Texture.class );
		
		resourceMap.put( fighterInfo.getBorderFilePath(), Texture.class );
		resourceMap.put( fighterInfo.getFighterFilePath(), Texture.class );
		
		Array<SkillInfo> skillInfos = fighterInfo.getSkillInfos();
		for( SkillInfo info : skillInfos ){
			resourceMap.put( info.getIconFilePath(), Texture.class );
		}
		
		return resourceMap;
	}
	
	public Drawable getBgDrawable(){ return getDrawable( bgFilePath ); }
	public BitmapFont getFont(){ return getFont( fontFilePath ); }
	public TextureRegion getSkillPlaceHolderRegion(){ return getTextureRegion(skillPlaceHolderFilePath); }
}
