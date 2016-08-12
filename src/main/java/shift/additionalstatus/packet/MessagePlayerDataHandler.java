package shift.additionalstatus.packet;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import shift.additionalstatus.AdditionalStatus;
import shift.additionalstatus.capability.AdditionalPlayerData;
import shift.additionalstatus.capability.EntityPlayerManager;

public class MessagePlayerDataHandler implements IMessageHandler<PacketPlayerData, IMessage> {

	@Override
    public IMessage onMessage(PacketPlayerData message, MessageContext ctx) {

		EntityPlayer p = getPlayer(message);

        if (p == null) return null;

        AdditionalPlayerData data = EntityPlayerManager.getAdditionalPlayerData(p);

        data.deserializeNBT(message.getData());


        return null;

    }

	public EntityPlayer getPlayer(PacketPlayerData message) {

        NBTTagCompound nbt = message.getData();

        if (nbt.hasKey("uuid")) {

            UUID uuid = UUID.fromString(nbt.getString("uuid"));

            //自分自身は更新しない
            if (uuid.equals(AdditionalStatus.proxy.getClientPlayer().getUniqueID())) return null;

            List<EntityPlayer> player = AdditionalStatus.proxy.getClientPlayer().worldObj.playerEntities;

            for (EntityPlayer p : player) {
                if (p.getUniqueID().equals(uuid)) {

                    return p;

                }
            }

        } else {
            return AdditionalStatus.proxy.getClientPlayer();
        }

        return null;

    }
}