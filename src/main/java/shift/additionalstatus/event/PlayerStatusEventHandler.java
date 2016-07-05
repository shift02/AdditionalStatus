package shift.additionalstatus.event;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shift.additionalstatus.api.AdditionalStatusAPI;
import shift.additionalstatus.api.capability.CapabilityMoistureHandler;
import shift.additionalstatus.api.capability.IMoistureHandler;
import shift.additionalstatus.capability.EntityPlayerManager;

public class PlayerStatusEventHandler {

	/**バニラの食べ物を食べた時の動作*/
	/** 食べ物を食べた時の動作 */
    @SubscribeEvent
    public void onPlayerUseItemEvent(LivingEntityUseItemEvent.Finish event) {

        ItemStack item = event.getItem();
        EntityPlayer player = (EntityPlayer) event.getEntity();
        if(player.getEntityWorld().isRemote)return;
        
        if(!item.hasCapability(CapabilityMoistureHandler.MOISTURE_HANDLER_CAPABILITY, null))return;
        
        IMoistureHandler m = item.getCapability(CapabilityMoistureHandler.MOISTURE_HANDLER_CAPABILITY, null);
        AdditionalStatusAPI.playerManager.addMoistureStats(player, m.getMoisture(), m.getMoistureSaturation());
        
    }
    
    /**
     * 水分関係
     */

    // ブロック破壊
    @SubscribeEvent
    public void onBlockBreakEvent(BreakEvent event) {

        if (event.getWorld().isRemote) {
            return;
        }

        EntityPlayer player = event.getPlayer();

        float i = 0.025f;

        if (BiomeDictionary.isBiomeOfType(event.getWorld().getBiomeGenForCoords(event.getPos()), Type.SANDY)) {
            i *= 4;
        }
        if (event.getWorld().getBlockState(event.getPos()).getMaterial() == Material.SAND) {
            i *= 2;
        }

        AdditionalStatusAPI.addMoistureExhaustion(player, i);

    }

    // ダメージ
    @SubscribeEvent
    public void onLivingHurtEvent(LivingHurtEvent event) {

        if (event.getEntityLiving().worldObj.isRemote || !(event.getEntityLiving() instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer player = (EntityPlayer) event.getEntityLiving();

        AdditionalStatusAPI.addMoistureExhaustion(player, event.getAmount() * 0.2f);

    }

    //攻撃
    @SubscribeEvent
    public void onAttackEntityEvent(AttackEntityEvent event) {

        if (event.getEntityLiving().worldObj.isRemote || !(event.getEntityLiving() instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer player = (EntityPlayer) event.getEntityLiving();

        AdditionalStatusAPI.addMoistureExhaustion(player, 0.23f);

    }

    //ジャンプ
    @SubscribeEvent
    public void onLivingJump2(LivingJumpEvent event) {

        if (!(event.getEntityLiving() instanceof EntityPlayer)) {
            return;
        }

        if (event.getEntityLiving().worldObj.isRemote) {
            return;
        }

        EntityPlayer player = (EntityPlayer) event.getEntityLiving();

        float i = 1;

        if (BiomeDictionary.isBiomeOfType(player.worldObj.getBiomeGenForCoords(player.playerLocation), Type.SANDY)) {
            i = 2;
        }

        if (player.isSprinting()) {
        	AdditionalStatusAPI.addMoistureExhaustion(player, 0.1f * i);
        } else {
        	AdditionalStatusAPI.addMoistureExhaustion(player, 0.05f * i);
        }

    }

    /**
     * スタミナ関係
     */

    // ブロック破壊
    @SubscribeEvent
    public void onBlockBreakEventS(BreakEvent event) {

        if (event.getWorld().isRemote) {
            return;
        }

        EntityPlayer player = event.getPlayer();

        float i = 0.65f;

        if (BiomeDictionary
                .isBiomeOfType(
                        event.getWorld().getBiomeGenForCoords(event.getPos()),
                        Type.SANDY)) {
            i *= 4;
        }
        if (event.getWorld().getBlockState(event.getPos()).getMaterial() == Material.SAND) {
            i *= 2;
        }

        AdditionalStatusAPI.addStaminaExhaustion(player, i);

    }

    // ダメージ
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onLivingHurtEvent2(LivingHurtEvent event) {

        if (event.getEntityLiving().worldObj.isRemote || !(event.getEntityLiving() instanceof EntityPlayer)) {
            return;
        }

        if (event.isCanceled()) return;

        EntityPlayer player = (EntityPlayer) event.getEntityLiving();

        if (event.getAmount() < 0) return;

        float d = this.applyArmorCalculations(player, event.getSource(), event.getAmount());

        AdditionalStatusAPI.addStaminaExhaustion(player, d * 1.2f);

    }

    protected float applyArmorCalculations(EntityPlayer player, DamageSource p_70655_1_, float p_70655_2_) {

        if (!p_70655_1_.isUnblockable()) {
            int i = 25 - player.getTotalArmorValue();
            float f1 = p_70655_2_ * i;
            p_70655_2_ = f1 / 25.0F;
        }

        return p_70655_2_;

    }

    // ジャンプ
    @SubscribeEvent
    public void onLivingJump(LivingJumpEvent event) {

        if (!(event.getEntityLiving() instanceof EntityPlayer)) {
            return;
        }

        if (event.getEntityLiving().worldObj.isRemote) {
            return;
        }

        EntityPlayer player = (EntityPlayer) event.getEntityLiving();

        float i = 1;

        if (BiomeDictionary.isBiomeOfType(player.worldObj.getBiomeGenForCoords(player.playerLocation), Type.SANDY)) {
            i = 2;
        }

        if (player.isSprinting()) {
        	AdditionalStatusAPI.addStaminaExhaustion(player, 2.2f * i);
        } else {
        	AdditionalStatusAPI.addStaminaExhaustion(player, 0.5f * i);
        }

    }

    //攻撃
    @SubscribeEvent
    public void onAttackEntityEvent2(AttackEntityEvent event) {

        if (event.getEntityLiving().worldObj.isRemote || !(event.getEntityLiving() instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer player = (EntityPlayer) event.getEntityLiving();

        AdditionalStatusAPI.addStaminaExhaustion(player, 1.4f);

    }

    //ベットで回復
    @SubscribeEvent
    public void LivingSleepingEvent(PlayerWakeUpEvent event) {

        if (event.getEntityPlayer().worldObj.isRemote) {
            return;
        }

        if (!(event.getEntityPlayer() instanceof EntityPlayer)) {
            return;
        }

        if (event.updateWorld()) return;

        if (!event.getEntityLiving().worldObj.isRemote) {

            EntityPlayer player = event.getEntityPlayer();

            if (EntityPlayerManager.getMoistureStats(player).getMoistureLevel() > 4 && player.getFoodStats().getFoodLevel() > 4) {
                EntityPlayerManager.getStaminaStats(player).addStats(player, 100, 20.0f);
            } else {
                EntityPlayerManager.getStaminaStats(player).addStats(player, 40, 0.0f);
            }
            player.getFoodStats().addExhaustion(19.3f);
            AdditionalStatusAPI.addMoistureExhaustion(player, 20.3f);

        }

    }
	
}
