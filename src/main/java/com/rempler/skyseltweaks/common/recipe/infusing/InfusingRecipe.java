package com.rempler.skyseltweaks.common.recipe.infusing;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.rempler.skyseltweaks.SkySelTweaks;
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

public class InfusingRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private ItemStack result;
    private final NonNullList<Ingredient> recipeItems;
    private static int health = 4;

    public InfusingRecipe(ResourceLocation id, ItemStack result, NonNullList<Ingredient> recipeItems, int health) {
        this.id = id;
        this.result = result;
        this.recipeItems = recipeItems;
        InfusingRecipe.health = health;
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

    public void setResultItem(ItemStack stack) {
        result = stack;
    }

    public static int getHealth() {
        return health;
    }

    public void setHealth(int health1) {
        health = health1;
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

    public static class Type implements RecipeType<InfusingRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final ResourceLocation ID = new ResourceLocation(SkySelTweaks.MOD_ID, "infusing");
    }

    public static class Serializer implements RecipeSerializer<InfusingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(SkySelTweaks.MOD_ID,"infusing");

        @Override
        public InfusingRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            int health = GsonHelper.getAsInt(json, "health", 4);

            return new InfusingRecipe(id, output, inputs, health);
        }

        @Override
        public InfusingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            inputs.replaceAll(ignored -> Ingredient.fromNetwork(buf));

            ItemStack output = buf.readItem();
            int health = buf.readVarInt();
            return new InfusingRecipe(id, output, inputs, health);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, InfusingRecipe recipe) {
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
