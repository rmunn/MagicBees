package magicbees.main.utils.compat.botania;

import magicbees.bees.BeeManager;
import magicbees.main.utils.compat.ForestryHelper;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.recipe.RecipeManaInfusion;
import forestry.api.apiculture.EnumBeeType;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.apiculture.IBee;

public class SpeciesRecipeManaInfusion extends RecipeManaInfusion {

	private IAlleleBeeSpecies outputSpecies;
	private IAlleleBeeSpecies inputSpecies;
	private EnumBeeType beeType;
	
	private ItemStack outputCache;

	public SpeciesRecipeManaInfusion(IAlleleBeeSpecies outSpecies, IAlleleBeeSpecies inSpecies, int mana, EnumBeeType type) {
		super(BeeManager.getDefaultItemStackForSpecies(outSpecies, type), BeeManager.getDefaultItemStackForSpecies(inSpecies, type), mana);
		outputSpecies = outSpecies;
		inputSpecies = inSpecies;
		this.beeType = type;
	}
	
	@Override
	public boolean matches(ItemStack stack) {
		if (!BeeManager.beeRoot.isMember(stack, beeType.ordinal())) {
			return false;
		}
		
		IBee bee = BeeManager.beeRoot.getMember(stack);
		if (bee.getGenome().getPrimary().equals(inputSpecies)) {
			outputCache = ForestryHelper.replaceSpecies(stack, outputSpecies);
			return true;
		}

		return false;
	}
	
	@Override
	public ItemStack getOutput() {
		ItemStack currentOutput = outputCache;
		outputCache = super.getOutput();
		return currentOutput;
	}

}
