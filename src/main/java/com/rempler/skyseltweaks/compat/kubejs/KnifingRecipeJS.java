package com.rempler.skyseltweaks.compat.kubejs;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.item.ingredient.IngredientStackJS;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.util.ListJS;
import org.jetbrains.annotations.Nullable;

public class KnifingRecipeJS extends RecipeJS {
    @Override
    public void create(ListJS listJS) {
        this.inputItems.add(parseResultItem(listJS.get(0)));
        this.outputItems.add(parseResultItem(listJS.get(1)));
    }

    @Override
    public @Nullable JsonElement serializeIngredientStack(IngredientStackJS in) {
        return new JsonObject();
    }

    @Override
    public void deserialize() {
        this.inputItems.add(parseResultItem(this.json.get("blockInput")));
        this.outputItems.add(parseResultItem(this.json.get("output")));
    }

    @Override
    public void serialize() {
        if (this.serializeInputs) {
            json.add("blockInput", this.inputItems.get(0).toJson());
        }
        if (this.serializeOutputs) {
            json.add("output", this.outputItems.get(0).toJson());
        }
    }
}
