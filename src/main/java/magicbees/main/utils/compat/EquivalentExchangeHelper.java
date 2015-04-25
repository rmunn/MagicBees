package magicbees.main.utils.compat;

import magicbees.main.Config;
import magicbees.main.utils.ItemInterface;
import cpw.mods.fml.common.Loader;

public class EquivalentExchangeHelper {
	private static boolean isEquivalentExchangePresent = false;

	public static boolean isActive() {
		return isEquivalentExchangePresent;
	}

	public static void preInit() {
		if (Loader.isModLoaded("EE3") && Config.equivalentExchangeActive) {
			isEquivalentExchangePresent = true;
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

	private static void getBlocks() {
	}

	private static void getItems() {
		Config.eeMinuimShard = ItemInterface.getItem("EE3", "miniumShard");
	}
}
