package com.rempler.skyseltweaks.compat.crt.freezing;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.rempler.skyseltweaks.common.recipe.freezing.FreezingRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.skyseltweaks.FreezingRecipes")
public class FreezingRecipeManager implements IRecipeManager<FreezingRecipe> {

    @ZenCodeType.Method
    public ZenFreezingRecipe create(String recipeId) {
        recipeId = fixRecipeName(recipeId);
        ResourceLocation location = new ResourceLocation(CraftTweakerConstants.MOD_ID, recipeId);
        ZenFreezingRecipe recipe = ZenFreezingRecipe.builder(location);
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this, recipe.build(), ""));
        return recipe;
    }

    @Override
    public RecipeType<FreezingRecipe> getRecipeType() {
        return FreezingRecipe.Type.INSTANCE;
    }
}
