package magicbees.integration.redstonearsenal;

import elec332.core.api.module.ElecModule;
import magicbees.MagicBees;
import magicbees.bees.BeeIntegrationInterface;
import magicbees.util.ModNames;
import magicbees.util.Utils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * Created by Elec332 on 19-5-2017.
 */
@ElecModule(owner = MagicBees.modid, name = "RedstoneArsenal Integration", modDependencies = ModNames.RSA)
public class IntegrationRSA {

	@ElecModule.EventHandler
	public void init(FMLInitializationEvent event){
		BeeIntegrationInterface.itemRSAFluxedElectrumNugget = new ItemStack(Utils.getItem(ModNames.RSA, "material"), 1, 64);
		BeeIntegrationInterface.blockRSAFluxedElectrum = Utils.getBlock(ModNames.RSA, "storage").getDefaultState();
	}

}
