package magicbees.main.utils.compat;

import magicbees.main.Config;
import magicbees.main.utils.BlockInterface;
import cpw.mods.fml.common.Loader;

public class BotaniaHelper {
	private static boolean isBotaniaActive = false;
	public static final String Name = "AWWayofTime";

	public static boolean isActive() {
		return isBotaniaActive;
	}

	public static void preInit() {
		if (Loader.isModLoaded(Name) && Config.bloodMagicActive) {
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
		Config.bmBloodStoneBrick = BlockInterface.getBlock(Name, "bloodStoneBrick");
	}

	public static void getItems() {
	}
}
