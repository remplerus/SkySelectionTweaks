package com.rempler.skyseltweaks.common.events;

import com.rempler.skyseltweaks.SkySelTweaks;
import com.rempler.skyseltweaks.common.init.SkySelItems;
import com.rempler.skyseltweaks.common.recipe.freezing.FreezingRecipe;
import com.rempler.skyseltweaks.compat.top.TOPCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;

@Mod.EventBusSubscriber(modid = SkySelTweaks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {
    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, FreezingRecipe.Type.ID, FreezingRecipe.Type.INSTANCE);
    }

    @SubscribeEvent
    public static void registerTOP(InterModEnqueueEvent event) {
        if (ModList.get().isLoaded("theoneprobe")) {
            TOPCompat.register();
        }
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
            if (block.defaultBlockState().is(Blocks.SNOW_BLOCK)) {
                level.addFreshEntity(new ItemEntity(level, playerPos.getX(), playerPos.getY() + 1, playerPos.getZ(), Items.SNOWBALL.getDefaultInstance()));
                level.setBlockAndUpdate(event.getHitVec().getBlockPos(), Blocks.SNOW.defaultBlockState().setValue(SnowLayerBlock.LAYERS, 7));
            }
            if (block.defaultBlockState().is(Blocks.SNOW)) {
                BlockState snowBlock = level.getBlockState(event.getHitVec().getBlockPos());
                level.addFreshEntity(new ItemEntity(level, playerPos.getX(), playerPos.getY() + 1, playerPos.getZ(), Items.SNOWBALL.getDefaultInstance()));
                if (snowBlock.getValue(SnowLayerBlock.LAYERS) > 1) {
                    level.setBlockAndUpdate(event.getHitVec().getBlockPos(), Blocks.SNOW.defaultBlockState().setValue(SnowLayerBlock.LAYERS, snowBlock.getValue(SnowLayerBlock.LAYERS) - 1));
                } else {
                    level.setBlockAndUpdate(event.getHitVec().getBlockPos(), Blocks.AIR.defaultBlockState());
                }
            }
        }
    }
}
