package mt.fighterlist;

import mt.domain.FighterInfo;
import mt.domain.FighterStatus;
import mt.resources.AbstractResourceLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.IntMap.Entry;

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

	private String fighterStatusFilePath = "assets/data/player/fighter_status.data";
	
	private String fontFilePath = "assets/font/font.fnt";
	
	private Array<FighterInfo> fighterInfos;
	
	public FighterListResourceLoader(){ init(); }
	
	@Override
	protected ObjectMap<String, Class<?>> initResourceMap() {
		fighterInfos = loadHeroInfos( fighterStatusFilePath );
		
		ObjectMap<String, Class<?>> resourceFilePathMap = new ObjectMap<String, Class<?>>();
		resourceFilePathMap.put( bgFilePath, Texture.class );
		resourceFilePathMap.put( headerFilePath, Texture.class );
		resourceFilePathMap.put( defaultBarFilePath, Texture.class );
		resourceFilePathMap.put( activeBarFilePath, Texture.class );
		resourceFilePathMap.put( checkBoxFilePath, Texture.class );
		resourceFilePathMap.put( checkedBoxFilePath, Texture.class );
		resourceFilePathMap.put( borderBgFilePath, Texture.class );
		resourceFilePathMap.put( fontFilePath, BitmapFont.class );
		
		for( FighterInfo info : fighterInfos ){
			resourceFilePathMap.put( info.getSmallBorderFilePath(), Texture.class );
			resourceFilePathMap.put( info.getSmallFighterFilePath(), Texture.class );
		}
		
		return resourceFilePathMap;
	}

	private Array<FighterInfo> loadHeroInfos(String fighterStatusFilePath) {
		Array<FighterInfo> infos = new Array<FighterInfo>(5);
		Json json = new Json();
		FighterStatus fighterStatus = json.fromJson( FighterStatus.class, Gdx.files.internal( fighterStatusFilePath ) );
		//加载出征战宠的信息
		for( Entry<Integer> entry : fighterStatus.getFighters().entries() ){
			FighterInfo fighterInfo = json.fromJson( FighterInfo.class, Gdx.files.internal( "assets/data/fighter/"+entry.value ) );
			fighterInfo.setFormationIndex( entry.key );
			infos.add( fighterInfo );
		}
		//加载未出征的战宠信息
		for( Integer id : fighterStatus.getCandidates() ){
			FighterInfo fighterInfo = json.fromJson( FighterInfo.class, Gdx.files.internal( "assets/data/fighter/"+id ) );
			fighterInfo.setFormationIndex( -1 );
			infos.add( fighterInfo );
		}
		return infos;
	}

	public Array<FighterInfo> getHeroInfos() { return fighterInfos; }
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
