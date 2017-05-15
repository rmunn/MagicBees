package magicbees.item.types;

import elec332.core.item.IEnumItem;
import elec332.core.item.ItemEnumBased;
import magicbees.MagicBees;
import magicbees.init.ItemRegister;
import magicbees.util.MagicBeesResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

/**
 * Created by Elec332 on 15-5-2017.
 */
public enum EnumNuggetType implements IEnumItem {

	IRON("dustTinyIron"),
	DIAMOND,
	EMERALD,
	APATITE,
	COPPER("dustTinyCopper"),
	TIN("dustTinyTin")
	;

	EnumNuggetType(String... sA){
		if (sA != null){
			for (String s : sA) {
				List<ItemStack> l = OreDictionary.getOres(s);
				if (!l.isEmpty()) {
					for (ItemStack stack : l) {
						if (!stack.isEmpty()) {
							replacement = stack.copy();
							break;
						}
					}
				}
			}
		}
	}

	private ItemStack replacement;

	public ItemStack getStack(){
		if (replacement != null){
			return replacement;
		}
		return ItemRegister.orePartItem.getStackFromType(this);
	}

	@Override
	public boolean shouldShow() {
		return replacement == null;
	}

	@Override
	public void initializeItem(ItemEnumBased<? extends IEnumItem> itemEnumBased) {
		itemEnumBased.setCreativeTab(MagicBees.creativeTab);
	}

	@Override
	public ResourceLocation getTextureLocation() {
		return new MagicBeesResourceLocation("items/part"+toString().toLowerCase());
	}

}
