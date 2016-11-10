package magicbees.world.feature;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HiveGenNether extends HiveGenUnderground {

	public HiveGenNether(int minLevel, int range, int surroundCount)
	{
		super(minLevel, range, Blocks.NETHERRACK, surroundCount);
	}

	@Override
	public int getYForHive(World world, int x, int z)
	{
		int y = super.getYForHive(world, x, z);

		if (!isValidLocation(world, x, y, z))
		{
			int searchDirection = world.rand.nextBoolean() ? 4 : -4;
			while (!Block.isEqualTo(world.getBlockState(new BlockPos(x, y, z)).getBlock(), replace))
			{
				y += searchDirection;
				if (y < minLevel || y > minLevel + range)
				{
					return -1;
				}
			}
		}

		return y;
	}
}
