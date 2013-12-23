package mt.fighterlist;

import mt.domain.FighterInfo;
import mt.resources.AbstractResourceLoader;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class FighterListResourceLoader extends AbstractResourceLoader{
	
	private String bgFilePath = "assets/images/home_images/bg.jpg";
	private String headerFilePath = "assets/images/list_page/list_header.png";
	private String defaultBarFilePath = "assets/images/list_page/hero_bar.png";
	private String activeBarFilePath = "assets/images/list_page/hero_bar_active.png";
	private String borderBgFilePath = "assets/images/border/100.png";
	//复选框
	private String checkBoxFilePath = "assets/images/list_page/check_box.png";
	//激活的复选框（打上勾的复选框）
	private String checkedBoxFilePath = "assets/images/list_page/box_checked.png";

	private String fontFilePath = "assets/font/font.fnt";
	
	public FighterListResourceLoader(){ 
		init(); 
	}
	
	@Override
	protected ObjectMap<String, Class<?>> initResourceMap() {
		ObjectMap<String, Class<?>> resourceFilePathMap = new ObjectMap<String, Class<?>>();
		resourceFilePathMap.put( bgFilePath, Texture.class );
		resourceFilePathMap.put( headerFilePath, Texture.class );
		resourceFilePathMap.put( defaultBarFilePath, Texture.class );
		resourceFilePathMap.put( activeBarFilePath, Texture.class );
		resourceFilePathMap.put( checkBoxFilePath, Texture.class );
		resourceFilePathMap.put( checkedBoxFilePath, Texture.class );
		resourceFilePathMap.put( borderBgFilePath, Texture.class );
		resourceFilePathMap.put( fontFilePath, BitmapFont.class );
		
		return resourceFilePathMap;
	}
	
	public void loadFighterInfos( Array<FighterInfo> fighterInfos ){
		ObjectMap<String, Class<?>> resourceFilePathMap = new ObjectMap<String, Class<?>>();
		for( FighterInfo info : fighterInfos ){
			resourceFilePathMap.put( info.getSmallBorderFilePath(), Texture.class );
			resourceFilePathMap.put( info.getSmallFighterFilePath(), Texture.class );
		}
		loadResource( resourceFilePathMap );
	}

	public Drawable getHeaderDrawable(){ return getDrawable(headerFilePath); }
	public Drawable getDefaultBarDrawable(){ return getDrawable(defaultBarFilePath); }
	public Drawable getActiveBarDrawable(){ return getDrawable(activeBarFilePath); }
	public Drawable getCheckBoxDrawable(){ return getDrawable(checkBoxFilePath); }
	public Drawable getCheckedBoxDrawable(){ return getDrawable(checkedBoxFilePath); }
	public Drawable getBgDrawable(){ return getDrawable(bgFilePath); }
	public BitmapFont getFont(){ return getFont(fontFilePath); }
	
	public TextureRegion getBorderBgTextureRegion() {
		return getTextureRegion( borderBgFilePath );
	}
	
}
