package com.rempler.skyseltweaks.data;

import com.rempler.skyseltweaks.SkySelTweaks;
import com.rempler.skyseltweaks.common.init.SkySelBlocks;
import com.rempler.skyseltweaks.common.init.SkySelItems;
import com.rempler.skyseltweaks.common.recipe.freezing.FreezingRecipe;
import com.rempler.skyseltweaks.common.recipe.freezing.FreezingRecipeBuilder;
import com.rempler.skyseltweaks.common.recipe.infusing.InfusingRecipeBuilder;
import com.rempler.skyseltweaks.common.recipe.knifing.KnifingRecipeBuilder;
import com.rempler.skyseltweaks.common.recipe.knifing2.Knifing2RecipeBuilder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.awt.*;
import java.util.function.Consumer;

public class SkySelRecipeGen extends RecipeProvider implements IConditionBuilder {
    public SkySelRecipeGen(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        //icy
        FreezingRecipeBuilder.freezing(Items.SNOWBALL, SkySelItems.HEAVY_SNOWBALL.get())
                .unlockedBy(fastString(Items.SNOWBALL.getRegistryName()), fastTrigger(Items.SNOWBALL))
                .save(consumer);
        FreezingRecipeBuilder.freezing(SkySelItems.HEAVY_SNOW.get(), Items.DIRT, 25000)
                .unlockedBy(fastString(SkySelItems.HEAVY_SNOW.get().getRegistryName()), fastTrigger(SkySelItems.HEAVY_SNOW.get()))
                .save(consumer);
        FreezingRecipeBuilder.freezing(SkySelItems.BONEY_GUNPOWDER.get(), Items.SPRUCE_SAPLING, 12500)
                .unlockedBy(fastString(SkySelItems.HEAVY_SNOW.get().getRegistryName()), fastTrigger(SkySelItems.HEAVY_SNOW.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(SkySelItems.BONEY_GUNPOWDER.get())
                .requires(Items.BONE, 2)
                .requires(Items.GUNPOWDER, 2)
                .unlockedBy(fastString(Items.BONE.getRegistryName()), fastTrigger(Items.BONE))
                .unlockedBy(fastString(Items.GUNPOWDER.getRegistryName()), fastTrigger(Items.GUNPOWDER))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(SkySelBlocks.HEAVY_SNOW.get())
                .requires(SkySelItems.HEAVY_SNOWBALL.get(), 4)
                .unlockedBy(fastString(SkySelItems.HEAVY_SNOWBALL.get().getRegistryName()), fastTrigger(SkySelItems.HEAVY_SNOWBALL.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(SkySelBlocks.MINI_FREEZER.get())
                .pattern("s")
                .pattern("s")
                .define('s', Items.SNOW_BLOCK)
                .unlockedBy(fastString(Items.SNOW_BLOCK.getRegistryName()), fastTrigger(Items.SNOW_BLOCK))
                .save(consumer);

        //infusing
        InfusingRecipeBuilder.infusing(SkySelItems.CACTUS_NEEDLE.get(), 8, SkySelBlocks.CACTUS_FRUIT_NEEDLE.get(), Items.OAK_SAPLING)
                .unlockedBy(fastString(SkySelItems.CACTUS_FRUIT_NEEDLE.get().getRegistryName()), fastTrigger(SkySelItems.CACTUS_FRUIT_NEEDLE.get()))
                .unlockedBy(fastString(SkySelItems.CACTUS_NEEDLE.get().getRegistryName()), fastTrigger(SkySelItems.CACTUS_NEEDLE.get()))
                .save(consumer);
        InfusingRecipeBuilder.infusing(SkySelItems.CACTUS_FRUIT.get(), 4, Tags.Items.SAND, Items.DIRT)
                .unlockedBy(fastString(Tags.Items.SAND.location()), fastTrigger(Tags.Items.SAND))
                .unlockedBy(fastString(Items.DIRT.getRegistryName()), fastTrigger(Items.DIRT))
                .save(consumer);

        //knifing
        KnifingRecipeBuilder.knifing(SkySelItems.CACTUS_NEEDLE.get(), Items.CACTUS)
                .unlockedBy(fastString(SkySelTweaks.KNIFES.location()), fastTrigger(SkySelTweaks.KNIFES))
                .save(consumer);

        //knifing
        Knifing2RecipeBuilder.knifing(SkySelItems.CACTUS_FRUIT.get(), Items.CACTUS)
                .unlockedBy(fastString(SkySelTweaks.KNIFES.location()), fastTrigger(SkySelTweaks.KNIFES))
                .save(consumer);

        //knifes
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
                .unlockedBy(fastString(SkySelItems.CACTUS_NEEDLE.get().getRegistryName()), fastTrigger(SkySelItems.CACTUS_NEEDLE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(SkySelItems.CACTUS_FRUIT_NEEDLE.get())
                .pattern("f")
                .pattern("n")
                .define('f', SkySelItems.CACTUS_FRUIT.get())
                .define('n', SkySelItems.CACTUS_NEEDLE.get())
                .unlockedBy(fastString(SkySelItems.CACTUS_NEEDLE.get().getRegistryName()), fastTrigger(SkySelItems.CACTUS_NEEDLE.get()))
                .unlockedBy(fastString(SkySelItems.CACTUS_FRUIT.get().getRegistryName()), fastTrigger(SkySelItems.CACTUS_FRUIT.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(SkySelItems.INFUSION_STONE.get())
                .pattern("s ")
                .pattern(" s")
                .define('s', Items.SANDSTONE)
                .unlockedBy(fastString(Items.SANDSTONE.getRegistryName()), fastTrigger(Items.SANDSTONE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(SkySelItems.RED_INFUSION_STONE.get())
                .pattern("s ")
                .pattern(" s")
                .define('s', Items.RED_SANDSTONE)
                .unlockedBy(fastString(Items.RED_SANDSTONE.getRegistryName()), fastTrigger(Items.RED_SANDSTONE))
                .save(consumer);
    }

    private void createKnifeRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, TagKey<Item> ingredient) {
        ShapedRecipeBuilder.shaped(result)
                .pattern("i ")
                .pattern(" s")
                .define('i', ingredient)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy(fastString(ingredient.location()), fastTrigger(ingredient))
                .save(consumer);
    }

    private String fastString(ResourceLocation location) {
        return "has_" + location.getPath();
    }

    private InventoryChangeTrigger.TriggerInstance fastTrigger(TagKey<Item> o) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(o).build());
    }
    private InventoryChangeTrigger.TriggerInstance fastTrigger(ItemLike o) {
        return inventoryTrigger(ItemPredicate.Builder.item().of(o).build());
    }
}
