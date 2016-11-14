package magicbees.item;

import elec332.core.api.client.IColoredItem;
import elec332.core.compat.forestry.bee.IDefaultHiveFrame;
import elec332.core.item.AbstractTexturedItem;
import magicbees.MagicBees;
import magicbees.bees.EnumBeeModifiers;
import forestry.api.apiculture.IBeeModifier;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * Created by Elec332 on 22-8-2016.
 */
public class ItemBeeFrames extends AbstractTexturedItem implements IDefaultHiveFrame, IColoredItem {

    public ItemBeeFrames(EnumBeeModifiers modifier) {
        super(new ResourceLocation(MagicBees.modid, "frames."+modifier.name().toLowerCase()));
        setMaxDamage(modifier.getDamage());
        this.modifier = modifier;
    }

    private final EnumBeeModifiers modifier;

    @Override
    @Nonnull
    public CreativeTabs[] getCreativeTabs() {
        return super.getCreativeTabs();
    }

    @Override
    public IBeeModifier getBeeModifier() {
        return modifier;
    }

    @Override
    protected ResourceLocation[] getTextureLocations() {
        return modifier.getTextures();
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int tintIndex) {
        return modifier.getColorFromItemStack(stack, tintIndex);
    }

}
