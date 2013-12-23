package mt.util;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap.Entry;

import mt.domain.Commodity;
import mt.domain.FighterInfo;

public class PropertyCalculator {
	
	public static void calculate( FighterInfo fighterInfo ){
		Array<Commodity> equips = fighterInfo.getEquipments();
		for( Commodity equip : equips ){
			for( Entry<String,Integer> entry : equip.getProperties().entries() ){
				String propertyIndex = entry.key;
				//生命
				if( "0".equals(propertyIndex) ){
					fighterInfo.setHp( fighterInfo.getHp() + entry.value );
				//法力
				}else if ( "1".equals(propertyIndex) ){
					fighterInfo.setMagic( fighterInfo.getMagic() + entry.value);
				//最小物攻
				}else if ( "2".equals(propertyIndex) ){
					fighterInfo.setMinMeleeAttack( fighterInfo.getMinMeleeAttack() + entry.value );
				//最大物攻
				}else if ( "3".equals(propertyIndex) ){
					fighterInfo.setMaxMeleeAttack( fighterInfo.getMaxMeleeAttack() + entry.value );
				//最小魔攻
				}else if ( "4".equals(propertyIndex) ){
					fighterInfo.setMinSpellAttack( fighterInfo.getMinSpellAttack() + entry.value );
				//最大魔攻
				}else if ( "5".equals(propertyIndex) ){
					fighterInfo.setMaxSpellAttack( fighterInfo.getMaxSpellAttack() + entry.value );
				//命中
				}else if ( "6".equals(propertyIndex) ){
					fighterInfo.setHitScore( fighterInfo.getHitScore() + entry.value );
				//暴击
				}else if ( "7".equals(propertyIndex) ){
					fighterInfo.setCrit( fighterInfo.getCrit() + entry.value );
				//闪避
				}else if ( "8".equals(propertyIndex) ){
					fighterInfo.setDoage( fighterInfo.getDoage() + entry.value );
				//幸运
				}else if ( "9".equals(propertyIndex) ){
					fighterInfo.setLuck( fighterInfo.getLuck() + entry.value );
				}
			}
		}
	}

	/**
	 * 装上一件装备
	 * @param info
	 * @param equipment
	 */
	public static void addEquipment( FighterInfo fighterInfo, Commodity equipment ){
		for( Entry<String,Integer> entry : equipment.getProperties().entries() ){
			String propertyIndex = entry.key;
			//生命
			if( "0".equals(propertyIndex) ){
				fighterInfo.setHp( fighterInfo.getHp() + entry.value );
			//法力
			}else if ( "1".equals(propertyIndex) ){
				fighterInfo.setMagic( fighterInfo.getMagic() + entry.value);
			//最小物攻
			}else if ( "2".equals(propertyIndex) ){
				fighterInfo.setMinMeleeAttack( fighterInfo.getMinMeleeAttack() + entry.value );
			//最大物攻
			}else if ( "3".equals(propertyIndex) ){
				fighterInfo.setMaxMeleeAttack( fighterInfo.getMaxMeleeAttack() + entry.value );
			//最小魔攻
			}else if ( "4".equals(propertyIndex) ){
				fighterInfo.setMinSpellAttack( fighterInfo.getMinSpellAttack() + entry.value );
			//最大魔攻
			}else if ( "5".equals(propertyIndex) ){
				fighterInfo.setMaxSpellAttack( fighterInfo.getMaxSpellAttack() + entry.value );
			//命中
			}else if ( "6".equals(propertyIndex) ){
				fighterInfo.setHitScore( fighterInfo.getHitScore() + entry.value );
			//暴击
			}else if ( "7".equals(propertyIndex) ){
				fighterInfo.setCrit( fighterInfo.getCrit() + entry.value );
			//闪避
			}else if ( "8".equals(propertyIndex) ){
				fighterInfo.setDoage( fighterInfo.getDoage() + entry.value );
			//幸运
			}else if ( "9".equals(propertyIndex) ){
				fighterInfo.setLuck( fighterInfo.getLuck() + entry.value );
			}
		}
	}
	
	/**
	 * 移除一件装备
	 * @param fighterInfo
	 * @param equipment
	 */
	public static void removeEquipment( FighterInfo fighterInfo, Commodity equipment ){
		for( Entry<String,Integer> entry : equipment.getProperties().entries() ){
			String propertyIndex = entry.key;
			//生命
			if( "0".equals(propertyIndex) ){
				fighterInfo.setHp( fighterInfo.getHp() - entry.value );
			//法力
			}else if ( "1".equals(propertyIndex) ){
				fighterInfo.setMagic( fighterInfo.getMagic() - entry.value);
			//最小物攻
			}else if ( "2".equals(propertyIndex) ){
				fighterInfo.setMinMeleeAttack( fighterInfo.getMinMeleeAttack() - entry.value );
			//最大物攻
			}else if ( "3".equals(propertyIndex) ){
				fighterInfo.setMaxMeleeAttack( fighterInfo.getMaxMeleeAttack() - entry.value );
			//最小魔攻
			}else if ( "4".equals(propertyIndex) ){
				fighterInfo.setMinSpellAttack( fighterInfo.getMinSpellAttack() - entry.value );
			//最大魔攻
			}else if ( "5".equals(propertyIndex) ){
				fighterInfo.setMaxSpellAttack( fighterInfo.getMaxSpellAttack() - entry.value );
			//命中
			}else if ( "6".equals(propertyIndex) ){
				fighterInfo.setHitScore( fighterInfo.getHitScore() - entry.value );
			//暴击
			}else if ( "7".equals(propertyIndex) ){
				fighterInfo.setCrit( fighterInfo.getCrit() - entry.value );
			//闪避
			}else if ( "8".equals(propertyIndex) ){
				fighterInfo.setDoage( fighterInfo.getDoage() - entry.value );
			//幸运
			}else if ( "9".equals(propertyIndex) ){
				fighterInfo.setLuck( fighterInfo.getLuck() - entry.value );
			}
		}
	}
}
