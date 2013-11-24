package mt.actors;

import mt.resources.FontLoader;
import mt.resources.ResourcesLoader;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class TeamBox extends WidgetGroup{

	private Image teamBox;
	
	private Image leaderIcon;
	
	private WidgetGroup heroContainer1 = new WidgetGroup();
	private WidgetGroup heroContainer2 = new WidgetGroup();
	private WidgetGroup heroContainer3 = new WidgetGroup();
	private WidgetGroup heroContainer4 = new WidgetGroup();
	private WidgetGroup heroContainer5 = new WidgetGroup();
	
	private BitmapFont bitmapFont;
	
	public TeamBox( ){
		teamBox = new Image( ResourcesLoader.getTeamBoxDrawable() );
		leaderIcon = new Image( ResourcesLoader.getLeaderIconDrawable() );
		leaderIcon.scale( 0.001f );	
		leaderIcon.setPosition( 20, 230 );
		
		heroContainer1.setPosition( 45, 112 );
		heroContainer2.setPosition( 184, 112 );
		heroContainer3.setPosition( 290, 112 );
		heroContainer4.setPosition( 396, 112 );
		heroContainer5.setPosition( 502, 112 );
		
		//font
		bitmapFont = FontLoader.getBitmapFont();
		
		addActor( teamBox );
		addActor( leaderIcon );
		addActor( heroContainer1  );
		addActor( heroContainer2  );
		addActor( heroContainer3  );
		addActor( heroContainer4  );
		addActor( heroContainer5  );
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		produceHeroWidget( heroContainer1, 2, 160 );
		produceHeroWidget( heroContainer2, 1, 165 );
		produceHeroWidget( heroContainer3, 1, 165 );
		produceHeroWidget( heroContainer4, null, null );
		produceHeroWidget( heroContainer5, null, null );
		
		bitmapFont.draw( batch, "LV: "+25, 40, getY()+85 );
		bitmapFont.draw( batch, "LV: "+1, 150, getY()+85 );
		bitmapFont.draw( batch, "LV: "+1, 236, getY()+85 );
		bitmapFont.draw( batch, "LV: ", 321, getY()+85 );
		bitmapFont.draw( batch, "LV: ", 408, getY()+85 );
		
		bitmapFont.draw( batch, "领导力", 45, getY()+44 );
		bitmapFont.draw( batch, 14+"/"+26, 130, getY()+43 );
		bitmapFont.draw( batch, "团队实力", 260, getY()+44 );
		bitmapFont.draw( batch, ""+3604, 370, getY()+43 );
	}
	
	private static int threshold= 0;
	private WidgetGroup produceHeroWidget( WidgetGroup container, Integer borderIndex, Integer heroIndex ){
		//////////////avoid memory leak, ad hoc here/////////////// here recreate too many Image, these image should be cached somewhere
		if( threshold > 5 ){
			return null;
		}
		threshold++;
		////////////////////////////////////////////////////////////////
		container.clear();
		
		Image borderBg = new Image( ResourcesLoader.getBorderIconDrawable( 0 ) );
		container.addActor( borderBg );
		
		if( heroIndex != null ){
			Image hero = new Image( ResourcesLoader.getHeroIconDrawable( heroIndex ) );
			container.addActor( hero );
		}
		
		if( borderIndex != null ){
			Image border = new Image( ResourcesLoader.getBorderIconDrawable( borderIndex ) );
			container.addActor( border );
		}
		
		if( heroIndex == null ){
			Image addIcon = new Image( ResourcesLoader.getAddIconDrawable() );
			addIcon.setPosition( 56, 60 );
			container.addActor( addIcon );
		}
		
		return container;
	}
	
	
	
}
