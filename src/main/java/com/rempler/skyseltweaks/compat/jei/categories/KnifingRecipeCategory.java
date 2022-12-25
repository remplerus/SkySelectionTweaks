package com.rempler.skyseltweaks.compat.jei.categories;

import com.rempler.skyseltweaks.common.init.SkySelItems;
import com.rempler.skyseltweaks.common.recipe.knifing.KnifingRecipe;
import com.rempler.skyseltweaks.common.utils.SkySelConstants;
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
public class KnifingRecipeCategory implements IRecipeCategory<KnifingRecipe> {
    public static final ResourceLocation UID = SkySelConstants.KNIFING_RL;
    public static final ResourceLocation TEXTURE = new ResourceLocation(SkySelConstants.MODID, "textures/gui/freezer.png");
    private final IDrawable background;
    private final IDrawable icon;
    public KnifingRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.drawableBuilder(TEXTURE, 0, 0, 51, 36).build();
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(SkySelItems.CACTUS_KNIFE.get()));
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent("category." + SkySelConstants.MODID + ".knifing");
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
    public Class<? extends KnifingRecipe> getRecipeClass() {
        return KnifingRecipe.class;
    }

    @Override
    public RecipeType<KnifingRecipe> getRecipeType() {
        return new RecipeType<>(UID, KnifingRecipe.class);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, KnifingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 51, 36).addItemStack(recipe.getBlock());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 106, 36).addItemStack(recipe.getResultItem());
    }
}
