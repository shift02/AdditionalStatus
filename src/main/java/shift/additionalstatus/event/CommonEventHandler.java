package shift.additionalstatus.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shift.additionalstatus.capability.AdditionalPlayerData;
import shift.additionalstatus.capability.EntityPlayerManager;

public class CommonEventHandler {

	public static Entity boat = new EntityDummy(null);


	@SubscribeEvent
	public void onLivingJumpEvent(LivingJumpEvent event) {

		if(event.getEntityLiving().worldObj.isRemote)return;


		if(!(event.getEntityLiving() instanceof EntityPlayer))return;

		EntityPlayer p = (EntityPlayer) event.getEntityLiving();

		if(!p.isSneaking())return;

		if(p.isRiding())return;


		if(p.motionX!=0.0)return;
		if(p.motionZ!=0.0)return;
		AdditionalPlayerData data = EntityPlayerManager.getAdditionalPlayerData(p);

		data.getSit().jump = true;

		if(data.getSit().isSit){

			data.getSit().prevSit = false;

		}else{

			data.getSit().prevSit = true;

		}

	}

	public static class EntityDummy extends Entity{

		public EntityDummy(World worldIn) {
			super(worldIn);
			this.setSize(0.0F, 0.0F);
		}

		@Override
		protected void entityInit() {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		protected void readEntityFromNBT(NBTTagCompound compound) {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		protected void writeEntityToNBT(NBTTagCompound compound) {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public double getMountedYOffset() {
			return 0.0;
		}

	}

}
