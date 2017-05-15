package magicbees.integration.railcraft;

import elec332.core.api.module.ElecModule;
import elec332.core.world.WorldHelper;
import magicbees.MagicBees;
import magicbees.api.ITransmutationHandler;
import magicbees.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import javax.annotation.Nullable;

/**
 * Created by Elec332 on 15-5-2017.
 */
@SuppressWarnings("deprecation")
@ElecModule(owner = MagicBees.modid, name = "Railcraft Integration")
public class IntegrationRailcraft {

	private Block quarried, abyssal;

	@ElecModule.EventHandler
	public void init(FMLInitializationEvent event){
		quarried = Utils.getBlock("railcraft", "brick_quarried");
		abyssal = Utils.getBlock("railcraft", "brick_abyssal");
		MagicBees.transmutationController.addTransmutationHandler(new ITransmutationHandler() {

			@Override
			public boolean transmute(World world, BlockPos pos, ItemStack block, Biome biome) {
				BlockStone.EnumType type = null;
				boolean hT = false;
				if (block.getItem() == Item.getItemFromBlock(Blocks.STONE)){
					IBlockState state = Blocks.STONE.getStateFromMeta(block.getMetadata());
					type = state.getValue(BlockStone.VARIANT);
					hT = true;
				}
				if (block.getItem() == Item.getItemFromBlock(Blocks.COBBLESTONE)){
					hT = true;
				}
				if (hT){
					IBlockState state = trySpawnRC(biome, type);
					if (state != null){
						WorldHelper.setBlockState(world, pos, state, 3);
						return true;
					}
				}
				return false;
			}

		});
	}

	private IBlockState trySpawnRC(Biome biome, @Nullable BlockStone.EnumType type){
		int meta = type == null ? 5 : 2;
		if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.SNOWY)){
			return quarried.getStateFromMeta(meta);
		}
		if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.WATER) && !biome.getBiomeName().toLowerCase().contains("river")){
			return abyssal.getStateFromMeta(meta);
		}
		return null;
	}

}
