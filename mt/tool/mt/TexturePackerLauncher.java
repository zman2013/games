package mt;

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class TexturePackerLauncher {

	public static void main(String[] args){
		//������������ͼƬ��
		TexturePacker2.processIfModified("assets/images/skills/melee_attack_src", "assets/images/skills/melee_attack", "melee_attack");
	}
}
