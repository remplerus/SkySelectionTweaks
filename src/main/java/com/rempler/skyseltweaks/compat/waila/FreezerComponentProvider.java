package com.rempler.skyseltweaks.compat.waila;

import com.rempler.skyseltweaks.common.block.entity.BaseFreezerBlockEntity;
import mcp.mobius.waila.api.BlockAccessor;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IServerDataProvider;
import mcp.mobius.waila.api.ITooltip;
import mcp.mobius.waila.api.config.IPluginConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public enum FreezerComponentProvider implements IComponentProvider, IServerDataProvider<BlockEntity> {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        BaseFreezerBlockEntity blockEntity = (BaseFreezerBlockEntity) blockAccessor.getBlockEntity();
        iTooltip.addAll(blockEntity.getWailaInfo(blockEntity));
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean showDetails) {
        BaseFreezerBlockEntity be = (BaseFreezerBlockEntity) blockEntity;
        compoundTag.putInt("freezeTime", be.getFreezingTime());
    }
}
