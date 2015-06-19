package magicbees.bees;

import magicbees.item.types.CombType;
import magicbees.item.types.DropType;
import magicbees.item.types.NuggetType;
import magicbees.item.types.PollenType;
import magicbees.item.types.ResourceType;
import magicbees.main.Config;
import magicbees.main.utils.LogHelper;
import magicbees.main.utils.compat.ArsMagicaHelper;
import magicbees.main.utils.compat.BotaniaHelper;
import magicbees.main.utils.compat.EquivalentExchangeHelper;
import magicbees.main.utils.compat.ForestryHelper;
import magicbees.main.utils.compat.RedstoneArsenalHelper;
import magicbees.main.utils.compat.ThaumcraftHelper;
import magicbees.main.utils.compat.ThermalExpansionHelper;
import magicbees.main.utils.compat.BotaniaHelper.PastureSeed;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import static magicbees.bees.BeeSpecies.*;

public class BeeProductHelper {
	
	public static void initBaseProducts() {
		MYSTICAL.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 15);
		SORCEROUS.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 15);
		UNUSUAL.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 15);
		ATTUNED.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 15);
		ELDRITCH.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 15);

		ESOTERIC.addProduct(Config.combs.getStackForType(CombType.OCCULT), 18);
		MYSTERIOUS.addProduct(Config.combs.getStackForType(CombType.OCCULT), 20);
		ARCANE.addProduct(Config.combs.getStackForType(CombType.OCCULT), 25)
			.addSpecialty(Config.drops.getStackForType(DropType.ENCHANTED, 1), 9);

		CHARMED.addProduct(Config.combs.getStackForType(CombType.OTHERWORLDLY), 18);
		ENCHANTED.addProduct(Config.combs.getStackForType(CombType.OTHERWORLDLY), 20);
		SUPERNATURAL.addProduct(Config.combs.getStackForType(CombType.OTHERWORLDLY), 25)
			.addSpecialty(Config.pollen.getStackForType(PollenType.UNUSUAL), 8);

		ETHEREAL.addProduct(Config.combs.getStackForType(CombType.OCCULT), 10)
			.addProduct(Config.combs.getStackForType(CombType.OTHERWORLDLY), 10);

		WINDY.addProduct(Config.combs.getStackForType(CombType.AIRY), 25);
		FIREY.addProduct(Config.combs.getStackForType(CombType.FIREY), 25);
		EARTHY.addProduct(Config.combs.getStackForType(CombType.EARTHY), 25);
		WATERY.addProduct(Config.combs.getStackForType(CombType.WATERY), 25)
			.addSpecialty(new ItemStack(Blocks.ice), 2);

		PUPIL.addProduct(Config.combs.getStackForType(CombType.PAPERY), 20);
		SCHOLARLY.addProduct(Config.combs.getStackForType(CombType.PAPERY), 25);
		SAVANT.addProduct(Config.combs.getStackForType(CombType.PAPERY), 40);

		AWARE.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 18);
		SPIRIT.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 22)
			.addSpecialty(Config.combs.getStackForType(CombType.SOUL), 16);
		SOUL.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 28)
			.addSpecialty(Config.combs.getStackForType(CombType.SOUL), 20);

		SKULKING.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 10);
		GHASTLY.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 8)
			.addSpecialty(new ItemStack(Items.ghast_tear), 2);
		SPIDERY.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 13)
			.addProduct(new ItemStack(Items.string), 8)
			.addSpecialty(new ItemStack(Items.spider_eye), 8);
		SMOULDERING.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 10)
			.addProduct(Config.combs.getStackForType(CombType.MOLTEN), 10)
			.addSpecialty(new ItemStack(Items.blaze_rod), 5);
		BIGBAD.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 18)
			.addProduct(new ItemStack(Items.beef), 12)
			.addProduct(new ItemStack(Items.chicken), 12)
			.addSpecialty(new ItemStack(Items.melon), 10);

		TIMELY.addProduct(Config.combs.getStackForType(CombType.TEMPORAL), 16);
		LORDLY.addProduct(Config.combs.getStackForType(CombType.TEMPORAL), 19);
		DOCTORAL.addProduct(Config.combs.getStackForType(CombType.TEMPORAL), 24)
			.addSpecialty(new ItemStack(Config.jellyBaby), 7);

		INFERNAL.addProduct(Config.combs.getStackForType(CombType.MOLTEN), 12);
		HATEFUL.addProduct(Config.combs.getStackForType(CombType.MOLTEN), 18);
		SPITEFUL.addProduct(Config.combs.getStackForType(CombType.MOLTEN), 24);
		WITHERING.addSpecialty(Config.miscResources.getStackForType(ResourceType.SKULL_CHIP), 15);

		OBLIVION.addProduct(Config.combs.getStackForType(CombType.FORGOTTEN), 14);
		NAMELESS.addProduct(Config.combs.getStackForType(CombType.FORGOTTEN), 19);
		ABANDONED.addProduct(Config.combs.getStackForType(CombType.FORGOTTEN), 24);
		FORLORN.addProduct(Config.combs.getStackForType(CombType.FORGOTTEN), 30);
		DRACONIC.addSpecialty(Config.miscResources.getStackForType(ResourceType.DRAGON_DUST), 15);

		IRON.addProduct(ForestryHelper.itemHoneycomb, 10)
				.addSpecialty(Config.nuggets.getStackForType(NuggetType.IRON), 18);
		GOLD.addProduct(ForestryHelper.itemHoneycomb, 10)
				.addSpecialty(new ItemStack(Items.gold_nugget, 1), 16);
		DIAMOND.addProduct(ForestryHelper.itemHoneycomb, 10)
				.addSpecialty(Config.nuggets.getStackForType(NuggetType.DIAMOND), 6);
		EMERALD.addProduct(ForestryHelper.itemHoneycomb, 10)
				.addSpecialty(Config.nuggets.getStackForType(NuggetType.EMERALD), 4);
		COPPER.addProduct(ForestryHelper.itemHoneycomb, 10)
				.addSpecialty(Config.nuggets.getStackForType(NuggetType.COPPER), 20);
		TIN.addProduct(ForestryHelper.itemHoneycomb, 10)
				.addSpecialty(Config.nuggets.getStackForType(NuggetType.TIN), 20);
		APATITE.addProduct(ForestryHelper.itemHoneycomb, 10)
				.addSpecialty(Config.nuggets.getStackForType(NuggetType.APATITE), 10);
		
		MUTABLE.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.PARCHED.ordinal()), 30)
				.addProduct(Config.combs.getStackForType(CombType.TRANSMUTED), 10);
		TRANSMUTING.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.PARCHED.ordinal()), 10)
				.addProduct(Config.combs.getStackForType(CombType.TRANSMUTED), 30)
				.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.SILKY.ordinal()), 5)
				.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.SIMMERING.ordinal()), 5);
		CRUMBLING.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.PARCHED.ordinal()), 10)
				.addProduct(Config.combs.getStackForType(CombType.TRANSMUTED), 30)
				.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.POWDERY.ordinal()), 10)
				.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.COCOA.ordinal()), 15);
		
		INVISIBLE.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 35);

	}

	public static void initOreDictSProducts() {
		SILVER.addProduct(ForestryHelper.itemHoneycomb, 10);
		if (OreDictionary.getOres("ingotSilver").size() > 0) {
			SILVER.addSpecialty(Config.nuggets.getStackForType(NuggetType.SILVER), 16);
		}
		else {
			SILVER.setInactive();
		}
		
		LEAD.addProduct(ForestryHelper.itemHoneycomb, 10);
		if (OreDictionary.getOres("ingotLead").size() > 0) {
			LEAD.addSpecialty(Config.nuggets.getStackForType(NuggetType.LEAD), 17);
		}
		else {
			LEAD.setInactive();
		}
		
		ALUMINUM.addProduct(ForestryHelper.itemHoneycomb, 10);
		if (OreDictionary.getOres("nuggetAluminum").size() > 0) {
			ALUMINUM.addSpecialty(OreDictionary.getOres("nuggetAluminum").get(0), 20);
		} else {
			ALUMINUM.setInactive();
		}

		TE_PLATINUM.addProduct(ForestryHelper.itemHoneycomb, 10);
		if (OreDictionary.getOres("nuggetPlatinum").size() > 0&& OreDictionary.getOres("ingotPlatinum").size() > 0) {
			TE_PLATINUM.addSpecialty(OreDictionary.getOres("nuggetPlatinum").get(0), 18);
		} else {
			TE_PLATINUM.setInactive();
		}

		TE_NICKEL.addProduct(ForestryHelper.itemHoneycomb, 10);
		if (OreDictionary.getOres("nuggetNickel").size() > 0 && OreDictionary.getOres("ingotNickel").size() > 0) {
			TE_NICKEL.addSpecialty(OreDictionary.getOres("nuggetNickel").get(0), 18);
		} else {
			TE_NICKEL.setInactive();
		}

		ARDITE.addProduct(ForestryHelper.itemHoneycomb, 10);
		if (OreDictionary.getOres("nuggetArdite").size() > 0) {
			ARDITE.addSpecialty(OreDictionary.getOres("nuggetArdite").get(0), 18);
		} else {
			ARDITE.setInactive();
		}

		COBALT.addProduct(ForestryHelper.itemHoneycomb, 10);
		if (OreDictionary.getOres("nuggetCobalt").size() > 0) {
			COBALT.addSpecialty(OreDictionary.getOres("nuggetCobalt").get(0), 18);
		}
		if (OreDictionary.getOres("nuggetNaturalCobalt").size() > 0) {
			COBALT.addSpecialty(OreDictionary.getOres("nuggetNaturalCobalt").get(0), 24);
		}
		if (COBALT.getSpecialty().size() <= 0) {
			COBALT.setInactive();
		}

		TE_BRONZE.addProduct(ForestryHelper.itemHoneycomb, 10);
		if (OreDictionary.getOres("nuggetBronze").size() > 0 && OreDictionary.getOres("ingotBronze").size() > 0) {
			TE_BRONZE.addSpecialty(OreDictionary.getOres("nuggetBronze").get(0), 18);
		} else {
			TE_BRONZE.setInactive();
		}

		TE_INVAR.addProduct(ForestryHelper.itemHoneycomb, 10);
		if (OreDictionary.getOres("nuggetInvar").size() > 0 && OreDictionary.getOres("ingotInvar").size() > 0) {
			TE_INVAR.addSpecialty(OreDictionary.getOres("nuggetInvar").get(0), 18);
		} else {
			TE_INVAR.setInactive();
		}

		TE_ELECTRUM.addProduct(ForestryHelper.itemHoneycomb, 10);
		if (OreDictionary.getOres("nuggetElectrum").size() > 0 && OreDictionary.getOres("ingotElectrum").size() > 0) {
			TE_ELECTRUM.addSpecialty(OreDictionary.getOres("nuggetElectrum").get(0), 18);
		} else {
			TE_ELECTRUM.setInactive();
		}

		MANYULLYN.addProduct(ForestryHelper.itemHoneycomb, 10);
		if (OreDictionary.getOres("nuggetManyullyn").size() > 0) {
			MANYULLYN.addSpecialty(OreDictionary.getOres("nuggetManyullyn").get(0), 16);
		} else {
			MANYULLYN.setInactive();
		}
		
		OSMIUM.addProduct(ForestryHelper.itemHoneycomb, 15);
		if (OreDictionary.getOres("nuggetOsmium").size() > 0) {
			OSMIUM.addSpecialty(OreDictionary.getOres("nuggetOsmium").get(0), 16);
		}
		else {
			OSMIUM.setInactive();
		}
	}
	
	public static void initThaumcraftProducts() {
		TC_AIR.addProduct(Config.combs.getStackForType(CombType.AIRY), 20);
		TC_FIRE.addProduct(Config.combs.getStackForType(CombType.FIREY), 20);
		TC_WATER.addProduct(Config.combs.getStackForType(CombType.WATERY), 20);
		TC_EARTH.addProduct(Config.combs.getStackForType(CombType.EARTHY), 20);
		TC_ORDER.addProduct(Config.combs.getStackForType(CombType.TC_ORDER), 20);
		TC_CHAOS.addProduct(Config.combs.getStackForType(CombType.TC_CHAOS), 20);

		TC_VIS.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 10);
		TC_TAINT.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 18);
		TC_ATTRACT.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 14);
		TC_PURE.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 16);
		TC_REJUVENATING.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 18);

		TC_BRAINY.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 10)
			.addProduct(new ItemStack(Items.rotten_flesh), 6);
		TC_BATTY.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 10)
			.addSpecialty(new ItemStack(Items.gunpowder), 4);
		TC_WISPY.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.SILKY.ordinal()), 22)
				.addSpecialty(new ItemStack(Config.fCraftingResource, 1, ForestryHelper.CraftingMaterial.SILK_WISP.ordinal()), 4);
		TC_CHICKEN.addProduct(ForestryHelper.itemHoneycomb, 10);
		TC_BEEF.addProduct(ForestryHelper.itemHoneycomb, 10);
		TC_PORK.addProduct(ForestryHelper.itemHoneycomb, 10);
		
		if (ThaumcraftHelper.isActive()) {
			SCHOLARLY.addSpecialty(Config.miscResources.getStackForType(ResourceType.LORE_FRAGMENT), 2);
			SAVANT.addSpecialty(Config.miscResources.getStackForType(ResourceType.LORE_FRAGMENT), 5);

			TC_AIR.addSpecialty(new ItemStack(Config.tcShard, 1, ThaumcraftHelper.ShardType.AIR.ordinal()), 5);
			TC_FIRE.addSpecialty(new ItemStack(Config.tcShard, 1, ThaumcraftHelper.ShardType.FIRE.ordinal()), 5);
			TC_WATER.addSpecialty(new ItemStack(Config.tcShard, 1, ThaumcraftHelper.ShardType.WATER.ordinal()), 5);
			TC_EARTH.addSpecialty(new ItemStack(Config.tcShard, 1, ThaumcraftHelper.ShardType.EARTH.ordinal()), 5);
			TC_ORDER.addSpecialty(new ItemStack(Config.tcShard, 1, ThaumcraftHelper.ShardType.ORDER.ordinal()), 5);
			TC_CHAOS.addSpecialty(new ItemStack(Config.tcShard, 1, ThaumcraftHelper.ShardType.CHAOS.ordinal()), 5);

			TC_BRAINY.addSpecialty(new ItemStack(Config.tcMiscResource, 1, ThaumcraftHelper.MiscResource.ZOMBIE_BRAIN.ordinal()), 2);
			TC_CHICKEN.addSpecialty(new ItemStack(Config.tcNuggetChicken, 1), 9);
			TC_BEEF.addSpecialty(new ItemStack(Config.tcNuggetBeef, 1), 9);
			TC_PORK.addSpecialty(new ItemStack(Config.tcNuggetPork, 1), 9);
		}
	}
	
	public static void initEquivalentExchange3Species() {
		EE_MINIUM.addProduct(Config.combs.getStackForType(CombType.OCCULT), 16);
		
		if (EquivalentExchangeHelper.isActive()) {
			EE_MINIUM.addSpecialty(new ItemStack(Config.eeMinuimShard), 6);
		}
	}
	
	public static void initArsMagicaSpecies() {
		AM_ESSENCE.addProduct(Config.combs.getStackForType(CombType.AM_ESSENCE), 12);
		AM_QUINTESSENCE.addProduct(Config.combs.getStackForType(CombType.AM_ESSENCE), 23);
		AM_EARTH.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 12);
		AM_AIR.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 12);
		AM_FIRE.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 12);
		AM_WATER.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 12);
		AM_LIGHTNING.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 12);
		AM_PLANT.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 12);
		AM_ICE.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 12);
		AM_ARCANE.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 19);
		AM_VORTEX.addProduct(Config.combs.getStackForType(CombType.AM_ESSENCE), 10);
		AM_WIGHT.addProduct(Config.combs.getStackForType(CombType.SOUL), 30)
			.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 10);
		
		if (ArsMagicaHelper.isActive()) {
			AM_QUINTESSENCE.addSpecialty(new ItemStack(Config.amItemResource, 1, ArsMagicaHelper.ResourceType.ARCANE_COMPOUND.ordinal()), 5);
			AM_EARTH.addSpecialty(new ItemStack(Config.amEssence, 1, ArsMagicaHelper.EssenceType.EARTH.ordinal()), 7);
			AM_AIR.addSpecialty(new ItemStack(Config.amEssence, 1, ArsMagicaHelper.EssenceType.AIR.ordinal()), 7);
			AM_FIRE.addSpecialty(new ItemStack(Config.amEssence, 1, ArsMagicaHelper.EssenceType.FIRE.ordinal()), 7);
			AM_WATER.addSpecialty(new ItemStack(Config.amEssence, 1, ArsMagicaHelper.EssenceType.WATER.ordinal()), 7);
			AM_LIGHTNING.addSpecialty(new ItemStack(Config.amEssence, 1, ArsMagicaHelper.EssenceType.LIGHTNING.ordinal()), 7);
			AM_PLANT.addSpecialty(new ItemStack(Config.amEssence, 1, ArsMagicaHelper.EssenceType.PLANT.ordinal()), 7);
			AM_ICE.addSpecialty(new ItemStack(Config.amEssence, 1, ArsMagicaHelper.EssenceType.ICE.ordinal()), 7);
			AM_ARCANE.addSpecialty(new ItemStack(Config.amEssence, 1, ArsMagicaHelper.EssenceType.ARCANE.ordinal()), 11);
			AM_VORTEX.addSpecialty(new ItemStack(Config.amEssence, 1, ArsMagicaHelper.EssenceType.EARTH.ordinal()), 15);
			AM_WIGHT.addSpecialty(new ItemStack(Config.amItemResource, 1, ArsMagicaHelper.ResourceType.ARCANE_COMPOUND.ordinal()), 11);
		}
	}
	
	public static void initThermalExpansionProducts() {
		TE_DANTE.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 10)
			.addProduct(Config.combs.getStackForType(CombType.MOLTEN), 10)
			.addSpecialty(new ItemStack(Items.blaze_powder), 5);
		TE_PYRO.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 10)
			.addProduct(Config.combs.getStackForType(CombType.MOLTEN), 10)
			.addSpecialty(new ItemStack(Items.blaze_powder), 5);
		TE_DESTABILIZED.addProduct(Config.combs.getStackForType(CombType.TE_DESTABILIZED), 10)
			.addProduct(Config.combs.getStackForType(CombType.OCCULT), 10)
			.addSpecialty(new ItemStack(Items.redstone), 5);
		TE_LUX.addProduct(Config.combs.getStackForType(CombType.TE_LUX), 10)
			.addProduct(Config.combs.getStackForType(CombType.OCCULT), 10)
			.addSpecialty(new ItemStack(Items.glowstone_dust), 5);
		TE_WINSOME.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 10)
			.addProduct(Config.combs.getStackForType(CombType.TE_ENDEARING), 5)
			.addSpecialty(new ItemStack(Items.ender_pearl), 5);
		TE_ENDEARING.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.MYSTERIOUS.ordinal()), 10)
			.addProduct(Config.combs.getStackForType(CombType.TE_ENDEARING), 5);
		TE_COAL.addProduct(ForestryHelper.itemHoneycomb, 10)
			.addProduct(Config.combs.getStackForType(CombType.TE_CARBON), 5)
			.addSpecialty(new ItemStack(Items.coal), 5);
		TE_BLIZZY.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.FROZEN.ordinal()), 10);
		TE_GELID.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.FROZEN.ordinal()), 10);
		
		if (ThermalExpansionHelper.isActive()) {
			LogHelper.info("Thermal Expansion bees active");
			TE_BLIZZY.addSpecialty(Config.teDustBlizz, 9);
			TE_GELID.addSpecialty(Config.teDustCryotheum, 9);
			TE_DANTE.addSpecialty(Config.teDustSulfur, 9);
			TE_PYRO.addSpecialty(Config.teDustPyrotheum, 9);
			TE_WINSOME.addSpecialty(Config.teDustPlatinum, 9);
			TE_ENDEARING.addSpecialty(Config.teEnderiumNugget, 9);
		}
	}
	
	public static void initRedstoneArsenelProducts() {
		RSA_FLUXED.addProduct(ForestryHelper.itemHoneycomb, 10);
		
		if (RedstoneArsenalHelper.isActive()) {
			RSA_FLUXED.addSpecialty(RedstoneArsenalHelper.fluxNugget, 9);
		}
	}
	
	public static void initBloodMagicProducts() {
		BM_BLOODY.addProduct(ForestryHelper.itemHoneycomb, 10);
		BM_BOUND.addProduct(ForestryHelper.itemHoneycomb, 10);
	}
	
	public static void initBotaniaProducts() {
		BOT_ROOTED.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 10);
		BOT_BOTANIC.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 10)
			.addProduct(Config.combs.getStackForType(CombType.TRANSMUTED), 5);
		BOT_BLOSSOM.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 20)
			.addProduct(Config.combs.getStackForType(CombType.TRANSMUTED), 5);
		BOT_FLORAL.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 25)
			.addProduct(Config.combs.getStackForType(CombType.TRANSMUTED), 5);
		BOT_VAZBEE.addProduct(Config.combs.getStackForType(CombType.SOUL), 5)
			.addProduct(new ItemStack(Items.dye, 1, 9), 20)
			.addProduct(new ItemStack(Blocks.wool, 1, 9), 2)
			.addProduct(new ItemStack(Blocks.red_flower, 1, 7), 6)
			.addProduct(Config.combs.getStackForType(CombType.TRANSMUTED), 15);
		BOT_SOMNOLENT.addProduct(Config.combs.getStackForType(CombType.WATERY), 8)
			.addProduct(Config.combs.getStackForType(CombType.SOUL), 15);
		BOT_DREAMING.addProduct(Config.combs.getStackForType(CombType.WATERY), 16)
			.addProduct(Config.combs.getStackForType(CombType.SOUL), 33);
		BOT_ALFHEIM.addProduct(Config.combs.getStackForType(CombType.OTHERWORLDLY), 28);
		
		if (BotaniaHelper.isActive()) {
			for (int i = 0; i < 16; ++i) {
				ItemStack petal = new ItemStack(BotaniaHelper.itemPetal, 1, i);
				BOT_BOTANIC.addSpecialty(petal, 1);
				BOT_BLOSSOM.addSpecialty(petal, 1);
				BOT_FLORAL.addSpecialty(petal, 1);
			}

			for (PastureSeed type : PastureSeed.values()) {
				BOT_VAZBEE.addSpecialty(new ItemStack(BotaniaHelper.itemPastureSeed, 1, type.ordinal()), 4);
			}
		}
	}
}
