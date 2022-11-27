package com.rempler.skyseltweaks.common.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;

public class InfusionStoneItem extends Item {
    public InfusionStoneItem(Properties pProperties, int durability) {
        super(pProperties.durability(durability));
    }
}
