package magicbees.main.utils;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemInterface
{
	public static ItemStack getItemStack(String item)
	{
		return GameRegistry.makeItemStack("Forestry:" + item, 0, 1, null);
	}

	public static ItemStack getItemStack(String modId, String item)
	{
		return GameRegistry.makeItemStack(modId + ":" + item, 0, 1, null);
	}

	public static ItemStack getItemStack(String modId, String item, int stackSize)
	{
		return GameRegistry.makeItemStack(modId + ":" + item, 0, stackSize, null);
	}

	public static Item getItem(String item)
	{
		Item i;
		i = GameRegistry.findItem("Forestry", item);
		if (i == null)
		{
			LogHelper.warn("Trying to get Item " + item + " from Forestry which does not exist!");
		}
		return i;
	}

	public static Item getItem(String modId, String item)
	{
		Item i;
		i = GameRegistry.findItem(modId, item);
		if (i == null)
		{
			LogHelper.warn("Trying to get Item " + item + " from " + modId + " which does not exist!");
		}
		return i;
	}
}
