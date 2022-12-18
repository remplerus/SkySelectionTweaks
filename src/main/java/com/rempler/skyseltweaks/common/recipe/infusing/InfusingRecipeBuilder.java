package com.rempler.skyseltweaks.common.recipe.infusing;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.rempler.skyseltweaks.SkySelTweaks;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class InfusingRecipeBuilder implements RecipeBuilder {
    private final Item result;
    private final Ingredient ingredient;
    private final Item block;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    private final int health;
    private static final int standardHealth = 2;
    public InfusingRecipeBuilder(ItemLike ingredient, int itemCount, ItemLike block, ItemLike result, int health) {
        this.ingredient = Ingredient.of(ingredient);
        this.count = itemCount;
        this.block = block.asItem();
        this.result = result.asItem();
        this.health = health;
    }

    public static InfusingRecipeBuilder infusing(ItemLike ingredient, int count, ItemLike block, ItemLike result) {
        return infusing(ingredient, count, block, result, standardHealth);
    }

    public static InfusingRecipeBuilder infusing(ItemLike ingredient, ItemLike block, ItemLike result) {
        return infusing(ingredient, 1, block, result);
    }

    public static InfusingRecipeBuilder infusing(ItemLike ingredient, int count, ItemLike block, ItemLike result, int health) {
        return new InfusingRecipeBuilder(ingredient, count, block, result, health);
    }

    @Override
    public InfusingRecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
        return this;
    }

    @Override
    public InfusingRecipeBuilder group(@Nullable String pGroupName) {
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

        pFinishedRecipeConsumer.accept(new InfusingRecipeBuilder.Result(pRecipeId, this.ingredient, this.count, this.block, this.result, this.health,
                this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/" +
                this.result.getItemCategory().getRecipeFolderName() + "/" + pRecipeId.getPath())));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final int itemCount;
        private final Item block;
        private final Item result;
        private final Ingredient ingredient;
        private final int health;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation pId, Ingredient ingredient, int count, Item block, Item pResult, int standardHealth, Advancement.Builder pAdvancement,
                      ResourceLocation pAdvancementId) {
            this.id = pId;
            this.itemCount = count;
            this.block = block;
            this.result = pResult;
            this.health = standardHealth;
            this.ingredient = ingredient;
            this.advancement = pAdvancement;
            this.advancementId = pAdvancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray jsonarray = new JsonArray();
            jsonarray.add(this.ingredient.toJson());
            pJson.add("ingredients", jsonarray);
            pJson.addProperty("inputCount", this.itemCount);
            JsonObject jsonObject1 = new JsonObject();
            jsonObject1.addProperty("item", this.block.getRegistryName().toString());
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", this.result.getRegistryName().toString());
            pJson.add("blockInput", jsonObject1);
            pJson.addProperty("health", this.health);
            pJson.add("output", jsonobject);
        }

        @Override
        public ResourceLocation getId() {
            return new ResourceLocation(SkySelTweaks.MOD_ID, "infusing/" + id.getPath());
        }

        @Override
        public RecipeSerializer<?> getType() {
            return InfusingRecipe.Serializer.INSTANCE;
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
