package luke.crates.mixin;

import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = EntityPlayer.class, remap = false)
public class EntityPlayerMixin extends EntityLiving {


	public EntityPlayerMixin(World world) {
		super(world);
	}

	@Unique
	public void displayGUICrate(IInventory iinventory) {
	}

}
