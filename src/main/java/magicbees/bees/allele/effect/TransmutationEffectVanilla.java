package magicbees.bees.allele.effect;

import magicbees.api.bees.ITransmutationEffectLogic;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.oredict.OreDictionary;

public class TransmutationEffectVanilla implements ITransmutationEffectLogic {
	@Override
	public boolean tryTransmutation(World world, Biome biome, ItemStack sourceBlock, BlockPos blockPos) {
		return trySpawnSandstone(world, biome, sourceBlock, blockPos);
	}

	private boolean trySpawnSandstone(World world, Biome biome, ItemStack sourceBlock, BlockPos blockPos) {
		boolean flag = false;
		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SANDY) && OreDictionary.itemMatches(new ItemStack(Blocks.SAND), sourceBlock, false)) {
			world.setBlockState(blockPos, Blocks.SANDSTONE.getDefaultState());
			flag = true;
		}
		return flag;
	}
}
