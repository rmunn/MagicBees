package magicbees.util;

import com.google.common.base.Preconditions;
import elec332.core.java.ReflectionHelper;
import elec332.core.main.ElecCore;
import elec332.core.util.RegistryHelper;
import forestry.api.genetics.IAlleleSpeciesBuilder;
import forestry.apiculture.PluginApiculture;
import forestry.apiculture.blocks.BlockRegistryApiculture;
import forestry.apiculture.items.ItemRegistryApiculture;
import forestry.core.PluginCore;
import forestry.core.blocks.BlockRegistryCore;
import forestry.core.items.ItemRegistryCore;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;

/**
 * Created by Elec332 on 15-5-2017.
 */
public class Utils {

	public static ItemRegistryApiculture getApicultureItems(){
		return itemsA;
	}

	public static BlockRegistryApiculture getApicultureBlocks(){
		return blocksA;
	}

	public static ItemRegistryCore getCoreItems(){
		return itemsC;
	}

	public static BlockRegistryCore getCoreBlocks(){
		return blocksC;
	}

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

	public static AxisAlignedBB getAABB(BlockPos pos, int range, boolean y){
		int xCoord = pos.getX(), yCoord = pos.getY(), zCoord = pos.getZ();
		return new AxisAlignedBB(xCoord - range, yCoord - (y ? range : 0), zCoord - range, xCoord + range + 1, yCoord + 1 + (y ? range : 0), zCoord + range + 1);
	}

	private static final ItemRegistryApiculture itemsA;
	private static final BlockRegistryApiculture blocksA;
	private static final ItemRegistryCore itemsC;
	private static final BlockRegistryCore blocksC;

	static {
		try {
			itemsA = Preconditions.checkNotNull((ItemRegistryApiculture) ReflectionHelper.makeFinalFieldModifiable(PluginApiculture.class.getDeclaredField("items")).get(null));
			blocksA = Preconditions.checkNotNull((BlockRegistryApiculture) ReflectionHelper.makeFinalFieldModifiable(PluginApiculture.class.getDeclaredField("blocks")).get(null));
			itemsC = Preconditions.checkNotNull((ItemRegistryCore) ReflectionHelper.makeFinalFieldModifiable(PluginCore.class.getDeclaredField("items")).get(null));
			blocksC = Preconditions.checkNotNull((BlockRegistryCore) ReflectionHelper.makeFinalFieldModifiable(PluginCore.class.getDeclaredField("blocks")).get(null));
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}

}
