package magicbees.main.utils.compat;

import magicbees.main.Config;
import magicbees.main.utils.BlockInterface;
import magicbees.main.utils.ItemInterface;
import cpw.mods.fml.common.Loader;

public class BotaniaHelper {
	
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
		}
	}

	public static void postInit() {
	}

	public static void getBlocks() {
		Config.botLivingRock = BlockInterface.getBlock(Name, "livingrock");
		Config.botLivingWood = BlockInterface.getBlock(Name, "livingwood");
	}

	public static void getItems() {
		Config.botManaResource = ItemInterface.getItem(Name, "manaResource");
	}
}
