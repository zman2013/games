package mt.bag;

import mt.domain.Commodity;
import mt.resources.AbstractResourceLoader;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class BagResourceLoader extends AbstractResourceLoader{
	
	private String bgFilePath = "assets/images/home_images/bg.jpg";
	
	private String detailBgFilePath = "assets/images/commodity/data.dat_000456.png";
	
	private String fontFilePath = "assets/font/font.fnt";
	
	private String cellFilePath = "assets/images/skills/icon/0.png";
	
	private String buttonFilePath = "assets/images/shared/data.dat_000454.png";
	private String activeButtonFilePath = "assets/images/shared/data.dat_000455.png";
	
	public BagResourceLoader(){ init(); }

	@Override
	protected ObjectMap<String, Class<?>> initResourceMap() {
		ObjectMap<String, Class<?>> resourceMap = new ObjectMap<String, Class<?>>();
		resourceMap.put( bgFilePath, Texture.class );
		resourceMap.put( fontFilePath, BitmapFont.class );
		resourceMap.put( cellFilePath, Texture.class );
		resourceMap.put( detailBgFilePath, Texture.class );
		resourceMap.put( buttonFilePath, Texture.class );
		resourceMap.put( activeButtonFilePath, Texture.class );
		
		return resourceMap;
	}
	
	public void loadCommodityResource( Array<Commodity> commodities ){
		ObjectMap<String, Class<?>> resourceMap = new ObjectMap<String, Class<?>>();
		for( Commodity commodity : commodities ){
			resourceMap.put( commodity.getIconFilePath(), Texture.class );
		}
		loadResource( resourceMap );
	}
	
	public Drawable getBgDrawable(){ return getDrawable( bgFilePath ); }
	public Drawable getDetailBgDrawable(){ return getDrawable( detailBgFilePath ); }
	public BitmapFont getFont(){ return getFont( fontFilePath ); }
	public Texture getCellTexture(){ return getTexture( cellFilePath ); }
	public Drawable getButtonDrawable(){ return getDrawable( buttonFilePath ); }
	public Drawable getActiveButtonDrawable(){ return getDrawable( activeButtonFilePath ); }

}
