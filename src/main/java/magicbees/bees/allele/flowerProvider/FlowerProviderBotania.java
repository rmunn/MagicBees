package magicbees.bees.allele.flowerProvider;

import magicbees.main.utils.LocalizationManager;
import magicbees.main.utils.compat.BotaniaHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import forestry.api.genetics.IIndividual;
import forestry.api.genetics.IPollinatable;

public class FlowerProviderBotania extends FlowerProvider {
	
	public FlowerProviderBotania() {
		super(1);
		flowers.add(new FlowerImpl(BotaniaHelper.blockMysticalFlower, OreDictionary.WILDCARD_VALUE, 1, true));
	}

	@Override
	public boolean growFlower(World world, IIndividual individual, int x, int y, int z) {
		if (world.isAirBlock(x, y, z) && BotaniaHelper.blockMysticalFlower.canPlaceBlockAt(world, x, y, z)) {
			world.setBlock(x, y, z, BotaniaHelper.blockMysticalFlower);
			world.setBlockMetadataWithNotify(x, y, z, world.rand.nextInt(16), 2);
		}
		return false;
	}

	@Override
	public ItemStack[] affectProducts(World world, IIndividual individual, int x, int y, int z, ItemStack[] products) {
		return products;
	}

	@Override
	public String getDescription() {
		return LocalizationManager.getLocalizedString("flowerProvider.botania");
	}

	@Override
	public boolean isAcceptedFlower(World world, IIndividual individual, int x, int y, int z) {
		return world.getBlock(x, y, z) == BotaniaHelper.blockMysticalFlower;
	}

	@Override
	public boolean isAcceptedPollinatable(World world, IPollinatable pollinatable) {
		return pollinatable.getPlantType().size() > 0;
	}
}
