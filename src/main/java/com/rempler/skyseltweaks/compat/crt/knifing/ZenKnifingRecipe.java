package com.rempler.skyseltweaks.compat.crt.knifing;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.rempler.skyseltweaks.common.recipe.knifing.KnifingRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.skyseltweaks.ZenKnifingRecipe")
public class ZenKnifingRecipe {
    private final KnifingRecipe internal;

    private ZenKnifingRecipe(ResourceLocation recipeId) {
        this.internal = new KnifingRecipe(recipeId, ItemStack.EMPTY, ItemStack.EMPTY);
    }

    @ZenCodeType.Method
    public static ZenKnifingRecipe builder(ResourceLocation recipeId) {
        return new ZenKnifingRecipe(recipeId);
    }

    public KnifingRecipe build() {
        return internal;
    }

    @ZenCodeType.Method
    public ZenKnifingRecipe setOutput(IItemStack output) {
        internal.setResultItem(output.getInternal());
        return this;
    }

    @ZenCodeType.Method
    public ZenKnifingRecipe setBlock(Block block) {
        internal.setBlock(block.asItem().getDefaultInstance());
        return this;
    }
}
