package magicbees.main.utils.compat;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import magicbees.main.Config;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class AppliedEnergisticsHelper {
	
	public static ItemStack silicon;
	public static ItemStack fluixCrystal;
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
			skystone = GameRegistry.findBlock(Name, "tile.skystone");
			silicon = GameRegistry.findItemStack(Name, "item.silicon", 1);
		}
	}
	
	public static void postInit() {
		if (isActive()) {
			
		}
	}
}
