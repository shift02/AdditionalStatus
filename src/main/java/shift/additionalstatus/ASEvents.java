package shift.additionalstatus;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shift.additionalstatus.capability.EntityPlayerManager;
import shift.additionalstatus.event.ClientEventHandler;
import shift.additionalstatus.event.HUDEventHandler;

public class ASEvents {

	public static void preInit(FMLPreInitializationEvent event) {


        MinecraftForge.EVENT_BUS.register(EntityPlayerManager.instance);
		// FMLCommonHandler.instance().bus().register(EntityPlayerManager.instance);
        
		if (event.getSide().isClient()) MinecraftForge.EVENT_BUS.register(new HUDEventHandler());


        if (event.getSide().isClient()) {
            ClientEventHandler e = new ClientEventHandler();
            MinecraftForge.EVENT_BUS.register(e);
			// FMLCommonHandler.instance().bus().register(e);
        }

	}
	
}
