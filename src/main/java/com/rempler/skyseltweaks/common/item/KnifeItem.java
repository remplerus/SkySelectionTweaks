package com.rempler.skyseltweaks.common.item;

import com.rempler.skyseltweaks.SkySelTweaks;
import com.rempler.skyseltweaks.common.recipe.knifing.KnifingRecipe;
import com.rempler.skyseltweaks.common.recipe.knifing2.Knifing2Recipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Random;

public class KnifeItem extends DiggerItem {
    private final String special;
    public KnifeItem(Properties pProperties, int durability, String special) {
        super(-1f, -2f, Tiers.WOOD, SkySelTweaks.BLOCK_TO_KNIFE, pProperties.durability(durability));
        this.special = special;
    }
    public KnifeItem(Properties pProperties, int durability) {
        this(pProperties, durability, "");
    }

    private String getSpecial() {
        return special;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level pLevel = pContext.getLevel();
        List<KnifingRecipe> recipeList = pLevel.getRecipeManager().getAllRecipesFor(KnifingRecipe.Type.INSTANCE);
        Player pPlayer = pContext.getPlayer();
        if (pPlayer == null) {
            return InteractionResult.FAIL;
        }
        if (!pLevel.isClientSide) {
            for (KnifingRecipe recipe : recipeList) {
                if (pPlayer.getUsedItemHand().equals(InteractionHand.MAIN_HAND)) {
                    if (recipe != null && recipe.getResultItem() != ItemStack.EMPTY) {
                        ItemStack item = recipe.getResultItem();
                        if (recipe.getBlock().is(pLevel.getBlockState(pContext.getClickedPos()).getBlock().asItem())) {
                            if (getSpecial().equals("golden")) {
                                int random = new Random().nextInt(0, 4);
                                for (int i = 0; i < 10; i++) {
                                    if (random <= 2) {
                                        dropItems(pLevel, pPlayer, item);
                                    }
                                }
                            }
                            dropItems(pLevel, pPlayer, item);
                            damageItem(pPlayer);
                        }
                    }
                }
            }
            return InteractionResult.SUCCESS;
        } else {
            pPlayer.swing(InteractionHand.MAIN_HAND);
        }

        return super.useOn(pContext);
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (pEntityLiving instanceof Player player) {
            List<Knifing2Recipe> recipeList = pLevel.getRecipeManager().getAllRecipesFor(Knifing2Recipe.Type.INSTANCE);
            for (Knifing2Recipe recipe : recipeList) {
                if (player.getUsedItemHand().equals(InteractionHand.MAIN_HAND)) {
                    if (recipe != null && recipe.getResultItem() != ItemStack.EMPTY) {
                        ItemStack item = recipe.getResultItem();
                        if (recipe.getBlock().is(pState.getBlock().asItem())) {
                            if (!pLevel.isClientSide) {
                                if (getSpecial().equals("golden")) {
                                    int random = new Random().nextInt(0, 4);
                                    for (int i = 0; i < 10; i++) {
                                        if (random <= 2) {
                                            dropItems(pLevel, player, item);
                                        }
                                    }
                                }
                                dropItems(pLevel, player, item);
                                damageItem(player);
                                pLevel.setBlockAndUpdate(pPos, Blocks.AIR.defaultBlockState());
                            }
                        }
                    }
                }
                if (pLevel.isClientSide) {
                    player.swing(InteractionHand.MAIN_HAND);
                }
                return true;
            }
        }
        return mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
    }

    private void dropItems(Level level, Player player, ItemStack stack) {
        level.addFreshEntity(new ItemEntity(level, player.getX(), player.getY()+1, player.getZ(), stack));
    }

    private void damageItem(Player player) {
        player.getMainHandItem().hurtAndBreak(1, player, player1 -> player1.broadcastBreakEvent(player.getUsedItemHand()));
    }
}
