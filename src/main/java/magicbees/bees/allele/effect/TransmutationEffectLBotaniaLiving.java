package magicbees.bees.allele.effect;

import magicbees.api.bees.ITransmutationEffectLogic;
import magicbees.main.utils.compat.BotaniaHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.oredict.OreDictionary;

public class TransmutationEffectLBotaniaLiving implements ITransmutationEffectLogic {

	@Override
	public boolean tryTransmutation(World world, Biome biome, ItemStack sourceBlock, BlockPos blockPos) {
		int[] oreIDs = OreDictionary.getOreIDs(sourceBlock);
		for (int oreId : oreIDs) {
			if (oreId == OreDictionary.getOreID("logWood")) {
				world.setBlockState(blockPos, BotaniaHelper.blockLivingWood.getDefaultState());
				return true;
			}
			else if (oreId == OreDictionary.getOreID("stone")) {
				world.setBlockState(blockPos, BotaniaHelper.blockLivingRock.getDefaultState());
				return true;
			}
			else if (oreId == OreDictionary.getOreID("livingwood")) {
				world.setBlockState(blockPos, BotaniaHelper.blockDreamWood.getDefaultState());
				return true;
			}
		}
		return false;
	}
}
