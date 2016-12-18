package edm.Configs;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import edm.Refs;

public class Config_Handler extends ModConfig{

	public static Configuration config;
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (eventArgs.configID.equals(Refs.MOD_ID))
			this.syncConfig();
	}

	public static void configOptions(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile());
		syncConfig();
	}

	static String gen = "general";
	
	static String Chance = "chance";
	
	static String path_gen = "configgui.edm.category.general.";
	static String path_chance = "configgui.edm.category.chance.";
	
	public static void syncConfig(){
		
		death(Player_onDeath, "Player onDeath", true);
		sync(Player_Grief, "Player Grief", true);
		sync(Player_Fire, "Player Fire", true);
		ent_chance(Player_Chance, "Player Chance", 100);
		size(Player_Size, "Player Size", 3);
		
		death(Passive_onDeath, "Passive onDeath", true);
		sync(Passive_Grief, "Passive Grief", true);
		sync(Passive_Fire, "Passive Fire", true);
		ent_chance(Passive_Chance, "Passive Chance", 0);
		size(Passive_Size, "Passive Size", 2);
		
		death(Villager_onDeath, "Villager onDeath", true);
		sync(Villager_Grief, "Villager Grief", true);
		sync(Villager_Fire, "Villager Fire", true);
		ent_chance(Villager_Chance, "Villager Chance", 0);
		size(Villager_Size, "Villager Size", 2);
		
		death(Monster_onDeath, "Monster onDeath", true);
		sync(Monster_Grief, "Monster Grief", true);
		sync(Monster_Fire, "Monster Fire", true);
		ent_chance(Monster_Chance, "Monster Chance", 0);
		size(Monster_Size, "Monster Size", 2);
		
		death(Boss_onDeath, "Boss onDeath", true);
		sync(Boss_Grief, "Boss Grief", true);
		sync(Boss_Fire, "Boss Fire", true);
		ent_chance(Boss_Chance, "Boss Chance", 0);
		size(Boss_Size, "Boss Size", 8);
		
		death(Anything_onDeath, "Anything onDeath", true);
		sync(Anything_Grief, "Anything Grief", true);
		sync(Anything_Fire, "Anything Fire", true);
		ent_chance(Anything_Chance, "Anything Chance", 0);
		size(Anything_Size, "Anything Size", 1);
		
		chance(Chain_Reaction, "Chain_Reaction", true);
		chance(Grief_Chance, "Grief_Chance", 100);
		chance(Fire_Chance, "Fire_Chance", 40);		
		chance(Size_Variance, "Size_Variance", 60);
		
		if(config.hasChanged())
			config.save();
	}
	
	
	public static void chance(int name, String desc, int def){
		name = config.get(Chance, path_chance+desc, def, path_chance+desc+".tooltip").getInt();
	}
	
	public static void chance(Boolean name, String desc, Boolean def){		
		name = config.get(Chance, path_chance+desc, def,  path_chance+desc+".tooltip").getBoolean(def);
	}
	public static void size(int name, String desc, int def){
		name = config.get(gen, desc, def, "Explosion size, (1-40F)(3 = Creeper size)").getInt();
	}	
	
	public static void ent_chance(int name, String desc, int def){
		name = config.get(gen, desc, def, "Chance Entity Type will Explode ondeath").getInt();
	}
	
	public static void sync(Boolean name, String desc, Boolean def){		
		name = config.get(gen, desc, def).getBoolean(def);
	}
	
	public static void death(Boolean name, String desc, Boolean def){		
		name = config.get(gen, desc, def,"Enable if Entity type will explode on death").getBoolean(def);
	}
}