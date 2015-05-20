package magicbees.bees;

import magicbees.api.bees.AuraChargeType;

public enum AuraCharge {
	MUTATION(AuraChargeType.MUTATION, 850, 11),
	DEATH(AuraChargeType.DEATH, 400, 5),
	PRODUCTION(AuraChargeType.PRODUCTION, 300, 3),
	;

	public final AuraChargeType type;
	public final int duration;
	/** The number of ticks to count between draining vis from a nearby booster. **/
	public final int tickRate;

	AuraCharge(AuraChargeType chargeType, int duration, int tickRate) {
		this.type = chargeType;
		this.duration = duration;
		this.tickRate = tickRate;
	}
	
	public static AuraCharge fromChargeType(AuraChargeType type) {
		for (AuraCharge charge : values()) {
			if (type == charge.type) {
				return charge;
			}
		}
		
		return null;
	}
}
