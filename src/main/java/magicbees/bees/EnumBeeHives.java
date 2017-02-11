package magicbees.bees;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import elec332.core.compat.forestry.ForestryAlleles;
import elec332.core.compat.forestry.bee.HiveDrop;
import elec332.core.compat.forestry.bee.IHiveEnum;
import elec332.core.java.JavaHelper;
import forestry.api.apiculture.*;
import forestry.api.apiculture.BeeManager;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.EnumTolerance;
import forestry.api.genetics.IAllele;
import magicbees.MagicBees;
import forestry.api.apiculture.hives.HiveManager;
import forestry.api.apiculture.hives.IHiveDescription;
import forestry.api.apiculture.hives.IHiveGen;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import magicbees.item.types.EnumCombType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

/**
 * Created by Elec332 on 20-8-2016.
 */
public enum EnumBeeHives implements IHiveEnum {

    CURIOUS(EnumBeeSpecies.MYSTICAL, 12, false, EnumHiveGen.CURIOUS){

        @Override
        protected void registerDrops() {
            addDefaultDrops(15);
        }

    },
    UNUSUAL(EnumBeeSpecies.UNUSUAL, 12, false, EnumHiveGen.UNUSUAL) {

        @Override
        protected void registerDrops() {
            addDefaultDrops(15);
        }

    },
    RESONANT(EnumBeeSpecies.SORCEROUS, 12, false, EnumHiveGen.RESONANT) {

        @Override
        protected void registerDrops() {
            addDefaultDrops(20);
        }

    },
    DEEP(EnumBeeSpecies.ATTUNED, 4, true, EnumHiveGen.DEEP){

        @Override
        protected void registerDrops() {
            addDefaultDrops(20);
        }

        @Override
        public boolean showInTab() {
            return false;
        }

    },
    ;

    private EnumBeeHives(EnumBeeSpecies beeType, int light, boolean ignoreClimate, EnumHiveGen... genTypes){
        this.bee = beeType;
        this.ignoreClimate = ignoreClimate;
        this.light = light;
        this.genTypes = Preconditions.checkNotNull(genTypes);
    }

    private final EnumBeeSpecies bee;
    private final boolean ignoreClimate;
    private final int light;
    private final EnumHiveGen[] genTypes;


    @Override
    @SuppressWarnings("all")
    public IHiveDescription getHiveDescription() {
        return null;
    }

    @Nonnull
    @Override
    public List<IHiveDescription> getHiveDescriptions() {
        List<IHiveDescription> ret = Lists.newArrayList();
        for (EnumHiveGen gen : genTypes){
            ret.add(new Desc(gen));
        }
        return ret;
    }

    @Override
    public String getUid() {
        return MagicBees.modid + ".hive." + name();
    }

    @Override
    public int getMeta() {
        return ordinal();
    }

    @Override
    public boolean showInTab(){
        return true;
    }

    @Override
    public int getLight(){
        return light;
    }

    protected abstract void registerDrops();

    protected final EnumBeeSpecies getBeeType(){
        return bee;
    }

    protected void addDefaultDrops(int rr){
        addDrop(new HiveDrop(bee, 80, comb).setIgnobleChance(0.7f));
        addDrop(new HiveDrop(addRainResist(bee), rr, comb));
        addDrop(valiantDrop);
    }

    static {
        for (EnumBeeHives hive : values()){
            HiveManager.hiveRegistry.registerHive(hive.getUid(), hive.getHiveDescription());
        }
    }

    private class Desc implements IHiveDescription {

        private Desc(EnumHiveGen gen){
            this.gen = gen;
        }

        private final EnumHiveGen gen;

        @Override
        public IHiveGen getHiveGen() {
            return gen.hiveGen;
        }

        @Override
        public IBlockState getBlockState() {
            return MagicBees.hiveBlock.getStateFromHive(EnumBeeHives.this);
        }

        @Override
        public boolean isGoodBiome(Biome biome) {
            return JavaHelper.hasAtLeastOneMatch(Lists.newArrayList(BiomeDictionary.getTypesForBiome(biome)), gen.biomes);
        }

        @Override
        public boolean isGoodHumidity(EnumHumidity humidity) {
            if (ignoreClimate){
                return true;
            }
            EnumHumidity idealHumidity = bee.getSpecies().getHumidity();
            EnumTolerance humidityTolerance = bee.getGenome().getToleranceHumid();
            return AlleleManager.climateHelper.isWithinLimits(humidity, idealHumidity, humidityTolerance);

        }

        @Override
        public boolean isGoodTemperature(EnumTemperature temperature) {
            if (ignoreClimate){
                return true;
            }
            EnumTemperature idealTemperature = bee.getSpecies().getTemperature();
            EnumTolerance temperatureTolerance = bee.getGenome().getToleranceTemp();
            return AlleleManager.climateHelper.isWithinLimits(temperature, idealTemperature, temperatureTolerance);

        }

        @Override
        public float getGenChance() {
            return gen.chance;
        }

        @Override
        public void postGen(World world, Random random, BlockPos blockPos) {
            gen.postGen(world, random, blockPos);
        }

    }

    private static final ItemStack comb = MagicBees.combItem.getStackFromType(EnumCombType.MUNDANE);
    private static final IHiveDrop valiantDrop = new HiveDrop(addRainResist(EnumBeeSpecies.getForestrySpeciesTemplate("Valiant")), 5, comb);

    private static IBeeGenome addRainResist(IAllele[] alleles){
        IAllele[] ret = alleles.clone();
        ret[EnumBeeChromosome.TOLERATES_RAIN.ordinal()] = ForestryAlleles.TRUE_RECESSIVE;
        return BeeManager.beeRoot.templateAsGenome(ret);
    }

    private static IBeeGenome addRainResist(EnumBeeSpecies species){
        return addRainResist(species.getAlleles());
    }

}
