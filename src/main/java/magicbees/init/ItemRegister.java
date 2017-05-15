package magicbees.init;

import elec332.core.item.ItemEnumBased;
import magicbees.bees.EnumBeeModifiers;
import magicbees.item.ItemMagicBeesFrame;
import magicbees.item.ItemMoonDial;
import magicbees.item.ItemMysteriousMagnet;
import magicbees.item.types.*;
import magicbees.util.MagicBeesResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Elec332 on 4-3-2017.
 */
public final class ItemRegister {

    public static ItemEnumBased<EnumCombType> combItem;
    public static ItemEnumBased<EnumDropType> dropItem;
    public static ItemEnumBased<EnumPollenType> pollenItem;
    public static ItemEnumBased<EnumPropolisType> propolisItem;
    public static ItemEnumBased<EnumWaxType> waxItem;
    public static ItemEnumBased<EnumResourceType> resourceItem;
    public static ItemEnumBased<EnumNuggetType> orePartItem;
    //frames
    public static Item magicFrame, resilientFrame, gentleFrame, metabolicFrame, necroticFrame, temporalFrame, oblivionFrame;
    public static Item mysteriousMagnet, moonDial;

    public static void init(){
        combItem = GameRegistry.register(new ItemEnumBased<>(new MagicBeesResourceLocation("beeComb"), EnumCombType.class));
        dropItem = GameRegistry.register(new ItemEnumBased<>(new MagicBeesResourceLocation("drop"), EnumDropType.class));
        pollenItem = GameRegistry.register(new ItemEnumBased<>(new MagicBeesResourceLocation("pollen"), EnumPollenType.class));
        propolisItem = GameRegistry.register(new ItemEnumBased<>(new MagicBeesResourceLocation("propolis"), EnumPropolisType.class));
        waxItem = GameRegistry.register(new ItemEnumBased<>(new MagicBeesResourceLocation("wax"), EnumWaxType.class));
        resourceItem = GameRegistry.register(new ItemEnumBased<>(new MagicBeesResourceLocation("resource"), EnumResourceType.class));
        orePartItem = GameRegistry.register(new ItemEnumBased<>(new MagicBeesResourceLocation("orepart"), EnumNuggetType.class));

        magicFrame = GameRegistry.register(new ItemMagicBeesFrame(EnumBeeModifiers.MAGIC));
        resilientFrame = GameRegistry.register(new ItemMagicBeesFrame(EnumBeeModifiers.RESILIENT));
        gentleFrame = GameRegistry.register(new ItemMagicBeesFrame(EnumBeeModifiers.GENTLE));
        metabolicFrame = GameRegistry.register(new ItemMagicBeesFrame(EnumBeeModifiers.METABOLIC));
        necroticFrame = GameRegistry.register(new ItemMagicBeesFrame(EnumBeeModifiers.NECROTIC));
        temporalFrame = GameRegistry.register(new ItemMagicBeesFrame(EnumBeeModifiers.TEMPORAL));
        oblivionFrame = GameRegistry.register(new ItemMagicBeesFrame(EnumBeeModifiers.OBLIVION));

        mysteriousMagnet = GameRegistry.register(new ItemMysteriousMagnet());
        moonDial = GameRegistry.register(new ItemMoonDial());
    }

}
