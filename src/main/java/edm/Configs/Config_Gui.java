package edm.Configs;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import edm.Refs;

//Tutorial Source ->  https://github.com/Minalien/BlogArchive/blob/master/ForgeTutorials/Spotlight__Config_GUIs.md
// + http://forum.feed-the-beast.com/threads/code-snippets-classes.51404/page-4#post-876553
// + http://jabelarminecraft.blogspot.com/p/minecraft-modding-configuration-guis.html

public class Config_Gui extends GuiConfig {

	//Simple version
	/*
	public Config_Gui(GuiScreen parent) {
		super(parent,
				new ConfigElement(Config_Handler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
				Refs.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(Config_Handler.config.toString()));
	}  
	*/
	public Config_Gui(GuiScreen parent) {
		super(parent, getConfigElements(), Refs.MOD_ID, false, false, Refs.MOD_NAME+" Configuration");
	}

	/** Compiles a list of config elements */
	private static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();

		//Add categories to config GUI
		list.add(categoryElement(Config_Handler.config.CATEGORY_GENERAL, "configgui.edm.category.general.tooltip", "configgui.edm.category.general"));
		list.add(categoryElement(Config_Handler.Chance, "configgui.edm.category.chance.tooltip", "configgui.edm.category.chance"));
		return list;
	}

	/** Creates a button linking to another screen where all options of the category are available */
	private static IConfigElement categoryElement(String category, String name, String tooltip_key) {
		return new DummyConfigElement.DummyCategoryElement(name, tooltip_key,
				new ConfigElement(Config_Handler.config.getCategory(category)).getChildElements());
	}
}