package com.rempler.skyseltweaks.common.init;

import com.rempler.skyseltweaks.SkySelTweaks;
import com.rempler.skyseltweaks.common.block.BaseFreezerBlock;
import com.rempler.skyseltweaks.common.block.IronFreezerBlock;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SkySelBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SkySelTweaks.MOD_ID);
    public static final RegistryObject<FlowerBlock> CACTUS_FRUIT_NEEDLE = BLOCKS.register("cactus_fruit_needle", () ->
            new FlowerBlock(MobEffects.MOVEMENT_SPEED, 0, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<BaseFreezerBlock> MINI_FREEZER = BLOCKS.register("mini_freezer", () ->
            new BaseFreezerBlock(BlockBehaviour.Properties.of(Material.SNOW).strength(2.0f, 5.0f).sound(SoundType.SNOW).dynamicShape()));
    public static final RegistryObject<IronFreezerBlock> IRON_FREEZER = BLOCKS.register("iron_freezer", () ->
            new IronFreezerBlock(BlockBehaviour.Properties.of(Material.METAL).strength(3.5f, 10.0f).sound(SoundType.METAL).dynamicShape()));
    public static final RegistryObject<Block> HEAVY_SNOW = BLOCKS.register("heavy_snow", () ->
            new Block(BlockBehaviour.Properties.of(Material.SNOW).strength(2.0f).sound(SoundType.SNOW)));

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
