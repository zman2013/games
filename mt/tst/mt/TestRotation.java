package mt;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TestRotation extends ApplicationAdapter{

	TextureRegion region;
	
	SpriteBatch batch;
	
	@Override
	public void create() {
		Texture rect = new Texture( "assets/images/border/2.png" );
		region = new TextureRegion( rect );
		
		batch = new SpriteBatch();
	}

	@Override
	public void render() {
		 Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	     Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	     
	     float x = 150;
	     float y = 150;
	     float width = 100;
	     float height = 100;
	     
	     float originX = 200;
	     float originY = 200;
	     
	     
	     batch.begin();
	     batch.draw( region, x, y, width, height );
	     for( int i = 2; i < 4; i ++ ){
		     batch.draw( region, x, y, originX, originY, width, height, 1, 1, 30*i );
	     }
	     
	     batch.draw( region, x, y, 10, 10 );
	     batch.draw( region, x+originX, y+originY, 10, 10 );
	     batch.end();
	     
	}

}
