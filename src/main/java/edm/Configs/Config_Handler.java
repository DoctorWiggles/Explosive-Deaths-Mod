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
		if (eventArgs.modID.equals(Refs.MOD_ID))
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
		
		Player_onDeath= death(Player_onDeath, "Player onDeath", true);
		Player_Grief= sync(Player_Grief, "Player Grief", true);
		Player_Fire= sync(Player_Fire, "Player Fire", true);
		Player_Chance= ent_chance(Player_Chance, "Player Chance", 100);
		Player_Size= size(Player_Size, "Player Size", 3);

		Passive_onDeath= death(Passive_onDeath, "Passive onDeath", true);
		Passive_Grief= sync(Passive_Grief, "Passive Grief", true);
		Passive_Fire= sync(Passive_Fire, "Passive Fire", true);
		Passive_Chance= ent_chance(Passive_Chance, "Passive Chance", 0);
		Passive_Size= size(Passive_Size, "Passive Size", 2);

		Villager_onDeath= death(Villager_onDeath, "Villager onDeath", true);
		Villager_Grief= sync(Villager_Grief, "Villager Grief", true);
		Villager_Fire= sync(Villager_Fire, "Villager Fire", true);
		Villager_Chance= ent_chance(Villager_Chance, "Villager Chance", 0);
		Villager_Size= size(Villager_Size, "Villager Size", 2);

		Monster_onDeath = death(Monster_onDeath, "Monster onDeath", true);
		Monster_Grief = sync(Monster_Grief, "Monster Grief", true);
		Monster_Fire = sync(Monster_Fire, "Monster Fire", true);
		Monster_Chance = ent_chance(Monster_Chance, "Monster Chance", 0);
		Monster_Size = size(Monster_Size, "Monster Size", 2);
		
		Boss_onDeath = death(Boss_onDeath, "Boss onDeath", true);
		Boss_Grief = sync(Boss_Grief, "Boss Grief", true);
		Boss_Fire = sync(Boss_Fire, "Boss Fire", true);
		Boss_Chance = ent_chance(Boss_Chance, "Boss Chance", 0);
		Boss_Size = size(Boss_Size, "Boss Size", 8);
		
		//Anything_onDeath = config.get(gen, "Anything onDeath", true).getBoolean(true);
		Anything_onDeath = death(Anything_onDeath, "Anything onDeath", true);
		Anything_Grief= sync(Anything_Grief, "Anything Grief", true);
		Anything_Fire= sync(Anything_Fire, "Anything Fire", true);
		//Anything_Chance = config.get(gen, "Anything Chance", 0).getInt(0);
		Anything_Chance= ent_chance(Anything_Chance, "Anything Chance", 0);
		Anything_Size = size(Anything_Size, "Anything Size", 1);
		
		Chain_Reaction = chance(Chain_Reaction, "Chain_Reaction", true);
		Grief_Chance = chance(Grief_Chance, "Grief_Chance", 100);
		Fire_Chance = chance(Fire_Chance, "Fire_Chance", 40);		
		Size_Variance = chance(Size_Variance, "Size_Variance", 60);
		
		if(config.hasChanged())
			config.save();
	}
	
	
	public static int chance(int name, String desc, int def){
		return config.get(Chance, path_chance+desc, def, path_chance+desc+".tooltip").getInt();
	}
	
	public static boolean chance(Boolean name, String desc, Boolean def){		
		return config.get(Chance, path_chance+desc, def,  path_chance+desc+".tooltip").getBoolean(def);
	}
	public static int size(int name, String desc, int def){
		return config.get(gen, desc, def, "Explosion size, (1-40F)(3 = Creeper size)").getInt();
	}	
	
	public static int ent_chance(int name, String desc, int def){
		return config.get(gen, desc, def, "Chance Entity Type will Explode ondeath").getInt();
	}
	
	public static boolean sync(Boolean name, String desc, Boolean def){		
		return config.get(gen, desc, def).getBoolean(def);
	}
	
	public static boolean death(Boolean name, String desc, Boolean def){		
		return config.get(gen, desc, def,"Enable if Entity type will explode on death").getBoolean(def);
	}
}