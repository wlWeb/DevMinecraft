package com.whisper.template.config;

import com.whisper.template.proxy.CommonProxy;

import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.registry.GameRegistry;



public class ConfigHandler {

	private static Configuration config = null;
	
	public static void preInit() {
		
	}
	
	public static Configuration getConfig() {
		return config;
	}
}