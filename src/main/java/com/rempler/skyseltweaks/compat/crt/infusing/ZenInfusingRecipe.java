package com.rempler.skyseltweaks.compat.crt.infusing;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.rempler.skyseltweaks.common.recipe.infusing.InfusingRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.skyseltweaks.ZenInfusingRecipe")
public class ZenInfusingRecipe {
    private final InfusingRecipe internal;
    private NonNullList<Ingredient> blockList = NonNullList.create();

    private ZenInfusingRecipe(ResourceLocation recipeId) {
        this.internal = new InfusingRecipe(recipeId, ItemStack.EMPTY, blockList, NonNullList.create(), 1, 0);
    }

    @ZenCodeType.Method
    public static ZenInfusingRecipe builder(ResourceLocation recipeId) {
        return new ZenInfusingRecipe(recipeId);
    }

    public InfusingRecipe build() {
        return internal;
    }

    @ZenCodeType.Method
    public ZenInfusingRecipe setInput(IIngredient input) {
        internal.setIngredients(input.asVanillaIngredient());
        return this;
    }

    @ZenCodeType.Method
    public ZenInfusingRecipe setOutput(IItemStack output) {
        internal.setResultItem(output.getInternal());
        return this;
    }

    @ZenCodeType.Method
    public ZenInfusingRecipe setHealth(int health) {
        internal.setHealth(health);
        return this;
    }

    @ZenCodeType.Method
    public ZenInfusingRecipe setBlock(Ingredient block) {
        blockList.add(block);
        internal.setBlock(blockList);
        return this;
    }

    @ZenCodeType.Method
    public ZenInfusingRecipe setInputCount(int count) {
        internal.setCount(count/2);
        return this;
    }
}
