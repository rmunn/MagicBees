package magicbees.bees.allele.flowerProvider;

import magicbees.main.utils.LocalizationManager;
import magicbees.main.utils.compat.ThaumcraftHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import forestry.api.genetics.IIndividual;
import forestry.api.genetics.IPollinatable;

public class FlowerProviderThaumcraftFlower extends FlowerProvider {
	
	public FlowerProviderThaumcraftFlower() {
		super(2);
		flowers.add(new FlowerImpl(ThaumcraftHelper.plant, ThaumcraftHelper.BlockPlant.SHIMMERLEAF.ordinal(), 1, true));
		flowers.add(new FlowerImpl(ThaumcraftHelper.plant, ThaumcraftHelper.BlockPlant.CINDERPEARL.ordinal(), 1, true));
	}

	@Override
	public boolean isAcceptedFlower(World world, IIndividual genome, int x, int y, int z) {
		if (world.getBlock(x, y, z) == ThaumcraftHelper.plant) {
			int meta = world.getBlockMetadata(x, y, z);
			if (meta == ThaumcraftHelper.BlockPlant.SHIMMERLEAF.ordinal()
					|| meta == ThaumcraftHelper.BlockPlant.CINDERPEARL.ordinal()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean growFlower(World world, IIndividual genome, int x, int y, int z) {
		boolean flag = false;
		Block blockDown = world.getBlock(x, y - 1, z);
		if (world.getBlock(x, y, z).isAir(world, x, y, z)) {
			if (blockDown == Blocks.dirt || blockDown == Blocks.grass) {
				world.setBlock(x, y, z, ThaumcraftHelper.plant, ThaumcraftHelper.BlockPlant.SHIMMERLEAF.ordinal(), 2);
				flag = true;
			} else if (blockDown == Blocks.sand) {
				world.setBlock(x, y, z, ThaumcraftHelper.plant, ThaumcraftHelper.BlockPlant.CINDERPEARL.ordinal(), 2);
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public String getDescription() {
		return LocalizationManager.getLocalizedString("flowerProvider.magic");
	}

	@Override
	public ItemStack[] affectProducts(World world, IIndividual genome, int x, int y, int z, ItemStack[] products) {
		return products;
	}

	@Override
	public boolean isAcceptedPollinatable(World world, IPollinatable pollinatable) {
		return 0 < pollinatable.getPlantType().size();
	}

}
