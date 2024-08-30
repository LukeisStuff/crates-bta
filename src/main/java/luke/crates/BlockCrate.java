package luke.crates;

import com.mojang.nbt.CompoundTag;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class BlockCrate extends BlockTileEntity {

	public BlockCrate(String key, int id, Material material) {
		super(key, id, material);
	}

	public TileEntity getNewBlockEntity() {
		return new TileEntityCrate();
	}


	public boolean onBlockRightClicked(World world, int x, int y, int z, EntityPlayer player, Side side, double xHit, double yHit) {
		if(!world.isClientSide) {
			TileEntityCrate tileEntity = (TileEntityCrate) world.getBlockTileEntity(x, y, z);
			player.displayGUIChest(tileEntity);
		}
		return true;
	}

	public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
		ItemStack stack = new ItemStack(this);
		TileEntityCrate crate = (TileEntityCrate) tileEntity;
		InventoryHelper.writeInventoryContentsToNBT(stack.getData(), crate.content);
		if(crate.customName != null) {
			stack.setCustomName(crate.customName);
		}
		ItemStack[] array = new ItemStack[1];
		array[0] = stack;
		return array;
	}

	public void onBlockPlaced(World world, int x, int y, int z, Side side, EntityLiving entity, double sideHeight) {
		CompoundTag data = getDefaultStack().getData();
		TileEntityCrate crate = (TileEntityCrate) world.getBlockTileEntity(x, y, z);
		if(data.containsKey("Items")) {
			crate.content = InventoryHelper.readInventoryContentsFromNBT(data, new ItemStack[crate.getSizeInventory()]);
		}
		if(getDefaultStack().hasCustomName()) {
			crate.customName = getDefaultStack().getCustomName();
		}
	}

}
