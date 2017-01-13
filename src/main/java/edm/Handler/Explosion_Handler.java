package edm.Handler;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import edm.Configs.ModConfig;

public class Explosion_Handler extends ModConfig{

	
	protected static Random randy = new Random();
	
	@SubscribeEvent(priority=EventPriority.NORMAL)
	public void OnDeath_Event(LivingDeathEvent event){
		
	  Entity ent = event.getEntity();	
	  if(event.getSource().isExplosion() && Chain_Reaction == false){
		  return;
	  }
	  
	  if(!ent.worldObj.isRemote){
		
		if(ent instanceof EntityPlayer && roll(Player_onDeath, Player_Chance)){
			Explode(ent, Player_Size, Player_Fire, Player_Grief);
		}
		
		else if(ent instanceof EntityAnimal && roll(Passive_onDeath, Passive_Chance)){
			Explode(ent, Passive_Size, Passive_Fire, Passive_Grief);
		}
		
		else if(ent instanceof EntityVillager && roll(Villager_onDeath, Villager_Chance)){
			Explode(ent, Villager_Size, Villager_Fire, Villager_Grief);
		}
		
		else if(ent instanceof EntityMob && roll(Monster_onDeath, Monster_Chance)){
			Explode(ent, Monster_Size, Monster_Fire, Monster_Grief);
		}
		
		else if(!ent.isNonBoss() && roll(Boss_onDeath, Boss_Chance)){
			Explode(ent, Boss_Size, Boss_Fire, Boss_Grief);
		}
		
		else if(roll(Anything_onDeath, Anything_Chance)){
			if(ent instanceof EntityMob ||
			     ent instanceof EntityVillager ||
				 ent instanceof EntityAnimal ||
				 ent instanceof EntityPlayer ||
				 !ent.isNonBoss() ){return;}
			Explode(ent, Anything_Size, Anything_Fire, Anything_Grief);			
		}
	  }
		
	}
	
	public void Explode(Entity ent, int f,Boolean fire, Boolean grief){
		Explode(ent, (float)f, fire, grief);
	}
	
	public boolean roll (boolean flag, int chance){		
		int rand = randy.nextInt(100);	
		return (flag && (rand <= chance) && rand!= 0);
	}
	public boolean roll (int chance){		
		int rand = randy.nextInt(100);
		return ((rand <= chance) && rand!= 0);
	}
	public float amplify(float f){
		int rand = (randy.nextInt(Size_Variance)+100);
		float amp = ((float)rand/100)*f;		
		if (amp > 40) amp = 40;
		return amp;
	}
	
	public void Explode(Entity ent, Float f,Boolean fire, Boolean grief){
		
			if(!roll(grief, Grief_Chance)){
				grief = false;
			}
			if(!roll(fire, Fire_Chance)){
				fire = false;
			}			
			
			ent.worldObj.newExplosion(ent, ent.posX, ent.posY, ent.posZ, amplify(f), fire, grief);
			
	}
	
}
