package magicbees.item.types;

import elec332.core.compat.forestry.ForestryCompatHandler;
import elec332.core.item.IEnumItem;
import elec332.core.item.ItemEnumBased;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Elec332 on 19-12-2016.
 */
public enum EnumDropType implements IEnumItem {

    ENCHANTED(7216237, 16748543),
    INTELLECT(2462029, 1630322),
    DESTABILIZED(13369388, 7012632),
    CARBON(4539717, 986895),
    LUX(16118692, 13224061),
    ENDEARING(1237977, 433815);

    EnumDropType(int c1, int c2){
        this.firstColor = c1;
        this.secondColor = c2;
    }

    private final int firstColor, secondColor;

    @Override
    public int getColorFromItemStack(ItemStack stack, int tintIndex) {
        return tintIndex == 0 ? firstColor : secondColor;
    }

    @Override
    public void initializeItem(ItemEnumBased<? extends IEnumItem> itemEnumBased) {
        itemEnumBased.setCreativeTab(ForestryCompatHandler.getForestryBeeTab());
    }

    @Override
    public ResourceLocation[] getTextures() {
        return textures;
    }

    @Override
    public ResourceLocation getTextureLocation() {
        return null;
    }

    private static final ResourceLocation[] textures;

    static {
        textures = new ResourceLocation[]{
                new ResourceLocation("forestry", "items/honeyDrop.0"), new ResourceLocation("forestry", "items/honeyDrop.1")
        };
    }

}
