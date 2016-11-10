package magicbees.bees.allele.effect;

import java.util.HashMap;

import magicbees.bees.AlleleEffect;
import magicbees.bees.BeeManager;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.IBeeModifier;
import forestry.api.genetics.IEffectData;

public class AlleleEffectCrumbling extends AlleleEffect {
	private static HashMap<ItemStack, ItemStack> crumbleMap = new HashMap<ItemStack, ItemStack>();

	public static void addPairToMap(ItemStack source, ItemStack target) {
		boolean add = true;
		for (ItemStack i : crumbleMap.keySet()) {
			if (OreDictionary.itemMatches(i, source, false)) {
				add = false;
				break;
			}
		}
		if (add) {
			crumbleMap.put(source, target);
		}
	}

	public AlleleEffectCrumbling(String id, boolean isDominant) {
		super(id, isDominant, 600);

		addPairToMap(new ItemStack(Blocks.STONE), new ItemStack(Blocks.COBBLESTONE));
		addPairToMap(new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.MOSSY_COBBLESTONE));
		addPairToMap(new ItemStack(Blocks.STONEBRICK, 1, 0), new ItemStack(Blocks.STONEBRICK, 1, 2));
		addPairToMap(new ItemStack(Blocks.STONEBRICK, 1, 2), new ItemStack(Blocks.STONEBRICK, 1, 1));
		addPairToMap(new ItemStack(Blocks.COBBLESTONE_WALL), new ItemStack(Blocks.COBBLESTONE_WALL, 1, 1));
		addPairToMap(new ItemStack(Blocks.GRAVEL), new ItemStack(Blocks.SAND));
	}

	@Override
	public IEffectData validateStorage(IEffectData storedData) {
		if (storedData == null || !(storedData instanceof magicbees.bees.allele.effect.EffectData)) {
			storedData = new magicbees.bees.allele.effect.EffectData(1, 0, 0);
		}
		return storedData;
	}

	@Override
	protected IEffectData doEffectThrottled(IBeeGenome genome, IEffectData storedData, IBeeHousing housing) {
		World world = housing.getWorldObj();
		BlockPos coords = housing.getCoordinates();
		IBeeModifier beeModifier = BeeManager.beeRoot.createBeeHousingModifier(housing);

		// Get random coords within territory
		int xRange = (int) (beeModifier.getTerritoryModifier(genome, 1f) * genome.getTerritory().getX());
		int yRange = (int) (beeModifier.getTerritoryModifier(genome, 1f) * genome.getTerritory().getY());
		int zRange = (int) (beeModifier.getTerritoryModifier(genome, 1f) * genome.getTerritory().getZ());

		int xCoord = coords.getX() + world.rand.nextInt(xRange) - xRange / 2;
		int yCoord = coords.getY() + world.rand.nextInt(yRange) - yRange / 2;
		int zCoord = coords.getZ() + world.rand.nextInt(zRange) - zRange / 2;
		BlockPos pos = new BlockPos(xCoord, yCoord, zCoord);
		IBlockState block = world.getBlockState(pos);
		if (block != null) {
			ItemStack source = new ItemStack(block.getBlock(), 1, block.getBlock().getMetaFromState(block));
			for (ItemStack key : crumbleMap.keySet()) {
				if (OreDictionary.itemMatches(source, key, false)) {
					ItemStack target = crumbleMap.get(key);
					world.setBlockState(pos, Block.getBlockFromItem(target.getItem()).getStateFromMeta(target.getItemDamage()), 2);

					break;
				}
			}
		}

		storedData.setInteger(0, 0);

		return storedData;
	}

}
