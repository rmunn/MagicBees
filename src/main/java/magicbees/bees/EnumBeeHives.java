package magicbees.bees;

import elec332.core.compat.forestry.bee.HiveDrop;
import elec332.core.compat.forestry.bee.IHiveEnum;
import magicbees.MagicBees;
import forestry.api.apiculture.hives.HiveManager;
import forestry.api.apiculture.hives.IHiveDescription;
import forestry.api.apiculture.hives.IHiveGen;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * Created by Elec332 on 20-8-2016.
 */
public enum EnumBeeHives implements IHiveEnum, IHiveDescription {

    TEST(0.1f, HiveManager.genHelper.ground(Blocks.GRASS)) {

        @Override
        protected void registerDrops() {
            addDrop(new HiveDrop(EnumBeeSpecies.ARCANE, 0.1));
        }

    };

    private EnumBeeHives(float chance, IHiveGen gen){
        this.chance = chance;
        this.gen = gen;
    }

    private final float chance;
    private final IHiveGen gen;

    @Override
    public IHiveGen getHiveGen() {
        return gen;
    }

    @Override
    public IBlockState getBlockState() {
        return MagicBees.hiveBlock.getStateFromHive(this);
    }

    @Override
    public boolean isGoodBiome(Biome biome) {
        return true;
    }

    @Override
    public boolean isGoodHumidity(EnumHumidity enumHumidity) {
        return true;
    }

    @Override
    public boolean isGoodTemperature(EnumTemperature enumTemperature) {
        return true;
    }

    @Override
    public float getGenChance() {
        return chance;
    }

    @Override
    public void postGen(World world, Random random, BlockPos blockPos) {
    }

    @Override
    @Nonnull
    public IHiveDescription getHiveDescription() {
        return this;
    }

    @Override
    public String getUid() {
        return MagicBees.modid + ".hive." + name();
    }

    @Override
    public int getMeta() {
        return ordinal();
    }

    protected abstract void registerDrops();

}
