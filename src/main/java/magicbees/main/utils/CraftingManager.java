package magicbees.main.utils;

import java.util.Hashtable;
import java.util.Map;

import magicbees.item.ItemCapsule;
import magicbees.item.types.CombType;
import magicbees.item.types.DropType;
import magicbees.item.types.FluidType;
import magicbees.item.types.NuggetType;
import magicbees.item.types.PollenType;
import magicbees.item.types.PropolisType;
import magicbees.item.types.ResourceType;
import magicbees.item.types.WaxType;
import magicbees.main.Config;
import magicbees.main.utils.compat.ArsMagicaHelper;
import magicbees.main.utils.compat.ForestryHelper;
import magicbees.main.utils.compat.ThaumcraftHelper;
import magicbees.main.utils.compat.ThermalModsHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import forestry.api.recipes.RecipeManagers;

public class CraftingManager {
	
	public static void registerLiquidContainers() {
		registerLiquidContainer(Config.magicCapsule);
		registerLiquidContainer(Config.voidCapsule);
	}

	public static void setupCrafting() {
		// Broken up into seperate sections to make things a bit easier to find.
		setupVanillaCrafting();
		setupCentrifugeRecipes();
		setupCarpenterRecipes();
	}

	private static void setupVanillaCrafting() {
		ItemStack input;
		ItemStack output;

		// Magic capsules
		output = new ItemStack(Config.magicCapsule);
		output.stackSize = 4;
		GameRegistry.addRecipe(new ShapedOreRecipe(output,
				"WWW",
				'W', "waxMagical"
		));

		// Concentrated Fertilizer -> Forestry fertilizer
		input = Config.miscResources.getStackForType(ResourceType.EXTENDED_FERTILIZER);
		output = ItemInterface.getItemStack("Forestry", "fertilizerCompound", 6);
		GameRegistry.addRecipe(output,
				" S ", " F ", " S ",
				'F', input,
				'S', Blocks.SAND
		);
		GameRegistry.addRecipe(output,
				"   ", "SFS", "   ",
				'F', input,
				'S', Blocks.SAND
		);

		output = output.copy();
		output.stackSize = 12;
		GameRegistry.addRecipe(output,
				"aaa", "aFa", "aaa",
				'F', input,
				'a', ItemInterface.getItemStack("ash")
		);

		// "bottling" Intellect drops
		GameRegistry.addRecipe(new ItemStack(Items.EXPERIENCE_BOTTLE),
				"DDD", "DBD", "DDD",
				'D', Config.drops.getStackForType(DropType.INTELLECT),
				'B', Items.GLASS_BOTTLE
		);

		GameRegistry.addRecipe(new ItemStack(Blocks.SOUL_SAND, 4),
				"SwS", "wDw", "SwS",
				'S', Blocks.SAND,
				'D', Blocks.DIRT,
				'w', Config.wax.getStackForType(WaxType.SOUL)
		);
		GameRegistry.addRecipe(new ItemStack(Blocks.SOUL_SAND, 4),
				"wSw", "SDS", "wSw",
				'S', Blocks.SAND,
				'D', Blocks.DIRT,
				'w', Config.wax.getStackForType(WaxType.SOUL)
		);

		output = new ItemStack(Config.hiveFrameMagic);
		input = ItemInterface.getItemStack("frameUntreated");
		GameRegistry.addRecipe(output,
				"www", "wfw", "www",
				'w', Config.wax.getStackForType(WaxType.MAGIC),
				'f', input
		);

		GameRegistry.addRecipe(new ItemStack(Config.hiveFrameTemporal),
				"sPs", "PfP", "sPs",
				's', Blocks.SAND,
				'P', Config.pollen.getStackForType(PollenType.PHASED),
				'f', Config.hiveFrameMagic
		);

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Config.effectJar),
				"GSG", "QPQ", "GGG",
				'G', Blocks.GLASS,
				'S', "slabWood",
				'P', Config.pollen.getStackForType(PollenType.UNUSUAL),
				'Q', Items.QUARTZ
		));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Config.moonDial),
				"DqD", "qrq", "DqD",
				'r', Items.REDSTONE,
				'q', Items.QUARTZ,
				'D', "dyeGreen"
		));

		GameRegistry.addRecipe(Config.miscResources.getStackForType(ResourceType.SKULL_FRAGMENT),
				"xxx", "xxx", "xxx",
				'x', Config.miscResources.getStackForType(ResourceType.SKULL_CHIP)
		);

		GameRegistry.addRecipe(new ItemStack(Items.SKULL, 1, 1),
				"xxx", "xxx",
				'x', Config.miscResources.getStackForType(ResourceType.SKULL_FRAGMENT)
		);

		GameRegistry.addRecipe(Config.miscResources.getStackForType(ResourceType.DRAGON_CHUNK),
				"xxx", "xxx",
				'x', Config.miscResources.getStackForType(ResourceType.DRAGON_DUST)
		);

		GameRegistry.addRecipe(new ItemStack(Blocks.DRAGON_EGG, 1),
				"ccc", "cec", "ccc",
				'c', Config.miscResources.getStackForType(ResourceType.DRAGON_CHUNK),
				'e', Config.miscResources.getStackForType(ResourceType.ESSENCE_FALSE_LIFE)
		);

		GameRegistry.addRecipe(new ShapedOreRecipe(Config.miscResources.getStackForType(ResourceType.ESSENCE_EVERLASTING_DURABILITY),
				"gwg", "wiw", "gwg",
				'g', Blocks.GLASS,
				'w', "waxMagical",
				'i', Blocks.IRON_BLOCK
		));
		GameRegistry.addRecipe(new ShapedOreRecipe(Config.miscResources.getStackForType(ResourceType.ESSENCE_EVERLASTING_DURABILITY),
				"wgw", "gig", "wgw",
				'g', Blocks.GLASS,
				'w', "waxMagical",
				'i', Blocks.IRON_BLOCK
		));

		GameRegistry.addRecipe(Config.miscResources.getStackForType(ResourceType.ESSENCE_FALSE_LIFE),
				"gwg", "wfw", "gwg",
				'g', Blocks.GLASS,
				'w', Config.wax.getStackForType(WaxType.SOUL),
				'f', Blocks.RED_FLOWER
		);
		GameRegistry.addRecipe(Config.miscResources.getStackForType(ResourceType.ESSENCE_FALSE_LIFE),
				"wgw", "gfg", "wgw",
				'g', Blocks.GLASS,
				'w', Config.wax.getStackForType(WaxType.SOUL),
				'f', Blocks.RED_FLOWER
		);

		GameRegistry.addRecipe(Config.miscResources.getStackForType(ResourceType.ESSENCE_SHALLOW_GRAVE),
				"gwg", "wfw", "gwg",
				'g', Blocks.GLASS,
				'w', Config.wax.getStackForType(WaxType.SOUL),
				'f', Items.ROTTEN_FLESH
		);
		GameRegistry.addRecipe(Config.miscResources.getStackForType(ResourceType.ESSENCE_SHALLOW_GRAVE),
				"wgw", "gfg", "wgw",
				'g', Blocks.GLASS,
				'w', Config.wax.getStackForType(WaxType.SOUL),
				'f', Items.ROTTEN_FLESH
		);

		GameRegistry.addRecipe(Config.miscResources.getStackForType(ResourceType.ESSENCE_LOST_TIME),
				"wgw", "gcg", "wgw",
				'g', Blocks.GLASS,
				'w', Config.wax.getStackForType(WaxType.SOUL),
				'c', Items.CLOCK
		);
		GameRegistry.addRecipe(Config.miscResources.getStackForType(ResourceType.ESSENCE_LOST_TIME),
				"gwg", "wcw", "gwg",
				'g', Blocks.GLASS,
				'w', Config.wax.getStackForType(WaxType.SOUL),
				'c', Items.CLOCK
		);

		GameRegistry.addRecipe(Config.miscResources.getStackForType(ResourceType.ESSENCE_FICKLE_PERMANENCE),
				"wew", "gcg", "wew",
				'w', Config.wax.getStackForType(WaxType.SOUL),
				'c', Items.MAGMA_CREAM,
				'e', Items.EGG,
				'g', Blocks.GLOWSTONE
		);
		GameRegistry.addRecipe(Config.miscResources.getStackForType(ResourceType.ESSENCE_FICKLE_PERMANENCE),
				"wgw", "ece", "wgw",
				'w', Config.wax.getStackForType(WaxType.SOUL),
				'c', Items.MAGMA_CREAM,
				'e', Items.EGG,
				'g', Blocks.GLOWSTONE
		);

		GameRegistry.addRecipe(Config.miscResources.getStackForType(ResourceType.ESSENCE_SCORNFUL_OBLIVION),
				"gst", "sEs", "tsg",
				'g', Config.miscResources.getStackForType(ResourceType.ESSENCE_SHALLOW_GRAVE),
				't', Config.miscResources.getStackForType(ResourceType.ESSENCE_LOST_TIME),
				's', new ItemStack(Items.SKULL, 1, 1),
				'E', Blocks.DRAGON_EGG
		);

		// IF YOU UPDATE THESE, CHANGE THE RECIPES IN THAUMCRAFT HELPER, YOU IDIOT.
		input = new ItemStack(Config.hiveFrameMagic);
		GameRegistry.addShapelessRecipe(new ItemStack(Config.hiveFrameResilient),
				Config.miscResources.getStackForType(ResourceType.ESSENCE_EVERLASTING_DURABILITY),
				input
		);

		GameRegistry.addShapelessRecipe(new ItemStack(Config.hiveFrameGentle),
				Config.miscResources.getStackForType(ResourceType.ESSENCE_FALSE_LIFE),
				input
		);

		GameRegistry.addShapelessRecipe(new ItemStack(Config.hiveFrameNecrotic),
				Config.miscResources.getStackForType(ResourceType.ESSENCE_SHALLOW_GRAVE),
				input
		);

		GameRegistry.addShapelessRecipe(new ItemStack(Config.hiveFrameMetabolic),
				Config.miscResources.getStackForType(ResourceType.ESSENCE_FICKLE_PERMANENCE),
				input
		);

		GameRegistry.addShapelessRecipe(new ItemStack(Config.hiveFrameTemporal),
				Config.miscResources.getStackForType(ResourceType.ESSENCE_LOST_TIME),
				input
		);

		GameRegistry.addShapelessRecipe(new ItemStack(Config.hiveFrameOblivion),
				Config.miscResources.getStackForType(ResourceType.ESSENCE_SCORNFUL_OBLIVION),
				ItemInterface.getItemStack("frameProven")
		);
		
		output = new ItemStack(Config.magicApiary);
		GameRegistry.addShapelessRecipe(output,
				Config.pollen.getStackForType(PollenType.UNUSUAL, 2),
				Config.drops.getStackForType(DropType.ENCHANTED, 2),
				new ItemStack(ForestryHelper.apicultureBlock, 1, ForestryHelper.ApicultureBlock.APIARY.ordinal())
		);

		GameRegistry.addRecipe(new ItemStack(Config.enchantedEarth),
				"d d", " e ", "d d",
				'd', new ItemStack(Blocks.DIRT, 1, OreDictionary.WILDCARD_VALUE),
				'e', Config.miscResources.getStackForType(ResourceType.ESSENCE_FALSE_LIFE)
		);
		GameRegistry.addRecipe(new ItemStack(Config.enchantedEarth),
				" d ", "ded", " d ",
				'd', new ItemStack(Blocks.DIRT, 1, OreDictionary.WILDCARD_VALUE),
				'e', Config.miscResources.getStackForType(ResourceType.ESSENCE_FALSE_LIFE)
		);

		if (OreDictionary.getOres("ingotCopper").size() <= 0) {
			NuggetType.COPPER.setInactive();
		}
		else {
			GameRegistry.addRecipe(new ShapedOreRecipe(OreDictionary.getOres("ingotCopper").get(0),
					"xxx", "xxx", "xxx",
					'x', "nuggetCopper"
			));
		}
		
		if (OreDictionary.getOres("ingotTin").size() <= 0) {
			NuggetType.TIN.setInactive();
		}
		else {
			GameRegistry.addRecipe(new ShapedOreRecipe(OreDictionary.getOres("ingotTin").get(0),
					"xxx", "xxx", "xxx",
					'x', "nuggetTin"
			));
		}
		
		if (OreDictionary.getOres("ingotSilver").size() <= 0) {
			NuggetType.SILVER.setInactive();
		}
		else {
			GameRegistry.addRecipe(new ShapedOreRecipe(OreDictionary.getOres("ingotSilver").get(0),
					"xxx", "xxx", "xxx",
					'x', "nuggetSilver"
			));
		}
		
		if (OreDictionary.getOres("ingotLead").size() <= 0) {
			NuggetType.LEAD.setInactive();
		}
		else {
			GameRegistry.addRecipe(new ShapedOreRecipe(OreDictionary.getOres("ingotLead").get(0),
					"xxx", "xxx", "xxx",
					'x', "nuggetLead"
			));
		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.IRON_INGOT),
				"xxx", "xxx", "xxx",
				'x', "nuggetIron"
		));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.DIAMOND),
				"xxx", "xxx", "xxx",
				'x', "nuggetDiamond"
		));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.EMERALD),
				"xxx", "xxx", "xxx",
				'x', "nuggetEmerald"
		));

		GameRegistry.addRecipe(new ShapedOreRecipe(ItemInterface.getItemStack("apatite"),
				"xxx", "xxx", "xxx",
				'x', Config.nuggets.getStackForType(NuggetType.APATITE)
		));

		output = Config.miscResources.getStackForType(ResourceType.DIMENSIONAL_SINGULARITY);
		GameRegistry.addRecipe(output,
				" G ", "QEQ", " W ",
				'E', Items.ENDER_EYE,
				'Q', Blocks.QUARTZ_BLOCK,
				'W', Blocks.END_STONE,
				'G', Blocks.GOLD_BLOCK
		);

		output = Config.voidCapsule.getCapsuleForLiquid(FluidType.EMPTY);
		output.stackSize = 4;
		GameRegistry.addRecipe(output,
				"T T", "GFG", "T T",
				'G', Blocks.GLASS_PANE,
				'F', Config.miscResources.getStackForType(ResourceType.DIMENSIONAL_SINGULARITY),
				'T', Items.GOLD_NUGGET
		);

		output = new ItemStack(Config.magnet);
		GameRegistry.addRecipe(output,
				" i ", "cSc", " d ",
				'i', Items.IRON_INGOT,
				'c', Items.COMPASS,
				'd', Items.DIAMOND,
				'S', Config.miscResources.getStackForType(ResourceType.DIMENSIONAL_SINGULARITY)
		);

		for (int level = 1; level <= 8; level++) {
			output = new ItemStack(Config.magnet, 1, level * 2);
			GameRegistry.addRecipe(new ShapedOreRecipe(output,
					" d ", "mSm", " B ",
					'd', Items.DIAMOND,
					'm', "mb.magnet.level" + (level - 1),
					'B', Blocks.REDSTONE_BLOCK,
					'S', Config.miscResources.getStackForType(ResourceType.DIMENSIONAL_SINGULARITY)
			));
		}

		if (ThaumcraftHelper.isActive()) {
			input = Config.miscResources.getStackForType(ResourceType.LORE_FRAGMENT);
			output = new ItemStack(ThaumcraftHelper.miscResource, 1, ThaumcraftHelper.MiscResource.KNOWLEDGE_FRAGMENT.ordinal());
			GameRegistry.addShapelessRecipe(output,
					input, input, input, input
			);

			if (Config.thaumaturgeBackpackActive) {
				// T1 Thaumaturge's backpack
				GameRegistry.addRecipe(new ItemStack(Config.thaumaturgeBackpackT1),
						"SWS", "NCN", "SWS",
						'S', Items.STRING,
						'W', Blocks.WOOL,
						'N', new ItemStack(ThaumcraftHelper.miscResource, 1, ThaumcraftHelper.MiscResource.AMBER.ordinal()),
						'C', Blocks.CHEST
				);
			}
		}

		if (ArsMagicaHelper.isActive()) {
			input = ItemInterface.getItemStack("apatite");
			output = Config.miscResources.getStackForType(ResourceType.EXTENDED_FERTILIZER, 4);
			GameRegistry.addShapelessRecipe(output,
					new ItemStack(ArsMagicaHelper.essence, 1, ArsMagicaHelper.EssenceType.EARTH.ordinal()),
					input, input
			);
			GameRegistry.addShapelessRecipe(output,
					new ItemStack(ArsMagicaHelper.essence, 1, ArsMagicaHelper.EssenceType.PLANT.ordinal()),
					input, input
			);
		}
	}
	
	private static Map<ItemStack, Float> newMap() {
		return new Hashtable<ItemStack, Float>();
	}

	private static void setupCentrifugeRecipes() {
		ItemStack beeswax = ItemInterface.getItemStack("beeswax");
		ItemStack propolis = ItemInterface.getItemStack("propolis");
		propolis.setItemDamage(ForestryHelper.Propolis.PULSATING.ordinal());
		Map<ItemStack, Float> output = newMap();

		// 20 is the 'average' time to centrifuge a comb.
		output.put(beeswax, 0.90f);
		output.put(ItemInterface.getItemStack("honeyDrop"), 0.60f);
		output.put(Config.wax.getStackForType(WaxType.MAGIC), 0.10f);
		RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.MUNDANE), output);
		
		output = newMap();
		output.put(ItemInterface.getItemStack("refractoryWax"), 0.86f);
		output.put(ItemInterface.getItemStack("honeyDrop"), 0.087f);
		RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.MOLTEN), output);
		
		output = newMap();
		output.put(Config.wax.getStackForType(WaxType.AMNESIC), 0.50f);
		output.put(propolis, 0.50f);
		output.put(ItemInterface.getItemStack("honeyDrop"), 0.40f);
		RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.FORGOTTEN), output);
		
		output = newMap();
		output.put(Config.wax.getStackForType(WaxType.MAGIC), 1f);
		output.put(ItemInterface.getItemStack("honeyDrop"), 0.60f);
		RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.OCCULT), output);
		
		output = newMap();
		output.put(beeswax, 0.50f);
		output.put(Config.wax.getStackForType(WaxType.MAGIC), 0.22f);
		output.put(ItemInterface.getItemStack("honeyDrop"), 1f);
		RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.OTHERWORLDLY), output);
		
		output = newMap();
		output.put(ItemInterface.getItemStack("beeswax"), 0.80f);
		output.put(Config.wax.getStackForType(WaxType.MAGIC), 0.20f);
		output.put(new ItemStack(Items.PAPER), 0.057f);
		RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.PAPERY), output);
		
		output = newMap();
		output.put(Config.wax.getStackForType(WaxType.MAGIC), 0.90f);
		output.put(ItemInterface.getItemStack("honeydew"), 0.40f);
		output.put(Config.drops.getStackForType(DropType.INTELLECT), 0.10f);
		RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.INTELLECT), output);
		
		propolis.setItemDamage(ForestryHelper.Propolis.NORMAL.ordinal());
		output = newMap();
		output.put(ItemInterface.getItemStack("beeswax"), 0.90f);
		output.put(propolis, 0.20f);
		output.put(ItemInterface.getItemStack("honeydew"), 0.35f);
		RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.FURTIVE), output);
		
		output = newMap();
		output.put(Config.wax.getStackForType(WaxType.SOUL), 0.95f);
		output.put(ItemInterface.getItemStack("honeydew"), 0.26f);
		RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.SOUL), output);
		
		output = newMap();
		output.put(Config.wax.getStackForType(WaxType.MAGIC), 1f);
		output.put(Config.pollen.getStackForType(PollenType.PHASED), 0.055f);
		output.put(new ItemStack(ForestryHelper.honeydew, 1), 0.60f);
		RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.TEMPORAL), output);
		
		output = newMap();
		output.put(ItemInterface.getItemStack("beeswax"), 0.80f);
		output.put(Config.wax.getStackForType(WaxType.MAGIC), 0.80f);
		output.put(Config.propolis.getStackForType(PropolisType.UNSTABLE), 0.15f);
		RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.TRANSMUTED), output);

		output = newMap();
		output.put(Config.wax.getStackForType(WaxType.MAGIC), 1f);
		output.put(new ItemStack(Items.FEATHER), 0.60f);
		RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.AIRY), output);
		
		output = newMap();
		output.put(Config.wax.getStackForType(WaxType.MAGIC), 1f);
		output.put(new ItemStack(Items.BLAZE_POWDER), 0.60f);
		RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.FIREY), output);
		
		output = newMap();
		output.put(Config.wax.getStackForType(WaxType.MAGIC), 1f);
		output.put(new ItemStack(Items.DYE), 0.60f);
		RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.WATERY), output);
		
		output = newMap();
		output.put(Config.wax.getStackForType(WaxType.MAGIC), 1f);
		output.put(new ItemStack(Items.CLAY_BALL), 0.60f);
		RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.EARTHY), output);

		if (ThaumcraftHelper.isActive()) {
			output = newMap();
			output.put(Config.wax.getStackForType(WaxType.MAGIC), 1f);
			output.put(new ItemStack(Items.FEATHER), 0.60f);
			output.put(Config.propolis.getStackForType(PropolisType.AIR), 0.80f);
			RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.TC_AIR), output);
			
			output = newMap();
			output.put(Config.wax.getStackForType(WaxType.MAGIC), 1f);
			output.put(new ItemStack(Items.BLAZE_POWDER), 0.60f);
			output.put(Config.propolis.getStackForType(PropolisType.FIRE), 0.80f);
			RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.TC_FIRE), output);
			
			output = newMap();
			output.put(Config.wax.getStackForType(WaxType.MAGIC), 1f);
			output.put(new ItemStack(Items.DYE), 0.60f);
			output.put(Config.propolis.getStackForType(PropolisType.WATER), 0.80f);
			RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.TC_WATER), output);
			
			output = newMap();
			output.put(Config.wax.getStackForType(WaxType.MAGIC), 1f);
			output.put(new ItemStack(Items.CLAY_BALL), 0.60f);
			output.put(Config.propolis.getStackForType(PropolisType.EARTH), 0.80f);
			RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.TC_EARTH), output);
			
			output = newMap();
			output.put(Config.wax.getStackForType(WaxType.MAGIC), 1f);
			output.put(new ItemStack(Items.REDSTONE), 0.60f);
			output.put(Config.propolis.getStackForType(PropolisType.ORDER), 0.80f);
			RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.TC_ORDER), output);
			
			output = newMap();
			output.put(Config.wax.getStackForType(WaxType.MAGIC), 1f);
			output.put(new ItemStack(Items.GUNPOWDER), 0.60f);
			output.put(Config.propolis.getStackForType(PropolisType.CHAOS), 0.80f);
			RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.TC_CHAOS), output);

			output = newMap();
			output.put(propolis, 1f);
			output.put(Config.miscResources.getStackForType(ResourceType.TC_DUST_AIR), 0.90f);
			RecipeManagers.centrifugeManager.addRecipe(8, Config.propolis.getStackForType(PropolisType.AIR), output);
			output = newMap();
			output.put(propolis, 1f);
			output.put(Config.miscResources.getStackForType(ResourceType.TC_DUST_FIRE), 0.90f);
			RecipeManagers.centrifugeManager.addRecipe(8, Config.propolis.getStackForType(PropolisType.FIRE), output);
			output = newMap();
			output.put(propolis, 1f);
			output.put(Config.miscResources.getStackForType(ResourceType.TC_DUST_WATER), 0.90f);
			RecipeManagers.centrifugeManager.addRecipe(8, Config.propolis.getStackForType(PropolisType.WATER), output);
			output = newMap();
			output.put(propolis, 1f);
			output.put(Config.miscResources.getStackForType(ResourceType.TC_DUST_EARTH), 0.90f);
			RecipeManagers.centrifugeManager.addRecipe(8, Config.propolis.getStackForType(PropolisType.EARTH), output);
			output = newMap();
			output.put(propolis, 1f);
			output.put(Config.miscResources.getStackForType(ResourceType.TC_DUST_ORDER), 0.90f);
			RecipeManagers.centrifugeManager.addRecipe(8, Config.propolis.getStackForType(PropolisType.ORDER), output);
			output = newMap();
			output.put(propolis, 1f);
			output.put(Config.miscResources.getStackForType(ResourceType.TC_DUST_CHAOS), 0.90f);
			RecipeManagers.centrifugeManager.addRecipe(8, Config.propolis.getStackForType(PropolisType.CHAOS), output);
		}

		if (ArsMagicaHelper.isActive()) {
			output = newMap();
			output.put(Config.wax.getStackForType(WaxType.MAGIC),  0.85f);
			output.put(new ItemStack(ArsMagicaHelper.itemResource),  0.10f);
			output.put(new ItemStack(ArsMagicaHelper.itemResource),  0.024f);
			RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.AM_ESSENCE), output);
			output = newMap();
			output.put(ItemInterface.getItemStack("beeswax"),  0.50f);
			output.put(ItemInterface.getItemStack("refractoryWax"),  0.50f);
			output.put(ItemInterface.getItemStack("honeydew"),  0.65f);
			RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.AM_POTENT), output);
		}
		
		if (ThermalModsHelper.isActive()) {
			output = newMap();
			output.put(Config.wax.getStackForType(WaxType.MAGIC),  0.55f);
			output.put(Config.drops.getStackForType(DropType.DESTABILIZED),  0.22f);
			RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.TE_DESTABILIZED), output);
			output = newMap();
			output.put(Config.wax.getStackForType(WaxType.MAGIC),  0.55f);
			output.put(Config.drops.getStackForType(DropType.CARBON),  0.22f);
			RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.TE_CARBON), output);
			output = newMap();
			output.put(Config.wax.getStackForType(WaxType.MAGIC),  0.55f);
			output.put(Config.drops.getStackForType(DropType.LUX),  0.22f);
			RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.TE_LUX), output);
			output = newMap();
			output.put(Config.wax.getStackForType(WaxType.MAGIC),  0.55f);
			output.put(Config.drops.getStackForType(DropType.ENDEARING),  0.22f);
			RecipeManagers.centrifugeManager.addRecipe(20, Config.combs.getStackForType(CombType.TE_ENDEARING), output);
		}

	}

	private static void setupCarpenterRecipes() {
		ItemStack input;
		ItemStack output;

		output = ItemInterface.getItemStack("Forestry", "candle", 24);
		RecipeManagers.carpenterManager.addRecipe(30, new FluidStack(FluidRegistry.WATER, 600), null, output,
				" S ", "WWW", "WWW",
				'W', Config.wax,
				'S', Items.STRING
		);

		output = ItemInterface.getItemStack("Forestry", "candle", 6);
		input = ItemInterface.getItemStack("craftingMaterial");
		input.setItemDamage(ForestryHelper.CraftingMaterial.SILK_WISP.ordinal()); // Set to Silk Wisp
		RecipeManagers.carpenterManager.addRecipe(30, new FluidStack(FluidRegistry.WATER, 600), null, output,
				"WSW",
				'W', Config.wax,
				'S', input
		);

		output = Config.miscResources.getStackForType(ResourceType.AROMATIC_LUMP, 2);
		RecipeManagers.carpenterManager.addRecipe(30, FluidRegistry.getFluidStack("for.honey", 1000), null, output,
				" P ", "JDJ", " P ",
				'P', ItemInterface.getItemStack("pollen"),
				'J', ItemInterface.getItemStack("royalJelly"),
				'D', Config.drops.getStackForType(DropType.ENCHANTED)
		);

		RecipeManagers.carpenterManager.addRecipe(30, FluidRegistry.getFluidStack("for.honey", 1000), null, output,
				" J ", "PDP", " J ",
				'P', ItemInterface.getItemStack("pollen"),
				'J', ItemInterface.getItemStack("royalJelly"),
				'D', Config.drops.getStackForType(DropType.ENCHANTED)
		);

		if (ThaumcraftHelper.isActive())
		{
			// Carpenter recipes
			input = ItemInterface.getItemStack("craftingMaterial");
			input.setItemDamage(3); // Set to Silk Mesh
			output = new ItemStack(Config.thaumaturgeBackpackT2);
			RecipeManagers.carpenterManager.addRecipe(200, new FluidStack(FluidRegistry.WATER, 1000), null, output,
					"WXW", "WTW", "WWW",
					'X', Items.DIAMOND,
					'W', input,
					'T', new ItemStack(Config.thaumaturgeBackpackT1)
			);
		}
	}

	private static void registerLiquidContainer(ItemCapsule baseCapsule) {
		ItemStack empty = new ItemStack(baseCapsule, 1, 0);
		ItemStack filled;
		FluidStack liquid = null;

		for (FluidType fluidType : FluidType.values()) {
			switch (fluidType) {
				case EMPTY:
					liquid = null;
					break;
				case WATER:
					liquid = new FluidStack(FluidRegistry.WATER, baseCapsule.getType().capacity);
					break;
				case LAVA:
					liquid = new FluidStack(FluidRegistry.LAVA, baseCapsule.getType().capacity);
					break;
				default:
					liquid = FluidRegistry.getFluidStack(fluidType.liquidID, baseCapsule.getType().capacity);
					break;
			}

			if (liquid != null) {
				filled = new ItemStack(baseCapsule, 1, fluidType.ordinal());
				FluidContainerRegistry.registerFluidContainer(liquid, filled, empty);

				// Register with Squeezer/Bottler
				//RecipeManagers.bottlerManager.addRecipe(5, liquid, empty, filled);  Outdated?
				RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] { filled }, liquid,
						Config.wax.getStackForType(WaxType.MAGIC), 20);
				fluidType.available = true;
			}
		}
		// Empty will be set to unavailable. Obviously, it always is.
		FluidType.EMPTY.available = true;
	}
}
