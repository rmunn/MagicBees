package magicbees.main.utils.compat;

import magicbees.main.Config;
import net.minecraft.block.Block;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class AppliedEnergisticsHelper {
	
	public static Block skystone;
	
	public static final String Name = "appliedenergistics2";
	private static boolean isModActive = false;
	
	public static boolean isActive() {
		return isModActive;
	}

	public static void preInit() {
		if (Loader.isModLoaded(Name) && Config.ae2Active) {
			isModActive = true;
		}
	}
	
	public static void init() {
		if (isActive()) {
			skystone = GameRegistry.findBlock(Name, "tile.BlockSkyStone");
		}
	}
	
	public static void postInit() {
		/*if (isActive()) {
		}*/
	}
}
