package magicbees.init;

import com.google.common.collect.Maps;
import elec332.core.util.ItemStackHelper;
import forestry.api.recipes.RecipeManagers;
import forestry.apiculture.PluginApiculture;
import forestry.core.PluginCore;
import magicbees.MagicBees;
import magicbees.item.types.EnumCombType;
import magicbees.item.types.EnumWaxType;
import net.minecraft.item.ItemStack;

import java.util.Map;

/**
 * Created by Elec332 on 13-2-2017.
 */
public class RecipeRegister {

    public static void init(){

    }

    private static void registerRecipes(){
    }

    private static void registerForestryRecipes(){
        CombCentrifugeRecipe recipe;
        ItemStack beesWax = PluginCore.getItems().beeswax.getItemStack();
        ItemStack honeyDrop = new ItemStack(PluginApiculture.getItems().honeyDrop);
        ItemStack magicWax = getWax(EnumWaxType.MAGIC);
        ItemStack refractoryWax = PluginCore.getItems().refractoryWax.getItemStack();

        recipe = new CombCentrifugeRecipe(EnumCombType.MUNDANE);
        recipe.addProduct(beesWax, 0.9f);
        recipe.addProduct(honeyDrop, 0.6f);
        recipe.addProduct(magicWax, 0.1f);
        recipe.register(20);
        recipe = new CombCentrifugeRecipe(EnumCombType.MOLTEN);
        recipe.addProduct(refractoryWax, 0.86f);
        recipe.addProduct(honeyDrop, 0.087f);
        recipe.register(20);

    }

    private static ItemStack getWax(EnumWaxType wax){
        return MagicBees.waxItem.getStackFromType(wax);
    }

    private static class CombCentrifugeRecipe {

        private CombCentrifugeRecipe(EnumCombType comb){
            this.comb = comb;
            this.products = Maps.newHashMap();
        }

        private EnumCombType comb;
        private final Map<ItemStack, Float> products;

        protected void addProduct(ItemStack stack, float chance){
            products.put(ItemStackHelper.copyItemStack(stack), chance);
        }

        protected void register(int time){
            RecipeManagers.centrifugeManager.addRecipe(time, MagicBees.combItem.getStackFromType(comb), products);
        }

    }

}
