package magicbees.bees.allele.effect;

import java.util.Locale;

import magicbees.api.bees.ITransmutationEffectLogic;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class TransmutationEffectRailcraft implements ITransmutationEffectLogic {
	@Override
	public boolean tryTransmutation(World world, Biome biome, ItemStack sourceBlock, BlockPos blockPos) {
		boolean flag = false;
		if (OreDictionary.itemMatches(new ItemStack(Blocks.STONE), sourceBlock, false)
				|| OreDictionary.itemMatches(new ItemStack(Blocks.COBBLESTONE), sourceBlock, false)) {
			flag = trySpawnQuarriedStone(world, biome, sourceBlock, blockPos) || trySpawnAbyssalStone(world, biome, sourceBlock, blockPos);
		}
		return flag;
	}

	private boolean trySpawnQuarriedStone(World world, Biome biome, ItemStack sourceBlock, BlockPos blockPos) {
		boolean flag = false;
		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.FOREST) && !BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SNOWY)) {
			//TODO set valid item name from new Railcraft
			ItemStack item = GameRegistry.makeItemStack("Railcraft:cube.stone.quarried",0, 1, null);
			if (item != null) {
				world.setBlockState(blockPos, Block.getBlockFromItem(item.getItem()).getStateFromMeta(item.getItemDamage()), 2);
				flag = true;
			}
		}
		return flag;
	}

	private boolean trySpawnAbyssalStone(World world, Biome biome, ItemStack sourceBlock, BlockPos blockPos) {
		boolean flag = false;
		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.WATER) && !biome.getBiomeName().toLowerCase(Locale.ENGLISH).contains("river")) {
			ItemStack item = GameRegistry.makeItemStack("Railcraft:cube.stone.abyssal", 0, 1, null);
			if (item != null) {
				world.setBlockState(blockPos, Block.getBlockFromItem(item.getItem()).getStateFromMeta(item.getItemDamage()), 2);
				flag = true;
			}
		}
		return flag;
	}
}
