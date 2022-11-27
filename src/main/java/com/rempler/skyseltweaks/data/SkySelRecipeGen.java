package com.rempler.skyseltweaks.data;

import com.rempler.skyseltweaks.common.init.SkySelBlocks;
import com.rempler.skyseltweaks.common.init.SkySelItems;
import com.rempler.skyseltweaks.common.recipe.freezing.FreezingRecipeBuilder;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class SkySelRecipeGen extends RecipeProvider implements IConditionBuilder {
    public SkySelRecipeGen(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        //icy
        FreezingRecipeBuilder.freezing(Items.SNOWBALL, SkySelItems.HEAVY_SNOWBALL.get())
                .unlockedBy("has_snowball", inventoryTrigger(ItemPredicate.Builder.item().of(Items.SNOWBALL).build()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(SkySelBlocks.HEAVY_SNOW.get())
                .requires(SkySelItems.HEAVY_SNOWBALL.get(), 4)
                .unlockedBy("has_heavy_snowball", inventoryTrigger(ItemPredicate.Builder.item().of(SkySelItems.HEAVY_SNOWBALL.get()).build()))
                .save(consumer);

        //sandy
        createKnifeRecipe(consumer, SkySelItems.WOODEN_KNIFE.get(), ItemTags.PLANKS);
        createKnifeRecipe(consumer, SkySelItems.STONE_KNIFE.get(), Tags.Items.STONE);
        createKnifeRecipe(consumer, SkySelItems.IRON_KNIFE.get(), Tags.Items.INGOTS_IRON);
        createKnifeRecipe(consumer, SkySelItems.DIAMOND_KNIFE.get(), Tags.Items.GEMS_DIAMOND);
        createKnifeRecipe(consumer, SkySelItems.EMERALD_KNIFE.get(), Tags.Items.GEMS_EMERALD);
        createKnifeRecipe(consumer, SkySelItems.NETHERITE_KNIFE.get(), Tags.Items.INGOTS_NETHERITE);
        createKnifeRecipe(consumer, SkySelItems.GOLDEN_KNIFE.get(), Tags.Items.INGOTS_GOLD);
        ShapedRecipeBuilder.shaped(SkySelItems.CACTUS_KNIFE.get())
                .pattern("i ")
                .pattern(" i")
                .define('i', SkySelItems.CACTUS_NEEDLE.get())
                .unlockedBy("has_" + SkySelItems.CACTUS_NEEDLE.get().getRegistryName().getPath(), inventoryTrigger(ItemPredicate.Builder.item().of(SkySelItems.CACTUS_NEEDLE.get()).build()))
                .save(consumer);
    }

    private void createKnifeRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, TagKey<Item> ingredient) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("i ")
                .pattern(" s")
                .define('i', ingredient)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_" + ingredient.location().getPath(), inventoryTrigger(ItemPredicate.Builder.item().of(ingredient).build()))
                .save(consumer);
    }

}
