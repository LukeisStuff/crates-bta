package luke.crates;

import net.minecraft.client.gui.GuiContainer;
import net.minecraft.core.block.entity.TileEntityDispenser;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.player.inventory.ContainerDispenser;
import net.minecraft.core.player.inventory.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GuiCrate
	extends GuiContainer {
	public GuiCrate(InventoryPlayer inventoryplayer, TileEntityDispenser tileentitydispenser) {
		super(new ContainerDispenser(inventoryplayer, tileentitydispenser));
	}

	@Override
	public void drawGuiContainerForegroundLayer() {
		this.fontRenderer.drawString(I18n.getInstance().translateKey("gui.crate.label.dispenser"), 60, 6, 0x404040);
		this.fontRenderer.drawString(I18n.getInstance().translateKey("gui.crate.label.inventory"), 8, this.ySize - 96 + 2, 0x404040);
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float f) {
		int i = this.mc.renderEngine.getTexture("/assets/minecraft/textures/gui/trap.png");
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.renderEngine.bindTexture(i);
		int j = (this.width - this.xSize) / 2;
		int k = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(j, k, 0, 0, this.xSize, this.ySize);
	}
}
