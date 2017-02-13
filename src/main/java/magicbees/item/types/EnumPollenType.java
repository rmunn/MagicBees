package magicbees.item.types;

import elec332.core.compat.forestry.ForestryCompatHandler;
import elec332.core.item.IEnumItem;
import elec332.core.item.ItemEnumBased;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

/**
 * Created by Elec332 on 19-12-2016.
 */
public enum EnumPollenType implements IEnumItem {

    UNUSUAL(new Color(14172539), new Color(10498137)),
    PHASED(new Color(4814004), new Color(4549541))
    ;

    EnumPollenType(Color firstColor, Color secondColor){
        this.firstColor = firstColor.getRGB();
        this.secondColor = secondColor.getRGB();
    }

    private final int firstColor, secondColor;

    @Override
    public void initializeItem(ItemEnumBased<? extends IEnumItem> item) {
        item.setCreativeTab(ForestryCompatHandler.getForestryBeeTab());
    }

    @Override
    public int getColorFromItemStack(ItemStack stack, int tintIndex) {
        return tintIndex == 0 ? firstColor : secondColor;
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
                new ResourceLocation("forestry", "items/pollen.0"), new ResourceLocation("forestry", "items/pollen.1")
        };
    }

}
