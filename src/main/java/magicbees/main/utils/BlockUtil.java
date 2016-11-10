package magicbees.main.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class BlockUtil {

	public static int getSurroundCount(World world, BlockPos pos, Block blockType)
	{
		int surroundCount = 0;
		if (canBlockReplaceAt(world, pos.add(1,0,0), blockType))
		{
			++surroundCount;
		}

		if (canBlockReplaceAt(world, pos.add(-1,0,0), blockType))
		{
			++surroundCount;
		}

		if (canBlockReplaceAt(world, pos.up(), blockType))
		{
			++surroundCount;
		}

		if (canBlockReplaceAt(world, pos.down(), blockType))
		{
			++surroundCount;
		}

		if (canBlockReplaceAt(world, pos.add(0,0,1), blockType))
		{
			++surroundCount;
		}

		if (canBlockReplaceAt(world, pos.add(0,0,-1), blockType))
		{
			++surroundCount;
		}

		return surroundCount;
	}

	public static boolean canBlockReplaceAt(World world, BlockPos pos, Block target)
	{
		// don't cause new chunks to load
		if (!world.isAreaLoaded(pos, 1))
		{
			return true;
		}
		IBlockState block = world.getBlockState(pos);
		return block.getBlock().isReplaceableOreGen(world.getBlockState(pos), world, pos, BlockStateMatcher.forBlock(target));
	}

	public static List<Chunk> getChunksInSearchRange(World world, int xCoord, int zCoord, int searchRadius) {
		List<Chunk> searchChunks = new ArrayList<Chunk>();
		int chunkRange = (int)Math.ceil(searchRadius / 16.0);
		int minChunkX = xCoord / 16 - chunkRange;
		int maxChunkX = xCoord / 16 + chunkRange;
		int minChunkZ = zCoord / 16 - chunkRange;
		int maxChunkZ = zCoord / 16 + chunkRange;
		
		for (int chunkX = minChunkX; chunkX <= maxChunkX; ++chunkX) {
			for (int chunkZ = minChunkZ; chunkZ <= maxChunkZ; ++chunkZ) {
				searchChunks.add(world.getChunkFromChunkCoords(chunkX, chunkZ));
			}
		}
		
		return searchChunks;
	}
}
