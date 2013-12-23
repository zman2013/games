package mt;

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class TexturePackerLauncher {

	public static void main(String[] args){
		//生成物理攻击的图片集
		TexturePacker2.processIfModified("assets/images/skills/melee_attack_src", "assets/images/skills/melee_attack", "melee_attack");
	}
}
