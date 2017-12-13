package com.whisper.template;

import com.whisper.template.config.ConfigHandler;
import com.whisper.template.proxy.CommonProxy;
import com.whisper.template.config.ConfigGuiFactory;
import com.whisper.template.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.CustomProperty;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

/*
@Modid docs - https://mcforge.readthedocs.io/en/latest/gettingstarted/structuring/#the-mcmodinfo-file
The "@Mod" let's Forge know that this is a mod and it should treat it as such. 
The "modid" tells forge what id it should use for your mod, in this case it references a string. 
The "name" part is the readable name that will be displayed, again this references a string. 
The "useMetadata" determines whether Forge should use the mcmod.info file to override the other settings in the annotation.
mcmod.info documentation - https://mcforge.readthedocs.io/en/latest/gettingstarted/structuring/#the-mcmodinfo-file
Another potential field is dependencies
 */

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY, useMetadata = true)

public class Template {

	// This creates the mod instance by telling forge it should run the Template class
	@Instance(value = Reference.MODID)
	public static Template instance = new Template();
	
	// Sets up the proxies reference more comments in proxy.common
	
	/*
	 Considering the use of a single “universal” jar for client and server mods, and the separation of the 
	 physical sides into two jars, an important question comes to mind: How do we use code that is only 
	 present on one physical side? All code in net.minecraft.client is only present on the physical client, and all 
	 code in net.minecraft.server.dedicated is only present on the physical server. If any class you write references those 
	 names in any way, they will crash the game when that respective class is loaded in an environment where those names do not exist. 
	 A very common mistake in beginners is to call Minecraft.getMinecraft().<doStuff>() in block or tile entity classes, which will crash 
	 any physical server as soon as the class is loaded.

	How do we resolve this? Luckily, FML provides us with a @SidedProxy annotation. We supply it the names of two classes 
	(one for serverSide, one for clientSide), and decorate a field with this annotation. When the mod starts, FML will instantiate 
	one of the two classes based on the physical side.
	 */
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	
	//PreInitialization
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.preInit();
	}

	//Initialization
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}