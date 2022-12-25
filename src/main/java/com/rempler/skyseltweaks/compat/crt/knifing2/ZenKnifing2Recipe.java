package com.rempler.skyseltweaks.compat.crt.knifing2;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.rempler.skyseltweaks.common.recipe.knifing2.Knifing2Recipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.skyseltweaks.ZenKnifing2Recipe")
public class ZenKnifing2Recipe {
    private final Knifing2Recipe internal;

    private ZenKnifing2Recipe(ResourceLocation recipeId) {
        this.internal = new Knifing2Recipe(recipeId, ItemStack.EMPTY, ItemStack.EMPTY);
    }

    @ZenCodeType.Method
    public static ZenKnifing2Recipe builder(ResourceLocation recipeId) {
        return new ZenKnifing2Recipe(recipeId);
    }

    public Knifing2Recipe build() {
        return internal;
    }

    @ZenCodeType.Method
    public ZenKnifing2Recipe setOutput(IItemStack output) {
        internal.setResultItem(output.getInternal());
        return this;
    }

    @ZenCodeType.Method
    public ZenKnifing2Recipe setBlock(Block block) {
        internal.setBlock(block.asItem().getDefaultInstance());
        return this;
    }
}
