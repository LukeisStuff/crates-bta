package luke.crates;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class CrateMod implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "crate";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        LOGGER.info("Carrying Crates initialized.");
    }

	@Override
	public void beforeGameStart() {
		new CrateBlocks().initializeBlocks();
	}

	@Override
	public void afterGameStart() {
		new CrateBlocks().initializeBlockDetails();
	}

	@Override
	public void onRecipesReady() {

	}

	@Override
	public void initNamespaces() {

	}
}
