package shift.additionalstatus.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shift.additionalstatus.ASConfig;
import shift.additionalstatus.AdditionalStatus;

public class ClientEventHandler {

	@SideOnly(Side.CLIENT)
	public static Minecraft mc = FMLClientHandler.instance().getClient();

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onConfigChanged(ConfigChangedEvent event) {

		if (event.getModID().equals(AdditionalStatus.MODID)) {

			ASConfig.syncConfig();
		}
	}

}
