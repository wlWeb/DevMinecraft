package com.whisper.template.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.whisper.template.config.ConfigHandler;

/* Proxy classes. These classes take care of doing this init of your mod and make sure the right
 things get done at the right 'side' (server versus client). It is considered good practice to work like this. 
 Note that in many cases you can use Common Proxy for the server side since most things you want to init 
 on the server you have to init client side as well. These proxies use @Mod.EventBusSubscribers so that they
 are automatically put on the forge event bus. That way the events for block, item, and model registration
 will get a chance to register early enough(preinit time).
 Side Documentation - https://mcforge.readthedocs.io/en/latest/concepts/sides/#sides-in-minecraft
 */

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {

    }

    public void init(FMLInitializationEvent e) {

    }

    public void postInit(FMLPostInitializationEvent e) {
    }

}