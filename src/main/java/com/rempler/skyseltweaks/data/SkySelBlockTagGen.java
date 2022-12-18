package com.rempler.skyseltweaks.data;

import com.rempler.skyseltweaks.SkySelTweaks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class SkySelBlockTagGen extends BlockTagsProvider {
    public SkySelBlockTagGen(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, SkySelTweaks.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(SkySelTweaks.BLOCK_TO_KNIFE).add(Blocks.CACTUS);
    }
}
