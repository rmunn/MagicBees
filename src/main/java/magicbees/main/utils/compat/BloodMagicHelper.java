package magicbees.main.utils.compat;

import WayofTime.alchemicalWizardry.api.altarRecipeRegistry.AltarRecipeRegistry;
import WayofTime.alchemicalWizardry.api.bindingRegistry.BindingRegistry;
import cpw.mods.fml.common.Loader;
import magicbees.bees.BeeSpecies;
import magicbees.main.Config;
import magicbees.main.utils.BlockInterface;
import magicbees.main.utils.ItemInterface;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import forestry.api.apiculture.EnumBeeType;

public class BloodMagicHelper
{

	//--- Blood Magic Blocks ---
	public static Block bloodStoneBrick;
	public static Block masterRitualStone;
	//--- Blood Magic Items ---
	public static ItemStack bloodShard;
	public static ItemStack incendium;
	public static ItemStack magicales;
	public static ItemStack sanctus;
	public static ItemStack aether;
	public static ItemStack crepitous;
	public static ItemStack crystallos;
	public static ItemStack terrae;
	public static ItemStack aquasalus;
	public static ItemStack tennebrae;

	private static boolean isBloodMagicActive = false;
	public static final String Name = "AWWayofTime";

	public static boolean isActive()
	{
		return isBloodMagicActive;
	}

	public static void preInit()
	{
		if (Loader.isModLoaded(Name) && Config.bloodMagicActive)
		{
			isBloodMagicActive = true;
		}
	}

	public static void init()
	{
		if (isActive())
		{
			getBlocks();
			getItems();
		}
	}

	public static void postInit()
	{
		if (isActive()) {
			// Disabled for now until we can figure out a better way to match these.
			/*BloodMagicHelper.addAltarRecipeBee(BeeSpecies.MYSTICAL, BeeSpecies.BM_BLOODY, 1, 1000, 100, 100);	
			BloodMagicHelper.addBindingRecipeBee(BeeSpecies.BM_BLOODY, BeeSpecies.BM_BOUND);*/
		}
	}

	public static void getBlocks()
	{
		bloodStoneBrick = BlockInterface.getBlock(Name, "bloodStoneBrick");
		masterRitualStone = BlockInterface.getBlock(Name, "blockMasterStone");
	}

	public static void getItems()
	{
		bloodShard = ItemInterface.getItemStack(Name, "weakBloodShard");
		incendium = ItemInterface.getItemStack(Name, "incendium");
		magicales = ItemInterface.getItemStack(Name, "magicales");
		sanctus = ItemInterface.getItemStack(Name, "sanctus");
		aether = ItemInterface.getItemStack(Name, "aether");
		crepitous = ItemInterface.getItemStack(Name, "crepitous");
		crystallos = ItemInterface.getItemStack(Name, "crystallos");
		terrae = ItemInterface.getItemStack(Name, "terrae");
		aquasalus = ItemInterface.getItemStack(Name, "aquasalus");
		tennebrae = ItemInterface.getItemStack(Name, "tennebrae");
	}

	public static void addAltarRecipe(ItemStack requiredItem, ItemStack result, int minTier, int liquidRequired, int consumptionRate, int drainRate, boolean canBeFilled)
	{
		AltarRecipeRegistry.registerAltarRecipe(result, requiredItem, minTier, liquidRequired, consumptionRate, drainRate, canBeFilled);
	}

	public static void addAltarRecipeBee(BeeSpecies inputBee, BeeSpecies outputBee, int minTier, int liquidRequired, int consumptionRate, int drainRate)
	{
		addAltarRecipe(inputBee.getBeeItem(EnumBeeType.DRONE), outputBee.getBeeItem(EnumBeeType.DRONE), minTier, liquidRequired, consumptionRate, drainRate, false);
		addAltarRecipe(inputBee.getBeeItem(EnumBeeType.PRINCESS), outputBee.getBeeItem(EnumBeeType.PRINCESS), minTier, liquidRequired, consumptionRate, drainRate, false);
		addAltarRecipe(inputBee.getBeeItem(EnumBeeType.QUEEN), outputBee.getBeeItem(EnumBeeType.QUEEN), minTier, liquidRequired, consumptionRate, drainRate, false);
	}

	public static void addBindingRecipe(ItemStack input, ItemStack output)
	{
		BindingRegistry.registerRecipe(output, input);
	}

	public static void addBindingRecipeBee(BeeSpecies inputBee, BeeSpecies outputBee)
	{
		addBindingRecipe(inputBee.getBeeItem(EnumBeeType.DRONE), outputBee.getBeeItem(EnumBeeType.DRONE));
		addBindingRecipe(inputBee.getBeeItem(EnumBeeType.PRINCESS), outputBee.getBeeItem(EnumBeeType.PRINCESS));
		addBindingRecipe(inputBee.getBeeItem(EnumBeeType.QUEEN), outputBee.getBeeItem(EnumBeeType.QUEEN));
	}
}
