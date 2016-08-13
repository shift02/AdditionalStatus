package shift.additionalstatus;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import shift.additionalstatus.api.AdditionalStatusAPI;
import shift.additionalstatus.api.capability.CapabilityMoistureHandler;
import shift.additionalstatus.api.capability.CapabilityStaminaHandler;
import shift.additionalstatus.api.capability.ItemDrink;
import shift.additionalstatus.capability.AdditionalPlayerData;
import shift.additionalstatus.capability.EntityPlayerManager;
import shift.additionalstatus.packet.ASPacketHandler;
import shift.additionalstatus.proxy.CommonProxy;

@Mod(modid = AdditionalStatus.MODID, version = AdditionalStatus.VERSION, guiFactory = "shift.additionalstatus.config.ASConfigGuiFactory")
public class AdditionalStatus {

	public static final String MODID = "AdditionalStatus";
	public static final String VERSION = "1.2.0";

	@Mod.Instance("AdditionalStatus")
	public static AdditionalStatus instance;

	@SidedProxy(modId = MODID, clientSide = "shift.additionalstatus.proxy.ClientProxy", serverSide = "shift.additionalstatus.proxy.CommonProxy")
	public static CommonProxy proxy;


	public static Item drinkingWaterBottle;

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
		CapabilityStaminaHandler.register();

		drinkingWaterBottle = new ItemDrink(4, 4, 0);
		drinkingWaterBottle.setCreativeTab(CreativeTabs.FOOD);
		drinkingWaterBottle.setMaxStackSize(1).setUnlocalizedName("as."+"drinking_water_bottle");
		GameRegistry.register(drinkingWaterBottle.setRegistryName(MODID, "DrinkingWaterBottle"));
		if (event.getSide().isClient()) {
            ModelLoader.setCustomModelResourceLocation(drinkingWaterBottle, 0, new ModelResourceLocation(MODID + ":" + "bottle_drinking_water", "inventory"));
        }

		GameRegistry.addSmelting(new ItemStack(Items.POTIONITEM), new ItemStack(drinkingWaterBottle), 0.1f);

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		// some example code
		//System.out.println("DIRT BLOCK >> " + Blocks.DIRT.getUnlocalizedName());
	}
}
