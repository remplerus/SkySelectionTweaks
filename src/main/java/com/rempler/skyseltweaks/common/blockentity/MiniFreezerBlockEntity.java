package com.rempler.skyseltweaks.common.blockentity;

import com.rempler.skyseltweaks.common.container.MiniFreezerContainer;
import com.rempler.skyseltweaks.common.container.MiniFreezerMenu;
import com.rempler.skyseltweaks.common.init.SkySelBEs;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class MiniFreezerBlockEntity extends BaseFreezerBlockEntity {
    public MiniFreezerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(SkySelBEs.MINI_FREEZER.get(), pPos, pBlockState);
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return new MiniFreezerMenu(pContainerId, pInventory, this);
    }
}
