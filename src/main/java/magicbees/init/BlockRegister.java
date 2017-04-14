package magicbees.init;

import elec332.core.compat.forestry.bee.BlockHive;
import magicbees.MagicBees;
import magicbees.bees.EnumBeeHives;
import magicbees.block.BlockEffectJar;
import magicbees.util.MagicBeesResourceLocation;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;

/**
 * Created by Elec332 on 4-3-2017.
 */
public final class BlockRegister {

    public static BlockHive<EnumBeeHives> hiveBlock;
    public static Block effectJar;

    public static void init(){
        hiveBlock = new BlockHive<EnumBeeHives>() {

            @Nonnull
            @Override
            public Class<EnumBeeHives> getHiveTypes() {
                return EnumBeeHives.class;
            }

        }.register(new MagicBeesResourceLocation("hiveBlock"));
        hiveBlock.setCreativeTab(MagicBees.creativeTab);
        effectJar = GameRegistry.register(new BlockEffectJar(), new MagicBeesResourceLocation("effectJar")).setCreativeTab(MagicBees.creativeTab);
    }

}
