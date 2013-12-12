package mt.deprecated;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * This class will be deprecated. Use ResouceUtil instead.
 * @author zman
 *
 */
public class ResourcesLoader {
	
	//header box
	private static Texture headerBoxTexture;
	private static TextureRegion headerBoxTextureRegion;
	private static TextureRegionDrawable headerBoxDrawable;

	//team box
	private static Texture teamBoxTexture;
	private static TextureRegion teamBoxTextureRegion;
	private static TextureRegionDrawable teamBoxDrawable;
	
	//menu box
	private static Texture menuBoxTexture;
	private static TextureRegion menuBoxTextureRegion;
	private static TextureRegionDrawable menuBoxDrawable;
	
	//header button
	private static Texture homeButtonTexture;
	private static TextureRegion homeButtonTextureRegion;
	private static TextureRegionDrawable homeButtonDrawable;
	private static Texture homeButtonActiveTexture;
	private static TextureRegion homeButtonActiveTextureRegion;
	private static TextureRegionDrawable homeButtonActiveDrawable;
	
	public static TextureRegionDrawable getHeaderBoxDrawable(){
		headerBoxTexture = new Texture( Gdx.files.internal("assets/images/home_images/header_box.png") );
		headerBoxTextureRegion = new TextureRegion( headerBoxTexture );
		headerBoxDrawable = new TextureRegionDrawable( headerBoxTextureRegion );
		
		return headerBoxDrawable;
	}
	
	public static TextureRegionDrawable getTeamBoxDrawable(){
		teamBoxTexture = new Texture( Gdx.files.internal("assets/images/home_images/team_box.png") );
		teamBoxTextureRegion = new TextureRegion( teamBoxTexture );
		teamBoxDrawable = new TextureRegionDrawable( teamBoxTextureRegion );
		
		return teamBoxDrawable;
	}
	
	public static TextureRegionDrawable getHomeButtonDrawable(){
		homeButtonTexture = new Texture( Gdx.files.internal("assets/images/home_images/home_button.png") );
		homeButtonTextureRegion = new TextureRegion( homeButtonTexture );
		homeButtonDrawable = new TextureRegionDrawable( homeButtonTextureRegion );
		
		return homeButtonDrawable;
	}
	
	public static TextureRegionDrawable getHomeButtonActiveDrawable(){
		homeButtonActiveTexture = new Texture( Gdx.files.internal("assets/images/home_images/home_button_active.png") );
		homeButtonActiveTextureRegion = new TextureRegion( homeButtonActiveTexture );
		homeButtonActiveDrawable = new TextureRegionDrawable( homeButtonActiveTextureRegion );
		
		return homeButtonActiveDrawable;
	}
	
	public static TextureRegionDrawable getMenuButtonDrawable(){
		menuBoxTexture = new Texture( Gdx.files.internal("assets/images/home_images/menu_box.png") );
		menuBoxTextureRegion = new TextureRegion( menuBoxTexture );
		menuBoxDrawable = new TextureRegionDrawable( menuBoxTextureRegion );
		
		return menuBoxDrawable;
	}
	
	//bg
	private static Texture bgTexture;
	private static TextureRegion bgTextureRegion;
	private static TextureRegionDrawable bgDrawable;
	
	public static TextureRegionDrawable getBgDrawable(){
		bgTexture = new Texture( Gdx.files.internal("assets/images/home_images/bg.jpg") );
		bgTextureRegion = new TextureRegion( bgTexture );
		bgDrawable = new TextureRegionDrawable( bgTextureRegion );
		
		return bgDrawable;
	}
	
	//level_1
	private static Texture level1Texture;
	private static TextureRegion level1TextureRegion;
	private static TextureRegionDrawable level1Drawable;
	
	public static TextureRegionDrawable getLevel1Drawable(){
		level1Texture = new Texture( Gdx.files.internal("assets/images/people_level/level_1.png") );
		level1TextureRegion = new TextureRegion( level1Texture );
		level1Drawable = new TextureRegionDrawable( level1TextureRegion );
		
		return level1Drawable;
	}
	
	//expbar_1_1
	private static Texture expBar11Texture;
	private static TextureRegion expBar11TextureRegion;
	private static TextureRegionDrawable expBar11Drawable;
	
	public static TextureRegionDrawable getExpBar11Drawable(){
		expBar11Texture = new Texture( Gdx.files.internal("assets/images/experience_bar/data.dat_000059.png") );
		expBar11TextureRegion = new TextureRegion( expBar11Texture );
		expBar11Drawable = new TextureRegionDrawable( expBar11TextureRegion );
		
		return expBar11Drawable;
	}
	
	//expbar_1_2
	private static Texture expBar12Texture;
	private static TextureRegion expBar12TextureRegion;
	private static TextureRegionDrawable expBar12Drawable;
	
	public static TextureRegionDrawable getExpBar12Drawable(){
		expBar12Texture = new Texture( Gdx.files.internal("assets/images/experience_bar/data.dat_000060.png") );
		expBar12TextureRegion = new TextureRegion( expBar12Texture );
		expBar12Drawable = new TextureRegionDrawable( expBar12TextureRegion );
		
		return expBar12Drawable;
	}
	
	//expbar_2_1
	private static Texture expBar21Texture;
	private static TextureRegion expBar21TextureRegion;
	private static TextureRegionDrawable expBar21Drawable;
	
	public static TextureRegionDrawable getExpBar21Drawable(){
		expBar21Texture = new Texture( Gdx.files.internal("assets/images/experience_bar/data.dat_000048.png") );
		expBar21TextureRegion = new TextureRegion( expBar21Texture );
		expBar21Drawable = new TextureRegionDrawable( expBar21TextureRegion );
		
		return expBar21Drawable;
	}
	
