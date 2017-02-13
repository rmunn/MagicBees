package magicbees.bees;

import elec332.core.compat.forestry.IIndividualBranch;
import elec332.core.compat.forestry.IIndividualDefinition;
import elec332.core.compat.forestry.bee.BeeGenomeTemplate;
import elec332.core.compat.forestry.bee.IBeeTemplate;
import elec332.core.util.MoonPhase;
import forestry.api.genetics.IAlleleEffect;
import forestry.apiculture.PluginApiculture;
import forestry.apiculture.items.EnumHoneyComb;
import magicbees.MagicBees;
import magicbees.bees.mutation.MoonPhaseMutationBonus;
import magicbees.bees.mutation.MoonPhaseMutationRestriction;
import magicbees.init.AlleleRegister;
import magicbees.item.types.EnumCombType;
import magicbees.item.types.EnumDropType;
import magicbees.item.types.EnumPollenType;
import magicbees.util.IMoreBeesBranch;
import forestry.api.apiculture.*;
import forestry.api.apiculture.BeeManager;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.Locale;

import static elec332.core.compat.forestry.ForestryAlleles.*;

/**
 * Created by Elec332 on 15-8-2016.
 */
//@RegisteredForestryIndividual
public enum EnumBeeSpecies implements IBeeTemplate {

