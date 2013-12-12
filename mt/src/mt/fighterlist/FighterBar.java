package mt.fighterlist;

import mt.actors.domain.FighterInfo;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class FighterBar extends Image{
	
//	private FighterInfo fighterInfo;
	
	private Drawable defaultBarDrawable;
	private Drawable activeBarDrawable;
	private TextureRegion borderBgRegion;
	private TextureRegion borderRegion;
	private TextureRegion fighterRegion;
//	private Drawable checkBoxDrawable;
//	private Drawable checkedBoxDrawable;
	
	public FighterBar( FighterInfo fighterInfo, FighterListResourceLoader resourceLoader ){
//		this.fighterInfo = fighterInfo;
		
		defaultBarDrawable = resourceLoader.getDefaultBarDrawable();
		activeBarDrawable = resourceLoader.getActiveBarDrawable();
//		checkBoxDrawable= resourceLoader.getCheckBoxDrawable();
//		checkedBoxDrawable = resourceLoader.getCheckedBoxDrawable();
		borderBgRegion = resourceLoader.getBorderBgTextureRegion();
		borderRegion = resourceLoader.getTextureRegion( fighterInfo.getSmallBorderFilePath() );
		fighterRegion = resourceLoader.getTextureRegion( fighterInfo.getSmallFighterFilePath() );
		
		setDrawable( defaultBarDrawable );
		
		initListener();
	}

	private Vector2 fighterOffset = new Vector2( -10, 0 );
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
//		float scaleX = getScaleX();
//		float scaleY = getScaleY();
		float scaleX = 0.8f;
		float scaleY = scaleX;
		float x = getX() + fighterOffset.x*scaleX;
		float y = getY();
		float rotation = getRotation();
		
		if (scaleX == 1 && scaleY == 1 && rotation == 0){
			batch.draw( borderBgRegion, x, y, borderBgRegion.getRegionWidth(), borderBgRegion.getRegionHeight() );
			batch.draw( fighterRegion, x, y, fighterRegion.getRegionWidth(), fighterRegion.getRegionHeight() );
			batch.draw( borderRegion, x, y, borderRegion.getRegionWidth(), borderRegion.getRegionHeight() );
		}else {
			batch.draw( borderBgRegion, x, y, 0, 0, borderBgRegion.getRegionWidth(), borderBgRegion.getRegionHeight(), scaleX, scaleY, rotation );
			batch.draw( fighterRegion, x, y, 0, 0, fighterRegion.getRegionWidth(), fighterRegion.getRegionHeight(), scaleX, scaleY, rotation );
			batch.draw( borderRegion, x, y, 0, 0, borderRegion.getRegionWidth(), borderRegion.getRegionHeight(), scaleX, scaleY, rotation );
		}
	}
	
	private void initListener() {
		addListener( new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				setDrawable( activeBarDrawable );
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
//				if( x>=500 & x<=540 & y>=30 & y<=70 ){
//					checked = !checked;
//				}
				setDrawable( defaultBarDrawable );
			}
		});
	}

}
