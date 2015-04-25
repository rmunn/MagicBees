package magicbees.bees;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import magicbees.item.types.CombType;
import magicbees.item.types.DropType;
import magicbees.item.types.NuggetType;
import magicbees.item.types.PollenType;
import magicbees.item.types.ResourceType;
import magicbees.main.CommonProxy;
import magicbees.main.Config;
import magicbees.main.utils.LocalizationManager;
import magicbees.main.utils.LogHelper;
import magicbees.main.utils.compat.ArsMagicaHelper;
import magicbees.main.utils.compat.BloodMagicHelper;
import magicbees.main.utils.compat.BotaniaHelper;
import magicbees.main.utils.compat.BotaniaHelper.PastureSeed;
import magicbees.main.utils.compat.EquivalentExchangeHelper;
import magicbees.main.utils.compat.ForestryHelper;
import magicbees.main.utils.compat.RedstoneArsenalHelper;
import magicbees.main.utils.compat.ThaumcraftHelper;
import magicbees.main.utils.compat.ThermalExpansionHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import com.mojang.authlib.GameProfile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.apiculture.EnumBeeType;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.IBeeRoot;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.core.IIconProvider;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.EnumTolerance;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IAlleleTolerance;
import forestry.api.genetics.IClassification;
import forestry.api.genetics.IIndividual;
import forestry.api.genetics.IMutation;

public enum BeeSpecies implements IAlleleBeeSpecies, IIconProvider
{
	MYSTICAL("Mystical", "mysticum", BeeGenomeManager.getTemplateMystical(), BeeClassification.VEILED,
			0xAFFFB7, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false, true),
	SORCEROUS("Sorcerous", "fascinatio", BeeGenomeManager.getTemplateSorcerous(), BeeClassification.VEILED,
			0xEA9A9A, EnumTemperature.HOT, EnumHumidity.ARID, false, false, true),
	UNUSUAL("Unusual", "inusitatus", BeeGenomeManager.getTemplateUnusual(), BeeClassification.VEILED,
			0x72D361, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false, true),
	ATTUNED("Attuned", "similis", BeeGenomeManager.getTemplateAttuned(), BeeClassification.VEILED,
			0x0086A8, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false, true),
	
