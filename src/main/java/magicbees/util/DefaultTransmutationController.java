package magicbees.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import elec332.core.util.ItemStackHelper;
import elec332.core.world.WorldHelper;
import elec332.core.world.location.BlockStateWrapper;
import magicbees.api.ITransmutationController;
import magicbees.api.ITransmutationHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.Set;

/**
 * Created by Elec332 on 13-2-2017.
 */
public class DefaultTransmutationController implements ITransmutationController {

    public DefaultTransmutationController(ITransmutationHandler... handlers){
        this.transmutationHandlers = Sets.newHashSet();
        if (handlers != null){
            for (ITransmutationHandler handler : handlers){
                addTransmutationHandler(handler);
            }
        }
    }

    private final Set<ITransmutationHandler> transmutationHandlers;

    @Override
    public boolean addTransmutationHandler(ITransmutationHandler transmutationHandler) {
        return transmutationHandlers.add(Preconditions.checkNotNull(transmutationHandler));
    }

    @Override
    public boolean transmute(World world, BlockPos pos) {
        if(!world.isAirBlock(pos)) {
            BlockStateWrapper bsw = new BlockStateWrapper(WorldHelper.getBlockState(world, pos));
            ItemStack source = bsw.toItemStack();
            if (!ItemStackHelper.isStackValid(source)){
                return false;
            }
            Biome biome = WorldHelper.getBiome(world, pos);

            for (ITransmutationHandler transmutationHandler : transmutationHandlers){
                if (transmutationHandler.transmute(world, pos, source, biome)){
                    return true;
                }
            }

        }
        return false;
    }

}
