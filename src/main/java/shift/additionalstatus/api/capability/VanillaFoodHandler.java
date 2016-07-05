package shift.additionalstatus.api.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shift.additionalstatus.api.AdditionalStatusAPI;
import shift.additionalstatus.api.capability.template.MoistureHandler;
import shift.additionalstatus.capability.EntityPlayerManager;

public class VanillaFoodHandler {
	
	public ResourceLocation moisture = new ResourceLocation(AdditionalStatusAPI.MODID, "IMoistureHandler");

	/**バニラの食べ物に水分とスタミナのステータスを追加*/
	@SubscribeEvent
	public void addStatus(AttachCapabilitiesEvent.Item evt) {
		
		Item item = evt.getItem();
		ItemStack food = evt.getItemStack();
		if(food==null)return;
		if(item==null)return;
		
		//水入り瓶
        if (item == Items.POTIONITEM && item.getDamage(food) == 0) {

        	evt.addCapability(moisture, new MoistureHandler(3, 0, 0));
            //SextiarySectorAPI.playerManager.addMoistureStats(player, 3, 0);
            //player.addExhaustion(4.5f);
            //player.addPotionEffect(new PotionEffect(Potion.hunger.getId(), 30, 0));
        }
		
		//きのこシチュー
        if (item == Items.MUSHROOM_STEW) {
        	
        	evt.addCapability(moisture, new MoistureHandler(2, 2, 0));
        	
        }
		
	}
	
}
