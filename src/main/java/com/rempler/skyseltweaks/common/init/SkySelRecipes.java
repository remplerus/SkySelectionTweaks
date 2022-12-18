package com.rempler.skyseltweaks.common.init;

import com.rempler.skyseltweaks.SkySelTweaks;
import com.rempler.skyseltweaks.common.recipe.freezing.FreezingRecipe;
import com.rempler.skyseltweaks.common.recipe.infusing.InfusingRecipe;
import com.rempler.skyseltweaks.common.recipe.knifing.KnifingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SkySelRecipes {
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SkySelTweaks.MOD_ID);

    public static final RegistryObject<RecipeSerializer<FreezingRecipe>> FREEZING_SERIALIZER =
            RECIPE_SERIALIZERS.register("freezing", () -> FreezingRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<InfusingRecipe>> INFUSING_SERIALIZER =
            RECIPE_SERIALIZERS.register("infusing", () -> InfusingRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<KnifingRecipe>> KNIFING_SERIALIZER =
            RECIPE_SERIALIZERS.register("knifing", () -> KnifingRecipe.Serializer.INSTANCE);

    public static void register(IEventBus bus) {
        RECIPE_SERIALIZERS.register(bus);
    }
}
