package com.rempler.skyseltweaks.common.recipe.freezing;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.rempler.skyseltweaks.common.utils.SkySelConstants;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class FreezingRecipeBuilder implements RecipeBuilder {
    private final Item result;
    private final Ingredient ingredient;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    private final int freezeTime;
    private static final int standardTime = 20;

    public FreezingRecipeBuilder(ItemLike ingredient, ItemLike result, int time) {
        this.ingredient = Ingredient.of(ingredient);
        this.result = result.asItem();
        this.freezeTime = time;
    }

    public static FreezingRecipeBuilder freezing(ItemLike ingredient, ItemLike result) {
        return freezing(ingredient, result, standardTime*10);
    }

    public static FreezingRecipeBuilder freezing(ItemLike ingredient, ItemLike result, int time) {
        return new FreezingRecipeBuilder(ingredient, result, time);
    }

    @Override
    public FreezingRecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
        return this;
    }

    @Override
    public FreezingRecipeBuilder group(@Nullable String pGroupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        this.advancement.parent(new ResourceLocation("recipes/root"))
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId))
                .rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);

        pFinishedRecipeConsumer.accept(new FreezingRecipeBuilder.Result(pRecipeId, this.result, this.freezeTime, this.ingredient,
                this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/" +
                this.result.getItemCategory().getRecipeFolderName() + "/" + pRecipeId.getPath())));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final Ingredient ingredient;
        private final int time;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation pId, Item pResult, int freezeTime, Ingredient ingredient, Advancement.Builder pAdvancement,
                      ResourceLocation pAdvancementId) {
            this.id = pId;
            this.result = pResult;
            this.time = freezeTime;
            this.ingredient = ingredient;
            this.advancement = pAdvancement;
            this.advancementId = pAdvancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray jsonarray = new JsonArray();
            jsonarray.add(ingredient.toJson());

            pJson.add("ingredients", jsonarray);
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", this.result.getRegistryName().toString());
            pJson.addProperty("freezeTime", this.time < 20 ? standardTime : this.time);

            pJson.add("output", jsonobject);
        }

        @Override
        public ResourceLocation getId() {
            return new ResourceLocation(SkySelConstants.FREEZING_RL + "/" +
                    this.result.getRegistryName().getPath());
        }

        @Override
        public RecipeSerializer<?> getType() {
            return FreezingRecipe.Serializer.INSTANCE;
        }

        @Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
