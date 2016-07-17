package shift.additionalstatus.asm.vanilla;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import shift.additionalstatus.ASConfig;
import shift.additionalstatus.api.AdditionalStatusAPI;

public class FoodStatsMethod {

    public static void onExhaustion(EntityPlayer player, float amount) {

        if (ASConfig.statusStamina) {
            AdditionalStatusAPI.addStaminaExhaustion(player, amount);
        } else {
            player.attackEntityFrom(DamageSource.starve, 1.0F);
        }
    }
}