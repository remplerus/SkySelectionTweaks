package com.rempler.skyseltweaks.compat.jei;

import com.rempler.skyseltweaks.SkySelTweaks;
import com.rempler.skyseltweaks.common.init.SkySelBlocks;
import com.rempler.skyseltweaks.common.init.SkySelItems;
import com.rempler.skyseltweaks.common.item.InfusionStoneItem;
import com.rempler.skyseltweaks.common.item.KnifeItem;
import com.rempler.skyseltweaks.common.recipe.freezing.FreezingRecipe;
import com.rempler.skyseltweaks.common.recipe.infusing.InfusingRecipe;
import com.rempler.skyseltweaks.common.recipe.knifing.KnifingRecipe;
import com.rempler.skyseltweaks.compat.jei.categories.FreezingRecipeCategory;
import com.rempler.skyseltweaks.compat.jei.categories.InfusingRecipeCategory;
import com.rempler.skyseltweaks.compat.jei.categories.KnifingRecipeCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class SkySelJEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(SkySelTweaks.MOD_ID, "skyseljei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new FreezingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new InfusingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new KnifingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<FreezingRecipe> freezingRecipeList = recipeManager.getAllRecipesFor(FreezingRecipe.Type.INSTANCE);
        List<InfusingRecipe> infusingRecipeList = recipeManager.getAllRecipesFor(InfusingRecipe.Type.INSTANCE);
        List<KnifingRecipe> knifingRecipeList = recipeManager.getAllRecipesFor(KnifingRecipe.Type.INSTANCE);
        registration.addRecipes(JEIRecipeTypes.FREEZING, freezingRecipeList);
        registration.addRecipes(JEIRecipeTypes.KNIFING, knifingRecipeList);
        registration.addRecipes(JEIRecipeTypes.INFUSING, infusingRecipeList);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        for (int i = 0; i < SkySelItems.ITEMS.getEntries().size(); i++) {
            Item item = SkySelItems.ITEMS.getEntries().stream().toList().get(i).get();
            if (item instanceof KnifeItem) {
                registration.addRecipeCatalyst(item.getDefaultInstance(), JEIRecipeTypes.KNIFING);
            }
            if (item instanceof InfusionStoneItem) {
                registration.addRecipeCatalyst(item.getDefaultInstance(), JEIRecipeTypes.INFUSING);
            }
        }
        registration.addRecipeCatalyst(new ItemStack(SkySelBlocks.MINI_FREEZER.get()), JEIRecipeTypes.FREEZING);
    }
}