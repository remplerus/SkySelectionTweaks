package com.rempler.skyseltweaks.client;

import com.rempler.skyseltweaks.common.block.container.BaseFreezerScreen;
import com.rempler.skyseltweaks.common.init.SkySelBlocks;
import com.rempler.skyseltweaks.common.init.SkySelMenus;
import com.rempler.skyseltweaks.common.utils.SkySelConstants;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = SkySelConstants.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void setupClient(final FMLClientSetupEvent ignored) {
        ItemBlockRenderTypes.setRenderLayer(SkySelBlocks.CACTUS_FRUIT_NEEDLE.get(), RenderType.cutout());
        MenuScreens.register(SkySelMenus.MINI_FREEZER.get(), BaseFreezerScreen::new);
    }
}
