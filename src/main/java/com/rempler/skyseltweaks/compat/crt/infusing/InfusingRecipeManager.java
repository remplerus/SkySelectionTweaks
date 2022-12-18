package com.rempler.skyseltweaks.compat.crt.infusing;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.rempler.skyseltweaks.common.recipe.freezing.FreezingRecipe;
import com.rempler.skyseltweaks.common.recipe.infusing.InfusingRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.skyseltweaks.InfusingRecipe")
public class InfusingRecipeManager implements IRecipeManager<InfusingRecipe> {

    @ZenCodeType.Method
    public ZenInfusingRecipe create(String recipeId) {
        recipeId = fixRecipeName(recipeId);
        ResourceLocation location = new ResourceLocation(CraftTweakerConstants.MOD_ID, recipeId);
        ZenInfusingRecipe recipe = ZenInfusingRecipe.builder(location);
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this, recipe.build(), ""));
        return recipe;
    }

    @Override
    public RecipeType<InfusingRecipe> getRecipeType() {
        return InfusingRecipe.Type.INSTANCE;
    }
}
