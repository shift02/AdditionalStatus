package shift.additionalstatus;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shift.additionalstatus.api.capability.VanillaFoodHandler;
import shift.additionalstatus.capability.EntityPlayerManager;
import shift.additionalstatus.event.ClientEventHandler;
import shift.additionalstatus.event.CommonEventHandler;
import shift.additionalstatus.event.HUDEventHandler;
import shift.additionalstatus.event.PlayerStatusEventHandler;

public class ASEvents {

	public static void preInit(FMLPreInitializationEvent event) {


        MinecraftForge.EVENT_BUS.register(EntityPlayerManager.instance);
        
        MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
        
        MinecraftForge.EVENT_BUS.register(new VanillaFoodHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerStatusEventHandler());
		// FMLCommonHandler.instance().bus().register(EntityPlayerManager.instance);
        
		if (event.getSide().isClient()) MinecraftForge.EVENT_BUS.register(new HUDEventHandler());


        if (event.getSide().isClient()) {
            ClientEventHandler e = new ClientEventHandler();
            MinecraftForge.EVENT_BUS.register(e);
			// FMLCommonHandler.instance().bus().register(e);
        }

	}
	
}
