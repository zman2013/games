package mt.fighterlist;

import mt.domain.FighterInfo;
import mt.domain.FighterStatus;
import mt.util.PropertyCalculator;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class FighterBar extends Image{
	
	private Drawable defaultBarDrawable;
	private Drawable activeBarDrawable;
	private TextureRegion borderBgRegion;
	private TextureRegion borderRegion;
	private TextureRegion fighterRegion;
	private Drawable checkBoxDrawable;
	private Drawable uncheckedBoxDrawable;
	private Drawable checkedBoxDrawable;
	private BitmapFont font;
	
	private FighterInfo fighterInfo;
	private FighterInfo displayFighterInfo;
	private boolean isFighter;
	private FighterListDataAccessor dataAccessor;
	
	
	/**
	 * 
	 * @param fighterInfo
	 * @param resourceLoader
	 * @param isFighter: true: 出征，false：未出征
	 */
	public FighterBar( FighterInfo fighterInfo, FighterListDataAccessor dataAccessor, FighterListResourceLoader resourceLoader, boolean isFighter ){
		this.fighterInfo = fighterInfo;
		this.displayFighterInfo = fighterInfo.clone();
		PropertyCalculator.calculate( displayFighterInfo );
		this.isFighter = isFighter;
		this.dataAccessor = dataAccessor;
		
		defaultBarDrawable = resourceLoader.getDefaultBarDrawable();
		activeBarDrawable = resourceLoader.getActiveBarDrawable();
		uncheckedBoxDrawable= resourceLoader.getCheckBoxDrawable();
		checkedBoxDrawable = resourceLoader.getCheckedBoxDrawable();
		borderBgRegion = resourceLoader.getBorderBgTextureRegion();
		borderRegion = resourceLoader.getTextureRegion( fighterInfo.getSmallBorderFilePath() );
		fighterRegion = resourceLoader.getTextureRegion( fighterInfo.getSmallFighterFilePath() );
		
		font = resourceLoader.getFont();
		
		setDrawable( defaultBarDrawable );
		
		if( isFighter ){
			checkBoxDrawable = checkedBoxDrawable;
		}else{
			checkBoxDrawable = uncheckedBoxDrawable;
		}
		
		initListener();
	}

	private Vector2 fighterOffset = new Vector2( -10, 0 );
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
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
		
		checkBoxDrawable.draw( batch, x+400, y+20, checkBoxDrawable.getMinWidth(), checkBoxDrawable.getMinHeight() );
		font.draw( batch, String.valueOf(displayFighterInfo.getLevel()), x+90, y+63 );
		font.draw( batch, displayFighterInfo.getName(), x+115, y+63 );
		font.draw( batch, String.valueOf(displayFighterInfo.getHp()), x+115, y+31 );
		font.draw( batch, displayFighterInfo.getMinMeleeAttack()+" ~ "+displayFighterInfo.getMaxMeleeAttack(), x+215, y+31 );
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
				setDrawable( defaultBarDrawable );
				
				if( x>=400 & x<=440 & y>=20 & y<=60 ){
					FighterStatus fighterStatus = dataAccessor.getFighterStatus();
					isFighter = !isFighter;
					if( isFighter ){
						//出征战宠不可多余5个
						if( dataAccessor.getFighterInfos().size >= 5 ){
							return;
						}
						checkBoxDrawable = checkedBoxDrawable;
						//从候选战宠列表中移除
						Array<FighterInfo> candidateInfos = dataAccessor.getCandidateInfos();
						for( int i = 0; i < candidateInfos.size; i ++ ){
							FighterInfo info = candidateInfos.get( i );
							if( info.getId() == fighterInfo.getId() ){
								candidateInfos.removeIndex( i );
								dataAccessor.getFighterStatus().getCandidates().removeIndex( i );
								break;
							}
						}
						//添加到出征战宠中
						Array<FighterInfo> fighterInfos = dataAccessor.getFighterInfos();
						fighterInfos.add( fighterInfo );
						ObjectMap<String, Integer> fighterMap = fighterStatus.getFighters();
						for( int i = 0; i < 5; i ++ ){
							if( !fighterMap.containsKey( String.valueOf(i) ) ){
								fighterMap.put( String.valueOf(i), fighterInfo.getId() );
								break;
							}
						}
					}else{
						checkBoxDrawable = uncheckedBoxDrawable;
						//从出征战宠列表中移除
						fighterStatus.getFighters().remove( String.valueOf(fighterInfo.getFormationIndex()) );
						Array<FighterInfo> fighterInfos = dataAccessor.getFighterInfos();
						for( int i = 0; i < fighterInfos.size; i ++ ){
							FighterInfo info = fighterInfos.get( i );
							if( info.getId() == fighterInfo.getId() ){
								fighterInfos.removeIndex( i );
								break;
							}
						}
						//添加到候选战宠中
						fighterStatus.getCandidates().add( fighterInfo.getId() );
						dataAccessor.getCandidateInfos().add( fighterInfo );
					}
					dataAccessor.flushFighterStatus( fighterStatus );
				}
			}
		});
	}

}
