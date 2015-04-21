package magicbees.main.utils.compat;

import magicbees.bees.BeeManager;
import magicbees.main.Config;
import magicbees.main.utils.BlockInterface;
import magicbees.main.utils.ItemInterface;
import magicbees.main.utils.compat.botania.BotaniaSignature;
import magicbees.main.utils.compat.botania.SubTileBeegonia;
import magicbees.main.utils.compat.botania.SubTileHiveacynth;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import vazkii.botania.api.BotaniaAPI;
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
			
			registerSubtiles();
		}
	}

	public static void postInit() {
		if (isActive()) {
			BotaniaAPI.registerSubTile(SubTileBeegonia.NAME, SubTileBeegonia.class);
			BotaniaAPI.registerSubTileSignature(SubTileBeegonia.class, new BotaniaSignature(SubTileBeegonia.NAME));
			BotaniaAPI.addSubTileToCreativeMenu(SubTileBeegonia.NAME);
			
			// Hiveacynth would appreciate it if this list existed.
			BeeManager.populateSpeciesListRarity();
			BotaniaAPI.registerSubTile(SubTileHiveacynth.NAME, SubTileHiveacynth.class);
			BotaniaAPI.registerSubTileSignature(SubTileHiveacynth.class, new BotaniaSignature(SubTileHiveacynth.NAME));
			BotaniaAPI.addSubTileToCreativeMenu(SubTileHiveacynth.NAME);
		}
	}

	public static void getBlocks() {
		Config.botLivingRock = BlockInterface.getBlock(Name, "livingrock");
		Config.botLivingWood = BlockInterface.getBlock(Name, "livingwood");
	}

	public static void getItems() {
		Config.botManaResource = ItemInterface.getItem(Name, "manaResource");
	}
	
	private static void registerSubtiles() {
		BotaniaAPI.registerSubTile(SubTileBeegonia.NAME, SubTileBeegonia.class);
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
