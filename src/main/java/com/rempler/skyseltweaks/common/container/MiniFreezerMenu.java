package com.rempler.skyseltweaks.common.container;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.Nullable;

public class MiniFreezerMenu extends AbstractContainerMenu {
    public MiniFreezerMenu(int pContainerId, Inventory pInventory, Container container) {
        super((MenuType<?>) container, pContainerId);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return false;
    }
}
