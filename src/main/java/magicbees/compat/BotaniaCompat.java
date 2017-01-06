package magicbees.compat;

import elec332.core.api.module.ElecModule;
import magicbees.MagicBees;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Elec332 on 14-10-2016.
 */
@ElecModule(owner = MagicBees.modid, name = "BotaniaCompat", modDependencies = "Botania")
public class BotaniaCompat {

    @SubscribeEvent
    public void preInit(FMLPreInitializationEvent event){

    }

}
