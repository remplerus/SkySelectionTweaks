package com.rempler.skyseltweaks.compat.kubejs;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.item.ingredient.IngredientStackJS;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.util.ListJS;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class InfusingRecipeJS extends RecipeJS {
    private int health = 4;
    private int inputCount = 1;
    private ItemStack block = ItemStack.EMPTY;
    @Override
    public void create(ListJS listJS) {
        this.inputItems.add(parseIngredientItem(listJS.get(0)));
        this.outputItems.add(parseResultItem(listJS.get(4)));
        health = (int) listJS.get(3);
        block = parseResultItem(listJS.get(2)).getItemStack();
        inputCount = (int) listJS.get(1);
    }

    @Override
    public @Nullable JsonElement serializeIngredientStack(IngredientStackJS in) {
        return new JsonObject();
    }

    @Override
    public void deserialize() {
        this.inputItems.add(parseIngredientItem(this.json.get("ingredients")));
        this.outputItems.add(parseResultItem(this.json.get("output")));
        this.block = parseResultItem(this.json.get("blockInput")).getItemStack();
        if (json.has("health")) {
            health = json.get("health").getAsInt();
        }
        if (json.has("inputCount")) {
            inputCount = json.get("inputCount").getAsInt();
        }
    }

    @Override
    public void serialize() {
        if (this.serializeInputs) {
            json.add("ingredients", this.inputItems.get(0).toJson());
        }
        json.addProperty("inputCount", inputCount);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("item", block.getItem().getRegistryName().toString());
        json.add("blockInput", jsonObject);
        if (this.serializeOutputs) {
            json.add("output", this.outputItems.get(0).toJson());
        }
        json.addProperty("health", health);
    }
}
