package magicbees.tileentity;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import magicbees.bees.AuraCharge;

public class AuraCharges {
	private static final String auraChargesTagName = "AuraCharges";

	private final Map<AuraCharge, Long> chargeStartTimes = new HashMap<AuraCharge, Long>(AuraCharge.values().length);
	private int enabledFlags = 0;

	public void beginConsumingCharge(AuraCharge auraChargeType, long worldTime) {
		chargeStartTimes.put(auraChargeType, worldTime);
	}

	public boolean isActive(AuraCharge auraChargeType) {
		return chargeStartTimes.keySet().contains(auraChargeType);
	}
	
	public boolean isEnabled(AuraCharge auraChargeType) {
		return (1 << auraChargeType.ordinal() & enabledFlags) != 0;
	}

	public boolean isExpired(AuraCharge auraChargeType, World worldObj) {
		Long chargeStart = chargeStartTimes.get(auraChargeType);
		return chargeStart == null || chargeStart + auraChargeType.duration <= worldObj.getTotalWorldTime();
	}

	public void endConsumingCharge(AuraCharge AuraChargeType) {
		chargeStartTimes.remove(AuraChargeType);
	}

	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		NBTTagCompound auraChargeTags = new NBTTagCompound();
		for (Map.Entry<AuraCharge, Long> chargeEntry : chargeStartTimes.entrySet()) {
			String chargeName = chargeEntry.getKey().toString();
			Long chargeStart = chargeEntry.getValue();
			auraChargeTags.setLong(chargeName, chargeStart);
		}
		nbtTagCompound.setTag(auraChargesTagName, auraChargeTags);
		nbtTagCompound.setInteger("enabledFlags", enabledFlags);
	}

	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		NBTTagCompound auraChargeTags = nbtTagCompound.getCompoundTag(auraChargesTagName);
		for (AuraCharge auraCharge : AuraCharge.values()) {
			String chargeName = auraCharge.toString();
			Long chargeStart = auraChargeTags.getLong(chargeName);
			if (chargeStart > 0) {
				chargeStartTimes.put(auraCharge, chargeStart);
			}
		}
		enabledFlags = nbtTagCompound.getInteger("enabledFlags");
	}

	public int writeChargesToFlags() {
		int flags = 0;
		for (AuraCharge auraChargeType : chargeStartTimes.keySet()) {
			flags |= (1 << (auraChargeType.ordinal() * 2));
		}
		return flags;
	}

	public void readChargesFromFlags(int flags) {
		chargeStartTimes.clear();
		for (AuraCharge auraCharge : AuraCharge.values()) {
			if ((flags & (1 << auraCharge.ordinal())) > 0) {
				chargeStartTimes.put(auraCharge, 0L);
			}
		}
	}
	
	public void readEnabledFromFlags(int newFlags) {
		enabledFlags = newFlags;
	}
	
	public int writeEnabledToFlags() {
		return enabledFlags;
	}

	public void setEnabled(AuraCharge chargeType, boolean on) {
		int flagPos = 1 << chargeType.ordinal();
		if (on) {
			enabledFlags |= flagPos;
		}
		else {
			enabledFlags &= ~flagPos;
		}
	}
}
