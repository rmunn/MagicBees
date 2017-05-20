package magicbees.bees;

import elec332.core.compat.forestry.ForestryAlleles;
import elec332.core.compat.forestry.allele.AlleleEffectThrottled;
import elec332.core.compat.forestry.allele.AlleleFlowerProvider;
import elec332.core.compat.forestry.bee.BeeGenomeTemplate;
import forestry.api.apiculture.IAlleleBeeEffect;
import forestry.api.apiculture.IAlleleBeeSpeciesBuilder;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.genetics.IAlleleEffect;
import forestry.api.genetics.IAlleleFlowers;
import forestry.api.genetics.IEffectData;
import magicbees.util.MagicBeesResourceLocation;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import static elec332.core.compat.forestry.ForestryAlleles.*;

/**
 * Created by Elec332 on 15-5-2017.
 */
public final class BeeIntegrationInterface {

	public static final ResourceLocation bot_flowers_name = new MagicBeesResourceLocation("flowersBotania");
	public static final ResourceLocation bot_dreaming_name = new MagicBeesResourceLocation("effectDreaming");

	public static AlleleFlowerProvider flowersBotania;
	public static IAlleleEffect effectDreaming;

	public static IBlockState blockRSAFluxedElectrum;
	public static ItemStack itemRSAFluxedElectrumNugget;
	public static IBlockState livingWood, aeSkyStone;
	public static Item itemPetal, itemPastureSeed, itemManaResource;
	public static int seedTypes;

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	static {
		Item nullItem = Items.FLOWER_POT;
		IBlockState nullBlock = Blocks.YELLOW_FLOWER.getDefaultState();
		//prevent null items/blocks/alleles/effects/whatever
		flowersBotania = new AlleleFlowerProvider(bot_flowers_name, ForestryAlleles.FLOWERS_VANILLA.getProvider());
		effectDreaming = getPlaceholderEffect(bot_dreaming_name);
		blockRSAFluxedElectrum = livingWood = aeSkyStone = nullBlock;
		itemPetal = itemPastureSeed = itemManaResource = nullItem;
		itemRSAFluxedElectrumNugget = new ItemStack(nullItem);
	}

	public static void specialMetalModifiy(BeeGenomeTemplate template){ //Also TE_LUX
		template.setHumidityTolerance(TOLERANCE_BOTH_1);
		template.setTemperatureTolerance(TOLERANCE_BOTH_1);
		template.setToleratesRain(TRUE_RECESSIVE);
		template.setSpeed(SPEED_SLOW);
		template.setFertility(FERTILITY_HIGH);
		template.setLifeSpan(LIFESPAN_LONGER);
		template.setFlowerProvider(FLOWERS_NETHER);
		template.setEffect((IAlleleEffect) EnumBeeSpecies.getForestryAllele("effectIgnition"));
		template.setNeverSleeps(TRUE_RECESSIVE);
		template.setCaveDwelling(TRUE_RECESSIVE);
	}

	public static void addPetals(IAlleleBeeSpeciesBuilder species, float chance){
		if (itemPetal == null){
			return;
		}
		for (int i = 0; i < EnumDyeColor.values().length; i++) {
			species.addSpecialty(new ItemStack(itemPetal, 1, i), chance);
		}
	}

	private BeeIntegrationInterface(){
		throw new RuntimeException();
	}

	private static IAlleleBeeEffect getPlaceholderEffect(ResourceLocation name){
		return new AlleleEffectThrottled(name) {

			@Override
			public IEffectData doEffectThrottled(IBeeGenome beeGenome, IEffectData effectData, IBeeHousing beeHousing) {
				return effectData;
			}

			@Override
			public IEffectData validateStorage(IEffectData effectData) {
				return effectData;
			}

		};
	}

}
