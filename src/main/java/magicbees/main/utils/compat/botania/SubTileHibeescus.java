package magicbees.main.utils.compat.botania;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import magicbees.bees.BeeManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.subtile.SubTileFunctional;
import forestry.api.apiculture.EnumBeeType;
import forestry.api.apiculture.IBee;

public class SubTileHibeescus extends SubTileFunctional {
	
	public static final String NAME = "hibeescus";
	private final int MANA_PER_OPERATION = 100000;
	private final int OPERATION_TICKS_TIME = 20 * 60 * 15;
	private final int RANGE = 1;
	
	private ItemStack beeSlot;
	private long operationTicksRemaining;

	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if (beeSlot != null && this.mana >= MANA_PER_OPERATION) {
			progressOperation();
		}
		else if (beeSlot == null && this.supertile.getWorldObj().getTotalWorldTime() % 10 == 0) {
			findBeeItemToHold();
		}
	}
	
	private void progressOperation() {
		operationTicksRemaining--;
		
		if (operationTicksRemaining <= 0) {
			mana -= MANA_PER_OPERATION;
			IBee bee = BeeManager.beeRoot.getMember(beeSlot);
			bee.setIsNatural(true);
			EnumBeeType beeType = EnumBeeType.QUEEN;
			if (bee.getMate() == null) {
				beeType = EnumBeeType.PRINCESS;
			}
			ItemStack outputStack = BeeManager.beeRoot.getMemberStack(bee, beeType.ordinal());
			Random r = supertile.getWorldObj().rand;
			EntityItem entity = new EntityItem(supertile.getWorldObj(),
					supertile.xCoord - RANGE + r.nextInt(RANGE * 2 + 1), supertile.yCoord + 1, supertile.zCoord - RANGE + r.nextInt(RANGE * 2 + 1),
					outputStack);
			entity.motionX = 0;
			entity.motionY = 0;
			entity.motionZ = 0;
			
			supertile.getWorldObj().spawnEntityInWorld(entity);
			beeSlot = null;
		}
	}
	
	private void findBeeItemToHold() {
		@SuppressWarnings("unchecked")
		List<EntityItem> items = supertile.getWorldObj().getEntitiesWithinAABB(EntityItem.class, getSearchBoundingBox());
		
		for (EntityItem itemEntity : items) {
			ItemStack item = itemEntity.getEntityItem();
			if (isItemPrincessOrQueen(item)) {
				beeSlot = itemEntity.getEntityItem();
				operationTicksRemaining = OPERATION_TICKS_TIME;
				
				// Princesses and Queens don't stack, but YOU NEVER KNOW.
				item.stackSize -= 1;
				if (item.stackSize <= 0) {
					itemEntity.setDead();
				}
				
				// Leave for loop
				break;
			}
		}
	}
	
	private boolean isItemPrincessOrQueen(ItemStack stack) {
		boolean isBee = BeeManager.beeRoot.isMember(stack, EnumBeeType.PRINCESS.ordinal()) 
				|| BeeManager.beeRoot.isMember(stack, EnumBeeType.QUEEN.ordinal());
		if (isBee) {
			IBee bee = BeeManager.beeRoot.getMember(stack);
			// Only process Ignoble bees.
			if (!bee.isNatural()) {
				return true;
			}
		}
		return false;
	}
	
	private AxisAlignedBB getSearchBoundingBox() {
		return AxisAlignedBB.getBoundingBox(supertile.xCoord - RANGE, supertile.yCoord, supertile.zCoord - RANGE,
						supertile.xCoord + RANGE + 1, supertile.yCoord + 1, supertile.zCoord + RANGE + 1);
	}

	@Override
	public void readFromPacketNBT(NBTTagCompound cmp) {
		super.readFromPacketNBT(cmp);
		if (cmp.hasKey("slot")) {
			NBTTagCompound itemTag = (NBTTagCompound)cmp.getTag("slot");
			beeSlot = ItemStack.loadItemStackFromNBT(itemTag);
		}
		operationTicksRemaining = cmp.getLong("operationTicks");
	}

	@Override
	public void writeToPacketNBT(NBTTagCompound cmp) {
		super.writeToPacketNBT(cmp);
		if (beeSlot != null) {
			NBTTagCompound itemTag = new NBTTagCompound();
			beeSlot.writeToNBT(itemTag);
			cmp.setTag("slot", itemTag);	
		}
		cmp.setLong("operationTicks", operationTicksRemaining);
	}

	@Override
	public ArrayList<ItemStack> getDrops(ArrayList<ItemStack> list) {
		if (beeSlot != null) {
			list.add(beeSlot);
		}
		return super.getDrops(list);
	}

	@Override
	public int getMaxMana() {
		return MANA_PER_OPERATION;
	}

	@Override
	public boolean acceptsRedstone() {
		return true;
	}
}
