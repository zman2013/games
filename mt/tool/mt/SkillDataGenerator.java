package mt;
import mt.formation.SkillInfo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;


public class SkillDataGenerator extends ApplicationAdapter{

	public static void main(
	        String[] args )
	    {
	        new LwjglApplication( new SkillDataGenerator(), "", 100, 100, true );
	    }

	public void create() {
		Array<SkillInfo> infos = new Array<SkillInfo>();
		infos.add( new SkillInfo( 0, "assets/images/skills/icon/1.png" ) );
		infos.add( new SkillInfo( 1, "assets/images/skills/icon/2.png" ) );
		infos.add( new SkillInfo( 2, "assets/images/skills/icon/3.png" ) );
		infos.add( new SkillInfo( 3, "assets/images/skills/icon/4.png" ) );
		infos.add( new SkillInfo( 4, "assets/images/skills/icon/5.png" ) );
		infos.add( new SkillInfo( 5, "assets/images/skills/icon/6.png" ) );
		
		Json json = new Json();
		String jsonAsString = json.prettyPrint( infos );
		FileHandle fh = Gdx.files.local( "assets/data/player/skill.data" );
		fh.writeString( jsonAsString, false );
	}
}
