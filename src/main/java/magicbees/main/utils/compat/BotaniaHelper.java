package magicbees.main.utils.compat;

import magicbees.bees.BeeManager;
import magicbees.main.Config;
import magicbees.main.utils.BlockInterface;
import magicbees.main.utils.ItemInterface;
import magicbees.main.utils.compat.botania.BotaniaAPIDistanceHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
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
			
			BotaniaAPIDistanceHelper.registerSubtiles();
		}
	}

	public static void postInit() {
		if (isActive()) {
			// Hiveacynth would appreciate it if this list existed.
			BeeManager.populateSpeciesListRarity();
			
			BotaniaAPIDistanceHelper.setupCraftingAndLexicon();
		}
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
