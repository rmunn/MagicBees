package magicbees.integration.botania;

import elec332.core.api.module.ElecModule;
import elec332.core.world.WorldHelper;
import magicbees.MagicBees;
import magicbees.api.ITransmutationHandler;
import magicbees.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Elec332 on 15-5-2017.
 */
@ElecModule(owner = MagicBees.modid, name = "Botania Integration")
public class IntegrationBotania {

	private Block livingWood, livingRock, dreamWood;

	@ElecModule.EventHandler
	public void postInit(FMLPostInitializationEvent event){
		livingWood = Utils.getBlock("botania", "livingwood");
		livingRock = Utils.getBlock("botania", "livingrock");
		dreamWood = Utils.getBlock("botania", "dreamwood");
		MagicBees.transmutationController.addTransmutationHandler(new ITransmutationHandler() {

			@Override
			public boolean transmute(World world, BlockPos pos, ItemStack block, Biome biome) {
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
			}

		});
	}

}
