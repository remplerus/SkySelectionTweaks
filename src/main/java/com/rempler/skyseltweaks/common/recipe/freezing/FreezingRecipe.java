package com.rempler.skyseltweaks.common.recipe.freezing;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.rempler.skyseltweaks.common.utils.SkySelConstants;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class FreezingRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private ItemStack result;
    private final NonNullList<Ingredient> recipeItems;
    private static int freezeTime = 200;

    public FreezingRecipe(ResourceLocation id, ItemStack result, NonNullList<Ingredient> recipeItems, int freezeTime) {
        this.id = id;
        this.result = result;
        this.recipeItems = recipeItems;
        FreezingRecipe.freezeTime = freezeTime;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        return recipeItems.get(0).test(pContainer.getItem(0));
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    public void setIngredients(Ingredient ingredient) {
        recipeItems.add(ingredient);
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        return result;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return result.copy();
    }

    public void setResultItem(ItemStack resultItem) {
        this.result = resultItem;
    }

    public static int getFreezeTime() {
        return freezeTime;
    }

    public void setFreezeTime(int time) {
        freezeTime = time;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<FreezingRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final ResourceLocation ID = SkySelConstants.FREEZING_RL;
    }

    public static class Serializer implements RecipeSerializer<FreezingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = SkySelConstants.FREEZING_RL;

        @Override
        public FreezingRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            int freezeTime = GsonHelper.getAsInt(json, "freezeTime", 200);

            return new FreezingRecipe(id, output, inputs, freezeTime);
        }

        @Override
        public FreezingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            inputs.replaceAll(ignored -> Ingredient.fromNetwork(buf));

            ItemStack output = buf.readItem();
            int freezeTime = buf.readVarInt();
            return new FreezingRecipe(id, output, inputs, freezeTime);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, FreezingRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
        }

        @Override
        public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
            return INSTANCE;
        }

        @Nullable
        @Override
        public ResourceLocation getRegistryName() {
            return ID;
        }

        @Override
        public Class<RecipeSerializer<?>> getRegistryType() {
            return Serializer.castClass(RecipeSerializer.class);
        }

        @SuppressWarnings("unchecked") // Need this wrapper, because generics
        private static <G> Class<G> castClass(Class<?> clazz) {
            return (Class<G>)clazz;
        }
    }
}
