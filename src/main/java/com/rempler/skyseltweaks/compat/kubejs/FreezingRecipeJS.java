package com.rempler.skyseltweaks.compat.kubejs;

import com.google.gson.JsonElement;
import dev.latvian.mods.kubejs.item.ingredient.IngredientStackJS;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.util.ListJS;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.Nullable;

public class FreezingRecipeJS extends RecipeJS {
    private int freezeTime = 200;
    @Override
    public void create(ListJS listJS) {
        this.inputItems.add(parseIngredientItem(listJS.get(0)));
        this.outputItems.add(parseResultItem(listJS.get(1)));
        if (listJS.size() > 2) {
            freezeTime = (int) listJS.get(2);
        }
    }

    @Override
    public @Nullable JsonElement serializeIngredientStack(IngredientStackJS in) {
        return new JsonObject();
    }

    @Override
    public void deserialize() {
        this.inputItems.add(parseIngredientItem(this.json.get("ingredients")));
        this.outputItems.add(parseResultItem(this.json.get("output")));
        if (json.has("freezeTime")) {
            freezeTime = json.get("freezeTime").getAsInt();
        }
    }

    @Override
    public void serialize() {
        if (this.serializeInputs) {
            json.add("ingredients", this.inputItems.get(0).toJson());
        }
        if (this.serializeOutputs) {
            json.add("output", this.outputItems.get(0).toJson());
        }
        json.addProperty("freezeTime", freezeTime);
    }
}
