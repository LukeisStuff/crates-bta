package luke.crates;

import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;

import net.minecraft.core.item.ItemStack;

public class InventoryHelper {

	public static ItemStack[] readInventoryContentsFromNBT(CompoundTag nbt, ItemStack[] inventory) {
		ListTag items = nbt.getList("Items");

		for(int i=0; i < items.tagCount(); i++) {
			CompoundTag item = (CompoundTag) items.tagAt(i);

			int slot = item.getByte("Slot") & 0xFF;
			if(slot < inventory.length) {
				inventory[slot] = ItemStack.readItemStackFromNbt(item);
			}
		}

		return inventory;
	}

	public static void writeInventoryContentsToNBT(CompoundTag nbt, ItemStack[] inventory) {
		ListTag items = new ListTag();

		for(int slot=0; slot < inventory.length; slot++) {
			ItemStack stack = inventory[slot];
			if(stack == null) {
				continue;
			}
			CompoundTag item = new CompoundTag();
			item.putByte("Slot", (byte) slot);
			stack.writeToNBT(item);
			items.addTag(item);
		}

		nbt.put("Items", items);
	}

}
