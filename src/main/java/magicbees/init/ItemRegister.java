package magicbees.init;

import elec332.core.item.AbstractTexturedItem;
import elec332.core.item.ItemEnumBased;
import elec332.core.util.RegistryHelper;
import elec332.core.util.recipes.RecipeHelper;
import magicbees.bees.EnumBeeModifiers;
import magicbees.item.*;
import magicbees.item.types.*;
import magicbees.util.MagicBeesResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

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
    public static Item mysteriousMagnet, moonDial, ironNugget;
    public static Item manasteelgrafter, manasteelScoop;

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

        manasteelgrafter = GameRegistry.register(new ItemManaSteelGrafter());
        manasteelScoop = GameRegistry.register(new ItemManaSteelScoop());

        fixIronNuggetStuff();

    }

    private static void fixIronNuggetStuff(){
        if (RegistryHelper.getItemRegistry().containsKey(new ResourceLocation("iron_nugget"))){
            ironNugget = RegistryHelper.getItemRegistry().getObject(new ResourceLocation("iron_nugget"));
        }
        if (ironNugget == null){
            ironNugget = GameRegistry.register(new AbstractTexturedItem(new MagicBeesResourceLocation("iron_nugget")){}.setCreativeTab(CreativeTabs.MATERIALS));
            OreDictionary.registerOre("nuggetIron", ironNugget);
            RecipeHelper.getCraftingManager().addShapelessRecipe(new ItemStack(Items.IRON_INGOT), "xxx", "xxx", "xxx", 'x', ironNugget);
        }
    }

}
