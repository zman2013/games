package mt.fighterlist;

import mt.actors.domain.FighterInfo;
import mt.resources.AbstractResourceLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
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

	private String heroFilePath = "assets/data/player/hero.data";
	private String candidateFilePath = "assets/data/player/candidate.data";
	
	private Array<FighterInfo> fighterInfos;
	
	public FighterListResourceLoader(){ init(); }
	
	@SuppressWarnings("rawtypes")
	@Override
	protected ObjectMap<String, Class> initResourceMap() {
		fighterInfos = loadHeroInfos( heroFilePath, candidateFilePath );
		
		ObjectMap<String, Class> resourceFilePathSet = new ObjectMap<String, Class>();
		resourceFilePathSet.put( bgFilePath, Texture.class );
		resourceFilePathSet.put( headerFilePath, Texture.class );
		resourceFilePathSet.put( defaultBarFilePath, Texture.class );
		resourceFilePathSet.put( activeBarFilePath, Texture.class );
		resourceFilePathSet.put( checkBoxFilePath, Texture.class );
		resourceFilePathSet.put( checkedBoxFilePath, Texture.class );
		resourceFilePathSet.put( borderBgFilePath, Texture.class );
		
		for( FighterInfo info : fighterInfos ){
			resourceFilePathSet.put( info.getSmallBorderFilePath(), Texture.class );
			resourceFilePathSet.put( info.getSmallFighterFilePath(), Texture.class );
		}
		
		return resourceFilePathSet;
	}

	@SuppressWarnings("unchecked")
	private Array<FighterInfo> loadHeroInfos(String heroFilePath, String candidateFilePath) {
		Json json = new Json();
		//加载出征战宠的信息
		Array<FighterInfo> heroInfos = json.fromJson( Array.class, FighterInfo.class, Gdx.files.internal( heroFilePath ) );
		//加载未出征英雄的信息
		Array<FighterInfo> candidateInfos =  json.fromJson( Array.class, FighterInfo.class, Gdx.files.internal( candidateFilePath ) );
		heroInfos.addAll( candidateInfos );
		return heroInfos;
	}

	public Array<FighterInfo> getHeroInfos() { return fighterInfos; }
	public Drawable getHeaderDrawable(){ return getDrawable(headerFilePath); }
	public Drawable getDefaultBarDrawable(){ return getDrawable(defaultBarFilePath); }
	public Drawable getActiveBarDrawable(){ return getDrawable(activeBarFilePath); }
	public Drawable getCheckBoxDrawable(){ return getDrawable(checkBoxFilePath); }
	public Drawable getCheckedBoxDrawable(){ return getDrawable(checkedBoxFilePath); }
	public Drawable getBgDrawable(){ return getDrawable(bgFilePath); }
	
	public TextureRegion getBorderBgTextureRegion() {
		return getTextureRegion( borderBgFilePath );
	}
	
}
