package magicbees.client.gui;

import magicbees.bees.AuraCharge;
import magicbees.main.CommonProxy;
import magicbees.main.utils.LogHelper;
import magicbees.main.utils.net.NetworkEventHandler;
import magicbees.tileentity.TileEntityMagicApiary;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMagicApiary extends GuiContainer
{
	
    public static final ResourceLocation BACKGROUND_LOCATION = new ResourceLocation(CommonProxy.DOMAIN, CommonProxy.GUI_TEXTURE + "apiary.png");

    private static final int WIDTH = 176;
    private static final int HEIGHT = 190;
    
    private static final int LIFEBAR_WIDTH = 2;
    private static final int LIFEBAR_HEIGHT = 46;
    
    private static final int LIFEBAR_DEST_X = 21;
    private static final int LIFEBAR_DEST_Y = 37;
    
    private static final int LIFEBAR_SRC_Y = 0;
    private static final int LIFEBAR_SRC_STAGE1_X = 184;
    private static final int LIFEBAR_SRC_STAGE2_X = 182;
    private static final int LIFEBAR_SRC_STAGE3_X = 180;
    private static final int LIFEBAR_SRC_STAGE4_X = 178;
    private static final int LIFEBAR_SRC_STAGE5_X = 176;
    
    private static final int WORKBOOST_WIDTH = 16;
    private static final int WORKBOOST_HEIGHT = 12;
    private static final int WORKBOOST_DEST_X = 37;
    private static final int WORKBOOST_DEST_Y = 18;
    private static final int WORKBOOST_SRC_X = 176;
    private static final int WORKBOOST_SRC_Y = 46;
    private static final int WORKBOOST_TOGGLE_X = 42;
    
    private static final int DEATHBOOST_WIDTH = 11;
    private static final int DEATHBOOST_HEIGHT = 13;
    private static final int DEATHBOOST_DEST_X = 25;
    private static final int DEATHBOOST_DEST_Y = 17;
    private static final int DEATHBOOST_SRC_X = 176;
    private static final int DEATHBOOST_SRC_Y = 58;
    private static final int DEATHBOOST_TOGGLE_X = 28;
    
    private static final int MUTATIONBOOST_WIDTH = 9;
    private static final int MUTATIONBOOST_HEIGHT = 13;
    private static final int MUTATIONBOOST_DEST_X = 15;
    private static final int MUTATIONBOOST_DEST_Y = 17;
    private static final int MUTATIONBOOST_SRC_X = 176;
    private static final int MUTATIONBOOST_SRC_Y = 71;
    private static final int MUTATIONBOOST_TOGGLE_X = 17;
    
    private static final int TOGGLE_SWITCH_SRC_X = 186;
    private static final int TOGGLE_SWITCH_SRC_Y = 0;
    private static final int TOGGLE_SWITCH_DEST_Y = 6;
    private static final int TOGGLE_SWTICH_WIDTH = 5;
    private static final int TOGGLE_SWITCH_HEIGHT = 5;
    
    private static final int SLIDER_TROUGH_WIDTH = 5;
    private static final int SLIDER_TROUGH_HEIGHT = 12;
    private static final int SLIDER_TROUGH_Y = 5;
    private static final int[] SLIDER_TROUGH_X = {MUTATIONBOOST_TOGGLE_X, DEATHBOOST_TOGGLE_X, WORKBOOST_TOGGLE_X};
    private static final int SLIDER_TROUGH_SWITCH_OFF_Y_OFFSET = -1;
    private static final int SLIDER_TROUGH_SWITCH_ON_Y_OFFSET = 5;
    

    public GuiMagicApiary(InventoryPlayer inventoryPlayer, TileEntityMagicApiary thaumicApiary) {
        super(new ContainerMagicApiary(inventoryPlayer, thaumicApiary));

        this.xSize = WIDTH;
        this.ySize = HEIGHT;
    }

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		fontRendererObj.drawString("Inventory", 9, 99, 0);
	}

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {
        GL11.glColor4f(1, 1, 1, 1);
        this.mc.getTextureManager().bindTexture(BACKGROUND_LOCATION);

        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        
        TileEntityMagicApiary apiary = getApiary();
        
        drawLifebar(apiary);

        if (apiary.isProductionBoosted()) {
        	drawBoostIcon(WORKBOOST_DEST_X, WORKBOOST_DEST_Y,
        					WORKBOOST_SRC_X, WORKBOOST_SRC_Y,
        					WORKBOOST_WIDTH, WORKBOOST_HEIGHT);
        }
        if (apiary.isDeathRateBoosted()) {
        	drawBoostIcon(DEATHBOOST_DEST_X, DEATHBOOST_DEST_Y,
        					DEATHBOOST_SRC_X, DEATHBOOST_SRC_Y,
        					DEATHBOOST_WIDTH, DEATHBOOST_HEIGHT);
        }
        if (apiary.isMutationBoosted()) {
        	drawBoostIcon(MUTATIONBOOST_DEST_X, MUTATIONBOOST_DEST_Y,
        					MUTATIONBOOST_SRC_X, MUTATIONBOOST_SRC_Y,
        					MUTATIONBOOST_WIDTH, MUTATIONBOOST_HEIGHT);
        }
        drawBoostToggle(WORKBOOST_TOGGLE_X, apiary.isProductionBoostEnabled());
        drawBoostToggle(DEATHBOOST_TOGGLE_X, apiary.isDeathBoostEnabled());
        drawBoostToggle(MUTATIONBOOST_TOGGLE_X, apiary.isMutationBoostEnabled());
    }

	protected TileEntityMagicApiary getApiary() {
    	return ((ContainerMagicApiary)this.inventorySlots).apiary;
    }

	private void drawLifebar(TileEntityMagicApiary apiary) {
		int value = LIFEBAR_HEIGHT - apiary.getHealthScaled(LIFEBAR_HEIGHT);
        int lifebarSrc = getLifebarSrc(apiary);
        this.drawTexturedModalRect(this.guiLeft + LIFEBAR_DEST_X, this.guiTop + value + LIFEBAR_DEST_Y,
        		lifebarSrc, LIFEBAR_SRC_Y, LIFEBAR_WIDTH, LIFEBAR_HEIGHT - value);
	}
    
    private int getLifebarSrc(TileEntityMagicApiary apiary) {
    	int value = apiary.getHealthScaled(5);
    	if (value >= 5) {
    		return LIFEBAR_SRC_STAGE1_X;
    	}
    	else if (value >= 4) {
    		return LIFEBAR_SRC_STAGE2_X;
    	}
    	else if (value >= 3) {
    		return LIFEBAR_SRC_STAGE3_X;
    	}
    	else if (value >= 2) {
    		return LIFEBAR_SRC_STAGE4_X;
    	}
    	else {
    		return LIFEBAR_SRC_STAGE5_X;
    	}
    }

	private void drawBoostIcon(int destX, int destY, int srcX, int srcY, int width, int height) {
        	this.drawTexturedModalRect(this.guiLeft + destX, this.guiTop + destY, srcX, srcY, width, height);
	}
	
	private void drawBoostToggle(int destX, boolean on) {
		int destY = TOGGLE_SWITCH_DEST_Y;
		if (on) {
			destY += SLIDER_TROUGH_SWITCH_ON_Y_OFFSET;
		}
		else {
			destY += SLIDER_TROUGH_SWITCH_OFF_Y_OFFSET;
		}
		this.drawTexturedModalRect(this.guiLeft + destX, this.guiTop + destY,
				TOGGLE_SWITCH_SRC_X, TOGGLE_SWITCH_SRC_Y, TOGGLE_SWTICH_WIDTH, TOGGLE_SWITCH_HEIGHT);
	}

	@Override
	protected void mouseClicked(int x, int y, int buttonId) {
		int clickX = x - this.guiLeft;
		int clickY = y - this.guiTop;
		int index = 0;
		TileEntityMagicApiary apiary = getApiary();
		AuraCharge charge = null;
		for (int xOffset : SLIDER_TROUGH_X) {
			if (!(clickX < xOffset || xOffset + SLIDER_TROUGH_WIDTH < clickX || clickY < SLIDER_TROUGH_Y || SLIDER_TROUGH_Y + SLIDER_TROUGH_HEIGHT < clickY)) {
				switch (index) {
				case 0:
					charge = AuraCharge.MUTATION;
					break;
				case 1:
					charge = AuraCharge.DEATH;
					break;
				case 2:
					charge = AuraCharge.PRODUCTION;
					break;
				}
			}
			++index;
		}
		
		if (charge != null) {
			mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1f));
			boolean chargeStatus = !apiary.isBoostEnabled(charge);
			LogHelper.warn("Sending network update: " + charge.toString() + " set to " + chargeStatus);
			NetworkEventHandler.getInstance().sendAuraEnabledUpdate(apiary, charge, chargeStatus);
			return;
		}
		
		super.mouseClicked(x, y, buttonId);
	}
}
