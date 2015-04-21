package magicbees.main.utils.compat;

import magicbees.bees.BeeManager;
import magicbees.main.Config;
import magicbees.main.utils.BlockInterface;
import magicbees.main.utils.ItemInterface;
import magicbees.main.utils.compat.botania.BotaniaAPIDistanceHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import vazkii.botania.api.mana.ManaItemHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BotaniaHelper {
	
	@SideOnly(Side.CLIENT)
	public static IIcon subtileIcons[];

	public static final String LEXICON_ENTRY_MANA_GEAR = "manaGear";
	
	public enum ManaResource {
		MANASTEEL,
		MANAPEARL,
		MANADIAMOND,
		LIVINGWOOD_TWIG,
		TERRASTEEL,
		LIFE_ESSENCE,
		REDSTONE_ROOT,
		ELEMENTIUM,
		PIXIE_DUST,
		DRAGONSTONE,
		PRISMARINE_SHARD,
		PLACEHOLDER,
		RED_STRING,
		DREAMWOOD_TWIG,
		GAIA_INGOT,
		ENDER_AIR_BOTTLE,
		MANA_STRING,
		;
	}
	
	public enum PastureSeed {
		GRASS,
		PODZOL,
		MYCELIUM,
		;
	}

	public static Block blockMysticalFlower;
	public static Block blockLivingRock;
	public static Block blockLivingWood;
	
	public static Item itemPetal;
	public static Item itemManaPetal;
	public static Item itemManaResource;
	public static Item itemPastureSeed;
	
	public static ItemStack[] mysticalFlowerVariants;
	
	public static IRecipe manasteelGrafterRecipe;
	public static IRecipe manasteelScoopRecipe;
	
	private static boolean isBotaniaActive = false;
	public static final String Name = "Botania";

	public static double beegoniaManaMultiplier;
	public static double hiveacynthManaMultiplier;
	public static double hiveacynthRainResistRate;
	public static double hiveacynthPrincessSpawnRate;
	public static double hiveacynthPristineRate;
	
	public static boolean isActive() {
		return isBotaniaActive;
	}

	public static void preInit() {
		if (Loader.isModLoaded(Name) && Config.botaniaActive) {
			isBotaniaActive = true;
		}
	}

	public static void init() {
		if (isActive()) {
			getBlocks();
			getItems();
			
			BotaniaAPIDistanceHelper.registerSubtiles();
		}
	}

	public static void postInit() {
		if (isActive()) {
			// Hiveacynth would appreciate it if this list existed.
			BeeManager.populateSpeciesListRarity();
			
			GameRegistry.addRecipe(new ItemStack(Config.manasteelScoop), new Object[] {
					"twt", "tmt", " t ",
					'm', new ItemStack(itemManaResource, 1, ManaResource.MANASTEEL.ordinal()),
					'w', Blocks.wool,
					't', new ItemStack(itemManaResource, 1, ManaResource.LIVINGWOOD_TWIG.ordinal())
			});
			manasteelScoopRecipe = (IRecipe) CraftingManager.getInstance().getRecipeList().get(CraftingManager.getInstance().getRecipeList().size() - 1);
			
			GameRegistry.addRecipe(new ItemStack(Config.manasteelGrafter), new Object[] {
				"  m", " t ", "t  ",
				'm', new ItemStack(itemManaResource, 1, ManaResource.MANASTEEL.ordinal()),
				't', new ItemStack(itemManaResource, 1, ManaResource.LIVINGWOOD_TWIG.ordinal())
			});
			manasteelGrafterRecipe = (IRecipe) CraftingManager.getInstance().getRecipeList().get(CraftingManager.getInstance().getRecipeList().size() - 1);
			
			BotaniaAPIDistanceHelper.setupCraftingAndLexicon();
		}
	}

	public static void getBlocks() {
		blockMysticalFlower = BlockInterface.getBlock(Name, "flower");
		blockLivingRock = BlockInterface.getBlock(Name, "livingrock");
		blockLivingWood = BlockInterface.getBlock(Name, "livingwood");
		
		// Generate 16-colour flowers.
		mysticalFlowerVariants = new ItemStack[16];
		for (int i = 0; i < 16; i++) {
			mysticalFlowerVariants[i] = new ItemStack(blockMysticalFlower, 1, i);
		}
	}

	public static void getItems() {
		itemPetal = ItemInterface.getItem(Name, "petal");
		itemManaPetal = ItemInterface.getItem(Name, "manaPetal");
		itemManaResource = ItemInterface.getItem(Name, "manaResource");
		itemPastureSeed = ItemInterface.getItem(Name, "grassSeeds");
	}
	
	public static void doBotaniaModuleConfigs(Configuration configuration) {
		Property p;
		String section = "botaniaPlugin";
		
		p = configuration.get(section, "beegoniaManaMultiplier", 1.0);
		p.comment = "Multiplier for the Beegonia's mana generation. Default: 1.0 (Affects duration, not throughput)";
		beegoniaManaMultiplier = p.getDouble();
		
		p = configuration.get(section, "hiveacynthManaMultiplier", 1.0);
		p.comment = "Multiplier for the Hiveacynth's mana consumption. Default: 1.0";
		hiveacynthManaMultiplier = p.getDouble();
		
		p = configuration.get(section, "hiveacynthRainResistRate", 0.1);
		p.comment = "Rate at which the Hiveacynth applies rain resist to spawned bees. Default: 0.1 Setting to 0 will disable.";
		hiveacynthRainResistRate = p.getDouble();
		
		p = configuration.get(section, "hiveacynthPrincessSpawnRate", 0.09);
		p.comment = "Rate at which the Hiveacynth will spawn a Princess instead of a Drone. Default: 0.09. Setting to 0 will disable.";
		hiveacynthPrincessSpawnRate = p.getDouble();
		
		p = configuration.get(section, "hiveacynthPristineRate", 0.15);
		p.comment = "Rate at which the Hiveacynth will produce a Pristine Princess, when it produces a princess. Default: 0.15. Setting to 0 will disable.";
		hiveacynthPristineRate = p.getDouble();
	}

	public static boolean requestMana(ItemStack stack, EntityPlayer player, int manaPerDamage, int charges) {
		return ManaItemHandler.requestManaExactForTool(stack, player, manaPerDamage * charges, true);
	}
}
