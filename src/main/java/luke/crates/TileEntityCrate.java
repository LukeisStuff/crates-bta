package luke.crates;

import com.mojang.nbt.CompoundTag;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.player.inventory.InventorySorter;

public class TileEntityCrate extends TileEntity implements IInventory {

	public ItemStack[] content = new ItemStack[9];
	public String customName;

	public TileEntityCrate() {
	}

	@Override
	public int getSizeInventory() {
		return content.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return content[i];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = content[slot];
		if(stack == null) {
			return null;
		}
		ItemStack taken = stack.splitStack(Math.min(amount, stack.stackSize));
		if(stack.stackSize <= 0) {
			content[slot] = null;
		}
		onInventoryChanged();
		return taken;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		content[i] = stack;
		onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return customName != null ? customName : "Crate";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		if(worldObj.getBlockTileEntity(x, y, z) != this) {
			return false;
		}
		return player.distanceToSqr(x + 0.5, y + 0.5, z + 0.5) <= 64.0;
	}

	@Override
	public void sortInventory() {
		InventorySorter.sortInventory(content);
	}

	@Override
	public void readFromNBT(CompoundTag nbt) {
		super.readFromNBT(nbt);
		content = InventoryHelper.readInventoryContentsFromNBT(nbt, new ItemStack[getSizeInventory()]);
		if(nbt.containsKey("CustomName")) {
			customName = nbt.getString("CustomName");
		}
	}

	@Override
	public void writeToNBT(CompoundTag nbt) {
		super.writeToNBT(nbt);
		InventoryHelper.writeInventoryContentsToNBT(nbt, content);
		if(customName != null) {
			nbt.putString("CustomName", customName);
		}
	}

}
