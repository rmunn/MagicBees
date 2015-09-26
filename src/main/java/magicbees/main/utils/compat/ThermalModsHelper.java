package magicbees.main.utils.compat;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;
import magicbees.item.types.DropType;
import magicbees.main.Config;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class ThermalModsHelper {
	
	public enum MiscResource {
		EnderiumBlock,
		ElectrumBlock,
		BronzeBlock,
		FluidCoal,
		FluidRedstone,
		FluidGlowstone,
		FluidEnder,;
	}

	public enum NuggetType {
		ENDERIUM,
		ELECTRUM,
		BRONZE,
		INVAR,
		NICKEL,
		PLATINUM,;
	}

	public enum Entity {
		BLIZZ("Blizz"),;

		public final String entityID;

		private Entity(String s)
		{
			this.entityID = s;
		}
	}

	//---ThermalExpansion Items ---
	public static ItemStack teEnderiumBlock;
	public static ItemStack teElectrumBlock;
	public static ItemStack teInvarBlock;
	public static ItemStack teNickelBlock;
	public static ItemStack tePlatinumBlock;
	public static ItemStack teBronzeBlock;
	public static ItemStack teEnderiumNugget;
	public static ItemStack teInvarNugget;
	public static ItemStack teElectrumNugget;
	public static ItemStack teNickelNugget;
	public static ItemStack tePlatinumNugget;
	public static ItemStack teDustCryotheum;
	public static ItemStack teDustBlizz;
	public static ItemStack teDustPyrotheum;
	public static ItemStack teDustSulfur;
	public static ItemStack teDustPlatinum;
	public static FluidStack teFluidGlowstone;
	public static FluidStack teFluidCoal;
	public static FluidStack teFluidRedstone;
	public static FluidStack teFluidEnder;
	
	public static final String Name = "ThermalExpansion";
	private static boolean isThermalExpansionPresent = false;

	public static boolean isActive() {
		return isThermalExpansionPresent;
	}

	public static void preInit() {
		if (Loader.isModLoaded(Name) && Config.thermalExpansionActive) {
			isThermalExpansionPresent = true;
		}
	}

	public static void init() {
		if (isActive()) {
			getBlocks();
			getItems();
			getFluids();
			setupCrafting();
		}
	}

	public static void postInit() {
		// if (isActive()) { }
	}

	private static void getFluids() {
		teFluidGlowstone = FluidRegistry.getFluidStack("glowstone", 50);
		teFluidCoal = FluidRegistry.getFluidStack("coal", 50);
		teFluidRedstone = FluidRegistry.getFluidStack("redstone", 50);
		teFluidEnder = FluidRegistry.getFluidStack("ender", 50);
	}

	private static void getBlocks() {
		teEnderiumBlock = GameRegistry.findItemStack(Name, "blockEnderium", 1);
		teElectrumBlock = GameRegistry.findItemStack(Name, "blockElectrum", 1);
		teInvarBlock = GameRegistry.findItemStack(Name, "blockInvar", 1);
		teNickelBlock = GameRegistry.findItemStack(Name, "blockNickel", 1);
		tePlatinumBlock = GameRegistry.findItemStack(Name, "blockPlatinum", 1);
		teBronzeBlock = GameRegistry.findItemStack(Name, "blockBronze", 1);
	}

	private static void getItems() {
		teEnderiumNugget = GameRegistry.findItemStack(Name, "nuggetEnderium", 1);
		teInvarNugget = GameRegistry.findItemStack(Name, "nuggetInvar", 1);
		teElectrumNugget = GameRegistry.findItemStack(Name, "nuggetElectrum", 1);
		teNickelNugget = GameRegistry.findItemStack(Name, "nuggetNickel", 1);
		tePlatinumNugget = GameRegistry.findItemStack(Name, "nuggetPlatinum", 1);
		teDustCryotheum = GameRegistry.findItemStack(Name, "dustCryotheum", 1);
		teDustBlizz = GameRegistry.findItemStack(Name, "dustBlizz", 1);
		teDustPyrotheum = GameRegistry.findItemStack(Name, "dustPyrotheum", 1);
		teDustSulfur = GameRegistry.findItemStack(Name, "dustSulfur", 1);
		teDustPlatinum = GameRegistry.findItemStack(Name, "dustPlatinum", 1);
	}

	private static void setupCrafting() {
		NBTTagCompound toSend = new NBTTagCompound();
		toSend.setInteger("energy", 4000);
		toSend.setTag("input", new NBTTagCompound());
		toSend.setTag("output", new NBTTagCompound());
		ItemStack carbonDrop = Config.drops.getStackForType(DropType.CARBON);
		carbonDrop.writeToNBT(toSend.getCompoundTag("input"));
		teFluidCoal.writeToNBT(toSend.getCompoundTag("output"));
		FMLInterModComms.sendMessage("ThermalExpansion", "CrucibleRecipe", toSend);


		toSend = new NBTTagCompound();
		toSend.setInteger("energy", 4000);
		toSend.setTag("input", new NBTTagCompound());
		toSend.setTag("output", new NBTTagCompound());
		ItemStack destabilizedDrop = Config.drops.getStackForType(DropType.DESTABILIZED);
		destabilizedDrop.writeToNBT(toSend.getCompoundTag("input"));
		teFluidRedstone.writeToNBT(toSend.getCompoundTag("output"));
		FMLInterModComms.sendMessage("ThermalExpansion", "CrucibleRecipe", toSend);

		toSend = new NBTTagCompound();
		toSend.setInteger("energy", 4000);
		toSend.setTag("input", new NBTTagCompound());
		toSend.setTag("output", new NBTTagCompound());
		ItemStack endearingDrop = Config.drops.getStackForType(DropType.ENDEARING);
		endearingDrop.writeToNBT(toSend.getCompoundTag("input"));
		teFluidEnder.writeToNBT(toSend.getCompoundTag("output"));
		FMLInterModComms.sendMessage("ThermalExpansion", "CrucibleRecipe", toSend);

		toSend = new NBTTagCompound();
		toSend.setInteger("energy", 4000);
		toSend.setTag("input", new NBTTagCompound());
		toSend.setTag("output", new NBTTagCompound());
		ItemStack luxDrop = Config.drops.getStackForType(DropType.LUX);
		luxDrop.writeToNBT(toSend.getCompoundTag("input"));
		teFluidGlowstone.writeToNBT(toSend.getCompoundTag("output"));
		FMLInterModComms.sendMessage("ThermalExpansion", "CrucibleRecipe", toSend);
	}
}