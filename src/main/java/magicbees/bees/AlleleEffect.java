package magicbees.bees;

import java.util.List;

import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.apiculture.IAlleleBeeEffect;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.genetics.IEffectData;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public abstract class AlleleEffect extends Allele implements IAlleleBeeEffect
{
	protected int throttle;
	protected boolean combinable;

	public AlleleEffect(String id, boolean isDominant, int timeout)
	{
		super("effect" + id, isDominant, EnumBeeChromosome.EFFECT);
		this.throttle = timeout;
		combinable = false;
	}

	@Override
	public boolean isCombinable()
	{
		return combinable;
	}
	
	public AlleleEffect setIsCombinable(boolean canCombine) {
		combinable = canCombine;
		return this;
	}

	@Override
	public abstract IEffectData validateStorage(IEffectData storedData);

	@Override
	public final IEffectData doEffect(IBeeGenome genome, IEffectData storedData, IBeeHousing housing)
	{
		int count = storedData.getInteger(0);
		if (count >= this.throttle)
		{
			storedData = this.doEffectThrottled(genome, storedData, housing);
		}
		else
		{
			storedData.setInteger(0, count + 1);
		}
		return storedData;
	}
	
	/**
	 * @param genome
	 * @param storedData
	 * @param housing
	 * @return
	 */
	protected abstract IEffectData doEffectThrottled(IBeeGenome genome, IEffectData storedData, IBeeHousing housing);

	@Override
	public IEffectData doFX(IBeeGenome genome, IEffectData storedData, IBeeHousing housing)
	{
		return Allele.forestryBaseEffect.doFX(genome, storedData, housing);
	}
	
	protected <T extends Entity> List<T> getEntitiesWithinRange(IBeeGenome genome, IBeeHousing housing, Class<T> entityClass)
	{
		// Get the size of the affected area
		Vec3i area = genome.getTerritory();
		BlockPos coords = housing.getCoordinates();
		
		// Calculate offset
		int[] min = new int[3];
		int[] max = new int[3];
		min[0] = coords.getX() - area.getX() / 2;
		max[0] = coords.getX() + area.getX() / 2;
		
		min[1] = coords.getY() - area.getY() / 2;
		max[1] = coords.getY() + area.getY() / 2;
		
		min[2] = coords.getZ() - area.getZ() / 2;
		max[2] = coords.getZ() + area.getZ() / 2;
		
		AxisAlignedBB bounds = new AxisAlignedBB(min[0], min[1], min[2], max[0], max[1], max[2]);
		return housing.getWorldObj().getEntitiesWithinAABB(entityClass, bounds);
	}

}
