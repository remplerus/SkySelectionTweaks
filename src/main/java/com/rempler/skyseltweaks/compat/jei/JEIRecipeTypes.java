package com.rempler.skyseltweaks.compat.jei;

import com.rempler.skyseltweaks.common.recipe.freezing.FreezingRecipe;
import com.rempler.skyseltweaks.common.recipe.infusing.InfusingRecipe;
import com.rempler.skyseltweaks.common.recipe.knifing.KnifingRecipe;
import com.rempler.skyseltweaks.common.recipe.knifing2.Knifing2Recipe;
import com.rempler.skyseltweaks.compat.jei.categories.FreezingRecipeCategory;
import com.rempler.skyseltweaks.compat.jei.categories.InfusingRecipeCategory;
import com.rempler.skyseltweaks.compat.jei.categories.Knifing2RecipeCategory;
import com.rempler.skyseltweaks.compat.jei.categories.KnifingRecipeCategory;
import mezz.jei.api.recipe.RecipeType;

public class JEIRecipeTypes {
    private JEIRecipeTypes() {}

    public static final RecipeType<KnifingRecipe> KNIFING =
            new RecipeType<>(KnifingRecipeCategory.UID, KnifingRecipe.class);
    public static final RecipeType<Knifing2Recipe> KNIFING2 =
            new RecipeType<>(Knifing2RecipeCategory.UID, Knifing2Recipe.class);
    public static final RecipeType<FreezingRecipe> FREEZING =
            new RecipeType<>(FreezingRecipeCategory.UID, FreezingRecipe.class);
    public static final RecipeType<InfusingRecipe> INFUSING =
            new RecipeType<>(InfusingRecipeCategory.UID, InfusingRecipe.class);
}
