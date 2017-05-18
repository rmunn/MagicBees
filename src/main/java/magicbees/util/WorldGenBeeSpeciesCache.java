package magicbees.util;

import com.google.common.collect.Lists;
import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.apiculture.IBee;
import forestry.api.genetics.IAllele;
import magicbees.bees.EnumBeeSpecies;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

/**
 * Created by Elec332 on 18-5-2017.
 */
public class WorldGenBeeSpeciesCache {

	private static List<Pair<IAlleleBeeSpecies, Double>> worldgenSpeciesWeights = Lists.newArrayList();
	private static double worldgenSpeciesWeightsTotal = 0.0D;

	public static IBee getRandomWorldgenSpecies(Random r, boolean rainResist) {
		Collections.shuffle(worldgenSpeciesWeights);
		double value = r.nextDouble() * worldgenSpeciesWeightsTotal;
		IAlleleBeeSpecies species = EnumBeeSpecies.getForestrySpecies("Forest");

		for (Pair<IAlleleBeeSpecies, Double> t : worldgenSpeciesWeights) {
			value -= t.getRight();
			if (value <= 0.0D) {
				species = t.getLeft();
				break;
			}
		}

		IAllele[] allelez = BeeManager.beeRoot.getTemplate(species);
		IAllele[] alleles = Arrays.copyOf(allelez, allelez.length);

		return BeeManager.beeRoot.getBee(BeeManager.beeRoot.templateAsGenome(alleles));
	}

	public static void addWorldgenSpeciesWeight(IAlleleBeeSpecies species, double d) {
		worldgenSpeciesWeights.add(Pair.of(species, d));
		worldgenSpeciesWeightsTotal += d;
	}

	public static void populateSpeciesListRarity() {
		worldgenSpeciesWeights.add(Pair.of(EnumBeeSpecies.getForestrySpecies("Forest"), 20.0D));
		worldgenSpeciesWeights.add(Pair.of(EnumBeeSpecies.getForestrySpecies("Meadows"), 20.0D));
		worldgenSpeciesWeights.add(Pair.of(EnumBeeSpecies.getForestrySpecies("Tropical"), 10.0D));
		worldgenSpeciesWeights.add(Pair.of(EnumBeeSpecies.getForestrySpecies("Modest"), 16.0D));
		worldgenSpeciesWeights.add(Pair.of(EnumBeeSpecies.getForestrySpecies("Wintry"), 10.0D));
		worldgenSpeciesWeights.add(Pair.of(EnumBeeSpecies.getForestrySpecies("Ended"), 0.5D));
		worldgenSpeciesWeights.add(Pair.of(EnumBeeSpecies.MYSTICAL.getSpecies(), 20.0D));
		worldgenSpeciesWeights.add(Pair.of(EnumBeeSpecies.UNUSUAL.getSpecies(), 20.0D));
		worldgenSpeciesWeights.add(Pair.of(EnumBeeSpecies.SORCEROUS.getSpecies(), 13.0D));
		worldgenSpeciesWeights.add(Pair.of(EnumBeeSpecies.ATTUNED.getSpecies(), 6.0D));
		worldgenSpeciesWeights.add(Pair.of(EnumBeeSpecies.INFERNAL.getSpecies(), 10.0D));
		worldgenSpeciesWeights.add(Pair.of(EnumBeeSpecies.OBLIVION.getSpecies(), 1.0D));

		for (Pair<IAlleleBeeSpecies, Double> t : worldgenSpeciesWeights) {
			worldgenSpeciesWeightsTotal += t.getRight();
		}

	}

}
