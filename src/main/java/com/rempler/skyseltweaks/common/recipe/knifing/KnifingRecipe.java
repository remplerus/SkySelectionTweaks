package com.rempler.skyseltweaks.common.recipe.knifing;

import com.google.gson.JsonObject;
import com.rempler.skyseltweaks.common.utils.SkySelConstants;
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

public class KnifingRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private ItemStack result;
    private ItemStack block;

    public KnifingRecipe(ResourceLocation id, ItemStack result, ItemStack block) {
        this.id = id;
        this.result = result;
        this.block = block;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        return block.is(pContainer.getItem(0).getItem());
    }

    public ItemStack getBlock() {
        return block;
    }

    public void setBlock(ItemStack block1) {
        block = block1;
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

    public static class Type implements RecipeType<KnifingRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final ResourceLocation ID = SkySelConstants.KNIFING_RL;
    }

    public static class Serializer implements RecipeSerializer<KnifingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = SkySelConstants.KNIFING_RL;

        @Override
        public KnifingRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            ItemStack block = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "blockInput"));

            return new KnifingRecipe(id, output, block);
        }

        @Override
        public KnifingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            ItemStack block = buf.readItem();
            ItemStack output = buf.readItem();
            return new KnifingRecipe(id, output, block);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, KnifingRecipe recipe) {
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
