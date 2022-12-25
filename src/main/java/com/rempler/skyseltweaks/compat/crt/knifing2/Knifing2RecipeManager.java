package com.rempler.skyseltweaks.compat.crt.knifing2;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.rempler.skyseltweaks.common.recipe.knifing2.Knifing2Recipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.skyseltweaks.Knifing2Recipe")
public class Knifing2RecipeManager implements IRecipeManager<Knifing2Recipe> {

    @ZenCodeType.Method
    public ZenKnifing2Recipe create(String recipeId) {
        recipeId = fixRecipeName(recipeId);
        ResourceLocation location = new ResourceLocation(CraftTweakerConstants.MOD_ID, recipeId);
        ZenKnifing2Recipe recipe = ZenKnifing2Recipe.builder(location);
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this, recipe.build(), ""));
        return recipe;
    }

    @Override
    public RecipeType<Knifing2Recipe> getRecipeType() {
        return Knifing2Recipe.Type.INSTANCE;
    }
}
