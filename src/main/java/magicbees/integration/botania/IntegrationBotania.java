package magicbees.integration.botania;

import elec332.core.api.module.ElecModule;
import elec332.core.compat.forestry.allele.AlleleFlowerProvider;
import elec332.core.world.WorldHelper;
import magicbees.MagicBees;
import magicbees.api.ITransmutationHandler;
import magicbees.bees.BeeIntegrationInterface;
import magicbees.bees.allele.AlleleEffectTransmuting;
import magicbees.util.DefaultTransmutationController;
import magicbees.util.ModNames;
import magicbees.util.Utils;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Elec332 on 15-5-2017.
 */
@ElecModule(owner = MagicBees.modid, name = "Botania Integration", modDependencies = ModNames.BOTANIA)
public class IntegrationBotania {

	private Block livingWood, livingRock, dreamWood;

	@ElecModule.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		BeeIntegrationInterface.livingWood = livingWood = Utils.getBlock(ModNames.BOTANIA, "livingwood");
		livingRock = Utils.getBlock(ModNames.BOTANIA, "livingrock");
		dreamWood = Utils.getBlock(ModNames.BOTANIA, "dreamwood");
		Block mysticalFlower = Utils.getBlock(ModNames.BOTANIA, "specialFlower");
		BeeIntegrationInterface.effectDreaming = new AlleleEffectTransmuting(BeeIntegrationInterface.bot_dreaming_name, new DefaultTransmutationController((ITransmutationHandler) (world, pos, block, biome) -> {

			int[] oreIDs = OreDictionary.getOreIDs(block);
			for (int oreId : oreIDs) {
				if (oreId == OreDictionary.getOreID("logWood")) {
					WorldHelper.setBlockState(world, pos, livingWood.getDefaultState(), 3);
					return true;
				} else if (oreId == OreDictionary.getOreID("stone")) {
					WorldHelper.setBlockState(world, pos, livingRock.getDefaultState(), 3);
					return true;
				} else if (oreId == OreDictionary.getOreID("livingwood")) {
					WorldHelper.setBlockState(world, pos, dreamWood.getDefaultState(), 3);
					return true;
				}
			}
			return false;

		}));
		BeeIntegrationInterface.flowersBotania = new AlleleFlowerProvider(BeeIntegrationInterface.bot_flowers_name, new FlowerProviderBotania("flowersBotania", mysticalFlower));
		BeeIntegrationInterface.flowersBotania.registerAcceptableFlower(mysticalFlower);

		BeeIntegrationInterface.itemPetal = Utils.getItem(ModNames.BOTANIA, "petal");
		BeeIntegrationInterface.itemPastureSeed = Utils.getItem(ModNames.BOTANIA, "grassSeeds");
		BeeIntegrationInterface.seedTypes = 9;

	}

}
