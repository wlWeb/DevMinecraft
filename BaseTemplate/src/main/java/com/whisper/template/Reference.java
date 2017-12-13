package com.whisper.template;

import net.minecraft.client.resources.I18n;


//Creates our references to various things such as the @Mod tag in the main mod file
public class Reference {
	public static final String MODID = "template";
	public static final String NAME = "Template Mod";
	public static final String VERSION = "0.0.1";
	public static final String CLIENT_PROXY_CLASS = "com.whisper.template.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "com.whisper.tempalte.proxy.ServerProxy";

	public static final String GUI_FACTORY = "com.whisper.template.config.ConfigGuiFactory";
}