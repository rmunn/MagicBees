package magicbees.bees;

import elec332.core.item.IEnumItem;
import elec332.core.item.ItemEnumBased;
import magicbees.MagicBees;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeModifier;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Elec332 on 21-8-2016.
 */
public enum EnumBeeModifiers implements IBeeModifier, IEnumItem {

    CREATIVE;

    @Override
    public void initializeItem(ItemEnumBased<? extends IEnumItem> item) {

    }

    @Override
    public ResourceLocation getTextureLocation() {
        return new ResourceLocation(MagicBees.modid, "items/frame");
    }

    public int getDamage(){
        return 300;
    }

    @Override
    public float getTerritoryModifier(IBeeGenome iBeeGenome, float v) {
        return 1;
    }

    @Override
    public float getMutationModifier(IBeeGenome iBeeGenome, IBeeGenome iBeeGenome1, float v) {
        return 100f;
    }

    @Override
    public float getLifespanModifier(IBeeGenome iBeeGenome, IBeeGenome iBeeGenome1, float v) {
        return 1E-4f;
    }

    @Override
    public float getProductionModifier(IBeeGenome iBeeGenome, float v) {
        return 1;
    }

    @Override
    public float getFloweringModifier(IBeeGenome iBeeGenome, float v) {
        return 1;
    }

    @Override
    public float getGeneticDecay(IBeeGenome iBeeGenome, float v) {
        return 1;
    }

    @Override
    public boolean isSealed() {
        return false;
    }

    @Override
    public boolean isSelfLighted() {
        return false;
    }

    @Override
    public boolean isSunlightSimulated() {
        return false;
    }

    @Override
    public boolean isHellish() {
        return false;
    }

}
