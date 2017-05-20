package magicbees.util;

import elec332.core.util.ItemStackHelper;
import elec332.core.util.OredictHelper;
import magicbees.init.ItemRegister;
import magicbees.item.types.EnumNuggetType;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.List;

/**
 * Created by Elec332 on 15-5-2017.
 */
public enum EnumOreResourceType {

	IRON(new ItemStack(ItemRegister.ironNugget), "nuggetIron"),
	GOLD(new ItemStack(Items.GOLD_NUGGET)),
	COPPER(EnumNuggetType.COPPER, "nuggetCopper"),
	TIN(EnumNuggetType.TIN, "nuggetTin"),
	SILVER("nuggetSilver", "dustTinySilver"),
	LEAD("nuggetLead", "dustTinyLead"),
	ALUMINIUM("nuggetAluminium", "nuggetAluminum"),
	ARDITE("nuggetArdite"),
	COBALT("nuggetCobalt"),
	MANYULLYN("nuggetManyullyn"),
	OSMIUM("nuggetOsmium"),
	DIAMOND(EnumNuggetType.DIAMOND, "nuggetDiamond"){

		@Override
		public String getType() {
			return "gem";
		}

	},
	EMERALD(EnumNuggetType.EMERALD, "nuggetEmerald"){

		@Override
		public String getType() {
			return "gem";
		}

	},
	APATITE(EnumNuggetType.APATITE, "nuggetApatite"){

		@Override
		public String getType() {
			return "gem";
		}

	},
	SILICON,
	CERTUS,
	FLUIX,
	PLATINUM("nuggetPlatinum"),
	NICKEL("nuggetNickel", "nuggetFerrous"),
	BRONZE(EnumNuggetType.BRONZE, "nuggetBronze"),
	INVAR("nuggetInvar"),
	ELECTRUM("nuggetElectrum"),
	;

	EnumOreResourceType(EnumNuggetType nugget, String... oreDictA){
		this(nugget.getStack(), oreDictA);
	}

	EnumOreResourceType(String... oreDictA){
		this((ItemStack)null, oreDictA);
	}

	@SuppressWarnings("all")
	EnumOreResourceType(ItemStack stack, String... oreDictA){
		if (oreDictA == null){
			oreDictA = new String[0];
		}
		if (oreDictA.length > 0) {
			if (ItemStackHelper.isStackValid(stack)) {
				if (!(stack.getItem() == ItemRegister.ironNugget && ItemRegister.ironNugget.getRegistryName().getResourceDomain().equals("minecraft"))) {
					for (String s : oreDictA) {
						if (s.startsWith("nugget")) {
							OreDictionary.registerOre(s, stack);
						}
					}
					if (oreDictA[0].startsWith("nugget")) {
						List<ItemStack> scks = OredictHelper.getOres(oreDictA[0].replace("nugget", getType()));
						if (scks.size() > 0 && ItemStackHelper.isStackValid(scks.get(0))) {
							GameRegistry.addRecipe(new ShapedOreRecipe(scks.get(0), "XXX", "XXX", "XXX", 'X', oreDictA[0]));
						}
					}
				}
			}
		}
		setStack(stack);
		this.oreDictA = oreDictA;
	}

	private String[] oreDictA;
	private ItemStack finalStack;

	public String getType(){
		return "ingot";
	}

	private void setStack(ItemStack stack){
		if (!ItemStackHelper.isStackValid(stack)){
			stack = null;
		}
		this.finalStack = stack;
	}

	public boolean enabled(){
		getStack();
		return finalStack != null;
	}

	public ItemStack getStack() {
		if (oreDictA != null){
			primLoop:
			for (String oreDict : oreDictA) {
				List<ItemStack> l = OreDictionary.getOres(oreDict);
				if (!l.isEmpty()) {
					for (ItemStack stack_ : l) {
						if (ItemStackHelper.isStackValid(stack_)) {
							setStack(ItemStackHelper.copyItemStack(stack_));
							break primLoop;
						}
					}
				}
			}
			oreDictA = null;
		}
		return finalStack == null ? ItemStackHelper.NULL_STACK : finalStack;
	}

	public static void registerRecipes(){

	}

}
