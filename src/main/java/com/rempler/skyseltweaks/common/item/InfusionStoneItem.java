package com.rempler.skyseltweaks.common.item;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class InfusionStoneItem extends Item {
    public InfusionStoneItem(Properties pProperties, int durability) {
        super(pProperties.durability(durability));
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {


        return super.useOn(pContext);
    }
}
