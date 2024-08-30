package luke.crates;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSound;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.CreativeHelper;

import static luke.crates.CrateMod.MOD_ID;

public class CrateBlocks {
	public int blockID(String blockName) {
		return CrateConfig.cfg.getInt("Block IDs." + blockName);
	}

	public static Block crate;
	public static Block cratePainted;

	public void initializeBlockDetails() {

		for (int color = 1; color < 17; color++) {
			CreativeHelper.setParent(cratePainted, color - 1, crate, 0);
		}

	}

	public void initializeBlocks() {

		BlockBuilder crates = new BlockBuilder(MOD_ID)
			.setBlockSound(new BlockSound("step.wood", "step.wood", 1.0f, 1.0f))
			.setHardness(2.5F)
			.setResistance(5.0f)
			.setImmovable()
			.setTags(BlockTags.MINEABLE_BY_AXE, BlockTags.FENCES_CONNECT);

		crate = crates
			.setTextures("crates:block/crate")
			.build(new BlockCrate("crate", blockID("crate"), Material.wood));

		cratePainted = crates
			.setTextures("crates:block/crate")
			.build(new BlockCrate("crate.painted", blockID("cratePainted"), Material.wood));


	}


}
