package magicbees.block;

import elec332.core.api.client.IIconRegistrar;
import elec332.core.api.client.model.IElecModelBakery;
import elec332.core.api.client.model.IElecQuadBakery;
import elec332.core.api.client.model.IElecTemplateBakery;
import elec332.core.client.model.loading.INoJsonBlock;
import magicbees.bees.EnumBeeHives;
import magicbees.util.MagicBeesResourceLocation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

/**
 * Created by Elec332 on 15-4-2017.
 */
public class BlockHive extends elec332.core.compat.forestry.bee.BlockHive<EnumBeeHives> implements INoJsonBlock {

	@SideOnly(Side.CLIENT)
	private TextureAtlasSprite[][] textures;
	@SideOnly(Side.CLIENT)
	private IBakedModel[] models;

	@Override
	public BlockHive register(@Nonnull ResourceLocation rl) {
		super.register(rl);
		return this;
	}

	@Nonnull
	@Override
	public Class<EnumBeeHives> getHiveTypes() {
		return EnumBeeHives.class;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IBakedModel getBlockModel(IBlockState iBlockState) {
		return models[getMetaFromState(iBlockState)];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IBakedModel getItemModel(ItemStack itemStack, World world, EntityLivingBase entityLivingBase) {
		int i = itemStack.getItemDamage();
		if (i > EnumBeeHives.values().length){
			i = 0;
		}
		return models[i];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerTextures(IIconRegistrar iconRegistrar) {
		int types = EnumBeeHives.values().length;
		textures = new TextureAtlasSprite[types][EnumFacing.VALUES.length];
		for (int i = 0; i < types; i++) {
			for (EnumFacing facing : EnumFacing.VALUES){
				textures[i][facing.ordinal()] = iconRegistrar.registerSprite(new MagicBeesResourceLocation("blocks/beehive."+i+(facing.getAxis().isVertical() ? ".top" : ".side")));
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerModels(IElecQuadBakery quadBakery, IElecModelBakery modelBakery, IElecTemplateBakery templateBakery) {
		int types = EnumBeeHives.values().length;
		models = new IBakedModel[types];
		for (int i = 0; i < types; i++) {
			models[i] = modelBakery.forTemplate(templateBakery.newDefaultBlockTemplate(textures[i]));
		}
	}

}
