package com.rempler.skyseltweaks.data;

import com.rempler.skyseltweaks.SkySelTweaks;
import com.rempler.skyseltweaks.common.init.SkySelBlocks;
import com.rempler.skyseltweaks.common.init.SkySelItems;
import com.rempler.skyseltweaks.compat.jei.categories.FreezingRecipeCategory;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.text.WordUtils;

public class SkySelLangGen extends LanguageProvider {
    private final String myModID;
    public SkySelLangGen(DataGenerator gen, String locale) {
        super(gen, SkySelTweaks.MOD_ID, locale);
        this.myModID = SkySelTweaks.MOD_ID;
    }

    @Override
    protected void addTranslations() {
        for (int i = 0; i < SkySelBlocks.BLOCKS.getEntries().stream().toList().size(); i++) {
            Block block = SkySelBlocks.BLOCKS.getEntries().stream().toList().get(i).get();
            add(block, captiallize(block));
        }
        for (int i = 0; i < SkySelItems.ITEMS.getEntries().stream().toList().size(); i++) {
            Item item = SkySelItems.ITEMS.getEntries().stream().toList().get(i).get();
            String string = item.getDescriptionId();
            if (string.contains("block.")) {
                string = string.replace("block.", "item.");
            }
            add(string, captiallize(item));
        }
        add("itemGroup." + myModID, "Sky Selection Tweaks");
        add("category." + myModID + ".freezing", "Freezing");
        add("category." + myModID +".infusing", "Infusing");
        add("category." + myModID +".knifing", "Knifing");
        add("container."+ myModID +".mini_freezer", "Mini Freezer");
        add("waila.freezer.progress", "Freezing: %d%%");
    }

    private String captiallize(Object object) {
        Item item = Items.AIR;
        if (object instanceof Block) {
            item = ((Block) object).asItem();
        } else if (object instanceof Item) {
            item = (Item) object;
        }
        if (!item.getDefaultInstance().is(Items.AIR)) {
            return WordUtils.capitalizeFully(item.getRegistryName().getPath().replace("_", " "));
        }
        return "";
    }
}
