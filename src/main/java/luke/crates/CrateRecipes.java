package luke.crates;

import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.RecipeEntrypoint;

import static luke.crates.CrateMod.MOD_ID;

public class CrateRecipes implements RecipeEntrypoint {

	public void initializeRecipes() {

	}

	@Override
	public void onRecipesReady() {
		initializeRecipes();
	}

	@Override
	public void initNamespaces() {
		RecipeBuilder.initNameSpace(MOD_ID);
		RecipeBuilder.getRecipeNamespace(MOD_ID);
	}

}
