package com.rempler.skyseltweaks.compat.kubejs;

import com.rempler.skyseltweaks.common.utils.SkySelConstants;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.RegisterRecipeHandlersEvent;

public class KubeJSSkySelPlugin extends KubeJSPlugin {
    @Override
    public void addRecipes(RegisterRecipeHandlersEvent event) {
        event.register(SkySelConstants.FREEZING_RL, FreezingRecipeJS::new);
        event.register(SkySelConstants.INFUSING_RL, InfusingRecipeJS::new);
        event.register(SkySelConstants.KNIFING_RL, KnifingRecipeJS::new);
        event.register(SkySelConstants.KNIFING2_RL, KnifingRecipeJS::new);
    }
}
