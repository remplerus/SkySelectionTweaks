package com.rempler.skyseltweaks.common.init;

import com.rempler.skyseltweaks.SkySelTweaks;
import com.rempler.skyseltweaks.common.blockentity.IronFreezerBlockEntity;
import com.rempler.skyseltweaks.common.blockentity.MiniFreezerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SkySelBEs {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, SkySelTweaks.MOD_ID);
    public static final RegistryObject<BlockEntityType<MiniFreezerBlockEntity>> MINI_FREEZER = BLOCK_ENTITIES.register("mini_freezer", () ->
            BlockEntityType.Builder.of(MiniFreezerBlockEntity::new, SkySelBlocks.MINI_FREEZER.get()).build(null));
    public static final RegistryObject<BlockEntityType<IronFreezerBlockEntity>> IRON_FREEZER = BLOCK_ENTITIES.register("iron_freezer", () ->
            BlockEntityType.Builder.of(IronFreezerBlockEntity::new, SkySelBlocks.IRON_FREEZER.get()).build(null));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
