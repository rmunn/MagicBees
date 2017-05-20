package magicbees.integration.appliedenergistics2;

import elec332.core.api.module.ElecModule;
import magicbees.MagicBees;
import magicbees.bees.BeeIntegrationInterface;
import magicbees.util.ModNames;
import magicbees.util.Utils;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * Created by Elec332 on 19-5-2017.
 */
@ElecModule(owner = MagicBees.modid, name = "AE2 Integration", modDependencies = ModNames.AE2)
public class IntegrationAE2 {

	@ElecModule.EventHandler
	public void init(FMLInitializationEvent event){
		BeeIntegrationInterface.aeSkyStone = Utils.getBlock(ModNames.AE2, "sky_stone_block").getDefaultState();
	}

}
