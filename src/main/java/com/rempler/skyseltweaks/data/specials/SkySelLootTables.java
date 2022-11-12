package com.rempler.skyseltweaks.data.specials;

import com.rempler.skyseltweaks.common.init.SkySelBlocks;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class SkySelLootTables extends BlockLoot {

    @Override
    protected void addTables() {
        this.dropSelf(SkySelBlocks.CACTUS_FRUIT_NEEDLE.get());
        this.dropSelf(SkySelBlocks.HEAVY_SNOW.get());
        this.dropSelf(SkySelBlocks.MINI_FREEZER.get());
        this.dropSelf(SkySelBlocks.IRON_FREEZER.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return SkySelBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
