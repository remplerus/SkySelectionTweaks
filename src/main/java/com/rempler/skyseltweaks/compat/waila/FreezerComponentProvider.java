package com.rempler.skyseltweaks.compat.waila;

import com.rempler.skyseltweaks.common.block.entity.BaseFreezerBlockEntity;
import mcp.mobius.waila.api.BlockAccessor;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IServerDataProvider;
import mcp.mobius.waila.api.ITooltip;
import mcp.mobius.waila.api.config.IPluginConfig;
import mcp.mobius.waila.api.ui.IElementHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.awt.*;
import java.text.DecimalFormat;

public enum FreezerComponentProvider implements IComponentProvider, IServerDataProvider<BlockEntity> {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        int progress = blockAccessor.getServerData().getInt("progress");
        if (progress != 0) {
            ListTag freezerItems = (ListTag) blockAccessor.getServerData().getCompound("jadeHandler").get("Items");
            NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);

            for(int i = 0; i < freezerItems.size(); ++i) {
                inventory.set(i, ItemStack.of(freezerItems.getCompound(i)));
            }

            IElementHelper helper = iTooltip.getElementHelper();
            int total = blockAccessor.getServerData().getInt("freezeTime");
            iTooltip.add(helper.progress(1f, new TranslatableComponent("waila.freezer.progress", new DecimalFormat().format(progress*100/(total))), helper.progressStyle().color(Color.orange.getRGB()), helper.borderStyle().color(Color.BLACK.getRGB())));
        }
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean showDetails) {
        BaseFreezerBlockEntity be = (BaseFreezerBlockEntity) blockEntity;
        CompoundTag beTag = be.saveWithoutMetadata();
        compoundTag.putInt("freezeTime", be.getFreezingTime());
        compoundTag.putInt("progress", beTag.getInt("progress"));
    }
}
