package shift.additionalstatus.asm.vanilla;

import net.minecraft.client.entity.EntityPlayerSP;
import shift.additionalstatus.AdditionalStatus;
import shift.additionalstatus.capability.EntityPlayerManager;

public class EntityPlayerSPMethod  {


    public static int isSprinting(int i) {

        try {

            EntityPlayerSP p = (EntityPlayerSP) AdditionalStatus.proxy.getClientPlayer();


            if (EntityPlayerManager.getStaminaStats(p).getStaminaLevel() == 0) return 0;

        } catch (Exception e) {

        }

        return 10;

    }

}