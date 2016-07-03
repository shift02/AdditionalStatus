package shift.additionalstatus.config;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import shift.additionalstatus.ASConfig;
import shift.additionalstatus.AdditionalStatus;

public class ASConfigGui extends GuiConfig {

	public ASConfigGui(GuiScreen parent) {
		super(parent, getConfigElements(), AdditionalStatus.MODID, false, false,
				I18n.format(ASConfig.SS_LANG + "title"));
    }

	private static List<IConfigElement> getConfigElements() {

		List<IConfigElement> list = Lists.newArrayList();

		for (String name : ASConfig.config.getCategoryNames()) {

			ConfigCategory category = ASConfig.config.getCategory(name);

			if (category.showInGui()) {

				list.add(new ConfigElement(category));
			}
		}

		return list;
	}
}