package magicbees.main.utils.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import magicbees.bees.AuraCharge;
import magicbees.main.utils.ChunkCoords;
import magicbees.main.utils.LogHelper;
import magicbees.main.utils.net.NetworkEventHandler.EventType;
import magicbees.tileentity.TileEntityMagicApiary;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;

public class EventAuraEnabledUpdate extends EventCoords {
	
	private int chargeOrdinal;
	private boolean enabled;

	public EventAuraEnabledUpdate(ChunkCoords position, AuraCharge charge, boolean status) {
		super(EventType.AURA_ENABLED_UPDATE, position);
		chargeOrdinal = charge.ordinal();
		enabled = status;
	}

	public EventAuraEnabledUpdate(DataInputStream data) {
		super(EventType.AURA_ENABLED_UPDATE, data);
		readDataFromInputStream(data);
	}

	@Override
	protected void writeDataToOutputStream(DataOutputStream byteStream) {
		super.writeDataToOutputStream(byteStream);

		try {
			byteStream.writeInt(chargeOrdinal);
			byteStream.writeBoolean(enabled);
		}
		catch (IOException e) {
			LogHelper.error("Could not write EventAuraEnabledUpdate data to stream.");
			e.printStackTrace();
		}
	}

	@Override
	protected void readDataFromInputStream(DataInputStream byteStream) {
		super.readDataFromInputStream(byteStream);

		try {
			chargeOrdinal = byteStream.readInt();
			enabled = byteStream.readBoolean();
		}
		catch (IOException e) {
			LogHelper.error("Could not read EventAuraEnabledUpdate data from stream.");
			e.printStackTrace();
		}
	}

	@Override
	public void process(EntityPlayerMP player) {
		TileEntity tile = FMLClientHandler.instance().getClient().theWorld.getTileEntity(getCoords().x, getCoords().y, getCoords().z);

		if (tile != null && tile instanceof TileEntityMagicApiary) {
			TileEntityMagicApiary apiary = (TileEntityMagicApiary) tile;
			AuraCharge charge = AuraCharge.values()[chargeOrdinal];
			apiary.setBoostEnabled(charge, enabled);
			LogHelper.warn(String.format("Updating tile at: (%d, %d, %d): charge %s set to " + enabled, getCoords().x, getCoords().y, getCoords().z, charge.toString()));
		
			/*if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
				NetworkEventHandler.getInstance().sendAuraEnabledUpdate(apiary, charge, enabled);
			}*/
		}
	}
}
