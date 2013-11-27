package mt.actors;

import mt.actors.sub.HeroSelectBar;
import mt.resources.FontLoader;
import mt.resources.ResourcesLoader;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class TeamListBox extends WidgetGroup{

	private ScrollPane scrollPane;
	
	private Image listHeader;
	
	//font
	private BitmapFont font;
	
	public TeamListBox(){
		listHeader = new Image( ResourcesLoader.getDrawable( "assets/images/home_images/list_header.png" ) );
		listHeader.setY( 520 );
		
		font = FontLoader.getBitmapFont();
		
		Table table = new Table();
		table.setY( 400 );
		HeroSelectBar bar = new HeroSelectBar( 160, 2, true, false );
		table.add( bar );
		table.row();
		bar = new HeroSelectBar( 160, 1, false, true );
		table.add( bar );
		table.row();
		for( int i = 0; i < 8; i ++ ){
			bar = new HeroSelectBar( 165, 1, false, false );
			table.add( bar );
			table.row();
		}

		scrollPane = new ScrollPane( table );
		scrollPane.setSize( listHeader.getWidth(), 524 );
		
		addActor( listHeader );
		addActor( scrollPane );
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		font.draw( batch, "гЂал", listHeader.getX()+235, listHeader.getY()+14 );
	}
	
	
	
}

