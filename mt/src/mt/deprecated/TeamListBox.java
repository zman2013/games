package mt.deprecated;

import mt.resources.FontLoader;
import mt.resources.ResourcesLoader;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class TeamListBox extends WidgetGroup{

	private ScrollPane scrollPane;
	
	private Image listHeader;
	
	//font
	private BitmapFont font;
	
	//stage
	private Stage stage;
	
	public TeamListBox( Stage stage ){
		this.stage = stage;
		
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
		
		initListener();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		font.draw( batch, "гЂал", listHeader.getX()+235, listHeader.getY()+14 );
	}
	
	private void initListener() {
		scrollPane.addListener( new ClickListener() {

			@Override
			public void enter(InputEvent event, float x, float y, int pointer,
					Actor fromActor) {
				super.enter(event, x, y, pointer, fromActor);
				
				stage.setScrollFocus( scrollPane );
			}

			@Override
			public void exit(InputEvent event, float x, float y, int pointer,
					Actor toActor) {
				super.exit(event, x, y, pointer, toActor);
				
				stage.setScrollFocus( null );
			}
			
		});
	}
	
}

