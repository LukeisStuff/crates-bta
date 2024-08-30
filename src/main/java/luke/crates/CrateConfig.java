package luke.crates;

import net.minecraft.core.block.Block;
import turniplabs.halplibe.util.ConfigUpdater;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CrateConfig {
	public static ConfigUpdater updater = ConfigUpdater.fromProperties();
	public static final Toml properties = new Toml("Carrying Crates TOML Config");
	public static TomlConfigHandler cfg;

	public static int blockIDs = 11200;

	static {
		properties.addCategory("World of Color")
			.addEntry("cfgVersion", 5);

		properties.addCategory("Block IDs");
		properties.addEntry("Block IDs.startingID", blockIDs);

		List<Field> blockFields = Arrays.stream(CrateBlocks.class.getDeclaredFields()).filter((F)-> Block.class.isAssignableFrom(F.getType())).collect(Collectors.toList());
		for (Field blockField : blockFields) {
			properties.addEntry("Block IDs." + blockField.getName(), blockIDs++);
		}

		cfg = new TomlConfigHandler(updater, CrateMod.MOD_ID, properties);

	}
}
