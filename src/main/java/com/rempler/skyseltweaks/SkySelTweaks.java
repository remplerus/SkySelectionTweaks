package com.rempler.skyseltweaks;

import com.mojang.logging.LogUtils;
import com.rempler.skyseltweaks.client.ClientSetup;
import com.rempler.skyseltweaks.common.events.EventHandler;
import com.rempler.skyseltweaks.common.init.SkySelBEs;
import com.rempler.skyseltweaks.common.init.SkySelBlocks;
import com.rempler.skyseltweaks.common.init.SkySelItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod("skyseltweaks")
public class SkySelTweaks {
    public static final String MOD_ID = "skyseltweaks";
    public static CreativeModeTab tab = new CreativeModeTab(MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return SkySelItems.CACTUS_FRUIT_NEEDLE.get().getDefaultInstance();
        }
    };
    public static TagKey<Item> KNIFES = ItemTags.create(new ResourceLocation(MOD_ID, "knifes"));
    public static TagKey<Item> INFUSION_STONES = ItemTags.create(new ResourceLocation(MOD_ID, "infusion_stones"));
    private static final IEventBus EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
    public static final Logger LOGGER = LogUtils.getLogger();

    public SkySelTweaks() {
        LOGGER.info("Starting Sky Selection Tweaks");
        SkySelItems.register(EVENT_BUS);
        SkySelBlocks.register(EVENT_BUS);
        SkySelBEs.register(EVENT_BUS);

        MinecraftForge.EVENT_BUS.addListener(EventHandler::onBlockRightClickEvent);
        EVENT_BUS.addListener(ClientSetup::setupClient);
    }
}
