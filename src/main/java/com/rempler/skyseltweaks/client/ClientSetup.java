package com.rempler.skyseltweaks.client;

import com.rempler.skyseltweaks.common.init.SkySelBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {
    public static void setupClient(final FMLClientSetupEvent ignored) {
        ItemBlockRenderTypes.setRenderLayer(SkySelBlocks.CACTUS_FRUIT_NEEDLE.get(), RenderType.cutout());
    }
}