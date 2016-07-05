package shift.additionalstatus;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shift.additionalstatus.api.AdditionalStatusAPI;
import shift.additionalstatus.api.capability.CapabilityMoistureHandler;
import shift.additionalstatus.capability.AdditionalPlayerData;
import shift.additionalstatus.capability.EntityPlayerManager;
import shift.additionalstatus.packet.ASPacketHandler;
import shift.additionalstatus.proxy.CommonProxy;

@Mod(modid = AdditionalStatus.MODID, version = AdditionalStatus.VERSION, guiFactory = "shift.additionalstatus.config.ASConfigGuiFactory")
public class AdditionalStatus {
	
	public static final String MODID = "AdditionalStatus";
	public static final String VERSION = "1.0";

	@Mod.Instance("AdditionalStatus")
	public static AdditionalStatus instance;

	@SidedProxy(modId = MODID, clientSide = "shift.additionalstatus.proxy.ClientProxy", serverSide = "shift.additionalstatus.proxy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		ASConfig.syncConfig();

		// 登録
		CapabilityManager.INSTANCE.register(AdditionalPlayerData.class, new AdditionalPlayerData(),
				AdditionalPlayerData.class);

		AdditionalStatusAPI.playerManager = EntityPlayerManager.instance;
		// MinecraftForge.EVENT_BUS.register(EntityPlayerManager.instance);

		ASPacketHandler.init(event);

		ASEvents.preInit(event);
		
		CapabilityMoistureHandler.register();

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		// some example code
		System.out.println("DIRT BLOCK >> " + Blocks.DIRT.getUnlocalizedName());
	}
}
