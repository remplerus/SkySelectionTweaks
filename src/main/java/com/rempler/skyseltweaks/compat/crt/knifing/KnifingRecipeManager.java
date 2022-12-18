package com.rempler.skyseltweaks.compat.crt.knifing;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import com.rempler.skyseltweaks.common.recipe.freezing.FreezingRecipe;
import com.rempler.skyseltweaks.common.recipe.knifing.KnifingRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.skyseltweaks.KnifingRecipe")
public class KnifingRecipeManager implements IRecipeManager<KnifingRecipe> {

    @ZenCodeType.Method
    public ZenKnifingRecipe create(String recipeId) {
        recipeId = fixRecipeName(recipeId);
        ResourceLocation location = new ResourceLocation(CraftTweakerConstants.MOD_ID, recipeId);
        ZenKnifingRecipe recipe = ZenKnifingRecipe.builder(location);
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this, recipe.build(), ""));
        return recipe;
    }

    @Override
    public RecipeType<KnifingRecipe> getRecipeType() {
        return KnifingRecipe.Type.INSTANCE;
    }
}
