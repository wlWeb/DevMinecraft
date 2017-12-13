package com.whisper.template.config;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.whisper.template.Reference;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * The main class for your configuration.
 * Get all of your customized values here
 * @author CJMinecraft
 *
 */
public class ConfigHandler {
	
	/**
	 * The object which holds the actual config file
	 */
	private static Configuration config = null;
	
	//test to add my own
	
	public static final String CATEGORY_GENERAL = "general";
	
	/**
	 * The name of the category for our blocks
	 */
	public static final String CATEGORY_NAME_BLOCKS = "blocks";
	
	/**
	 * The values which are loaded from the config file
	 */
	public static int machineCooldownBasic;
	public static int machineCooldownAdvanced;
	public static boolean isThisWorking;
	
	/**
	 * Called on the server and the client setting up the config file
	 */
	public static void preInit() {
		File configFile = new File(Loader.instance().getConfigDir(), "Config.cfg");
		config = new Configuration(configFile);
		syncFromFiles();
	}
	
	/**
	 * Receive the config object for use in the gui factory
	 * @return the config element
	 */
	public static Configuration getConfig() {
		return config;
	}
	
	/**
	 * Register our event which handles the gui factory saving the config
	 */
	public static void clientPreInit() {
		MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());
	}
	
	/**
	 * Sync the config from a change in the file
	 */
	public static void syncFromFiles() {
		syncConfig(true, true);
	}
	
	/**
	 * Sync the config from a change in the gui
	 */
	public static void syncFromGui() {
		syncConfig(false, true);
	}
	
	/**
	 * Sync the config from a change in the fields (i.e changing the machineCooldownBasic property using BoeConfig.machineCooldownBasic = ...)
	 */
	public static void syncFromFields() {
		syncConfig(false, false);
	}
	
	/**
	 * Used internally to sync all of our properties and fields
	 * @param loadFromConfigFile Should load the actual config file?
	 * @param readFieldsFromConfig Should read the values from the config?
	 */
	private static void syncConfig(boolean loadFromConfigFile, boolean readFieldsFromConfig) {
		if(loadFromConfigFile)
			config.load();
		
		Property propertyMachineCooldownBasic = config.get(CATEGORY_NAME_BLOCKS, "machine_cooldown_basic", 100);
		propertyMachineCooldownBasic.setLanguageKey("gui.config.blocks.machine_cooldown_basic.name");
		propertyMachineCooldownBasic.setComment("The cooldown for the basic tier of machine");
		propertyMachineCooldownBasic.setMinValue(10);
		propertyMachineCooldownBasic.setMaxValue(200);
		
		Property propertyMachineCooldownAdvanced = config.get(CATEGORY_NAME_BLOCKS, "machine_cooldown_advanced", 50);
		propertyMachineCooldownAdvanced.setLanguageKey("gui.config.blocks.machine_cooldown_advanced.name");
		propertyMachineCooldownAdvanced.setComment("The cooldown for the advanced tier of machine");
		propertyMachineCooldownAdvanced.setMinValue(10);
		propertyMachineCooldownAdvanced.setMaxValue(200);
		
		Property propertyIsThisWorking = config.get(CATEGORY_GENERAL, "isThisWorking", false);
		propertyIsThisWorking.setLanguageKey("gui.config.general.isThisWorking.name");
		propertyMachineCooldownBasic.setComment("Sanity check to see if I understand this.");
		
		List<String> propertyOrderBlocks = new ArrayList<String>();
		propertyOrderBlocks.add(propertyMachineCooldownBasic.getName());
		propertyOrderBlocks.add(propertyMachineCooldownAdvanced.getName());
		config.setCategoryPropertyOrder(CATEGORY_NAME_BLOCKS, propertyOrderBlocks);
		
		List<String> propertyOrderGeneral = new ArrayList<String>();
		propertyOrderGeneral.add(propertyIsThisWorking.getName());
		config.setCategoryPropertyOrder(CATEGORY_GENERAL, propertyOrderGeneral);
		
		if(readFieldsFromConfig) {
			machineCooldownBasic = propertyMachineCooldownBasic.getInt();
			machineCooldownAdvanced = propertyMachineCooldownAdvanced.getInt();
			isThisWorking = propertyIsThisWorking.getBoolean();
		}
		
		propertyMachineCooldownBasic.set(machineCooldownBasic);
		propertyMachineCooldownAdvanced.set(machineCooldownAdvanced);
		propertyIsThisWorking.set(isThisWorking);
		
		if(config.hasChanged())
			config.save();
	}
	
	/**
	 * Handles the updating of the config from the gui factory
	 * @author CJMinecraft
	 *
	 */
	public static class ConfigEventHandler {
		
		/**
		 * Syncs the update from the gui factory
		 */
		@SubscribeEvent(priority = EventPriority.LOWEST)
		public void onEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
			if(event.getModID().equals(Reference.MODID)) {
				syncFromGui();
			}
		}
		
	}

}