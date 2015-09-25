package magicbees.bees.allele.effect;

import java.util.List;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.genetics.IEffectData;
import magicbees.bees.AlleleEffect;
import magicbees.main.utils.BlockUtil;
import magicbees.main.utils.compat.thaumcraft.NodeHelper;

public class AlleleEffectNodeRepair extends AlleleEffect {

	public AlleleEffectNodeRepair(String id, boolean isDominant) {
		super(id, isDominant, 2);
	}

	@Override
	public IEffectData validateStorage(IEffectData storedData) {
		if (storedData == null || !(storedData instanceof magicbees.bees.allele.effect.EffectData)) {
			storedData = new magicbees.bees.allele.effect.EffectData(1, 0, 0);
		}
		return storedData;
	}

	@Override
	protected IEffectData doEffectThrottled(IBeeGenome genome, IEffectData storedData, IBeeHousing housing) {
		World world = housing.getWorld();
		int xCoord = housing.getXCoord();
		int yCoord = housing.getYCoord();
		int zCoord = housing.getZCoord();
		int range = (int)Math.ceil(genome.getTerritory()[0] * housing.getTerritoryModifier(genome, 1f));
		List<Chunk> chunks = BlockUtil.getChunksInSearchRange(world, xCoord, zCoord, range);
		
	    if (NodeHelper.repairNodeInRange(chunks, world, xCoord, yCoord, zCoord, range)) {
	    	storedData.setInteger(0, storedData.getInteger(0) - throttle);
	    }
    
		return storedData;
	}

}
