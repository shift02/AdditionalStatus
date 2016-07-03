package shift.additionalstatus.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import shift.additionalstatus.AdditionalStatus;
import shift.additionalstatus.capability.AdditionalPlayerData;
import shift.additionalstatus.capability.EntityPlayerManager;

public class MessagePlayerDataHandler implements IMessageHandler<PacketPlayerData, IMessage> {

	@Override
    public IMessage onMessage(PacketPlayerData message, MessageContext ctx) {

		EntityPlayer p = AdditionalStatus.proxy.getClientPlayer();

        if (p == null) return null;

        AdditionalPlayerData data = EntityPlayerManager.getAdditionalPlayerData(p);

        data.deserializeNBT(message.getData());


        return null;

    }
}