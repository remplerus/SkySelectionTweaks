package com.rempler.skyseltweaks.common.events;

import com.rempler.skyseltweaks.SkySelTweaks;
import com.rempler.skyseltweaks.common.init.SkySelBlocks;
import com.rempler.skyseltweaks.common.init.SkySelItems;
import com.rempler.skyseltweaks.common.recipe.FreezingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = SkySelTweaks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {
    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        //RecipeType.register(FreezingRecipe.Type.ID);
        Registry.register(Registry.RECIPE_TYPE, FreezingRecipe.Type.ID, FreezingRecipe.Type.INSTANCE);
    }


    public static void onBlockRightClickEvent(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getPlayer();
        ItemStack mainHand = player.getMainHandItem();
        ItemStack offHand = player.getOffhandItem();
        if (!event.getWorld().isClientSide && event.getHand() == InteractionHand.MAIN_HAND && player.isShiftKeyDown() && mainHand.isEmpty() && offHand.isEmpty()) {
            Level level = event.getWorld();
            BlockPos playerPos = player.blockPosition();
            Block block = level.getBlockState(event.getHitVec().getBlockPos()).getBlock();
            if (block.defaultBlockState().is(Blocks.CACTUS)) {
                level.addFreshEntity(new ItemEntity(level, playerPos.getX(), playerPos.getY() + 1, playerPos.getZ(), SkySelItems.CACTUS_NEEDLE.get().getDefaultInstance()));
                player.hurt(DamageSource.CACTUS, 2f);
            }
            if (mainHand.isEmpty() && block.defaultBlockState().is(Blocks.SNOW)) {
            }
        }
        //if (mainHand.is(SkySelTweaks.INFUSION_STONES) && offHand.is(SkySelItems.CACTUS_FRUIT.get())
        //        && block.defaultBlockState().is(SkySelBlocks.CACTUS_FRUIT_NEEDLE.get())
        //        && offHand.getCount() >= 8) {
        //    level.setBlock(event.getHitVec().getBlockPos(), Blocks.AIR.defaultBlockState(), 3);
        //    mainHand.getItem().getDefaultInstance().hurtAndBreak(1, player, (player1 ->
        //            player1.broadcastBreakEvent(event.getHand() == InteractionHand.MAIN_HAND ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND)));
        //    offHand.getItem().getDefaultInstance().shrink(8);
        //    level.addFreshEntity(new ItemEntity(level, playerPos.getX(), playerPos.getY() + 1, playerPos.getZ(), Items.DIRT.getDefaultInstance()));
        //}

    }
}
