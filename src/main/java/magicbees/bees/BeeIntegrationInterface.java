package magicbees.bees;

import elec332.core.compat.forestry.ForestryAlleles;
import elec332.core.compat.forestry.allele.AlleleEffectThrottled;
import elec332.core.compat.forestry.allele.AlleleFlowerProvider;
import forestry.api.apiculture.IAlleleBeeEffect;
import forestry.api.apiculture.IAlleleBeeSpeciesBuilder;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.genetics.IAlleleEffect;
import forestry.api.genetics.IAlleleFlowers;
import forestry.api.genetics.IEffectData;
import magicbees.util.MagicBeesResourceLocation;
import net.minecraft.block.Block;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Elec332 on 15-5-2017.
 */
public final class BeeIntegrationInterface {

	public static final ResourceLocation bot_flowers_name = new MagicBeesResourceLocation("flowersBotania");
	public static final ResourceLocation bot_dreaming_name = new MagicBeesResourceLocation("effectDreaming");

	public static AlleleFlowerProvider flowersBotania;
	public static IAlleleEffect effectDreaming;

	public static Block livingWood;
	public static Item itemPetal, itemPastureSeed;
	public static int seedTypes;

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

	static {
		flowersBotania = new AlleleFlowerProvider(bot_flowers_name, ForestryAlleles.FLOWERS_VANILLA.getProvider());
		effectDreaming = getPlaceholderEffect(bot_dreaming_name);
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