	//expbar_2_2
	private static Texture expBar22Texture;
	private static TextureRegion expBar22TextureRegion;
	private static TextureRegionDrawable expBar22Drawable;
	
	public static TextureRegionDrawable getExpBar22Drawable(){
		expBar22Texture = new Texture( Gdx.files.internal("assets/images/experience_bar/data.dat_000049.png") );
		expBar22TextureRegion = new TextureRegion( expBar22Texture );
		expBar22Drawable = new TextureRegionDrawable( expBar22TextureRegion );
		
		return expBar22Drawable;
	}
	
	//leader_icon
	private static Texture leaderIconTexture;
	private static TextureRegion leaderIconTextureRegion;
	private static TextureRegionDrawable leaderIconDrawable;
	public static TextureRegionDrawable getLeaderIconDrawable(){
		leaderIconTexture = new Texture( Gdx.files.internal("assets/images/team_images/leader_icon.png") );
		leaderIconTextureRegion = new TextureRegion( leaderIconTexture );
		leaderIconDrawable = new TextureRegionDrawable( leaderIconTextureRegion );
		
		return leaderIconDrawable;
	}
	
	//add_icon
	private static Texture addIconTexture;
	private static TextureRegion addIconTextureRegion;
	private static TextureRegionDrawable addIconDrawable;
	public static TextureRegionDrawable getAddIconDrawable(){
		addIconTexture = new Texture( Gdx.files.internal("assets/images/team_images/add_icon.png") );
		addIconTextureRegion = new TextureRegion( addIconTexture );
		addIconDrawable = new TextureRegionDrawable( addIconTextureRegion );
		
		return addIconDrawable;
	}
	
	//hero icon
	private static Texture heroIconTexture;
	private static TextureRegion heroIconTextureRegion;
	private static TextureRegionDrawable heroIconDrawable;
	private static IntMap<TextureRegionDrawable> heroIconCache = new IntMap<TextureRegionDrawable>();
	public static TextureRegionDrawable getHeroIconDrawable( int index ){
		if( heroIconCache.containsKey(index) ){
			return heroIconCache.get( index );
		}
		heroIconTexture = new Texture( Gdx.files.internal("assets/images/fighter/data.dat_000"+index+".png") );
		heroIconTextureRegion = new TextureRegion( heroIconTexture );
		heroIconDrawable = new TextureRegionDrawable( heroIconTextureRegion );
		heroIconCache.put( index, heroIconDrawable );
		return heroIconDrawable;
	}
	
	//border icon
	private static Texture borderIconTexture;
	private static TextureRegion borderIconTextureRegion;
	private static TextureRegionDrawable borderIconDrawable;
	private static IntMap<TextureRegionDrawable> borderIconCache = new IntMap<TextureRegionDrawable>();
	public static TextureRegionDrawable getBorderIconDrawable( int index ){
		if( borderIconCache.containsKey( index ) ){
			return borderIconCache.get( index );
		}
		borderIconTexture = new Texture( Gdx.files.internal("assets/images/team_images/border_"+index+".png") );
		borderIconTextureRegion = new TextureRegion( borderIconTexture );
		borderIconDrawable = new TextureRegionDrawable( borderIconTextureRegion );
		borderIconCache.put( index, borderIconDrawable );
		return borderIconDrawable;
	}
	
	//menu button image
	private static Texture menuButtonTexture;
	private static TextureRegion menuButtonTextureRegion;
	private static TextureRegionDrawable menuButtonDrawable;
	private static ObjectMap<String,TextureRegionDrawable> menuButtonCache = new ObjectMap<String, TextureRegionDrawable>();
	public static TextureRegionDrawable getMenuButtonDrawable( String filename ){
		if( menuButtonCache.containsKey( filename ) ){
			return menuButtonCache.get( filename );
		}
		menuButtonTexture = new Texture( Gdx.files.internal("assets/images/menu_button_images/"+filename+".png") );
		menuButtonTextureRegion = new TextureRegion( menuButtonTexture );
		menuButtonDrawable = new TextureRegionDrawable( menuButtonTextureRegion );
		menuButtonCache.put( filename, menuButtonDrawable );
		return menuButtonDrawable;
	}
	
	//resource generic getter
	private static Texture resourceTexture;
	private static TextureRegion resourceTextureRegion;
	private static TextureRegionDrawable resourceDrawable;
	private static ObjectMap<String,TextureRegionDrawable> resourceCache = new ObjectMap<String, TextureRegionDrawable>();
	public static TextureRegionDrawable getDrawable( String filepath ){
		if( resourceCache.containsKey( filepath ) ){
			return resourceCache.get( filepath );
		}
		resourceTexture = new Texture( Gdx.files.internal( filepath ) );
		resourceTextureRegion = new TextureRegion( resourceTexture );
		resourceDrawable = new TextureRegionDrawable( resourceTextureRegion );
		menuButtonCache.put( filepath, resourceDrawable );
		return resourceDrawable;
	}
	
	//resource generic getter
		private static Texture newResourceTexture;
		private static TextureRegion newResourceTextureRegion;
		private static TextureRegionDrawable newResourceDrawable;
		public static TextureRegionDrawable newDrawable( String filepath ){
			newResourceTexture = new Texture( Gdx.files.internal( filepath ) );
			newResourceTextureRegion = new TextureRegion( newResourceTexture );
			newResourceDrawable = new TextureRegionDrawable( newResourceTextureRegion );
			return newResourceDrawable;
		}
}
