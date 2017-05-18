package magicbees.util;

import com.google.common.base.Preconditions;
import elec332.core.main.ElecCore;
import elec332.core.util.RegistryHelper;
import forestry.api.apiculture.IAlleleBeeSpeciesBuilder;
import forestry.api.genetics.IAlleleSpeciesBuilder;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * Created by Elec332 on 15-5-2017.
 */
public class Utils {

	@Nonnull
	public static Block getBlock(String mod, String name){
		return Preconditions.checkNotNull(RegistryHelper.getBlockRegistry().getValue(new ResourceLocation(mod, name)));
	}

	@Nonnull
	public static Item getItem(String mod, String name){
		return Preconditions.checkNotNull(RegistryHelper.getItemRegistry().getValue(new ResourceLocation(mod, name)));
	}

	public static void setSecret(IAlleleSpeciesBuilder builder){
		if (!ElecCore.developmentEnvironment) {
			builder.setIsSecret();
		}
	}

}
