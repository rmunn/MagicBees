package magicbees.bees.allele.flowerProvider;

import java.util.ArrayList;
import java.util.List;

import magicbees.main.utils.LocalizationManager;
import magicbees.main.utils.compat.ThaumcraftHelper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.api.nodes.INode;
import forestry.api.genetics.IFlower;
import forestry.api.genetics.IIndividual;
import forestry.api.genetics.IPollinatable;

public class FlowerProviderAuraNode extends FlowerProvider {
	
	public FlowerProviderAuraNode() {
		super(2);
		flowers.add(new FlowerImpl(ThaumcraftHelper.airy, ThaumcraftHelper.AiryBlockType.NODE.ordinal(), 1, false));
		flowers.add(new FlowerImpl(ThaumcraftHelper.airy, ThaumcraftHelper.AiryBlockType.ENERGIZED_NODE.ordinal(), 1, false));
	}

	@Override
	public boolean isAcceptedFlower(World world, IIndividual genome, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		if (block.hasTileEntity(meta)) {
			TileEntity tileEntity = world.getTileEntity(x, y, z);
			if (tileEntity instanceof INode) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isAcceptedPollinatable(World world, IPollinatable pollinatable) {
		return pollinatable.getPlantType().size() > 0;
	}

	@Override
	public boolean growFlower(World world, IIndividual genome, int x, int y, int z) {
		return true;
	}

	@Override
	public String getDescription() {
		return LocalizationManager.getLocalizedString("flowerProvider.node");
	}

	@Override
	public ItemStack[] affectProducts(World world, IIndividual genome, int x, int y, int z, ItemStack[] products) {
		if (isAcceptedFlower(world, genome, x, y, z)) {
			
		}
		return products;
	}

	protected ItemStack[] addItemToProducts(ItemStack[] products, ItemStack itemStack) {
		for (ItemStack stack : products) {
			if (stack.getItem() == itemStack.getItem() && stack.getItemDamage() == itemStack.getItemDamage()) {
				if (stack.stackSize < stack.getItem().getItemStackLimit(stack)) {
					stack.stackSize += itemStack.stackSize;
					itemStack.stackSize = Math.max(stack.stackSize - stack.getItem().getItemStackLimit(stack), 0);
				}
			}
		}

		if (itemStack.stackSize > 0) {
			ItemStack[] newProducts = new ItemStack[products.length + 1];
			for (int i = 0; i < products.length; ++i) {
				newProducts[i] = products[i];
			}
			newProducts[products.length] = itemStack;
			products = newProducts;
		}

		return products;
	}

	@Override
	public List<IFlower> getFlowers() {
		return new ArrayList<IFlower>();
	}

}
