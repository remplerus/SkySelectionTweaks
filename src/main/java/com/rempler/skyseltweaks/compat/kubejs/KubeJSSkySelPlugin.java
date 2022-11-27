package com.rempler.skyseltweaks.compat.kubejs;

import com.rempler.skyseltweaks.SkySelTweaks;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.RegisterRecipeHandlersEvent;
import net.minecraft.resources.ResourceLocation;

public class KubeJSSkySelPlugin extends KubeJSPlugin {
    @Override
    public void addRecipes(RegisterRecipeHandlersEvent event) {
        event.register(new ResourceLocation(SkySelTweaks.MOD_ID, "freezing"), FreezingRecipeJS::new);
    }
}
