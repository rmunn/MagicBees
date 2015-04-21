package magicbees.main.utils.compat.botania;

import magicbees.bees.BeeGenomeManager;
import magicbees.bees.BeeManager;
import magicbees.main.utils.compat.BotaniaHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.subtile.SubTileFunctional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.apiculture.EnumBeeType;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.apiculture.IBee;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.genetics.IAllele;

public class SubTileHiveacynth extends SubTileFunctional {
	
	public static final String NAME = "hiveacynth";
	private static final int COST = 15000;
	
	

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (supertile.getWorldObj().isRemote) {
			return;
		}
		
		if (readyToProduceProduct()) {
			EnumBeeType beeType = EnumBeeType.DRONE;
			
			if (supertile.getWorldObj().rand.nextDouble() < BotaniaHelper.hiveacynthPrincessSpawnRate) {
				beeType = EnumBeeType.PRINCESS;
			}
			
			ItemStack stack = BeeManager.beeRoot.getMemberStack(getRandomBee(), beeType.ordinal());
			
			EntityItem entity = new EntityItem(supertile.getWorldObj(), supertile.xCoord, supertile.yCoord, supertile.zCoord, stack);
			supertile.getWorldObj().spawnEntityInWorld(entity);
		}
	}
	
	private IBee getRandomBee() {
		IAlleleBeeSpecies dropSpecies = BeeManager.getRandomWorldgenSpecies(supertile.getWorldObj().rand);
		IAllele[] speciesTemplate = BeeManager.beeRoot.getTemplate(dropSpecies.getUID());
		
		if (supertile.getWorldObj().rand.nextDouble() < BotaniaHelper.hiveacynthRainResistRate) {
			speciesTemplate = BeeGenomeManager.addRainResist(speciesTemplate);
		}
		
		IBeeGenome genome = BeeManager.beeRoot.templateAsGenome(speciesTemplate);
		return BeeManager.beeRoot.getBee(supertile.getWorldObj(), genome);
	}

	private boolean readyToProduceProduct() {
		return redstoneSignal == 0 && ticksExisted % 20==0;//0 == 0 && mana >= getFinalCost();
	}
	
	private int getFinalCost() {
		return (int)(COST * BotaniaHelper.hiveacynthManaMultiplier); 
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon() {
		return BotaniaAPI.getSignatureForName(NAME).getIconForStack(null);
	}
	
	@Override
	public boolean acceptsRedstone() {
		return true;
	}

	@Override
	public int getColor() {
		return 0x0071C6;
	}

	@Override
	public int getMaxMana() {
		return getFinalCost();
	}

}
