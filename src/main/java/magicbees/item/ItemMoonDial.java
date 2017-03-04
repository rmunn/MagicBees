package magicbees.item;

import com.google.common.base.Preconditions;
import elec332.core.api.client.model.IElecModelBakery;
import elec332.core.api.client.model.IElecQuadBakery;
import elec332.core.api.client.model.IElecTemplateBakery;
import elec332.core.item.AbstractTexturedItem;
import elec332.core.util.ItemStackHelper;
import elec332.core.util.MoonPhase;
import magicbees.util.Config;
import magicbees.util.MagicBeesResourceLocation;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Elec332 on 4-3-2017.
 */
public class ItemMoonDial extends AbstractTexturedItem {

    public ItemMoonDial() {
        super(new MagicBeesResourceLocation("moondial"));
    }

    @SideOnly(Side.CLIENT)
    private IBakedModel[] models;
    private static final ResourceLocation[] textureLocs;

    @Override
    public IBakedModel getItemModel(ItemStack stack, World world, EntityLivingBase entity) {
        if (world == null && entity == null){
            return models[0];
        }
        if (world == null){
            world = entity.world;
        }
        Preconditions.checkNotNull(world);
        return models[MoonPhase.getMoonPhase(world).ordinal()];
    }

    @Override
    public void registerModels(IElecQuadBakery quadBakery, IElecModelBakery modelBakery, IElecTemplateBakery templateBakery) {
        models = new IBakedModel[textures.length];
        for (int i = 0; i < textures.length; i++) {
            models[i] = modelBakery.itemModelForTextures(textures[i]);
        }
    }

    @Override
    protected ResourceLocation[] getTextureLocations() {
        return textureLocs;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
        if (Config.moonDialShowsPhaseInText && ItemStackHelper.isStackValid(player.getHeldItemMainhand()) && player.getHeldItemMainhand().getItem() == this){
            tooltip.add("\u00A77" + MoonPhase.getMoonPhase(player.getEntityWorld()).getLocalizedName());
        }
    }

    static {
        textureLocs = new ResourceLocation[MoonPhase.values().length];
        for (int i = 0; i < textureLocs.length; i++) {
            textureLocs[i] = new MagicBeesResourceLocation("moonDial."+i);
        }
    }

}
