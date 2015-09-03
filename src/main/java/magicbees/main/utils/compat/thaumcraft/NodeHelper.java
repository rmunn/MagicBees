package magicbees.main.utils.compat.thaumcraft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;

public class NodeHelper {

	@SuppressWarnings("unchecked")
	public static INode findNode(Chunk chunk, int x, int y, int z, int range) {
		Vec3 apiaryPos = Vec3.createVectorHelper(x, y, z);
		List<TileEntity> tileEntities = new ArrayList<TileEntity>(((Map<ChunkPosition, TileEntity>)chunk.chunkTileEntityMap).values());
		Collections.shuffle(tileEntities);
		for (TileEntity entity : tileEntities) {
			if (entity instanceof INode) {
				Vec3 tePos = Vec3.createVectorHelper(entity.xCoord, entity.yCoord, entity.zCoord);
				Vec3 result = apiaryPos.subtract(tePos);
				if (result.lengthVector() <= range) {
					return (INode)entity;
				}
			}
		}
		
		return null;
	}

	public static boolean rechargeNodeInRange(List<Chunk> chunks, World world, int xCoord, int yCoord, int zCoord, int range) {
		Collections.shuffle(chunks);
		for (Chunk chunk : chunks) {
	    	INode nearestNode = findNode(chunk, xCoord, yCoord, zCoord, range);
	    	boolean nodeChanged = false;
	        if (nearestNode != null) {
	            AspectList baseAspects = nearestNode.getAspectsBase().copy();
	            AspectList currentAspects = nearestNode.getAspects().copy();
	            for (Aspect aspect : nearestNode.getAspects().getAspects()) {
	            	int diff = baseAspects.getAmount(aspect) - currentAspects.getAmount(aspect);
	            	if (1 < diff) {
	            		nearestNode.addToContainer(aspect, world.rand.nextInt(diff - 1) + 1);
	            		nodeChanged = true;
	            	}
	            	else if (diff == 1) {
	            		nearestNode.addToContainer(aspect, 1);
	            		nodeChanged = true;
	            	}
	            }
	            if (nodeChanged) {
	            	updateNode(nearestNode, world);
	            }
	        	return true;
	        }
	    }
		return false;
	}
	
	public static boolean growNodeInRange(List<Chunk> chunks, World world, int xCoord, int yCoord, int zCoord, int range) {
		Collections.shuffle(chunks);
		for (Chunk chunk : chunks) {
			INode nearestNode = findNode(chunk, xCoord, yCoord, zCoord, range);
			if (nearestNode != null) {
				Aspect aspectToAdd;
				if (0.55f <= world.rand.nextFloat()) {
					List<Aspect> aspects = new ArrayList<Aspect>(Aspect.aspects.values());
					aspectToAdd = aspects.get(world.rand.nextInt(aspects.size()));
				}
				else {
					switch (world.rand.nextInt(6)) {
					default:
					case 0:
						aspectToAdd = Aspect.EARTH;
						break;
					case 1:
						aspectToAdd = Aspect.WATER;
						break;
					case 2:
						aspectToAdd = Aspect.FIRE;
						break;
					case 3:
						aspectToAdd = Aspect.AIR;
						break;
					case 4:
						aspectToAdd = Aspect.ORDER;
						break;
					case 5:
						aspectToAdd = Aspect.ENTROPY;
						break;
					}
				}
				
				nearestNode.setNodeVisBase(aspectToAdd, (short)(1 + world.rand.nextInt(2)));
				updateNode(nearestNode, world);
				return true;
			}
		}
		return false;
	}
	
	private static void updateNode(INode nearestNode, World world) {
        if (nearestNode instanceof TileEntity) {
        	TileEntity tile = (TileEntity)nearestNode;
        	tile.markDirty();
        	world.markBlockForUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
        }
	}
}
