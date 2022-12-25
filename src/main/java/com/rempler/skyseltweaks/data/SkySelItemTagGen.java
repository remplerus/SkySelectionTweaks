package com.rempler.skyseltweaks.data;

import com.rempler.skyseltweaks.SkySelTweaks;
import com.rempler.skyseltweaks.common.init.SkySelItems;
import com.rempler.skyseltweaks.common.utils.SkySelConstants;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SkySelItemTagGen extends ItemTagsProvider {
    public SkySelItemTagGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, new SkySelBlockTagGen(generator, existingFileHelper), SkySelConstants.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(SkySelTweaks.KNIFES).add(SkySelItems.CACTUS_KNIFE.get(), SkySelItems.DIAMOND_KNIFE.get(), SkySelItems.GOLDEN_KNIFE.get(), SkySelItems.EMERALD_KNIFE.get(),
                SkySelItems.IRON_KNIFE.get(), SkySelItems.NETHERITE_KNIFE.get(), SkySelItems.WOODEN_KNIFE.get(), SkySelItems.STONE_KNIFE.get());
        tag(SkySelTweaks.INFUSION_STONES).add(SkySelItems.INFUSION_STONE.get(), SkySelItems.RED_INFUSION_STONE.get());
    }
}
