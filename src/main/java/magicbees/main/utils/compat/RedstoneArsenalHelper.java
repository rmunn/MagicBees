package magicbees.main.utils.compat;

import magicbees.main.Config;
import magicbees.main.utils.ItemInterface;
import cpw.mods.fml.common.Loader;

public class RedstoneArsenalHelper {
	public enum MiscResource {
		FLUXED_ELECTRUMBLOCK, ;
	}

	public enum NuggetType {
		ELECTRUMFLUX, ;
	}

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
		if (isActive()) {
			getBlocks();
			getItems();
		}
	}

	public static void postInit() {
		// if (isActive()) { }
	}

	private static void getBlocks() {
		Config.rsaFluxBlock = ItemInterface.getItemStack("RedstoneArsenal", "blockElectrumFlux", 1);
	}

	private static void getItems() {
		Config.rsaFluxNugget = ItemInterface.getItemStack("RedstoneArsenal", "nuggetElectrumFlux", 1);
	}
}
