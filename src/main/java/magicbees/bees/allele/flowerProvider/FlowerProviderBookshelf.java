package magicbees.bees.allele.flowerProvider;

import java.util.EnumSet;

import magicbees.main.utils.LocalizationManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.oredict.OreDictionary;
import forestry.api.genetics.IIndividual;
import forestry.api.genetics.IPollinatable;

public class FlowerProviderBookshelf extends FlowerProvider {
	
	public FlowerProviderBookshelf() {
		super(1);
		flowers.add(new FlowerImpl(Blocks.bookshelf, OreDictionary.WILDCARD_VALUE, 1, false));
	}

	public boolean isAcceptedFlower(World world, IIndividual genome, int x, int i, int j) {
		boolean flag = false;
		if (world.getBlock(x, i, j) == Blocks.bookshelf) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean growFlower(World world, IIndividual genome, int x, int i, int j) {
		return true;
	}

	@Override
	public ItemStack[] affectProducts(World world, IIndividual genome, int x, int i, int j, ItemStack products[]) {
		return products;
	}

	@Override
	public boolean isAcceptedPollinatable(World world, IPollinatable pollinatable) {
		EnumSet<EnumPlantType> types = pollinatable.getPlantType();
		return types.size() > 0 && !types.contains(EnumPlantType.Nether) && !types.contains(EnumPlantType.Water);
	}

	@Override
	public String getDescription() {
		return LocalizationManager.getLocalizedString("flowerProvider.book");
	}
}
