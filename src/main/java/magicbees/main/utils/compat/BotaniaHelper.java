package magicbees.main.utils.compat;

import magicbees.bees.BeeManager;
import magicbees.main.Config;
import magicbees.main.utils.BlockInterface;
import magicbees.main.utils.ItemInterface;
import magicbees.main.utils.compat.botania.BotaniaLexiconEntry;
import magicbees.main.utils.compat.botania.BotaniaSignature;
import magicbees.main.utils.compat.botania.LexiconEntries;
import magicbees.main.utils.compat.botania.SubTileBeegonia;
import magicbees.main.utils.compat.botania.SubTileHiveacynth;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconPage;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.api.subtile.SubTileEntity;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BotaniaHelper {
	
	@SideOnly(Side.CLIENT)
	public static IIcon subtileIcons[];
	
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

	public static Block blockLivingRock;
	public static Block blockLivingWood;
	
	public static Item itemPetal;
	public static Item itemManaPetal;
	public static Item itemManaResource;
	
	private static boolean isBotaniaActive = false;
	public static final String Name = "Botania";

	public static double beegoniaManaMultiplier;
	public static double hiveacynthManaMultiplier;
	public static double hiveacynthRainResistRate;
	public static double hiveacynthPrincessSpawnRate;
	
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
			
			registerSubtile(SubTileBeegonia.NAME, SubTileBeegonia.class);
			registerSubtile(SubTileHiveacynth.NAME, SubTileHiveacynth.class);
		}
	}

	public static void postInit() {
		if (isActive()) {
			// Hiveacynth would appreciate it if this list existed.
			BeeManager.populateSpeciesListRarity();

			RecipePetals beegoniaRecipe = BotaniaAPI.registerPetalRecipe(BotaniaAPI.internalHandler.getSubTileAsStack(SubTileBeegonia.NAME), new Object[] {
					new ItemStack(itemPetal), new ItemStack(itemManaPetal), new ItemStack(itemPetal, 1, 4), new ItemStack(itemPetal, 1, 4),
					new ItemStack(itemPetal, 1, 4), new ItemStack(itemManaPetal, 1, 4), new ItemStack(itemManaPetal, 1, 15)
				});
			LexiconEntries.entryBeegonia = new BotaniaLexiconEntry(SubTileBeegonia.NAME, BotaniaAPI.categoryGenerationFlowers);
			LexiconEntries.entryBeegonia.setLexiconPages(new LexiconPage[] {
					BotaniaAPI.internalHandler.textPage("magicbees.botania.lexicon.beegonia.0"),
					BotaniaAPI.internalHandler.textPage("magicbees.botania.lexicon.beegonia.1"),
					BotaniaAPI.internalHandler.petalRecipePage("magicbees.botania.lexicon.beegonia.crafting", beegoniaRecipe),
					BotaniaAPI.internalHandler.textPage("magicbees.botania.lexicon.beegonia.2")
				});
			
			RecipePetals hiveacynthRecipe = BotaniaAPI.registerPetalRecipe(BotaniaAPI.internalHandler.getSubTileAsStack(SubTileHiveacynth.NAME), new Object[] {
					new ItemStack(itemPetal, 1, 3), new ItemStack(itemPetal, 1, 9), new ItemStack(itemPetal, 1, 9), new ItemStack(itemPetal, 1, 11),
					new ItemStack(itemManaPetal, 1, 3), new ItemStack(itemManaPetal, 1, 9), new ItemStack(itemManaPetal, 1, 11),
					new ItemStack(itemManaPetal, 1, 11), new ItemStack(itemManaResource, 1, ManaResource.REDSTONE_ROOT.ordinal())
				});
			LexiconEntries.entryHiveacynth = new BotaniaLexiconEntry(SubTileHiveacynth.NAME, BotaniaAPI.categoryFunctionalFlowers);
			LexiconEntries.entryHiveacynth.setLexiconPages(new LexiconPage[] {
					BotaniaAPI.internalHandler.textPage("magicbees.botania.lexicon.hiveacynth.0"),
					BotaniaAPI.internalHandler.textPage("magicbees.botania.lexicon.hiveacynth.1"),
					BotaniaAPI.internalHandler.petalRecipePage("magicbees.botania.lexicon.hiveacynth.crafting", hiveacynthRecipe)
				});
		}
	}

	private static void registerSubtile(String subtileName, Class<? extends SubTileEntity> tileClass) {
		BotaniaAPI.registerSubTile(subtileName, tileClass);
		BotaniaAPI.registerSubTileSignature(tileClass, new BotaniaSignature(subtileName));
		BotaniaAPI.addSubTileToCreativeMenu(subtileName);
	}

	public static void getBlocks() {
		blockLivingRock = BlockInterface.getBlock(Name, "livingrock");
		blockLivingWood = BlockInterface.getBlock(Name, "livingwood");
	}

	public static void getItems() {
		itemPetal = ItemInterface.getItem(Name, "petal");
		itemManaPetal = ItemInterface.getItem(Name, "manaPetal");
		itemManaResource = ItemInterface.getItem(Name, "manaResource");
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
		p.comment = "Rate at which the hiveacynth applies rain resist to spawned bees. Default: 0.1 Setting to 0 will disable.";
		hiveacynthRainResistRate = p.getDouble();
		
		p = configuration.get(section, "hiveacynthPrincessSpawnRate", 0.09);
		p.comment = "Rate at which the Hiveacynth will spawn a Princess instead of a Drone. Default: 0.09. Setting to 0 will disable.";
		hiveacynthPrincessSpawnRate = p.getDouble();
	}
}
