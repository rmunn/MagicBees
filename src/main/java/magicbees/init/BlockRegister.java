package magicbees.init;

import elec332.core.compat.forestry.bee.BlockHive;
import magicbees.bees.EnumBeeHives;
import magicbees.util.MagicBeesResourceLocation;

import javax.annotation.Nonnull;

/**
 * Created by Elec332 on 4-3-2017.
 */
public final class BlockRegister {

    public static BlockHive<EnumBeeHives> hiveBlock;

    public static void init(){
        hiveBlock = new BlockHive<EnumBeeHives>() {

            @Nonnull
            @Override
            public Class<EnumBeeHives> getHiveTypes() {
                return EnumBeeHives.class;
            }

        }.register(new MagicBeesResourceLocation("hiveBlock"));
    }

}