    MYSTICAL("mysticum", EnumBeeBranches.VEILED, true, new Color(0xAFFFB7)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.MUNDANE), 0.15f);
        }

        @Override
        public void registerMutations() {
            registerMundaneMutations();
        }

    },
    SORCEROUS("fascinatio", EnumBeeBranches.VEILED, true, new Color(0xEA9A9A)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setFertility(FERTILITY_HIGH);
            template.setTemperatureTolerance(TOLERANCE_DOWN_2);
            template.setHumidityTolerance(TOLERANCE_UP_1);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.setTemperature(EnumTemperature.HOT);
            speciesBuilder.setHumidity(EnumHumidity.ARID);
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.MUNDANE), 0.15f);
        }

        @Override
        public void registerMutations() {
            registerMundaneMutations();
        }

    },
    UNUSUAL("inusitatus", EnumBeeBranches.VEILED, true, new Color(0x72D361)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setTemperatureTolerance(TOLERANCE_BOTH_2);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.MUNDANE), 0.15f);
        }

        @Override
        public void registerMutations() {
            registerMundaneMutations();
        }

    },
    ATTUNED("similis", EnumBeeBranches.VEILED, true, new Color(0x0086A8)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setFertility(FERTILITY_HIGH);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.MUNDANE), 0.15f);
        }

        @Override
        public void registerMutations() {
            registerMundaneMutations();
        }

    },
    ELDRITCH("prodigiosus", EnumBeeBranches.VEILED, true, new Color(0x8D75A0)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setSpeed(SPEED_SLOWER);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.MUNDANE), 0.15f);
        }

        @Override //Done with registerMundaneMutations() below.
        public void registerMutations() {
        }

    },

    ESOTERIC("secretiore", EnumBeeBranches.ARCANE, true, new Color(0x001099)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            //Nope
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.OCCULT), 0.18f);
        }

        @Override
        public void registerMutations() {
            registerMutation(EnumBeeSpecies.getForestrySpecies("Cultivated"), ELDRITCH, 10);
        }

    },
    MYSTERIOUS("mysticus", EnumBeeBranches.ARCANE, true, new Color(0x762bc2)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setTemperatureTolerance(TOLERANCE_BOTH_2);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.OCCULT), 0.2f);
        }

        @Override
        public void registerMutations() {
            registerMutation(ELDRITCH, ESOTERIC, 8);
        }

    },
    ARCANE("arcanus", EnumBeeBranches.ARCANE, true, new Color(0xd242df)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setTemperatureTolerance(TOLERANCE_BOTH_2);
            template.setFertility(FERTILITY_NORMAL);
            template.setFloweringSpeed(FLOWERING_AVERAGE);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.setHasEffect();
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.OCCULT), 0.25f);
            speciesBuilder.addSpecialty(EnumBeeSpecies.getDrop(EnumDropType.ENCHANTED), 0.9f);
        }

        @Override
        public void registerMutations() {
            registerMutation(ESOTERIC, MYSTERIOUS, 8).addMutationCondition(new MoonPhaseMutationBonus(MoonPhase.WAXING_CRESCENT, MoonPhase.WAXING_GIBBOUS, 1.2F));
        }

    },

    CHARMED("larvatus", EnumBeeBranches.SUPERNATURAL, true, new Color(0x48EEEC)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.OTHERWORLDLY), 0.18f);
        }

        @Override
        public void registerMutations() {
            registerMutation(EnumBeeSpecies.getForestrySpecies("Cultivated"), ELDRITCH, 10);
        }

    },
    ENCHANTED("cantatus", EnumBeeBranches.SUPERNATURAL, true, new Color(0x18e726)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setTemperatureTolerance(TOLERANCE_BOTH_1);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.OTHERWORLDLY), 0.2f);
        }

        @Override
        public void registerMutations() {
            registerMutation(ELDRITCH, CHARMED, 8);
        }

    },
    SUPERNATURAL("coeleste", EnumBeeBranches.SUPERNATURAL, true, new Color(0x005614)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setTemperatureTolerance(TOLERANCE_BOTH_2);
            template.setHumidityTolerance(TOLERANCE_BOTH_1);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.setHasEffect();
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.OTHERWORLDLY), 0.25f);
            speciesBuilder.addSpecialty(EnumBeeSpecies.getPollen(EnumPollenType.UNUSUAL), 0.8f);
        }

        @Override
        public void registerMutations() {
            registerMutation(CHARMED, ENCHANTED, 8).addMutationCondition(new magicbees.bees.mutation.MoonPhaseMutationBonus(MoonPhase.WANING_GIBBOUS, MoonPhase.WANING_CRESCENT, 1.2F));
        }

    },

    ETHEREAL("diaphanum", EnumBeeBranches.MAGICAL, true, new Color(0xBA3B3B), new Color(0xEFF8FF)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setLifeSpan(LIFESPAN_SHORTENED);
            template.setFloweringSpeed(FLOWERING_AVERAGE);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.OCCULT), 0.1f);
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.OTHERWORLDLY), 0.1f);
        }

        @Override
        public void registerMutations() {
            registerMutation(ARCANE, SUPERNATURAL, 7);
        }

    },
    WATERY("aquatilis", EnumBeeBranches.MAGICAL, true, new Color(0x313C5E)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setHumidityTolerance(TOLERANCE_UP_1);
            template.setTemperatureTolerance(TOLERANCE_DOWN_2);
            template.setFlowerProvider(FLOWERS_SNOW);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.WATERY), 0.25f);
            //todo: speciality
        }

        @Override
        public void registerMutations() {
            registerMutation(SUPERNATURAL, ETHEREAL, 14).requireResource(Blocks.WATER.getDefaultState());
        }

    },
    EARTHY("fictili", EnumBeeBranches.MAGICAL, true, new Color(0x78822D)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setHumidityTolerance(TOLERANCE_BOTH_1);
            template.setLifeSpan(LIFESPAN_LONG);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.EARTHY), 0.25f);
        }

        @Override
        public void registerMutations() {
            registerMutation(SUPERNATURAL, ETHEREAL, 14).requireResource(Blocks.BRICK_BLOCK.getBlockState().getValidStates().toArray(new IBlockState[0]));
        }

    },
    FIREY("ardens", EnumBeeBranches.MAGICAL, true, new Color(0xD35119)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setHumidityTolerance(TOLERANCE_DOWN_1);
            template.setTemperatureTolerance(TOLERANCE_UP_2);
            template.setFlowerProvider(FLOWERS_CACTI);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.FIREY), 0.25f);
        }

        @Override
        public void registerMutations() {
            registerMutation(SUPERNATURAL, ETHEREAL, 14).requireResource(Blocks.LAVA.getDefaultState());
        }

    },
    WINDY("ventosum", EnumBeeBranches.MAGICAL, true, new Color(0xFFFDBA)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setTemperatureTolerance(TOLERANCE_BOTH_2);
            template.setSpeed(SPEED_FASTER);
            template.setFloweringSpeed(FLOWERING_FASTER);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.AIRY), 0.25f);
        }

        @Override
        public void registerMutations() {
            registerMutation(SUPERNATURAL, ETHEREAL, 14).requireResource("treeLeaves");
        }

    },

    PUPIL("disciplina", EnumBeeBranches.SCHOLARLY, true, new Color(0xFFFF00)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setSpeed(SPEED_SLOWER);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.PAPERY), 0.20f);
        }

        @Override
        public void registerMutations() {
            registerMutation(EnumBeeSpecies.getForestrySpecies("Monastic"), ARCANE, 10);
        }

    },
    SCHOLARLY("studiosis", EnumBeeBranches.SCHOLARLY, false, new Color(0x6E0000)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setFertility(FERTILITY_LOW);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.PAPERY), 0.25f);
        }

        @Override
        public void registerMutations() {
            registerMutation(ARCANE, PUPIL, 8);
        }

    },
    SAVANT("philologus", EnumBeeBranches.SCHOLARLY, false, new Color(0xFFA042)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setSpeed(SPEED_NORMAL);
            template.setFertility(FERTILITY_LOW);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.setHasEffect();
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.PAPERY), 0.40f);
        }

        @Override
        public void registerMutations() {
            registerMutation(PUPIL, SCHOLARLY, 6);
        }

    },

    AWARE("sensibilis", EnumBeeBranches.SOUL, false, new Color(0x5E95B5)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.INTELLECT), 0.18f);
        }

        @Override
        public void registerMutations() {
            registerMutation(ETHEREAL, ATTUNED, 10);
        }

    },
    SPIRIT("larva", EnumBeeBranches.SOUL, true, new Color(0xb2964b)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setLifeSpan(LIFESPAN_SHORTENED);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.INTELLECT), 0.22f);
            speciesBuilder.addSpecialty(EnumBeeSpecies.getComb(EnumCombType.SOUL), 0.16f);
        }

        @Override
        public void registerMutations() {
            registerMutation(ETHEREAL, AWARE, 8);
            registerMutation(ATTUNED, AWARE, 8);
        }

    },
    SOUL("anima", EnumBeeBranches.SOUL, false, new Color(0x7d591b)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setTemperatureTolerance(TOLERANCE_DOWN_2);
            template.setLifeSpan(LIFESPAN_NORMAL);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.setHasEffect();
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.INTELLECT), 0.28f);
            speciesBuilder.addSpecialty(EnumBeeSpecies.getComb(EnumCombType.SOUL), 0.2f);
        }

        @Override
        public void registerMutations() {
            registerMutation(AWARE, SPIRIT, 7);
        }

    },

    SKULKING("malevolens", EnumBeeBranches.SKULKING, true, new Color(0x524827)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setCaveDwelling(TRUE_RECESSIVE);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.setIsSecret();
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.FURTIVE), 0.1f);
        }

        @Override
        public void registerMutations() {
            registerMutation(EnumBeeSpecies.getForestrySpecies("Modest"), ELDRITCH, 12);
        }

    },
    GHASTLY("pallens", EnumBeeBranches.SKULKING, false, new Color(0xccccee), new Color(0xbf877c)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setCaveDwelling(TRUE_RECESSIVE);
            template.setTemperatureTolerance(TOLERANCE_BOTH_1);
            template.setToleratesRain(TRUE_RECESSIVE);
            template.setFertility(FERTILITY_LOW);
            template.setEffect(AlleleRegister.spawnGhast);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.setIsSecret();
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.FURTIVE), 0.8f);
            speciesBuilder.addSpecialty(new ItemStack(Items.GHAST_TEAR), 0.099f);
        }

        @Override
        public void registerMutations() {
            registerMutation(BATTY, ETHEREAL, 9);
        }

    },
    SPIDERY("araneolus", EnumBeeBranches.SKULKING, true, new Color(0x0888888), new Color(0x222222)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setCaveDwelling(TRUE_RECESSIVE);
            template.setTerritory(TERRITORY_LARGER);
            template.setEffect(AlleleRegister.spawnSpider);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.setIsSecret();
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.FURTIVE), 0.13f);
            speciesBuilder.addProduct(new ItemStack(Items.STRING), 0.08f);
            speciesBuilder.addSpecialty(new ItemStack(Items.SPIDER_EYE), 0.08f);
        }

        @Override
        public void registerMutations() {
            registerMutation(EnumBeeSpecies.getForestrySpecies("Tropical"), SKULKING, 10);
        }

    },
    SMOULDERING("flagrantia", EnumBeeBranches.SKULKING, false, new Color(0xFFC747), new Color(0xEA8344)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setCaveDwelling(TRUE_RECESSIVE);
            template.setEffect(AlleleRegister.spawnBlaze);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.setTemperature(EnumTemperature.HELLISH);
            speciesBuilder.setIsSecret();
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.FURTIVE), 0.10f);
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.MOLTEN), 0.10f);
            speciesBuilder.addSpecialty(new ItemStack(Items.BLAZE_ROD), 0.05f);
        }

        @Override
        public void registerMutations() {
            registerMutation(GHASTLY, HATEFUL, 7).restrictBiomeType(BiomeDictionary.Type.NETHER);
        }

    },
    BRAINY("cerebrum", EnumBeeBranches.SKULKING, true, new Color(0x83FF70)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setFertility(FERTILITY_LOW);
            //todo template.setEffect(AlleleRegister.spa)
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.setIsSecret();
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.FURTIVE), 0.10f);
            speciesBuilder.addProduct(new ItemStack(Items.ROTTEN_FLESH), 0.6f);
        }

        @Override
        public void registerMutations() {
            EnumBeeSpecies second;
            int chance;
            if (EnumBeeBranches.THAUMIC.enabled()){
                //todo
                second = MUTABLE;
                chance = 9;
            } else {
                second = MUTABLE;
                chance = 14;
            }
            registerMutation(SKULKING, second, chance);
        }

        @Override //TODO: spawn zombies?
        public boolean isActive() {
            return false;
        }

    },

    BIGBAD("magnumalum", EnumBeeBranches.SKULKING, true, new Color(0xA9344B), new Color(0x453536)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setCaveDwelling(TRUE_RECESSIVE);
            template.setEffect(AlleleRegister.spawnWolf);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.setNocturnal();
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.FURTIVE), 0.18f);
            speciesBuilder.addProduct(new ItemStack(Items.BEEF), 0.12f);
            speciesBuilder.addProduct(new ItemStack(Items.CHICKEN), 0.12f);
            //Is in original...  speciesBuilder.addSpecialty(new ItemStack(Items.MELON), 0.20f);
        }

        @Override
        public void registerMutations() {
            registerMutation(SKULKING, MYSTERIOUS, 7).addMutationCondition(new MoonPhaseMutationRestriction(MoonPhase.FULL));
        }

    },

    CHICKEN("pullus", EnumBeeBranches.FLESHY, true, new Color(0xFF0000), new Color(0xD3D3D3)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setEffect(AlleleRegister.spawnChicken);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.FURTIVE), 0.23f);
            speciesBuilder.addSpecialty(new ItemStack(Items.FEATHER), 0.08f);
            speciesBuilder.addProduct(new ItemStack(Items.EGG), 0.08f);
        }

        @Override
        public void registerMutations() {
            registerMutation(EnumBeeSpecies.getForestrySpecies("Common"), SKULKING, 12).restrictBiomeType(BiomeDictionary.Type.FOREST);
        }

    },
    BEEF("bubulae", EnumBeeBranches.FLESHY, true, new Color(0xB7B7B7), new Color(0x3F3024)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setEffect(AlleleRegister.spawnCow);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.FURTIVE), 0.25f);
            speciesBuilder.addSpecialty(new ItemStack(Items.LEATHER), 0.165f);
        }

        @Override
        public void registerMutations() {
            registerMutation(EnumBeeSpecies.getForestrySpecies("Common"), SKULKING, 12).restrictBiomeType(BiomeDictionary.Type.PLAINS);
        }

    },
    PORK("porcina", EnumBeeBranches.FLESHY, true, new Color(0xF1AEAC), new Color(0xDF847B)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setEffect(AlleleRegister.spawnPig);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.FURTIVE), 0.10f);
            speciesBuilder.addSpecialty(new ItemStack(Items.CARROT), 0.165f);
        }

        @Override
        public void registerMutations() {
            registerMutation(EnumBeeSpecies.getForestrySpecies("Common"), SKULKING, 12).restrictBiomeType(BiomeDictionary.Type.MOUNTAIN);
        }

    },
    BATTY("chiroptera", EnumBeeBranches.FLESHY, true, new Color(0x5B482B), new Color(0x271B0F)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setTerritory(TERRITORY_LARGE);
            template.setEffect(AlleleRegister.spawnBats);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.FURTIVE), 0.10f);
            speciesBuilder.addSpecialty(new ItemStack(Items.STRING), 0.00001f);
        }

        @Override
        public void registerMutations() {
            registerMutation(SKULKING, WINDY, 9);
        }

    },
    SHEEPISH("balans", EnumBeeBranches.FLESHY, true, new Color(0xF7F7F7), new Color(0xCACACA)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setCaveDwelling(TRUE_RECESSIVE);
            template.setTemperatureTolerance(TOLERANCE_DOWN_2);
            template.setEffect(AlleleRegister.spawnSheep);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.FURTIVE), 0.25f);
            speciesBuilder.addSpecialty(new ItemStack(Blocks.WOOL), 0.16f);
            speciesBuilder.addSpecialty(new ItemStack(Items.WHEAT), 0.24f);

        }

        @Override
        public void registerMutations() {
            registerMutation(PORK, SKULKING, 13).restrictBiomeType(BiomeDictionary.Type.PLAINS);
        }

    },
    HORSE("equus", EnumBeeBranches.FLESHY, true, new Color(0x906330), new Color(0x7B4E1B)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setSpeed(SPEED_FASTEST);
            template.setEffect(AlleleRegister.spawnHorse);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.FURTIVE), 0.25f);
            speciesBuilder.addSpecialty(new ItemStack(Items.LEATHER), 0.24f);
            speciesBuilder.addSpecialty(new ItemStack(Items.APPLE), 0.38f);
        }

        @Override
        public void registerMutations() {
            registerMutation(BEEF, SHEEPISH, 12).restrictBiomeType(BiomeDictionary.Type.PLAINS);
        }

    },
    CATTY("feline", EnumBeeBranches.FLESHY, true, new Color(0xECE684), new Color(0x563C24)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setCaveDwelling(TRUE_RECESSIVE);
            template.setTemperatureTolerance(TOLERANCE_BOTH_3);
            template.setEffect(AlleleRegister.spawnCat);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.setTemperature(EnumTemperature.HOT);
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.FURTIVE), 0.25f);
            speciesBuilder.addSpecialty(new ItemStack(Items.FISH), 0.24f);
        }

        @Override
        public void registerMutations() {
            registerMutation(CHICKEN, SPIDERY, 15).restrictBiomeType(BiomeDictionary.Type.JUNGLE);
        }

    },
    TIMELY("gallifreis", EnumBeeBranches.TIME, true, new Color(0xC6AF86)){

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setLifeSpan(LIFESPAN_ELONGATED);
            template.setEffect(AlleleRegister.effectSlowSpeed);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.TEMPORAL), 0.16f);
        }

        @Override
        public void registerMutations() {
            registerMutation(EnumBeeSpecies.getForestrySpecies("Imperial"), ETHEREAL, 8);
        }

    },
    LORDLY("rassilonis", EnumBeeBranches.TIME, false, new Color(0xC6AF86), new Color(0x8E0213)){

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setLifeSpan(LIFESPAN_LONG);
            template.setNeverSleeps(TRUE_RECESSIVE);
            template.setEffect((IAlleleEffect) EnumBeeSpecies.getForestryAllele("effectDrunkard"));
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.TEMPORAL), 0.19f);
        }

        @Override
        public void registerMutations() {
            registerMutation(EnumBeeSpecies.getForestrySpecies("Imperial"), TIMELY, 8);
        }

    },
    DOCTORAL("medicus qui", EnumBeeBranches.TIME, false, new Color(0xDDE5FC), new Color(0x4B6E8C)){

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setTemperatureTolerance(TOLERANCE_BOTH_3);
            template.setTerritory(TERRITORY_LARGE);
            template.setNeverSleeps(TRUE_RECESSIVE);
            template.setLifeSpan(LIFESPAN_LONGEST);
            template.setEffect((IAlleleEffect) EnumBeeSpecies.getForestryAllele("effectHeroic"));
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.TEMPORAL), 0.24f);
            //todo speciesBuilder.addSpecialty(new ItemStack(jellybaby), 0.078f);
        }

        @Override
        public void registerMutations() {
            registerMutation(TIMELY, LORDLY, 7);
        }

    },
    INFERNAL("infernales", EnumBeeBranches.ABOMINABLE, true, new Color(0xFF1C1C)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setSpeed(SPEED_SLOW);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.MOLTEN), 0.12f);
        }

        @Override
        public void registerMutations() {

        }

    },
    HATEFUL("odibilis", EnumBeeBranches.ABOMINABLE, false, new Color(0xDB00DB)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setSpeed(SPEED_SLOW);
            template.setLifeSpan(LIFESPAN_ELONGATED);
            template.setTerritory(TERRITORY_LARGER);
            template.setEffect((IAlleleEffect) EnumBeeSpecies.getForestryAllele("effectMisanthrope"));
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.MOLTEN), 0.18f);
        }

        @Override
        public void registerMutations() {
            registerMutation(INFERNAL, ELDRITCH, 9).restrictBiomeType(BiomeDictionary.Type.NETHER);
        }

    },
    SPITEFUL("maligna", EnumBeeBranches.ABOMINABLE, false, new Color(0x5FCC00)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setLifeSpan(LIFESPAN_LONG);
            template.setSpeed(SPEED_NORMAL);
            template.setTerritory(TERRITORY_LARGER);
            template.setEffect((IAlleleEffect) EnumBeeSpecies.getForestryAllele("effectMisanthrope"));
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.setHasEffect();
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.MOLTEN), 0.23f);
        }

        @Override
        public void registerMutations() {
            registerMutation(INFERNAL, HATEFUL, 7).restrictBiomeType(BiomeDictionary.Type.NETHER);
        }

    },
    WITHERING("vietus", EnumBeeBranches.ABOMINABLE, false, new Color(0x5B5B5B)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setTerritory(TERRITORY_LARGEST);
            template.setFertility(FERTILITY_LOW);
            template.setEffect(AlleleRegister.effectWithering);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.setHasEffect();
            //todo speciesBuilder.addSpecialty(skullChip, 0.15f);
        }

        @Override
        public void registerMutations() {
            registerMutation(EnumBeeSpecies.getForestrySpecies("Demonic"), SPITEFUL, 6).restrictBiomeType(BiomeDictionary.Type.NETHER);
        }

    },
    OBLIVION("oblivioni", EnumBeeBranches.EXTRINSIC, false, new Color(0xD5C3E5)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setFertility(FERTILITY_MAXIMUM);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.FORGOTTEN), 0.14f);
        }

        @Override
        public void registerMutations() {
            //Spawns in hives
        }

    },
    NAMELESS("sine nomine", EnumBeeBranches.EXTRINSIC, true, new Color(0x8ca7cb)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setFertility(FERTILITY_HIGH);
            template.setHumidityTolerance(TOLERANCE_BOTH_1);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.FORGOTTEN), 0.19f);
        }

        @Override
        public void registerMutations() {
            registerMutation(ETHEREAL, OBLIVION, 10);
        }

    },
    ABANDONED("reliquit", EnumBeeBranches.EXTRINSIC, true, new Color(0xc5cb8c)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setHumidityTolerance(TOLERANCE_BOTH_1);
            template.setLifeSpan(LIFESPAN_ELONGATED);
            template.setFertility(FERTILITY_NORMAL);
            template.setSpeed(SPEED_SLOW);
            template.setEffect((IAlleleEffect) EnumBeeSpecies.getForestryAllele("effectRepulsion"));
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.FORGOTTEN), 0.24f);
        }

        @Override
        public void registerMutations() {
            registerMutation(OBLIVION, NAMELESS, 8);
        }

    },
    FORLORN("perditus", EnumBeeBranches.EXTRINSIC, false, new Color(0xcba88c)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setFertility(FERTILITY_LOW);
            template.setHumidityTolerance(TOLERANCE_BOTH_1);
            template.setLifeSpan(LIFESPAN_LONGEST);
            template.setSpeed(SPEED_SLOW);
            template.setEffect((IAlleleEffect) EnumBeeSpecies.getForestryAllele("effectRepulsion"));
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.setHasEffect();
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.FORGOTTEN), 0.30f);
        }

        @Override
        public void registerMutations() {
            registerMutation(NAMELESS, ABANDONED, 6);
        }

    },
    DRACONIC("draconic", EnumBeeBranches.EXTRINSIC, false, new Color(0x9f56ad), new Color(0x5a3b62)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setFertility(FERTILITY_LOW);
            template.setHumidityTolerance(TOLERANCE_BOTH_1);
            template.setLifeSpan(LIFESPAN_LONGEST);
            template.setEffect((IAlleleEffect) EnumBeeSpecies.getForestryAllele("effectMisanthrope"));
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.setHasEffect();
            //todo speciesBuilder.addProduct(dragonDust, 0.15f);
        }

        @Override
        public void registerMutations() {
            registerMutation(EnumBeeSpecies.getForestrySpecies("Imperial"), ABANDONED, 6).restrictBiomeType(BiomeDictionary.Type.END);
        }

    },
    MUTABLE("mutable", EnumBeeBranches.TRANSMUTING, false, new Color(0xDBB24C), new Color(0xE0D5A6)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getForestryComb(EnumHoneyComb.PARCHED), 0.30f);
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.TRANSMUTED), 0.10f);
        }

        @Override
        public void registerMutations() {
            registerMutation(UNUSUAL, ELDRITCH, 12);
        }

    },
    TRANSMUTING("effectTransmuting", EnumBeeBranches.TRANSMUTING, false, new Color(0xDBB24C), new Color(0xA2D2D8)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setEffect(AlleleRegister.effectTransmuting);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getForestryComb(EnumHoneyComb.PARCHED), 0.10f);
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.TRANSMUTED), 0.30f);
            speciesBuilder.addProduct(EnumBeeSpecies.getForestryComb(EnumHoneyComb.SILKY), 0.5f);
            speciesBuilder.addProduct(EnumBeeSpecies.getForestryComb(EnumHoneyComb.SIMMERING), 0.5f);
        }

        @Override
        public void registerMutations() {
            registerMutation(UNUSUAL, MUTABLE, 9);
        }

    },
    CRUMBLING("crumbling", EnumBeeBranches.TRANSMUTING, false, new Color(0xDBB24C), new Color(0xDBA4A4)) {

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setEffect(AlleleRegister.effectCrumbling);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.setTemperature(EnumTemperature.HOT);
            speciesBuilder.setHumidity(EnumHumidity.ARID);
            speciesBuilder.addProduct(EnumBeeSpecies.getForestryComb(EnumHoneyComb.PARCHED), 0.10f);
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.TRANSMUTED), 0.30f);
            speciesBuilder.addProduct(EnumBeeSpecies.getForestryComb(EnumHoneyComb.POWDERY), 0.10f);
            speciesBuilder.addProduct(EnumBeeSpecies.getForestryComb(EnumHoneyComb.COCOA), 0.15f);
        }

        @Override
        public void registerMutations() {
            registerMutation(UNUSUAL, MUTABLE, 9);
        }

    },
    INVISIBLE("invisible", EnumBeeBranches.VEILED, false, new Color(0xffccff), new Color(0xffccff)){

        @Override
        public void modifyGenomeTemplate(BeeGenomeTemplate template) {
            template.setLifeSpan(LIFESPAN_SHORTEST);
            template.setHumidityTolerance(TOLERANCE_UP_1);
            template.setTemperatureTolerance(TOLERANCE_DOWN_2);
            template.setEffect(AlleleRegister.alleleInvisibility);
        }

        @Override
        public void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder) {
            speciesBuilder.addProduct(EnumBeeSpecies.getComb(EnumCombType.MUNDANE), 0.35f);
        }

        @Override
        public void registerMutations() {
            registerMutation(MYSTICAL, MUTABLE, 15);
        }

    },


    ;

    EnumBeeSpecies(String binominalName, IMoreBeesBranch branch, boolean dominant, Color primaryColor){
        this(binominalName, branch, dominant, primaryColor, branch.getSecondaryColor());
    }

    EnumBeeSpecies(String binomialName, IMoreBeesBranch branch, boolean dominant, Color primaryColor, Color secondaryColor){
        this.branch = branch;
        this.primaryColor = primaryColor.getRGB();
        this.secondaryColor = secondaryColor.getRGB();
        this.binominalName = binomialName;
        this.dominant = dominant;
        this.uid = MagicBees.modid + ".beeSpecies." + name().toLowerCase(Locale.ENGLISH);
        this.unlocalisedName = uid;
    }

    private final IMoreBeesBranch branch;
    private final int primaryColor, secondaryColor;
    private final String binominalName, uid, unlocalisedName;
    private final boolean dominant;

    private IIndividualDefinition<IBeeGenome, IBee, EnumBeeType> individual;

    @Override
    public int getPrimaryColor() {
        return primaryColor;
    }

    @Override
    public int getSecondaryColor() {
        return secondaryColor;
    }

    @Nonnull
    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public boolean isDominant() {
        return dominant;
    }

    @Nonnull
    @Override
    public String getUnlocalizedName() {
        return unlocalisedName;
    }

    @Nonnull
    @Override
    public String getBinominalName() {
        return this.binominalName;
    }

    @Nonnull
    @Override
    public IIndividualBranch<BeeGenomeTemplate> getIndividualBranch() {
        return branch;
    }

    @Override
    public abstract void modifyGenomeTemplate(BeeGenomeTemplate template);

    @Override
    public abstract void setSpeciesProperties(IAlleleBeeSpeciesBuilder speciesBuilder);

    @Override
    public abstract void registerMutations();

    private static ItemStack getComb(EnumCombType combType){
        return MagicBees.combItem.getStackFromType(combType);
    }

    private static ItemStack getDrop(EnumDropType drop){
        return MagicBees.dropItem.getStackFromType(drop);
    }

    private static ItemStack getPollen(EnumPollenType pollen){
        return MagicBees.pollenItem.getStackFromType(pollen);
    }

    private static ItemStack getForestryComb(EnumHoneyComb type){
        return new ItemStack(PluginApiculture.getItems().beeComb, 1, type.ordinal());
    }

    protected final IBeeMutationBuilder registerMutation(EnumBeeSpecies bee1, EnumBeeSpecies bee2, int chance) {
        if (!bee1.isActive()){
            MagicBees.logger.info("Species "+bee1+" is not active, not registering mutation for bee: "+this);
        }
        return registerMutation(bee1.getSpecies(), bee2, chance);
    }

    protected final IBeeMutationBuilder registerMutation(IAlleleBeeSpecies bee1, EnumBeeSpecies bee2, int chance) {
        if (!bee2.isActive()){
            MagicBees.logger.info("Species "+bee2+" is not active, not registering mutation for bee: "+this);
        }
        return forestry.api.apiculture.BeeManager.beeMutationFactory.createMutation(bee1, bee2.getSpecies(), this.individual.getAlleles(), chance);
    }

    public IAlleleBeeSpecies getSpecies(){
        return (IAlleleBeeSpecies) individual.getAlleles()[EnumBeeChromosome.SPECIES.ordinal()];
    }

    @Override
    public boolean isActive() {
        return branch.enabled();
    }

    @Override
    public void setIndividualDefinition(IIndividualDefinition<IBeeGenome, IBee, EnumBeeType> iIndividualDefinition) {
        individual = iIndividualDefinition;
    }

    @Override
    public IIndividualDefinition<IBeeGenome, IBee, EnumBeeType> getIndividualDefinition() {
        return individual;
    }

    protected void registerMundaneMutations(){
        String[] forestryMundane = new String[]{"Forest", "Meadows", "Modest", "Wintry", "Tropical", "Marshy"};
        for (String s : forestryMundane){
            forestry.api.apiculture.BeeManager.beeMutationFactory.createMutation(getSpecies(), getForestrySpecies(s), getForestrySpeciesTemplate("Common"), 15);
        }
        forestry.api.apiculture.BeeManager.beeMutationFactory.createMutation(getSpecies(), getForestrySpecies("Common"), getForestrySpeciesTemplate("Cultivated"), 12);
        forestry.api.apiculture.BeeManager.beeMutationFactory.createMutation(getSpecies(), getForestrySpecies("Cultivated"), ELDRITCH.getAlleles(), 12);
    }

    protected static IAllele[] getForestrySpeciesTemplate(String speciesName) {
        return BeeManager.beeRoot.getTemplate("forestry.species" + speciesName);
    }

    private static IAlleleBeeSpecies getForestrySpecies(String name) {
        return (IAlleleBeeSpecies) AlleleManager.alleleRegistry.getAllele("forestry.species" + name);
    }

    private static IAllele getForestryAllele(String name) {
        return AlleleManager.alleleRegistry.getAllele("forestry." + name);
    }

}
