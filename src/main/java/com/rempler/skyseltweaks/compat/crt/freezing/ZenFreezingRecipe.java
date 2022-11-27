package com.rempler.skyseltweaks.compat.crt.freezing;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.rempler.skyseltweaks.common.recipe.freezing.FreezingRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.skyseltweaks.ZenFreezingRecipe")
public class ZenFreezingRecipe {
    private final FreezingRecipe internal;

    private ZenFreezingRecipe(ResourceLocation recipeId) {
        this.internal = new FreezingRecipe(recipeId, ItemStack.EMPTY, NonNullList.create(), 0);
    }

    @ZenCodeType.Method
    public static ZenFreezingRecipe builder(ResourceLocation recipeId) {
        return new ZenFreezingRecipe(recipeId);
    }

    public FreezingRecipe build() {
        return internal;
    }

    @ZenCodeType.Method
    public ZenFreezingRecipe setInput(IIngredient input) {
        internal.setIngredients(input.asVanillaIngredient());
        return this;
    }

    @ZenCodeType.Method
    public ZenFreezingRecipe setOutput(IItemStack output) {
        internal.setResultItem(output.getInternal());
        return this;
    }

    @ZenCodeType.Method
    public ZenFreezingRecipe setFreezeTime(int time) {
        internal.setFreezeTime(time);
        return this;
    }
}
