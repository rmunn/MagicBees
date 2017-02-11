package magicbees.bees;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import forestry.api.apiculture.hives.HiveManager;
import forestry.api.apiculture.hives.IHiveGen;
import magicbees.util.HiveGenUnderground;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;

import java.util.List;
import java.util.Random;

/**
 * Created by Elec332 on 11-2-2017.
 */
public enum EnumHiveGen {

    CURIOUS(3.0f, HiveManager.genHelper.tree(), BiomeDictionary.Type.FOREST, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.HILLS),
    UNUSUAL(1.0f, HiveManager.genHelper.ground(Blocks.DIRT, Blocks.GRASS), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.RIVER),
    RESONANT(0.9f, HiveManager.genHelper.ground(Blocks.SAND, Blocks.SANDSTONE), BiomeDictionary.Type.SANDY, BiomeDictionary.Type.MESA, BiomeDictionary.Type.HOT, BiomeDictionary.Type.MAGICAL),
    //TODO: PostGen for all below
    DEEP(5.0f, new HiveGenUnderground(10, 15, 5), BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.MAGICAL),



    ;

    EnumHiveGen(float chance, IHiveGen hiveGen, BiomeDictionary.Type... biomes){
        this.chance = chance;
        this.hiveGen = hiveGen;
        this.biomes = Lists.newArrayList(Preconditions.checkNotNull(biomes));
    }

    final float chance;
    final IHiveGen hiveGen;
    final List<BiomeDictionary.Type> biomes;

    void postGen(World world, Random random, BlockPos blockPos) {
    }

}
