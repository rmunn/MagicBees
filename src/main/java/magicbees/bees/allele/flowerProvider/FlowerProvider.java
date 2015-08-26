package magicbees.bees.allele.flowerProvider;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import forestry.api.genetics.IFlower;
import forestry.api.genetics.IFlowerProvider;
import forestry.api.genetics.IIndividual;
import forestry.api.genetics.IPollinatable;

public abstract class FlowerProvider implements IFlowerProvider {

	protected List<IFlower> flowers;

	public FlowerProvider(int size) {
		flowers = new ArrayList<IFlower>(size);
	}

	@Override
	public boolean isAcceptedFlower(World world, IIndividual genome, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		for (IFlower flower : flowers) {
			if (flower.getBlock() == block) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<IFlower> getFlowers() {
		return flowers;
	}

	@Override
	public abstract boolean isAcceptedPollinatable(World world, IPollinatable pollinatable);

	@Override
	public abstract boolean growFlower(World world, IIndividual individual, int x, int y, int z);

	@Override
	public abstract String getDescription();

	@Override
	public abstract ItemStack[] affectProducts(World world, IIndividual individual, int x, int y, int z, ItemStack[] products);

}