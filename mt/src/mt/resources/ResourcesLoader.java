package mt.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

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
}
