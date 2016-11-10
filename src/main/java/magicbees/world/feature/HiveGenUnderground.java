package magicbees.world.feature;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import forestry.api.apiculture.hives.IHiveGen;

import magicbees.main.utils.BlockUtil;

import javax.annotation.Nullable;

public class HiveGenUnderground implements IHiveGen
{
	protected final int minLevel;
	protected final int range;
	protected final Block replace;
	private final int surroundCount;

	public HiveGenUnderground(int minLevel, int range, int surroundCount) {
		this(minLevel, range, Blocks.STONE, surroundCount);
	}

	public HiveGenUnderground(int minLevel, int range, Block replace, int surroundCount)
	{
		this.minLevel = minLevel;
		this.range = range;
		this.replace = replace;
		this.surroundCount = surroundCount;
	}

	@Nullable
	@Override
	public BlockPos getPosForHive(World world, int x, int z) {
		int y = minLevel + world.rand.nextInt(range);
		return y<0 ? null : new BlockPos(x, y, z);
	}

	@Override
	public boolean isValidLocation(World world, BlockPos pos)
	{
		return BlockUtil.getSurroundCount(world, pos.getX(), pos.getY(), pos.getZ(), replace) >= surroundCount;
	}

	@Override
	public boolean canReplace(IBlockState blockState, World world, BlockPos pos) {
		return !world.isAirBlock(pos) && BlockUtil.canBlockReplaceAt(world, pos, replace);
	}

}
