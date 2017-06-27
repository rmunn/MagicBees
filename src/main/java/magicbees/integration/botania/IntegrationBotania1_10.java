package magicbees.integration.botania;

import elec332.core.api.module.ElecModule;
import magicbees.MagicBees;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Elec332 on 27-6-2017.
 */
@ElecModule(owner = MagicBees.modid, name = "Botania Integration", modDependencies = "Botania")
public class IntegrationBotania1_10 {

	public IntegrationBotania1_10(){
		MagicBees.logger.info("Detected 1.10 version of botania, loading 1.10 wrapper...");
		integrationBotania = new IntegrationBotania();
	}

	private IntegrationBotania integrationBotania;

	@ElecModule.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		integrationBotania.preInit(event);
	}

	@ElecModule.EventHandler
	public void init(FMLInitializationEvent event){
		integrationBotania.init(event);
	}

	@ElecModule.EventHandler
	public void postInit(FMLPostInitializationEvent event){
		integrationBotania.postInit(event);
	}

}
