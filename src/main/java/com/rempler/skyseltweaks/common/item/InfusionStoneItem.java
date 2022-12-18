package com.rempler.skyseltweaks.common.item;

import com.rempler.skyseltweaks.SkySelTweaks;
import com.rempler.skyseltweaks.common.recipe.infusing.InfusingRecipe;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class InfusionStoneItem extends Item {
    public InfusionStoneItem(Properties pProperties, int durability) {
        super(pProperties.durability(durability));
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Player player = pContext.getPlayer();
        Level level = pContext.getLevel();
        Block block = level.getBlockState(pContext.getClickedPos()).getBlock();
        ItemStack mainHand = player.getMainHandItem();
        ItemStack offHand = player.getOffhandItem();

        List<InfusingRecipe> recipeList = level.getRecipeManager().getAllRecipesFor(InfusingRecipe.Type.INSTANCE);

        if (pContext.getHand().equals(InteractionHand.OFF_HAND)) {
            return InteractionResult.PASS;
        }

        for (InfusingRecipe recipe : recipeList) {
            if (pContext.getHand().equals(InteractionHand.MAIN_HAND)) {
                if (recipe != null && recipe.getResultItem() != ItemStack.EMPTY) {
                    if (player.getMaxHealth() < recipe.getHealth() && level.isClientSide) {
                        player.displayClientMessage(new TranslatableComponent(SkySelTweaks.MOD_ID + "." + InfusingRecipe.Type.ID.getPath() + ".max_health_too_low"), false);
                        return InteractionResult.FAIL;
                    }
                    if (offHand.getCount() < recipe.getCount()) {
                        return InteractionResult.FAIL;
                    }
                    if (recipe.getBlock().is(block.asItem()) && offHand.is(recipe.getIngredients().get(0).getItems()[0].getItem())) {
                        if (player.getHealth() >= recipe.getHealth() && !level.isClientSide && offHand != ItemStack.EMPTY) {
                            offHand.shrink(recipe.getCount());
                            level.addFreshEntity(new ItemEntity(level, player.getX(), player.getY(), player.getZ(), recipe.getResultItem()));
                            mainHand.hurtAndBreak(1, player, player1 -> player1.broadcastBreakEvent(pContext.getHand()));
                            player.hurt(DamageSource.MAGIC, recipe.getHealth());
                            level.setBlockAndUpdate(pContext.getClickedPos(), Blocks.AIR.defaultBlockState());
                        } else if (level.isClientSide && player.getHealth() < recipe.getHealth()) {
                            player.displayClientMessage(new TranslatableComponent(SkySelTweaks.MOD_ID + "." + InfusingRecipe.Type.ID.getPath() + ".health_too_low"), false);
                            return InteractionResult.FAIL;
                        }
                    }
                }
            }
        }
        if (level.isClientSide) {
            player.swing(InteractionHand.MAIN_HAND);
        }
        return InteractionResult.PASS;
    }
}
