package mt.formation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
	private String placeHolderFilePath = "assets/images/border/place_holder.png";
	//加号图片
	private String plusFilePath = "assets/images/border/plus.png";
	
	private String fighterFilePath = "assets/data/player/hero.data";
	
	private Array<FighterInfo> fighterInfos;
	
	public FormationResourceLoader(){ init(); }
	
	@Override
	protected ObjectMap<String, Class<?>> initResourceMap() {
		fighterInfos = loadFighterInfos( fighterFilePath );
		
		ObjectMap<String, Class<?>> resourceMap = new ObjectMap<String, Class<?>>();
		resourceMap.put( bgFilePath, Texture.class );
		resourceMap.put( headerFilePath, Texture.class );
		resourceMap.put( placeHolderFilePath, Texture.class );
		resourceMap.put( plusFilePath, Texture.class );
		
		for( FighterInfo info : fighterInfos ){
			resourceMap.put( info.getBorderFilePath(), Texture.class );
			resourceMap.put( info.getFighterFilePath(), Texture.class );
		}
		
		return resourceMap;
	}

	@SuppressWarnings("unchecked")
	private Array<FighterInfo> loadFighterInfos(String fighterFilePath) {
		Json json = new Json();
		//加载出征战宠的信息
		return json.fromJson( Array.class, FighterInfo.class, Gdx.files.internal( fighterFilePath ) );
	}

	public Array<FighterInfo> getFighterInfos() { return fighterInfos; }
	public Drawable getBgDrawable(){ return getDrawable(bgFilePath); }
	public TextureRegion getHeaderRegion(){ return getTextureRegion(headerFilePath); }
	public TextureRegion getPlaceHolderRegion(){ return getTextureRegion(placeHolderFilePath); }
	public Drawable getPlusDrawable(){ return getDrawable(plusFilePath); }
}
