package com.rempler.skyseltweaks.compat.jei.categories;

import com.rempler.skyseltweaks.SkySelTweaks;
import com.rempler.skyseltweaks.common.init.SkySelItems;
import com.rempler.skyseltweaks.common.recipe.infusing.InfusingRecipe;
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

public class InfusingRecipeCategory implements IRecipeCategory<InfusingRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(SkySelTweaks.MOD_ID, "infusing");
    public static final ResourceLocation TEXTURE = new ResourceLocation(SkySelTweaks.MOD_ID, "textures/gui/freezer.png");
    private final IDrawable background;
    private final IDrawable icon;
    public InfusingRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.drawableBuilder(TEXTURE, 0, 0, 51, 36).build();
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, SkySelItems.INFUSION_STONE.get().getDefaultInstance());
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent("category." + SkySelTweaks.MOD_ID + ".infusing");
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
    public Class<? extends InfusingRecipe> getRecipeClass() {
        return InfusingRecipe.class;
    }

    @Override
    public RecipeType<InfusingRecipe> getRecipeType() {
        return new RecipeType<>(UID, InfusingRecipe.class);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, InfusingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 51, 36).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 106, 36).addItemStack(recipe.getBlock());
        builder.addSlot(RecipeIngredientRole.CATALYST, 51, 52).addItemStack(SkySelItems.INFUSION_STONE.get().getDefaultInstance());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 106, 52).addItemStack(recipe.getResultItem());
    }
}