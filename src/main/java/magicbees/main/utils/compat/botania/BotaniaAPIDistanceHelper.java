package magicbees.main.utils.compat.botania;

import magicbees.bees.BeeSpecies;
import magicbees.main.utils.compat.BotaniaHelper;
import magicbees.main.utils.compat.BotaniaHelper.ManaResource;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.LexiconPage;
import vazkii.botania.api.recipe.RecipeElvenTrade;
import vazkii.botania.api.recipe.RecipeManaInfusion;
import vazkii.botania.api.recipe.RecipePetals;
import forestry.api.apiculture.EnumBeeType;

/**
 * Subordinate helper to the Botania Helper that has references to Botania API stuff that will cause
 * NoClassDefFoundErrors if included in the Botania Helper proper.
 */
public class BotaniaAPIDistanceHelper {
	
	public static LexiconEntry entryBeegonia;
	public static LexiconEntry entryHiveacynth;
	
	public static RecipeManaInfusion infusionBeeBotanical;
	public static RecipeManaInfusion infusionBeeVazbee;
	
	public static RecipeElvenTrade tradeBeeAelfheim;
	
	public static void registerSubtiles() {
		BotaniaAPI.registerSubTile(SubTileBeegonia.NAME, SubTileBeegonia.class);
		BotaniaAPI.registerSubTileSignature(SubTileBeegonia.class, new BotaniaSignature(SubTileBeegonia.NAME));
		BotaniaAPI.addSubTileToCreativeMenu(SubTileBeegonia.NAME);
		
		BotaniaAPI.registerSubTile(SubTileHiveacynth.NAME, SubTileHiveacynth.class);
		BotaniaAPI.registerSubTileSignature(SubTileHiveacynth.class, new BotaniaSignature(SubTileHiveacynth.NAME));
		BotaniaAPI.addSubTileToCreativeMenu(SubTileHiveacynth.NAME);
	}

	public static void setupCraftingAndLexicon() {
		infusionBeeBotanical = new SpeciesRecipeManaInfusion(BeeSpecies.BOT_BOTANIC, BeeSpecies.BOT_ROOTED, 55000, EnumBeeType.DRONE);
		BotaniaAPI.manaInfusionRecipes.add(infusionBeeBotanical);
		
		infusionBeeVazbee = new SpeciesRecipeManaInfusion(BeeSpecies.BOT_VAZBEE, BeeSpecies.BOT_VAZBEE, 167392, EnumBeeType.PRINCESS);
		infusionBeeVazbee.setAlchemy(true);
		BotaniaAPI.manaInfusionRecipes.add(infusionBeeVazbee);
		
		tradeBeeAelfheim = new SpeciesRecipeElvenTrade(BeeSpecies.BOT_DREAMING, BeeSpecies.BOT_AELFHEIM);
		
		LexiconEntry manasteelEntry = getLexiconEntryForName(BotaniaHelper.LEXICON_ENTRY_MANA_GEAR);
		manasteelEntry.addPage(BotaniaAPI.internalHandler.craftingRecipePage("magicbees.botania.lexicon.manasteelScoop", BotaniaHelper.manasteelScoopRecipe));
		manasteelEntry.addPage(BotaniaAPI.internalHandler.craftingRecipePage("magicbees.botania.lexicon.manasteelGrafter", BotaniaHelper.manasteelGrafterRecipe));

		RecipePetals beegoniaRecipe = BotaniaAPI.registerPetalRecipe(BotaniaAPI.internalHandler.getSubTileAsStack(SubTileBeegonia.NAME), new Object[] {
				new ItemStack(BotaniaHelper.itemPetal), new ItemStack(BotaniaHelper.itemManaPetal),
				new ItemStack(BotaniaHelper.itemPetal, 1, 4), new ItemStack(BotaniaHelper.itemPetal, 1, 4),
				new ItemStack(BotaniaHelper.itemPetal, 1, 4), new ItemStack(BotaniaHelper.itemManaPetal, 1, 4),
				new ItemStack(BotaniaHelper.itemManaPetal, 1, 15)
			});
		BotaniaAPIDistanceHelper.entryBeegonia = new BotaniaLexiconEntry(SubTileBeegonia.NAME, BotaniaAPI.categoryGenerationFlowers);
		BotaniaAPIDistanceHelper.entryBeegonia.setLexiconPages(new LexiconPage[] {
				BotaniaAPI.internalHandler.textPage("magicbees.botania.lexicon.beegonia.0"),
				BotaniaAPI.internalHandler.textPage("magicbees.botania.lexicon.beegonia.1"),
				BotaniaAPI.internalHandler.petalRecipePage("magicbees.botania.lexicon.beegonia.crafting", beegoniaRecipe),
				BotaniaAPI.internalHandler.textPage("magicbees.botania.lexicon.beegonia.2")
			});
		
		RecipePetals hiveacynthRecipe = BotaniaAPI.registerPetalRecipe(BotaniaAPI.internalHandler.getSubTileAsStack(SubTileHiveacynth.NAME), new Object[] {
				new ItemStack(BotaniaHelper.itemPetal, 1, 3), new ItemStack(BotaniaHelper.itemPetal, 1, 9),
				new ItemStack(BotaniaHelper.itemPetal, 1, 9), new ItemStack(BotaniaHelper.itemPetal, 1, 11),
				new ItemStack(BotaniaHelper.itemManaPetal, 1, 3), new ItemStack(BotaniaHelper.itemManaPetal, 1, 9),
				new ItemStack(BotaniaHelper.itemManaPetal, 1, 11), new ItemStack(BotaniaHelper.itemManaPetal, 1, 11),
				new ItemStack(BotaniaHelper.itemManaResource, 1, ManaResource.REDSTONE_ROOT.ordinal())
			});
		BotaniaAPIDistanceHelper.entryHiveacynth = new BotaniaLexiconEntry(SubTileHiveacynth.NAME, BotaniaAPI.categoryFunctionalFlowers);
		BotaniaAPIDistanceHelper.entryHiveacynth.setLexiconPages(new LexiconPage[] {
				BotaniaAPI.internalHandler.textPage("magicbees.botania.lexicon.hiveacynth.0"),
				BotaniaAPI.internalHandler.textPage("magicbees.botania.lexicon.hiveacynth.1"),
				BotaniaAPI.internalHandler.petalRecipePage("magicbees.botania.lexicon.hiveacynth.crafting", hiveacynthRecipe)
			});
	}
	
	public static LexiconEntry getLexiconEntryForName(String name) {
		for (LexiconEntry entry : BotaniaAPI.getAllEntries()) {
			if (entry.unlocalizedName.equals(name)) {
				return entry;
			}
		}
		return null;
	}
}
