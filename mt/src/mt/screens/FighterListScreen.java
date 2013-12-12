package mt.screens;

import mt.actor.shared.ReturnActor;
import mt.actors.domain.FighterInfo;
import mt.fighterlist.FighterBar;
import mt.fighterlist.FighterListResourceLoader;
import mt.resources.FontLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class FighterListScreen extends AbstractScreen{

	private Drawable bgDrawable;
	//�½�spriteBatch,��ʹ��stage batch��ԭ��stage batch��scrollpane����ʱ��Щ���⡣
	private SpriteBatch spriteBatch = new SpriteBatch();
	
	private Image listHeader;
	
	private ScrollPane scrollPane;
	
	private ReturnActor returnActor;
	
	//font
	private BitmapFont font;
	
	public FighterListScreen(){
		
		FighterListResourceLoader resourceLoader = new FighterListResourceLoader();
		
		bgDrawable = resourceLoader.getBgDrawable();
		
		listHeader = new Image( resourceLoader.getHeaderDrawable() );
		listHeader.setY( 640 );
		
		font = FontLoader.getBitmapFont();
		
		Table table = new Table();
		for( FighterInfo info : resourceLoader.getHeroInfos() ){
			FighterBar bar = new FighterBar( info, resourceLoader );
			table.add( bar );
			table.row();
		}

		scrollPane = new ScrollPane( table );
		scrollPane.setSize( stage.getWidth(), 560 );
		scrollPane.setY( 80 );
		
		//return actor
		returnActor = new ReturnActor( resourceLoader.getReturnDrawable(), stage.getWidth(), this, HomeScreen.class );
		
		stage.addActor( listHeader );
		stage.addActor( scrollPane );
		stage.addActor( returnActor );
		
		initListener();
	}

	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

		spriteBatch.begin();
		bgDrawable.draw( spriteBatch, 0, 0, stage.getWidth(), stage.getHeight() );
		spriteBatch.end();
		
		stage.act( delta );
		stage.draw();
		
		batch.begin();
		font.draw( batch, "Ӣ��", listHeader.getX()+212, listHeader.getY()+48 );
		batch.end();
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
