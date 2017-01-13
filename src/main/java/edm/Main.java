package edm;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edm.Configs.Config_Handler;
import edm.Handler.Explosion_Handler;

@Mod(	modid=Refs.MOD_ID,
		name=Refs.MOD_NAME,
		version=Refs.VERSION,
		guiFactory=Refs.guiFactory
		)
public class Main 
{
	@Instance(Refs.MOD_ID)
	public static Main instance = new Main();
	
	public static final Logger logger = LogManager.getLogger(Refs.MOD_ID);
	
	@SidedProxy(clientSide=Refs.CLIENT_PROXY_CLASS, serverSide=Refs.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static Explosion_Handler eventHandler = new Explosion_Handler();
	public static Config_Handler configHandler = new Config_Handler();


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
		FMLCommonHandler.instance().bus().register(Main.eventHandler);
		MinecraftForge.EVENT_BUS.register(Main.eventHandler);
		FMLCommonHandler.instance().bus().register(Main.configHandler);
		MinecraftForge.EVENT_BUS.register(Main.configHandler);
		
		Config_Handler.configOptions(event);	
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.init(event);

	}

	@EventHandler 
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
