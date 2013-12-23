package mt.actor.skill;

import com.badlogic.gdx.utils.ReflectionPool;

public class SkillPool {

	private static ReflectionPool<MeleeAttack>meleeAttackTool = new ReflectionPool<MeleeAttack>( MeleeAttack.class, 0, 100 );
	
	public static MeleeAttack getMeleeAttack(){
		MeleeAttack attack = meleeAttackTool.obtain();
		attack.init();
		return attack;
	}
}
