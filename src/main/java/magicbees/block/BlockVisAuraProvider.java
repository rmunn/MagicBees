package magicbees.block;

import magicbees.main.CommonProxy;
import magicbees.main.utils.TabMagicBees;
import magicbees.tileentity.TileEntityVisAuraProvider;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockVisAuraProvider extends BlockContainer {

	public BlockVisAuraProvider() {
		super(Material.GLASS);
		this.setCreativeTab(TabMagicBees.tabMagicBees);
		this.setRegistryName("visAuraProvider");
		this.setHardness(0.5f);
		this.setResistance(1.5f);
		this.setLightLevel(8);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityVisAuraProvider();
	}
	
	@Override
	public int getRenderType()
	{
		return CommonProxy.RenderIdVisAuraProvider;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = Blocks.planks.getIcon(0, 5);
	}

}
