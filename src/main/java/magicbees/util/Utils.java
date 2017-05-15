package magicbees.util;

import com.google.common.base.Preconditions;
import elec332.core.util.RegistryHelper;
import net.minecraft.block.Block;
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

}
