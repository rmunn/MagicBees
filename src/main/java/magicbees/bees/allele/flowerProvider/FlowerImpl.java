package magicbees.bees.allele.flowerProvider;

import net.minecraft.block.Block;
import forestry.api.genetics.IFlower;

public class FlowerImpl implements IFlower {

	private Block flowerBlock;
	private int meta;
	private double weight;
	private boolean plantable;
	
	public FlowerImpl(Block flower, int blockMeta, double flowerWeight, boolean isPlantable) {
		flowerBlock = flower;
		meta = blockMeta;
		weight = flowerWeight;
		plantable = isPlantable;
	}
	
	@Override
	public Block getBlock() {
		return flowerBlock;
	}

	@Override
	public int getMeta() {
		return meta;
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public void setWeight(double value) {
		weight = value;
	}

	@Override
	public boolean isPlantable() {
		return plantable;
	}

	@Override
	public int compareTo(IFlower other) {
		return (int)(weight - other.getWeight());
	}

}
