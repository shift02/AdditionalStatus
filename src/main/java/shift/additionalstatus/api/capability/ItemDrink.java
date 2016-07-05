package shift.additionalstatus.api.capability;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import shift.additionalstatus.api.capability.template.MoistureHandler;

/**
 * 飲み物のサンプル
 * @author Shift02ss
 *
 */
public class ItemDrink extends Item{
	
	public int moisture;
	public float moistureSaturation;
	public float moistureExhaustion;
	
	public ItemDrink(int m1,float m2, float m3){
		this.moisture = m1;
		this.moistureSaturation = m2;
		this.moistureExhaustion = m3;
	}

	@Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt)
    {
        return new MoistureHandler(moisture, moistureSaturation, moistureExhaustion);
    }
	
}
