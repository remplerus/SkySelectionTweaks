package com.rempler.skyseltweaks.common.blockentity;

import com.rempler.skyseltweaks.common.init.SkySelBEs;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class IronFreezerBlockEntity extends BaseFreezerBlockEntity {
    public IronFreezerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(SkySelBEs.IRON_FREEZER.get(), pPos, pBlockState);
        setContainerSize(3);
    }

    @Override
    protected Component getDefaultName() {
        return null;
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return null;
    }
}
