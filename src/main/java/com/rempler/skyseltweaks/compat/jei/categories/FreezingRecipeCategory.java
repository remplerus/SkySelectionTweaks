package com.rempler.skyseltweaks.compat.jei.categories;

import com.rempler.skyseltweaks.SkySelTweaks;
import com.rempler.skyseltweaks.common.init.SkySelBlocks;
import com.rempler.skyseltweaks.common.recipe.FreezingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@SuppressWarnings("removal")
public class FreezingRecipeCategory implements IRecipeCategory<FreezingRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(SkySelTweaks.MOD_ID, "freezing");
    public static final ResourceLocation TEXTURE = new ResourceLocation(SkySelTweaks.MOD_ID, "textures/gui/freezer.png");
    private final IDrawable background;
    private final IDrawable icon;
    public FreezingRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.drawableBuilder(TEXTURE, 0, 0, 51, 36).build();
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(SkySelBlocks.MINI_FREEZER.get()));
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent("category." + SkySelTweaks.MOD_ID + ".freezing");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Deprecated
    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Deprecated
    @Override
    public Class<? extends FreezingRecipe> getRecipeClass() {
        return FreezingRecipe.class;
    }

    @Override
    public RecipeType<FreezingRecipe> getRecipeType() {
        return new RecipeType<>(UID, FreezingRecipe.class);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FreezingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 51, 36).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 106, 36).addItemStack(recipe.getResultItem());
    }
}
