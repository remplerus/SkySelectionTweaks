package com.rempler.skyseltweaks.common.init;

import com.rempler.skyseltweaks.common.block.entity.BaseFreezerBlockEntity;
import com.rempler.skyseltweaks.common.utils.SkySelConstants;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SkySelBEs {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, SkySelConstants.MODID);
    public static final RegistryObject<BlockEntityType<BaseFreezerBlockEntity>> MINI_FREEZER = BLOCK_ENTITIES.register("mini_freezer", () ->
            BlockEntityType.Builder.of(BaseFreezerBlockEntity::new, SkySelBlocks.MINI_FREEZER.get()).build(null));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
