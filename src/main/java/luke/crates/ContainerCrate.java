package luke.crates;

import java.util.List;

import net.minecraft.core.InventoryAction;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.Container;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;

public class ContainerCrate extends Container {

	public TileEntityCrate tileEntityCrate;

	public int crateSlotsStart;
	public int inventorySlotsStart;
	public int hotbarSlotsStart;

	public ContainerCrate(InventoryPlayer playerInventory, TileEntityCrate tileEntityCrate) {
		this.tileEntityCrate = tileEntityCrate;

		// Crate
		for(int y=0; y < 3; y++) {
			for(int x=0; x < 3; x++) {
				addSlot(new Slot(tileEntityCrate, y * 3 + x, 62 + 18 * x, 17 + 18 * y));
			}
		}

		// Player Inventory
		for(int y=0; y < 3; y++) {
			for(int x=0; x < 9; x++) {
				addSlot(new Slot(playerInventory, 9 + y * 9 + x, 8 + x * 18, 84 + y * 18));
			}
		}

		// Hotbar
		for(int x=0; x < 9; x++) {
			addSlot(new Slot(playerInventory, x, 8 + x * 18, 142));
		}

		crateSlotsStart = 0;
		inventorySlotsStart = 9;
		hotbarSlotsStart = 36;
	}

	@Override
	public List<Integer> getMoveSlots(InventoryAction action, Slot slot, int target, EntityPlayer player) {
		if(slot.id >= crateSlotsStart && slot.id < inventorySlotsStart) {
			return getSlots(crateSlotsStart, 9, false);
		}

		if(action == InventoryAction.MOVE_ALL) {
			if(slot.id >= inventorySlotsStart && slot.id < hotbarSlotsStart) {
				return getSlots(inventorySlotsStart, 27, false);
			}
			if(slot.id >= hotbarSlotsStart) {
				return getSlots(hotbarSlotsStart, 9, false);
			}
		}
		if(action == InventoryAction.MOVE_SIMILAR) {
			if(slot.id >= inventorySlotsStart) {
				return getSlots(inventorySlotsStart, 36, false);
			}
		}
		return null;
	}

	@Override
	public List<Integer> getTargetSlots(InventoryAction action, Slot slot, int target, EntityPlayer player) {
		if(slot.id >= crateSlotsStart && slot.id < inventorySlotsStart) {
			return getSlots(inventorySlotsStart, 36, false);
		}else {
			return getSlots(crateSlotsStart, 9, false);
		}
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return tileEntityCrate.canInteractWith(player);
	}

}
