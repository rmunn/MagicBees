package magicbees.tileentity;

import cpw.mods.fml.relauncher.Side;
import magicbees.bees.AuraCharge;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class AuraCharges {
	private class ChargeInfo {
		public long startTime;
		public boolean active;
		public boolean enabled;
		
		public ChargeInfo() {
			startTime = 0;
			active = false;
			enabled = false;
		}
	}
	
	private static final String auraChargesTagName = "AuraCharges";

	private final ChargeInfo[] charges;
	
	public AuraCharges() {
		charges = new ChargeInfo[AuraCharge.values().length];
		for (AuraCharge charge : AuraCharge.values()) {
			charges[charge.ordinal()] = new ChargeInfo();
		}
	}

	public void beginConsumingCharge(AuraCharge auraChargeType, World worldObj) {
		ChargeInfo chargeInfo = charges[auraChargeType.ordinal()];
		chargeInfo.startTime = worldObj.getTotalWorldTime();
		chargeInfo.active = true;
	}

	public void endConsumingCharge(AuraCharge auraChargeType) {
		charges[auraChargeType.ordinal()].active = false;
	}

	public boolean isActive(AuraCharge auraChargeType) {
		return charges[auraChargeType.ordinal()].active;
	}

	public boolean isExpired(AuraCharge auraChargeType, World worldObj) {
		Long chargeStart = charges[auraChargeType.ordinal()].startTime;
		return chargeStart == null || chargeStart + auraChargeType.duration <= worldObj.getTotalWorldTime();
	}
	
	public void setEnabled(AuraCharge auraChargeType, boolean enabled) {
		ChargeInfo chargeInfo = charges[auraChargeType.ordinal()];
		chargeInfo.enabled = enabled;
	}
	
	public boolean isEnabled(AuraCharge auraChargeType) {
		return charges[auraChargeType.ordinal()].enabled;
	}

	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		NBTTagCompound auraChargeTags = new NBTTagCompound();
		for (AuraCharge chargeType : AuraCharge.values()) {
			ChargeInfo chargeInfo = charges[chargeType.ordinal()];
			String chargeName = chargeType.toString();
			NBTTagCompound info = new NBTTagCompound();
			info.setLong("startTime", chargeInfo.startTime);
			info.setBoolean("active", chargeInfo.active);
			info.setBoolean("enabled", chargeInfo.enabled);
			nbtTagCompound.setTag(chargeName, info);
		}
		nbtTagCompound.setTag(auraChargesTagName, auraChargeTags);
	}

	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		NBTTagCompound auraChargeTags = nbtTagCompound.getCompoundTag(auraChargesTagName);
		for (AuraCharge chargeType : AuraCharge.values()) {
			ChargeInfo chargeInfo = charges[chargeType.ordinal()];
			String chargeName = chargeType.toString();
			NBTTagCompound info = auraChargeTags.getCompoundTag(chargeName);
			chargeInfo.startTime = info.getLong("startTime");
			chargeInfo.active = info.getBoolean("active");
			chargeInfo.enabled = info.getBoolean("enabled");
		}
	}

	public int writeToFlags() {
		int flags = 0;
		for (AuraCharge auraChargeType : AuraCharge.values()) {
			ChargeInfo chargeInfo = charges[auraChargeType.ordinal()];
			if (chargeInfo.active) {
				flags |= 2 << (auraChargeType.ordinal() * 2);
			}
			if (chargeInfo.enabled) {
				flags |= 1 << (auraChargeType.ordinal() * 2);
			}
		}
		return flags;
	}

	public void readFromFlags(int flags) {
		for (AuraCharge auraChargeType : AuraCharge.values()) {
			ChargeInfo chargeInfo = charges[auraChargeType.ordinal()];
			if ((flags & 2 << (auraChargeType.ordinal() * 2)) != 0) {
				chargeInfo.active = true;
			}
			else {
				chargeInfo.active = false;
			}
			if ((flags & 1 << (auraChargeType.ordinal() * 2)) != 0) {
				chargeInfo.enabled = true;
			}
			else {
				chargeInfo.enabled = false;
			}
		}
	}

	// Called from NetworkEvents.
	public void readFromFlags(int flags, Side effectiveSide) {
		if (effectiveSide == Side.CLIENT) {
			// Read everything from the flags.
			readFromFlags(flags);
		}
		else {
			// Message from the clients can only updated enabled flags.
			for (AuraCharge auraChargeType : AuraCharge.values()) {
				ChargeInfo chargeInfo = charges[auraChargeType.ordinal()];
				if ((flags & 1 << (auraChargeType.ordinal() * 2)) != 0) {
					chargeInfo.enabled = true;
				}
				else {
					chargeInfo.enabled = false;
				}
			}
		}
	}
}
