package com.rempler.skyseltweaks.common.item;

import com.rempler.skyseltweaks.SkySelTweaks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.context.UseOnContext;

public class KnifeItem extends DiggerItem {
    private final String special;
    public KnifeItem(Properties pProperties, int durability, String special) {
        super(0f, 0f, Tiers.WOOD, BlockTags.create(new ResourceLocation(SkySelTweaks.MOD_ID, "cactus")), pProperties.durability(durability));
        this.special = special;
    }

    public String getSpecial() {
        return special;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {


        return super.useOn(pContext);
    }
}
