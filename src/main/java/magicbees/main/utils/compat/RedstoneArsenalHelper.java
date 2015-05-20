package magicbees.main.utils.compat;

import magicbees.main.Config;
import magicbees.main.utils.ItemInterface;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Loader;

public class RedstoneArsenalHelper {
	public enum MiscResource {
		FLUXED_ELECTRUMBLOCK, ;
	}

	public enum NuggetType {
		ELECTRUMFLUX, ;
	}
	
	public static ItemStack fluxBlock;
	public static ItemStack fluxNugget;

	public static final String Name = "RedstoneArsenal";
	private static boolean isRedstoneArsenalPresent = false;

	public static boolean isActive() {
		return isRedstoneArsenalPresent;
	}

	public static void preInit() {
		if (Loader.isModLoaded(Name) && Config.redstoneArsenalActive) {
			isRedstoneArsenalPresent = true;
		}
	}

	public static void init() {
		// if (isActive()) { }
	}

	public static void postInit() {
		if (isActive()) {
			getBlocks();
			getItems();
		}
	}

	private static void getBlocks() {
		fluxBlock = ItemInterface.getItemStack(Name, "blockElectrumFlux");
	}

	private static void getItems() {
		fluxNugget = ItemInterface.getItemStack(Name, "nuggetElectrumFlux");
	}
}