	ELDRITCH("Eldritch", "prodigiosus", BeeGenomeManager.getTemplateEldritch(), BeeClassification.VEILED,
			0x8D75A0, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	
	ESOTERIC("Esoteric", "secretiore", BeeGenomeManager.getTemplateEsoteric(), BeeClassification.ARCANE,
			0x001099, BodyColours.ARCANE, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	MYSTERIOUS("Mysterious", "mysticus", BeeGenomeManager.getTemplateMysterious(), BeeClassification.ARCANE,
			0x762bc2, BodyColours.ARCANE, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	ARCANE("Arcane", "arcanus", BeeGenomeManager.getTemplateArcane(), BeeClassification.ARCANE,
			0xd242df, BodyColours.ARCANE, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, true),

	CHARMED("Charmed", "larvatus", BeeGenomeManager.getTemplateCharmed(), BeeClassification.SUPERNATURAL,
			0x48EEEC, BodyColours.ARCANE, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	ENCHANTED("Enchanted", "cantatus", BeeGenomeManager.getTemplateEnchanted(), BeeClassification.SUPERNATURAL,
			0x18e726, BodyColours.ARCANE, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	SUPERNATURAL("Supernatural", "coeleste", BeeGenomeManager.getTemplateSupernatural(), BeeClassification.SUPERNATURAL,
			0x005614, BodyColours.ARCANE, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, true),

	ETHEREAL("Ethereal", "diaphanum", BeeGenomeManager.getTemplateEthereal(),BeeClassification.MAGICAL,
			0xBA3B3B, 0xEFF8FF, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),

	WATERY("Watery", "aquatilis", BeeGenomeManager.getTemplateWatery(), BeeClassification.MAGICAL,
			0x313C5E, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	EARTHY("Earthen", "fictili", BeeGenomeManager.getTemplateEarthy(), BeeClassification.MAGICAL,
			0x78822D, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	FIREY("Firey", "ardens", BeeGenomeManager.getTemplateFirey(), BeeClassification.MAGICAL,
			0xD35119, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	WINDY("Windy", "ventosum", BeeGenomeManager.getTemplateWindy(), BeeClassification.MAGICAL,
			0xFFFDBA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),

	PUPIL("Pupil", "disciplina", BeeGenomeManager.getTemplatePupil(), BeeClassification.SCHOLARLY,
			0xFFFF00, EnumTemperature.NORMAL, EnumHumidity.ARID, false, true),
	SCHOLARLY("Scholarly", "studiosis", BeeGenomeManager.getTemplateScholarly(), BeeClassification.SCHOLARLY,
			0x6E0000, EnumTemperature.NORMAL, EnumHumidity.ARID, false, false),
	SAVANT("Savant", "philologus", BeeGenomeManager.getTemplateSavant(), BeeClassification.SCHOLARLY,
			0xFFA042, EnumTemperature.NORMAL, EnumHumidity.ARID, true, false),

	AWARE("Aware", "sensibilis", BeeGenomeManager.getTemplateAware(), BeeClassification.MAGICAL,
			0x5E95B5, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	SPIRIT("Spirit", "larva", BeeGenomeManager.getTemplateSpirit(), BeeClassification.SOUL,
			0xb2964b, EnumTemperature.WARM, EnumHumidity.NORMAL, false, true),
	SOUL("Soul", "anima", BeeGenomeManager.getTemplateSoul(), BeeClassification.SOUL,
			0x7d591b, EnumTemperature.HELLISH, EnumHumidity.NORMAL, true, false),

	SKULKING("Skulking", "malevolens", BeeGenomeManager.getTemplateSkulking(), BeeClassification.SKULKING,
			0x524827, BodyColours.SKULKING, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	GHASTLY("Ghastly", "pallens", BeeGenomeManager.getTemplateGhastly(), BeeClassification.SKULKING,
			0xccccee, 0xbf877c, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	SPIDERY("Spidery", "araneolus", BeeGenomeManager.getTemplateSpidery(), BeeClassification.SKULKING,
			0x0888888, 0x222222, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	SMOULDERING("Smouldering", "flagrantia", BeeGenomeManager.getTemplateSmouldering(), BeeClassification.SKULKING,
			0xFFC747, 0xEA8344, EnumTemperature.HELLISH, EnumHumidity.NORMAL, false, false),
	BIGBAD("BigBad", "magnumalum", BeeGenomeManager.getTemplateBigbad(), BeeClassification.SKULKING,
			0xA9344B, 0x453536, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),

	TIMELY("Timely", "gallifreis", BeeGenomeManager.getTemplateTimely(), BeeClassification.TIME,
			0xC6AF86, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	LORDLY("Lordly", "rassilonis", BeeGenomeManager.getTemplateLordly(), BeeClassification.TIME,
			0xC6AF86, 0x8E0213, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	DOCTORAL("Doctoral", "medicus qui", BeeGenomeManager.getTemplateDoctoral(), BeeClassification.TIME,
			0xDDE5FC, 0x4B6E8C, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, false),

	INFERNAL("Infernal", "infernales", BeeGenomeManager.getTemplateInfernal(), BeeClassification.ABOMINABLE,
			0xFF1C1C, BodyColours.ABOMINABLE, EnumTemperature.HELLISH, EnumHumidity.ARID, false, true),
	HATEFUL("Hateful", "odibilis", BeeGenomeManager.getTemplateHateful(), BeeClassification.ABOMINABLE,
			0xDB00DB, BodyColours.ABOMINABLE, EnumTemperature.HELLISH, EnumHumidity.ARID, false, false),
	SPITEFUL("Spiteful", "maligna", BeeGenomeManager.getTemplateSpiteful(), BeeClassification.ABOMINABLE,
			0x5FCC00, BodyColours.ABOMINABLE, EnumTemperature.HELLISH, EnumHumidity.ARID, true, false),
	WITHERING("Withering", "vietus", BeeGenomeManager.getTemplateWithering(), BeeClassification.ABOMINABLE,
			0x5B5B5B, BodyColours.ABOMINABLE, EnumTemperature.HELLISH, EnumHumidity.ARID, true, false),

	OBLIVION("Oblivion", "oblivioni", BeeGenomeManager.getTemplateOblivion(), BeeClassification.EXTRINSIC,
			0xD5C3E5, BodyColours.EXTRINSIC, EnumTemperature.COLD, EnumHumidity.NORMAL, false, false),
	NAMELESS("Nameless", "sine nomine", BeeGenomeManager.getTemplateNameless(), BeeClassification.EXTRINSIC,
			0x8ca7cb, BodyColours.EXTRINSIC, EnumTemperature.COLD, EnumHumidity.NORMAL, false, true),
	ABANDONED("Abandoned", "reliquit", BeeGenomeManager.getTemplateAbandoned(), BeeClassification.EXTRINSIC,
			0xc5cb8c, BodyColours.EXTRINSIC, EnumTemperature.COLD, EnumHumidity.NORMAL, false, true),
	FORLORN("Forlorn", "perditus", BeeGenomeManager.getTemplateForlorn(), BeeClassification.EXTRINSIC,
			0xcba88c, BodyColours.EXTRINSIC, EnumTemperature.COLD, EnumHumidity.NORMAL, true, false),
	DRACONIC("Draconic", "draconic", BeeGenomeManager.getTemplateDraconic(), BeeClassification.EXTRINSIC,
			0x9f56ad, 0x5a3b62, EnumTemperature.COLD, EnumHumidity.NORMAL, true, false),

	IRON("Iron", "ferrus", BeeGenomeManager.getTemplateIron(), BeeClassification.METALLIC,
			0x686868, 0xE9E9E9, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	GOLD("Gold", "aurum", BeeGenomeManager.getTemplateGold(), BeeClassification.METALLIC,
			0x684B01, 0xFFFF0B, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, false),
	COPPER("Copper", "aercus", BeeGenomeManager.getTemplateCopper(), BeeClassification.METALLIC,
			0x684B01, 0xFFC81A, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	TIN("Tin", "stannum", BeeGenomeManager.getTemplateTin(), BeeClassification.METALLIC,
			0x3E596D, 0xA6BACB, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	SILVER("Silver", "argenteus", BeeGenomeManager.getTemplateSilver(), BeeClassification.METALLIC,
			0x747C81, 0x96BFC4, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	LEAD("Lead", "plumbeus", BeeGenomeManager.getTemplateLead(), BeeClassification.METALLIC,
			0x96BFC4, 0x91A9F3, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	ALUMINUM("Aluminum", "aluminium", BeeGenomeManager.getTemplateAluminum(), BeeClassification.METALLIC,
			0xEDEDED, 0x767676, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	ARDITE("Ardite", "aurantiaco", BeeGenomeManager.getTemplateArdite(), BeeClassification.METALLIC,
			0x720000, 0xFF9E00, EnumTemperature.HOT, EnumHumidity.ARID, false, false),
	COBALT("Cobalt", "caeruleo", BeeGenomeManager.getTemplateCobalt(), BeeClassification.METALLIC,
			0x03265F, 0x59AAEF, EnumTemperature.HOT, EnumHumidity.ARID, false, false),
	MANYULLYN("Manyullyn", "manahmanah", BeeGenomeManager.getTemplateManyullyn(), BeeClassification.METALLIC,
			0x481D6D, 0xBD92F1, EnumTemperature.HOT, EnumHumidity.ARID, true, false),

	DIAMOND("Diamond", "diamond", BeeGenomeManager.getTemplateDiamond(), BeeClassification.GEM,
			0x209581, 0x8DF5E3, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, false),
	EMERALD("Emerald", "prasinus", BeeGenomeManager.getTemplateEmerald(), BeeClassification.GEM,
			0x005300, 0x17DD62, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, false),
	APATITE("Apatite", "apatite", BeeGenomeManager.getTemplateApatite(), BeeClassification.GEM,
			0x2EA7EC, 0x001D51, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),

	MUTABLE("Mutable", "mutable", BeeGenomeManager.getTemplateMutable(), BeeClassification.TRANSMUTING,
			0xDBB24C, 0xE0D5A6, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TRANSMUTING("Transmuting", "transmuting", BeeGenomeManager.getTemplateTransmuting(), BeeClassification.TRANSMUTING,
			0xDBB24C, 0xA2D2D8, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	CRUMBLING("Crumbling", "crumbling", BeeGenomeManager.getTemplateCrumbling(), BeeClassification.TRANSMUTING,
			0xDBB24C, 0xDBA4A4, EnumTemperature.HOT, EnumHumidity.ARID, false, false),

	INVISIBLE("Invisible", "invisible", BeeGenomeManager.getTemplateInvisible(), BeeClassification.VEILED,
			0xffccff, 0xffffff, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),

	// --------- Thaumcraft Bees ---------------------------------------------------------------------------------------
	TC_AIR("TCAir", "aether", BeeGenomeManager.getTemplateTCAir(), BeeClassification.THAUMIC,
			0xD9D636, BodyColours.THAUMCRAFT, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, true),
	TC_FIRE("TCFire", "praefervidus", BeeGenomeManager.getTemplateTCFire(), BeeClassification.THAUMIC,
			0xE50B0B, BodyColours.THAUMCRAFT, EnumTemperature.HOT, EnumHumidity.ARID, true, true),
	TC_WATER("TCWater", "umidus", BeeGenomeManager.getTemplateTCWater(), BeeClassification.THAUMIC,
			0x36CFD9, BodyColours.THAUMCRAFT, EnumTemperature.NORMAL, EnumHumidity.DAMP, true, true),
	TC_EARTH("TCEarth", "sordida", BeeGenomeManager.getTemplateTCEarth(), BeeClassification.THAUMIC,
			0x005100, BodyColours.THAUMCRAFT, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, true),
	TC_ORDER("TCOrder", "ordinatus", BeeGenomeManager.getTemplateTCOrder(), BeeClassification.THAUMIC,
			0xaa32fc, BodyColours.THAUMCRAFT, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, true),
	TC_CHAOS("TCChaos", "tenebrarum", BeeGenomeManager.getTemplateTCChaos(), BeeClassification.THAUMIC,
			0xCCCCCC, BodyColours.THAUMCRAFT, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, false),

	TC_VIS("TCVis", "arcanus saecula", BeeGenomeManager.getTemplateTCVis(), BeeClassification.THAUMIC,
			0x004c99, 0x675ED1, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TC_TAINT("TCFlux", "arcanus labe", BeeGenomeManager.getTemplateTCTaint(), BeeClassification.THAUMIC,
			0x91376A, 0x675ED1, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TC_ATTRACT("TCAttractive", "tractus", BeeGenomeManager.getTemplateTCAttract(), BeeClassification.THAUMIC,
			0x96FFBC, 0x675ED1, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	TC_REJUVENATING("TCRejuvenating", "arcanus vitae", BeeGenomeManager.getTemplateTCRejuvinating(), BeeClassification.THAUMIC,
			0x91D0D9, 0x675ED1, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TC_PURE("TCPure", "arcanus puritatem", BeeGenomeManager.getTemplateTCPure(), BeeClassification.THAUMIC,
			0xb0092e, 0x675ED1, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),

	TC_BRAINY("TCBrainy", "cerebrum", BeeGenomeManager.getTemplateTCBrainy(), BeeClassification.THAUMIC,
			0x83FF70, BodyColours.SKULKING, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	TC_WISPY("TCWispy", "umbrabilis", BeeGenomeManager.getTemplateTCWispy(), BeeClassification.THAUMIC,
			0x9cb8d5, BodyColours.SKULKING, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TC_BATTY("TCBatty", "chiroptera", BeeGenomeManager.getTemplateTCBatty(), BeeClassification.THAUMIC,
			0x27350d, BodyColours.SKULKING, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),

	TC_CHICKEN("TCChicken", "pullus", BeeGenomeManager.getTemplateTCChicken(), BeeClassification.FLESHY,
			0x7D431E, 0xE0905E, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	TC_BEEF("TCBeef", "bubulae", BeeGenomeManager.getTemplateTCBeef(), BeeClassification.FLESHY,
			0x40221A, 0xAC6753, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	TC_PORK("TCPork", "porcina", BeeGenomeManager.getTemplateTCPork(), BeeClassification.FLESHY,
			0x725D2F, 0xD2BF93, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),

	// --------- Equivalent Exchange Bees -----------------------------------------------------------------------------	
	EE_MINIUM("EEMinium", "mutabilis", BeeGenomeManager.getTemplateEEMinium(), BeeClassification.ALCHEMICAL,
			0xac0921, 0x3a030b, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),

	// --------- Ars Magica Bees --------------------------------------------------------------------------------------	
	AM_ESSENCE("AMEssence", "essentia", BeeGenomeManager.getTemplateAMEssence(), BeeClassification.ESSENTIAL,
			0x86BBC5, BodyColours.ARSMAGICA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	AM_QUINTESSENCE("AMQuintessence", "cor essentia", BeeGenomeManager.getTemplateAMQuintessence(), BeeClassification.ESSENTIAL,
			0xE3A45B, BodyColours.ARSMAGICA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, true),

	AM_EARTH("AMEarth", "magica terra", BeeGenomeManager.getTemplateAMEarth(), BeeClassification.ESSENTIAL,
			0xAA875E, BodyColours.ARSMAGICA, EnumTemperature.WARM, EnumHumidity.ARID, false, false),
	AM_AIR("AMAir", "magica aer", BeeGenomeManager.getTemplateAMAir(), BeeClassification.ESSENTIAL,
			0xD5EB9D, BodyColours.ARSMAGICA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	AM_FIRE("AMFire", "magica ignis", BeeGenomeManager.getTemplateAMFire(), BeeClassification.ESSENTIAL,
			0x93451E, BodyColours.ARSMAGICA, EnumTemperature.HOT, EnumHumidity.ARID, false, false),
	AM_WATER("AMWater", "magica aqua", BeeGenomeManager.getTemplateAMWater(), BeeClassification.ESSENTIAL,
			0x3B7D8C, BodyColours.ARSMAGICA, EnumTemperature.NORMAL, EnumHumidity.DAMP, false, false),
	AM_LIGHTNING("AMLightning", "magica fulgur", BeeGenomeManager.getTemplateAMLightning(), BeeClassification.ESSENTIAL,
			0xEBEFA1, BodyColours.ARSMAGICA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	AM_PLANT("AMPlant", "magica herba", BeeGenomeManager.getTemplateAMPlant(), BeeClassification.ESSENTIAL,
			0x49B549, BodyColours.ARSMAGICA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	AM_ICE("AMIce", "magica glacium", BeeGenomeManager.getTemplateAMIce(), BeeClassification.ESSENTIAL,
			0x86BAC6, BodyColours.ARSMAGICA, EnumTemperature.COLD, EnumHumidity.NORMAL, false, false),
	AM_ARCANE("AMArcane", "magica arcanum", BeeGenomeManager.getTemplateAMArcane(), BeeClassification.ESSENTIAL,
			0x76184D, BodyColours.ARSMAGICA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, true, false),

	AM_VORTEX("AMVortex", "gurges", BeeGenomeManager.getTemplateAMVortex(), BeeClassification.ESSENTIAL,
			0x71BBE2, 0x0B35A8, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	AM_WIGHT("AMWight", "vectem", BeeGenomeManager.getTemplateAMWight(), BeeClassification.ESSENTIAL,
			0xB50000, 0x4C4837, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),

	// ---------------------Thermal Expansion Bees---------------------------
	TE_BLIZZY("TEBlizzy", "blizzard", BeeGenomeManager.getTemplateTEBlizzy(), BeeClassification.ABOMINABLE,
			0x0073C4, EnumTemperature.COLD, EnumHumidity.NORMAL, false, false),
	TE_GELID("TEGelid", "cyro", BeeGenomeManager.getTemplateTEGelid(), BeeClassification.ABOMINABLE,
			0x4AAFF7, EnumTemperature.COLD, EnumHumidity.NORMAL, true, true),
	TE_DANTE("TEDante", "inferno", BeeGenomeManager.getTemplateTEDante(), BeeClassification.ABOMINABLE,
			0xF7AC4A, EnumTemperature.HELLISH, EnumHumidity.ARID, false, false),
	TE_PYRO("TEPyro", "pyromaniac", BeeGenomeManager .getTemplateTEPyro(), BeeClassification.ABOMINABLE,
			0xFA930C, EnumTemperature.HELLISH, EnumHumidity.ARID, true, true),

	TE_ELECTRUM("TEElectrum", "electrum", BeeGenomeManager.getTemplateTEElectrum(), BeeClassification.THERMAL,
			0xEAF79E, EnumTemperature.HOT,EnumHumidity.ARID, false, false),
	TE_PLATINUM("TEPlatinum", "platina", BeeGenomeManager.getTemplateTEPlatinum(), BeeClassification.THERMAL,
			0x9EE7F7, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TE_NICKEL("TENickel", "nickel", BeeGenomeManager.getTemplateTENickel(),BeeClassification.THERMAL,
			0xB4C989, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TE_INVAR("TEInvar", "invar", BeeGenomeManager.getTemplateTEInvar(), BeeClassification.THERMAL,
			0xCDE3A1, EnumTemperature.HOT, EnumHumidity.ARID, false, false),
	TE_BRONZE("TEBronze", "pyropus",BeeGenomeManager.getTemplateTEBronze(), BeeClassification.THERMAL,
			0xB56D07, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TE_COAL("TECoal", "carbonis", BeeGenomeManager.getTemplateTECoal(), BeeClassification.THERMAL,
			0x2E2D2D, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TE_DESTABILIZED("TEDestabilized", "electric", BeeGenomeManager.getTemplateTEDestabilized(), BeeClassification.THERMAL,
			0x5E0203, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TE_LUX("TELux", "lux", BeeGenomeManager.getTemplateTELux(), BeeClassification.THERMAL,
			0xF1FA89, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	TE_WINSOME("TEWinsome", "cuniculus", BeeGenomeManager.getTemplateTEWinsome(), BeeClassification.ADORABLE,
			0x096B67, EnumTemperature.COLD, EnumHumidity.NORMAL, false, false),
	TE_ENDEARING("TEEndearing", "cognito", BeeGenomeManager.getTemplateTEEndearing(), BeeClassification.ADORABLE,
			0x069E97, EnumTemperature.COLD, EnumHumidity.NORMAL, true, true),

	// --------------------Redstone Arsenal Bees-----------------------------
	RSA_FLUXED("RSAFluxed", "Thermametallic electroflux", BeeGenomeManager.getTemplateRSAFluxed(), BeeClassification.THERMAL,
			0x9E060D, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),

	// ----------------------Bloodmagic Bees---------------------------------
	BM_BLOODY("BMBloody", "sanguis", BeeGenomeManager.getTemplateBMBloody(), BeeClassification.BLOODY,
			0xb7102f, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),

	BM_BOUND("BMBound", "obligatus", BeeGenomeManager.getTemplateBMBound(), BeeClassification.BLOODY,
			0xb7102f, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),

	// ----------------------Botania Bees---------------------------------------
	BOT_ROOTED("BotRooted", "truncus", BeeGenomeManager.getTemplateBotRooted(), BeeClassification.BOTANICAL,
			0x00A800, BodyColours.BOTANIA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),

	BOT_BOTANIC("BotBotanic", "botanica", BeeGenomeManager.getTemplateBotBotanic(), BeeClassification.BOTANICAL,
			0x94C661, BodyColours.BOTANIA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	BOT_BLOSSOM("BotBlossom", "viridis", BeeGenomeManager.getTemplateBotBlossom(), BeeClassification.BOTANICAL,
			0xA4C193, BodyColours.BOTANIA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),
	BOT_FLORAL("BotFloral", "florens", BeeGenomeManager.getTemplateBotFloral(), BeeClassification.BOTANICAL,
			0x29D81A, BodyColours.BOTANIA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),

	BOT_VAZBEE("BotVazbee", "vazbii", BeeGenomeManager.getTemplateBotVazbee(), BeeClassification.BOTANICAL,
			0xff6b9c, BodyColours.BOTANIA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),

	BOT_SOMNOLENT("BotSomnolent", "soporatus", BeeGenomeManager.getTemplateBotSomnolent(), BeeClassification.BOTANICAL,
			0x2978C6, BodyColours.BOTANIA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),
	BOT_DREAMING("BotDreaming", "somnior", BeeGenomeManager.getTemplateBotDreaming(), BeeClassification.BOTANICAL,
			0x123456, BodyColours.BOTANIA, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, true),

	BOT_ALFHEIM("BotAlfheim", "alfheimis", BeeGenomeManager.getTemplateBotAelfheim(), BeeClassification.BOTANICAL,
			-1, -1, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false),

	;

	// For body colours used by more than one bee.
	private class BodyColours {
		static final int ARCANE = 0xFF9D60;
		static final int ABOMINABLE = 0x960F00;
		static final int EXTRINSIC = 0xF696FF;
		static final int SKULKING = 0xe15236;
		static final int THAUMCRAFT = 0x999999;
		static final int ARSMAGICA = 0xE3A55B;
		static final int BOTANIA = 0xFFB2BB;
	}

	public static void setupBeeSpecies() {
		System.out.println("# bees: " + BeeSpecies.values().length);
		// Species must be set inactive prior to registration.
		if (ThaumcraftHelper.isActive()) {
			SCHOLARLY.addSpecialty(Config.miscResources.getStackForType(ResourceType.LORE_FRAGMENT), 2);
			SAVANT.addSpecialty(Config.miscResources.getStackForType(ResourceType.LORE_FRAGMENT), 5);

			TC_AIR.addSpecialty(new ItemStack(Config.tcShard, 1, ThaumcraftHelper.ShardType.AIR.ordinal()), 5);
			TC_FIRE.addSpecialty(new ItemStack(Config.tcShard, 1, ThaumcraftHelper.ShardType.FIRE.ordinal()), 5);
			TC_WATER.addSpecialty(new ItemStack(Config.tcShard, 1, ThaumcraftHelper.ShardType.WATER.ordinal()), 5);
			TC_EARTH.addSpecialty(new ItemStack(Config.tcShard, 1, ThaumcraftHelper.ShardType.EARTH.ordinal()), 5);
			TC_ORDER.addSpecialty(new ItemStack(Config.tcShard, 1, ThaumcraftHelper.ShardType.ORDER.ordinal()), 5);
			TC_CHAOS.addSpecialty(new ItemStack(Config.tcShard, 1, ThaumcraftHelper.ShardType.CHAOS.ordinal()), 5);

			// TC_PURE.addSpecialty(new ItemStack(Config.));

			TC_BRAINY.addSpecialty(new ItemStack(Config.tcMiscResource, 1, ThaumcraftHelper.MiscResource.ZOMBIE_BRAIN.ordinal()), 2);
			TC_CHICKEN.addSpecialty(new ItemStack(Config.tcNuggetChicken, 1), 9);
			TC_BEEF.addSpecialty(new ItemStack(Config.tcNuggetBeef, 1), 9);
			TC_PORK.addSpecialty(new ItemStack(Config.tcNuggetPork, 1), 9);
		} else {
			TC_CHAOS.setInactive();
			TC_AIR.setInactive();
			TC_FIRE.setInactive();
			TC_WATER.setInactive();
			TC_EARTH.setInactive();
			TC_ORDER.setInactive();

			TC_VIS.setInactive();
			TC_PURE.setInactive();

			TC_BRAINY.setInactive();
			TC_BATTY.setInactive();
			TC_CHICKEN.setInactive();
			TC_BEEF.setInactive();
			TC_PORK.setInactive();
		}

		TC_WISPY.setInactive();
		TC_TAINT.setInactive();
		TC_ATTRACT.setInactive();
		TC_REJUVENATING.setInactive();

		if (EquivalentExchangeHelper.isActive()) {
			EE_MINIUM.addSpecialty(new ItemStack(Config.eeMinuimShard), 6);
		} else {
			EE_MINIUM.setInactive();
		}

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
		} else {
			AM_ESSENCE.setInactive();
			AM_QUINTESSENCE.setInactive();
			AM_EARTH.setInactive();
			AM_AIR.setInactive();
			AM_FIRE.setInactive();
			AM_WATER.setInactive();
			AM_LIGHTNING.setInactive();
			AM_PLANT.setInactive();
			AM_ICE.setInactive();
			AM_ARCANE.setInactive();
			AM_VORTEX.setInactive();
			AM_WIGHT.setInactive();
		}

		// Thermal Expansion Bees

		if (ThermalExpansionHelper.isActive()) {
			LogHelper.info("Thermal Expansion bees active");
			TE_BLIZZY.addSpecialty(Config.teDustBlizz, 9);
			TE_GELID.addSpecialty(Config.teDustCryotheum, 9);
			TE_DANTE.addSpecialty(Config.teDustSulfur, 9);
			TE_PYRO.addSpecialty(Config.teDustPyrotheum, 9);
			TE_WINSOME.addSpecialty(Config.teDustPlatinum, 9);
			TE_ENDEARING.addSpecialty(Config.teEnderiumNugget, 9);
		} else {
			TE_BLIZZY.setInactive();
			TE_GELID.setInactive();
			TE_DANTE.setInactive();
			TE_PYRO.setInactive();
			TE_DESTABILIZED.setInactive();
			TE_COAL.setInactive();
			TE_LUX.setInactive();
			TE_WINSOME.setInactive();
			TE_ENDEARING.setInactive();
		}

		// Redstone Arsenal Bees

		if (RedstoneArsenalHelper.isActive()) {
			RSA_FLUXED.addSpecialty(Config.rsaFluxNugget, 9);
		} else {
			RSA_FLUXED.setInactive();
		}

		// Bloodmagic Bees

		if (BloodMagicHelper.isActive()) {
		} else {
			BM_BLOODY.setInactive();
		}

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
		} else {
			BOT_BLOSSOM.setInactive();
			BOT_BOTANIC.setInactive();
			BOT_FLORAL.setInactive();
			BOT_VAZBEE.setInactive();
		}

		// Oredict bees
		if (OreDictionary.getOres("ingotCopper").size() <= 0) {
			COPPER.setInactive();
		}
		if (OreDictionary.getOres("ingotTin").size() <= 0) {
			TIN.setInactive();
		}
		if (OreDictionary.getOres("ingotSilver").size() <= 0) {
			SILVER.setInactive();
		}
		if (OreDictionary.getOres("ingotLead").size() <= 0) {
			LEAD.setInactive();
		}
		if (OreDictionary.getOres("ingotNaturalAluminum").size() <= 0 && OreDictionary.getOres("ingotAluminum").size() <= 0
				&& OreDictionary.getOres("ingotAluminium").size() <= 0) {
			LogHelper.info("Aluminum bees inactive!");
			ALUMINUM.setInactive();
		}
		if (OreDictionary.getOres("ingotArdite").size() <= 0) {
			ARDITE.setInactive();
		}
		if (OreDictionary.getOres("ingotCobalt").size() <= 0) {
			COBALT.setInactive();
		}
		if (OreDictionary.getOres("ingotManyullyn").size() <= 0) {
			MANYULLYN.setInactive();
		}

		MYSTICAL.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 15).register();
		SORCEROUS.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 15).register();
		UNUSUAL.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 15).register();
		ATTUNED.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 15).register();
		ELDRITCH.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 15).register();

		ESOTERIC.addProduct(Config.combs.getStackForType(CombType.OCCULT), 18).register();
		MYSTERIOUS.addProduct(Config.combs.getStackForType(CombType.OCCULT), 20).register();
		ARCANE.addProduct(Config.combs.getStackForType(CombType.OCCULT), 25).addSpecialty(Config.drops.getStackForType(DropType.ENCHANTED, 1), 9).register();

		CHARMED.addProduct(Config.combs.getStackForType(CombType.OTHERWORLDLY), 18).register();
		ENCHANTED.addProduct(Config.combs.getStackForType(CombType.OTHERWORLDLY), 20).register();
		SUPERNATURAL.addProduct(Config.combs.getStackForType(CombType.OTHERWORLDLY), 25).addSpecialty(Config.pollen.getStackForType(PollenType.UNUSUAL), 8)
				.register();

		ETHEREAL.addProduct(Config.combs.getStackForType(CombType.OCCULT), 10).addProduct(Config.combs.getStackForType(CombType.OTHERWORLDLY), 10).register();

		WINDY.addProduct(Config.combs.getStackForType(CombType.AIRY), 25).register();
		FIREY.addProduct(Config.combs.getStackForType(CombType.FIREY), 25).register();
		EARTHY.addProduct(Config.combs.getStackForType(CombType.EARTHY), 25).register();
		WATERY.addProduct(Config.combs.getStackForType(CombType.WATERY), 25).addSpecialty(new ItemStack(Blocks.ice), 2).register();

		PUPIL.addProduct(Config.combs.getStackForType(CombType.PAPERY), 20).register();
		SCHOLARLY.addProduct(Config.combs.getStackForType(CombType.PAPERY), 25).register();
		SAVANT.addProduct(Config.combs.getStackForType(CombType.PAPERY), 40).register();

		AWARE.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 18).register();
		SPIRIT.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 22).addSpecialty(Config.combs.getStackForType(CombType.SOUL), 16).register();
		SOUL.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 28).addSpecialty(Config.combs.getStackForType(CombType.SOUL), 20).register();

		SKULKING.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 10).register();
		GHASTLY.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 8).addSpecialty(new ItemStack(Items.ghast_tear), 2).register();
		SPIDERY.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 13).addProduct(new ItemStack(Items.string), 8)
				.addSpecialty(new ItemStack(Items.spider_eye), 8).register();
		SMOULDERING.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 10).addProduct(Config.combs.getStackForType(CombType.MOLTEN), 10)
				.addSpecialty(new ItemStack(Items.blaze_rod), 5).register();

		TIMELY.addProduct(Config.combs.getStackForType(CombType.TEMPORAL), 16).register();
		LORDLY.addProduct(Config.combs.getStackForType(CombType.TEMPORAL), 19).register();
		DOCTORAL.addProduct(Config.combs.getStackForType(CombType.TEMPORAL), 24).addSpecialty(new ItemStack(Config.jellyBaby), 7).register();

		INFERNAL.addProduct(Config.combs.getStackForType(CombType.MOLTEN), 12).register();
		HATEFUL.addProduct(Config.combs.getStackForType(CombType.MOLTEN), 18).register();
		SPITEFUL.addProduct(Config.combs.getStackForType(CombType.MOLTEN), 24).register();
		WITHERING.addSpecialty(Config.miscResources.getStackForType(ResourceType.SKULL_CHIP), 15).register();

		OBLIVION.addProduct(Config.combs.getStackForType(CombType.FORGOTTEN), 14).register();
		NAMELESS.addProduct(Config.combs.getStackForType(CombType.FORGOTTEN), 19).register();
		ABANDONED.addProduct(Config.combs.getStackForType(CombType.FORGOTTEN), 24).register();
		FORLORN.addProduct(Config.combs.getStackForType(CombType.FORGOTTEN), 30).register();
		DRACONIC.addSpecialty(Config.miscResources.getStackForType(ResourceType.DRAGON_DUST), 15).register();

		IRON.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10)
				.addSpecialty(Config.nuggets.getStackForType(NuggetType.IRON), 18).register();
		GOLD.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10).addSpecialty(new ItemStack(Items.gold_nugget, 1), 16)
				.register();
		COPPER.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10)
				.addSpecialty(Config.nuggets.getStackForType(NuggetType.COPPER), 20).register();
		TIN.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10)
				.addSpecialty(Config.nuggets.getStackForType(NuggetType.TIN), 20).register();
		SILVER.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10)
				.addSpecialty(Config.nuggets.getStackForType(NuggetType.SILVER), 16).register();
		LEAD.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10)
				.addSpecialty(Config.nuggets.getStackForType(NuggetType.LEAD), 17).register();

		ALUMINUM.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10);
		if (OreDictionary.getOres("nuggetAluminum").size() > 0) {
			LogHelper.info("Aluminum bees active!");
			ALUMINUM.addSpecialty(OreDictionary.getOres("nuggetAluminum").get(0), 20);
		} else {
			LogHelper.info("Aluminum bees inactive!");
			ALUMINUM.setInactive();
		}
		ALUMINUM.register();

		ARDITE.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10);
		if (OreDictionary.getOres("nuggetArdite").size() > 0) {
			ARDITE.addSpecialty(OreDictionary.getOres("nuggetArdite").get(0), 18);
		} else {
			ARDITE.setInactive();
		}
		ARDITE.register();

		COBALT.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10);
		if (OreDictionary.getOres("nuggetCobalt").size() > 0) {
			COBALT.addSpecialty(OreDictionary.getOres("nuggetCobalt").get(0), 18);
		} else {
			COBALT.setInactive();
		}
		COBALT.register();

		MANYULLYN.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10);
		if (OreDictionary.getOres("nuggetManyullyn").size() > 0) {
			MANYULLYN.addSpecialty(OreDictionary.getOres("nuggetManyullyn").get(0), 16);
		} else {
			MANYULLYN.setInactive();
		}
		MANYULLYN.register();

		DIAMOND.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10)
				.addSpecialty(Config.nuggets.getStackForType(NuggetType.DIAMOND), 6).register();
		EMERALD.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10)
				.addSpecialty(Config.nuggets.getStackForType(NuggetType.EMERALD), 4).register();
		APATITE.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10)
				.addSpecialty(Config.nuggets.getStackForType(NuggetType.APATITE), 10).register();

		MUTABLE.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.PARCHED.ordinal()), 30)
				.addProduct(Config.combs.getStackForType(CombType.TRANSMUTED), 10).register();
		TRANSMUTING.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.PARCHED.ordinal()), 10)
				.addProduct(Config.combs.getStackForType(CombType.TRANSMUTED), 30)
				.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.SILKY.ordinal()), 5)
				.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.SIMMERING.ordinal()), 5).register();
		CRUMBLING.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.PARCHED.ordinal()), 10)
				.addProduct(Config.combs.getStackForType(CombType.TRANSMUTED), 30)
				.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.POWDERY.ordinal()), 10)
				.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.COCOA.ordinal()), 15).register();

		INVISIBLE.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 35).register();

		TC_AIR.addProduct(Config.combs.getStackForType(CombType.AIRY), 20).register();
		TC_FIRE.addProduct(Config.combs.getStackForType(CombType.FIREY), 20).register();
		TC_WATER.addProduct(Config.combs.getStackForType(CombType.WATERY), 20).register();
		TC_EARTH.addProduct(Config.combs.getStackForType(CombType.EARTHY), 20).register();
		TC_ORDER.addProduct(Config.combs.getStackForType(CombType.TC_ORDER), 20).register();
		TC_CHAOS.addProduct(Config.combs.getStackForType(CombType.TC_CHAOS), 20).register();

		TC_VIS.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 10).register();
		TC_TAINT.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 18).register();
		TC_ATTRACT.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 14).register();
		TC_PURE.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 16).register();
		TC_REJUVENATING.addProduct(Config.combs.getStackForType(CombType.INTELLECT), 18).register();

		TC_BRAINY.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 10).addProduct(new ItemStack(Items.rotten_flesh), 6).register();
		TC_BATTY.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 10).addSpecialty(new ItemStack(Items.gunpowder), 4).register();
		TC_WISPY.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.SILKY.ordinal()), 22)
				.addSpecialty(new ItemStack(Config.fCraftingResource, 1, ForestryHelper.CraftingMaterial.SILK_WISP.ordinal()), 4).register();
		TC_CHICKEN.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10).register();
		TC_BEEF.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10).register();
		TC_PORK.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10).register();

		EE_MINIUM.addProduct(Config.combs.getStackForType(CombType.OCCULT), 16).register();

		AM_ESSENCE.addProduct(Config.combs.getStackForType(CombType.AM_ESSENCE), 12).register();
		AM_QUINTESSENCE.addProduct(Config.combs.getStackForType(CombType.AM_ESSENCE), 23).register();
		AM_EARTH.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 12).register();
		AM_AIR.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 12).register();
		AM_FIRE.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 12).register();
		AM_WATER.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 12).register();
		AM_LIGHTNING.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 12).register();
		AM_PLANT.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 12).register();
		AM_ICE.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 12).register();
		AM_ARCANE.addProduct(Config.combs.getStackForType(CombType.AM_POTENT), 19).register();
		AM_VORTEX.addProduct(Config.combs.getStackForType(CombType.AM_ESSENCE), 10).register();
		AM_WIGHT.addProduct(Config.combs.getStackForType(CombType.SOUL), 30).addProduct(Config.combs.getStackForType(CombType.FURTIVE), 10).register();

		if (OreDictionary.getOres("ingotBronze").size() <= 0) {
			TE_BRONZE.setInactive();
		}
		if (OreDictionary.getOres("ingotElectrum").size() <= 0) {
			TE_ELECTRUM.setInactive();
		}
		if (OreDictionary.getOres("ingotPlatinum").size() <= 0) {
			TE_PLATINUM.setInactive();
		}
		if (OreDictionary.getOres("ingotNickel").size() <= 0) {
			TE_NICKEL.setInactive();
		}
		if (OreDictionary.getOres("ingotInvar").size() <= 0) {
			TE_INVAR.setInactive();
		}

		TE_ELECTRUM.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10);
		if (OreDictionary.getOres("nuggetElectrum").size() > 0) {
			TE_ELECTRUM.addSpecialty(OreDictionary.getOres("nuggetElectrum").get(0), 18);
		} else {
			TE_ELECTRUM.setInactive();
		}
		TE_ELECTRUM.register();

		TE_PLATINUM.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10);
		if (OreDictionary.getOres("nuggetPlatinum").size() > 0) {
			TE_PLATINUM.addSpecialty(OreDictionary.getOres("nuggetPlatinum").get(0), 18);
		} else {
			TE_PLATINUM.setInactive();
		}
		TE_PLATINUM.register();

		TE_NICKEL.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10);
		if (OreDictionary.getOres("nuggetNickel").size() > 0) {
			TE_NICKEL.addSpecialty(OreDictionary.getOres("nuggetNickel").get(0), 18);
		} else {
			TE_NICKEL.setInactive();
		}
		TE_NICKEL.register();

		TE_INVAR.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10);
		if (OreDictionary.getOres("nuggetInvar").size() > 0) {
			TE_INVAR.addSpecialty(OreDictionary.getOres("nuggetInvar").get(0), 18);
		} else {
			TE_INVAR.setInactive();
		}
		TE_INVAR.register();

		TE_BRONZE.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10);
		if (OreDictionary.getOres("nuggetBronze").size() > 0) {
			TE_BRONZE.addSpecialty(OreDictionary.getOres("nuggetBronze").get(0), 18);
		} else {
			TE_BRONZE.setInactive();
		}
		TE_BRONZE.register();

		RSA_FLUXED.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10);
		if (OreDictionary.getOres("nuggetElectrumFlux").size() > 0) {
			RSA_FLUXED.addSpecialty(OreDictionary.getOres("nuggetElectrumFlux").get(0), 18);
		} else {
			RSA_FLUXED.setInactive();
		}
		RSA_FLUXED.register();

		TE_DANTE.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 10).addProduct(Config.combs.getStackForType(CombType.MOLTEN), 10)
				.addSpecialty(new ItemStack(Items.blaze_powder), 5).register();

		TE_PYRO.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 10).addProduct(Config.combs.getStackForType(CombType.MOLTEN), 10)
				.addSpecialty(new ItemStack(Items.blaze_powder), 5).register();

		TE_DESTABILIZED.addProduct(Config.combs.getStackForType(CombType.TE_DESTABILIZED), 10).addProduct(Config.combs.getStackForType(CombType.OCCULT), 10)
				.addSpecialty(new ItemStack(Items.redstone), 5).register();

		TE_LUX.addProduct(Config.combs.getStackForType(CombType.TE_LUX), 10).addProduct(Config.combs.getStackForType(CombType.OCCULT), 10)
				.addSpecialty(new ItemStack(Items.glowstone_dust), 5).register();

		TE_WINSOME.addProduct(Config.combs.getStackForType(CombType.FURTIVE), 10).addProduct(Config.combs.getStackForType(CombType.TE_ENDEARING), 5)
				.addSpecialty(new ItemStack(Items.ender_pearl), 5).register();

		TE_ENDEARING.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.MYSTERIOUS.ordinal()), 10)
				.addProduct(Config.combs.getStackForType(CombType.TE_ENDEARING), 5).register();

		TE_COAL.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10)
				.addProduct(Config.combs.getStackForType(CombType.TE_CARBON), 5).addSpecialty(new ItemStack(Items.coal), 5).register();

		TE_BLIZZY.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.FROZEN.ordinal()), 10).register();

		TE_GELID.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.FROZEN.ordinal()), 10).register();

		BM_BLOODY.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10).register();

		BM_BOUND.addProduct(new ItemStack(Config.fBeeComb, 1, ForestryHelper.Comb.HONEY.ordinal()), 10).register();

		BOT_ROOTED.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 10).register();

		BOT_BOTANIC.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 10).addProduct(Config.combs.getStackForType(CombType.TRANSMUTED), 5).register();

		BOT_BLOSSOM.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 20).addProduct(Config.combs.getStackForType(CombType.TRANSMUTED), 5).register();

		BOT_FLORAL.addProduct(Config.combs.getStackForType(CombType.MUNDANE), 25).addProduct(Config.combs.getStackForType(CombType.TRANSMUTED), 5).register();

		BOT_VAZBEE.addProduct(Config.combs.getStackForType(CombType.SOUL), 5).addProduct(new ItemStack(Items.dye, 1, 9), 20)
				.addProduct(new ItemStack(Blocks.wool, 1, 9), 2).addProduct(new ItemStack(Blocks.red_flower, 1, 7), 6)
				.addProduct(Config.combs.getStackForType(CombType.TRANSMUTED), 15).register();

		BOT_SOMNOLENT.addProduct(Config.combs.getStackForType(CombType.WATERY), 8).addProduct(Config.combs.getStackForType(CombType.SOUL), 15).register();

		BOT_DREAMING.addProduct(Config.combs.getStackForType(CombType.WATERY), 16).addProduct(Config.combs.getStackForType(CombType.SOUL), 33).register();

		BOT_ALFHEIM.addProduct(Config.combs.getStackForType(CombType.OTHERWORLDLY), 28).register();

	}

	private String binomial;
	private String authority;
	private int primaryColour;
	private int secondaryColour;
	private EnumTemperature temperature;
	private EnumHumidity humidity;
	private boolean hasEffect;
	private boolean isSecret;
	private boolean isCounted;
	private boolean isActive;
	private boolean isNocturnal;
	private IClassification branch;
	private HashMap<ItemStack, Integer> products;
	private HashMap<ItemStack, Integer> specialties;
	private IAllele genomeTemplate[];
	private String uid;
	private boolean dominant;

	@SideOnly(Side.CLIENT)
	private IIcon[][] icons;

	private final static int defaultBodyColour = 0xFF7C26;

	private BeeSpecies(String speciesName, String genusName, IAllele[] genome, IClassification classification, int firstColour, EnumTemperature preferredTemp,
			EnumHumidity preferredHumidity, boolean hasGlowEffect, boolean isSpeciesDominant) {
		this(speciesName, genusName, genome, classification, firstColour, defaultBodyColour, preferredTemp, preferredHumidity, hasGlowEffect, true, true,
				isSpeciesDominant);
	}

	private BeeSpecies(String speciesName, String genusName, IAllele[] genome, IClassification classification, int firstColour, int secondColour,
			EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean hasGlowEffect, boolean isSpeciesDominant) {
		this(speciesName, genusName, genome, classification, firstColour, secondColour, preferredTemp, preferredHumidity, hasGlowEffect, true, true,
				isSpeciesDominant);
	}

	private BeeSpecies(String speciesName, String genusName, IAllele[] genome, IClassification classification, int firstColour, int secondColour,
			EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean isSecret, boolean hasGlowEffect, boolean isSpeciesDominant) {
		this(speciesName, genusName, genome, classification, firstColour, secondColour, preferredTemp, preferredHumidity, hasGlowEffect, isSecret, true,
				isSpeciesDominant);
	}

	private BeeSpecies(String speciesName, String genusName, IAllele[] genome, IClassification classification, int firstColour, EnumTemperature preferredTemp,
			EnumHumidity preferredHumidity, boolean isSecret, boolean hasGlowEffect, boolean isSpeciesDominant) {
		this(speciesName, genusName, genome, classification, firstColour, defaultBodyColour, preferredTemp, preferredHumidity, hasGlowEffect, isSecret, true,
				isSpeciesDominant);
	}

	private BeeSpecies(String speciesName, String genusName, IAllele[] genome, IClassification classification, int firstColour, int secondColour,
			EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean hasGlowEffect, boolean isSpeciesSecret, boolean isSpeciesCounted,
			boolean isSpeciesDominant) {
		this.uid = "magicbees.species" + speciesName;
		this.dominant = isSpeciesDominant;
		genomeTemplate = genome;
		AlleleManager.alleleRegistry.registerAllele(this);
		binomial = genusName;
		authority = "MysteriousAges";
		primaryColour = firstColour;
		secondaryColour = secondColour;
		temperature = preferredTemp;
		humidity = preferredHumidity;
		hasEffect = hasGlowEffect;
		isSecret = isSpeciesSecret;
		isCounted = isSpeciesCounted;
		products = new HashMap<ItemStack, Integer>();
		specialties = new HashMap<ItemStack, Integer>();
		this.branch = classification;
		this.branch.addMemberSpecies(this);
		this.isActive = true;
		this.isNocturnal = false;
	}

	public IAllele[] getGenome() {
		return genomeTemplate;
	}

	public BeeSpecies addProduct(ItemStack produce, int percentChance) {
		products.put(produce, percentChance);
		return this;
	}

	public BeeSpecies addSpecialty(ItemStack produce, int percentChance) {
		specialties.put(produce, Integer.valueOf(percentChance));
		return this;
	}

	public ItemStack getBeeItem(EnumBeeType beeType) {
		return BeeManager.beeRoot.getMemberStack(BeeManager.beeRoot.getBee(null, BeeManager.beeRoot.templateAsGenome(genomeTemplate)), beeType.ordinal());
	}

	@Override
	public String getName() {
		return LocalizationManager.getLocalizedString(getUID());
	}

	@Override
	public String getDescription() {
		return LocalizationManager.getLocalizedString(getUID() + ".description");
	}

	@Override
	public String getUnlocalizedName() {
		return getUID();
	}

	@Override
	public EnumTemperature getTemperature() {
		return temperature;
	}

	@Override
	public EnumHumidity getHumidity() {
		return humidity;
	}

	@Override
	public boolean hasEffect() {
		return hasEffect;
	}

	public BeeSpecies setInactive() {
		this.isActive = false;
		return this;
	}

	public boolean isActive() {
		return this.isActive;
	}

	@Override
	public boolean isSecret() {
		return isSecret;
	}

	@Override
	public boolean isCounted() {
		return isCounted;
	}

	@Override
	public String getBinomial() {
		return binomial;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	@Override
	public IClassification getBranch() {
		return this.branch;
	}

	@Override
	public HashMap<ItemStack, Integer> getProducts() {
		return products;
	}

	@Override
	public HashMap<ItemStack, Integer> getSpecialty() {
		return specialties;
	}

	@Override
	public String getUID() {
		return this.uid;
	}

	@Override
	public boolean isDominant() {
		return this.dominant;
	}

	@Override
	public IBeeRoot getRoot() {
		return BeeManager.beeRoot;
	}

	@Override
	public boolean isNocturnal() {
		return this.isNocturnal;
	}

	@Override
	public boolean isJubilant(IBeeGenome genome, IBeeHousing housing) {
		return true;
	}

	private BeeSpecies register() {
		BeeManager.beeRoot.registerTemplate(this.getGenome());
		if (!this.isActive) {
			AlleleManager.alleleRegistry.blacklistAllele(this.getUID());
		}
		return this;
	}

	@Override
	public int getIconColour(int renderPass) {
		int value = 0xffffff;
		if (renderPass == 0) {
			if (this.primaryColour == -1) {
				int hue = (int) (System.currentTimeMillis() >> 2) % 360;
				value = Color.getHSBColor(hue / 360f, 0.75f, 0.80f).getRGB();
			} else {
				value = this.primaryColour;
			}
		} else if (renderPass == 1) {
			if (this.secondaryColour == -1) {
				int hue = (int) (System.currentTimeMillis() >> 3) % 360;
				hue += 60;
				hue = hue % 360;
				value = Color.getHSBColor(hue / 360f, 0.5f, 0.6f).getRGB();
			} else {
				value = this.secondaryColour;
			}
		}
		return value;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIconProvider getIconProvider() {
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(EnumBeeType type, int renderPass) {
		return icons[type.ordinal()][Math.min(renderPass, 2)];
	}

	@Override
	public int getComplexity() {
		return 1 + getMutationPathLength(this, new ArrayList<IAllele>());
	}

	private int getMutationPathLength(IAllele species, ArrayList<IAllele> excludeSpecies) {
		int own = 1;
		int highest = 0;
		excludeSpecies.add(species);

		for (IMutation mutation : getRoot().getPaths(species, EnumBeeChromosome.SPECIES)) {
			if (!excludeSpecies.contains(mutation.getAllele0())) {
				int otherAdvance = getMutationPathLength(mutation.getAllele0(), excludeSpecies);
				if (otherAdvance > highest)
					highest = otherAdvance;
			}
			if (!excludeSpecies.contains(mutation.getAllele1())) {
				int otherAdvance = getMutationPathLength(mutation.getAllele1(), excludeSpecies);
				if (otherAdvance > highest)
					highest = otherAdvance;
			}
		}

		return own + (highest > 0 ? highest : 0);
	}

	@Override
	public float getResearchSuitability(ItemStack itemStack) {
		if (itemStack == null) {
			return 0f;
		}

		for (ItemStack product : this.products.keySet()) {
			if (itemStack.isItemEqual(product)) {
				return 1f;
			}
		}

		for (ItemStack specialty : this.specialties.keySet()) {
			if (specialty.isItemEqual(itemStack)) {
				return 1f;
			}
		}

		if (itemStack.getItem() == Config.fHoneyDrop) {
			return 0.5f;
		} else if (itemStack.getItem() == Config.fHoneydew) {
			return 0.7f;
		} else if (itemStack.getItem() == Config.fBeeComb || itemStack.getItem() == Config.combs) {
			return 0.4f;
		} else if (getRoot().isMember(itemStack)) {
			return 1.0f;
		} else {
			for (Map.Entry<ItemStack, Float> catalyst : BeeManager.beeRoot.getResearchCatalysts().entrySet()) {
				if (OreDictionary.itemMatches(itemStack, catalyst.getKey(), false)) {
					return catalyst.getValue();
				}
			}
		}

		return 0f;
	}

	@Override
	public ItemStack[] getResearchBounty(World world, GameProfile researcher, IIndividual individual, int bountyLevel) {
		System.out.println("Bounty level: " + bountyLevel);
		ArrayList<ItemStack> bounty = new ArrayList<ItemStack>();

		if (world.rand.nextFloat() < ((10f / bountyLevel))) {
			Collection<? extends IMutation> resultantMutations = getRoot().getCombinations(this);
			if (resultantMutations.size() > 0) {
				IMutation[] candidates = resultantMutations.toArray(new IMutation[resultantMutations.size()]);
				bounty.add(AlleleManager.alleleRegistry.getMutationNoteStack(researcher, candidates[world.rand.nextInt(candidates.length)]));
			}
		}

		for (ItemStack product : this.products.keySet()) {
			ItemStack copy = product.copy();
			copy.stackSize = 1 + world.rand.nextInt(bountyLevel / 2);
			bounty.add(copy);
		}

		for (ItemStack specialty : this.specialties.keySet()) {
			ItemStack copy = specialty.copy();
			copy.stackSize = world.rand.nextInt(bountyLevel / 3);
			if (copy.stackSize > 0) {
				bounty.add(copy);
			}
		}

		return bounty.toArray(new ItemStack[bounty.size()]);
	}

	@Override
	public String getEntityTexture() {
		return "/gfx/forestry/entities/bees/honeyBee.png";
	}

	@Override
	public void registerIcons(IIconRegister itemMap) {
		this.icons = new IIcon[EnumBeeType.values().length][3];

		String root = this.getIconPath();

		IIcon body1 = itemMap.registerIcon(root + "body1");

		for (int i = 0; i < EnumBeeType.values().length; i++) {
			if (EnumBeeType.values()[i] == EnumBeeType.NONE)
				continue;

			icons[i][0] = itemMap.registerIcon(root + EnumBeeType.values()[i].toString().toLowerCase(Locale.ENGLISH) + ".outline");
			icons[i][1] = (EnumBeeType.values()[i] != EnumBeeType.LARVAE) ? body1 : itemMap.registerIcon(root
					+ EnumBeeType.values()[i].toString().toLowerCase(Locale.ENGLISH) + ".body");
			icons[i][2] = itemMap.registerIcon(root + EnumBeeType.values()[i].toString().toLowerCase(Locale.ENGLISH) + ".body2");
		}
	}

	private String getIconPath() {
		String value;

		switch (this) {
		case SKULKING:
		case GHASTLY:
		case SPIDERY:
		case SMOULDERING:
		case TC_BRAINY:
		case TC_WISPY:
		case TC_BATTY:
		case AM_VORTEX:
		case AM_WIGHT:
		case TE_BLIZZY:
		case TE_GELID:
		case TE_DANTE:
		case TE_PYRO:
			value = CommonProxy.DOMAIN + ":bees/skulking/";
			break;

		default:
			value = ForestryHelper.Name.toLowerCase() + ":bees/default/";
			break;
		}

		return value;
	}

	public boolean canWorkInTemperature(EnumTemperature temp) {
		IAlleleTolerance tolerance = (IAlleleTolerance) genomeTemplate[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()];
		return AlleleManager.climateHelper
				.isWithinLimits(temp, EnumHumidity.NORMAL, temperature, tolerance.getValue(), EnumHumidity.NORMAL, EnumTolerance.NONE);
	}

	public boolean canWorkInHumidity(EnumHumidity humid) {
		IAlleleTolerance tolerance = (IAlleleTolerance) genomeTemplate[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()];
		return AlleleManager.climateHelper.isWithinLimits(EnumTemperature.NORMAL, humid, EnumTemperature.NORMAL, EnumTolerance.NONE, humidity,
				tolerance.getValue());
	}

	// / --------- Unused Functions
	// ---------------------------------------------

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(short texUID) {
		return icons[0][0];
	}
}
