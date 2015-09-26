package magicbees.bees.allele.flowerProvider;

import magicbees.main.utils.LocalizationManager;
import magicbees.main.utils.compat.ArsMagicaHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import forestry.api.genetics.IIndividual;
import forestry.api.genetics.IPollinatable;

public class FlowerProviderArsMagicaFlower extends FlowerProvider {
	
	public FlowerProviderArsMagicaFlower() {
		super(5);
		flowers.add(new FlowerImpl(ArsMagicaHelper.blackOrchid, 0, 1, true));
		flowers.add(new FlowerImpl(ArsMagicaHelper.desertNova, 0, 1, true));
		flowers.add(new FlowerImpl(ArsMagicaHelper.aum, 0, 1, true));
		flowers.add(new FlowerImpl(ArsMagicaHelper.wakebloom, 0, 1, true));
		flowers.add(new FlowerImpl(ArsMagicaHelper.tarmaRoot, 0, 1, true));
	}

	@Override
	public boolean growFlower(World world, IIndividual genome, int x, int y, int z) {
		boolean flag = false;
		Block blockDown = world.getBlock(x, y - 1, z);
		if (world.getBlock(x, y, z).isAir(world, x, y, z)) {
			if (blockDown == Blocks.dirt || blockDown == Blocks.grass) {
				int dart = world.rand.nextInt(100);
				Block block;
				if (dart > 60) {
					block = ArsMagicaHelper.blackOrchid;
				} else if (dart > 30) {
					block = ArsMagicaHelper.aum;
				} else {
					block = ArsMagicaHelper.tarmaRoot;
				}
				world.setBlock(x, y, z, block);
				flag = true;
			} else if (blockDown == Blocks.sand) {
				world.setBlock(x, y, z, ArsMagicaHelper.desertNova);
				flag = true;
			} else if (blockDown == Blocks.stone) {
				world.setBlock(x, y, z, ArsMagicaHelper.tarmaRoot);
				flag = true;
			} else if (blockDown == Blocks.water || blockDown == Blocks.water) {
				world.setBlock(x, y, z, ArsMagicaHelper.wakebloom);
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public String getDescription() {
		return LocalizationManager.getLocalizedString("flowerProvider.arsmagica");
	}

	@Override
	public ItemStack[] affectProducts(World world, IIndividual genome, int x, int y, int z, ItemStack[] products) {
		return products;
	}

	@Override
	public boolean isAcceptedPollinatable(World world, IPollinatable pollinatable) {
		return pollinatable.getPlantType().size() > 1;
	}

}
