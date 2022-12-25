package com.rempler.skyseltweaks;

import com.mojang.logging.LogUtils;
import com.rempler.skyseltweaks.common.events.EventHandler;
import com.rempler.skyseltweaks.common.init.SkySelBEs;
import com.rempler.skyseltweaks.common.init.SkySelBlocks;
import com.rempler.skyseltweaks.common.init.SkySelItems;
import com.rempler.skyseltweaks.common.init.SkySelMenus;
import com.rempler.skyseltweaks.common.init.SkySelRecipes;
import com.rempler.skyseltweaks.common.utils.SkySelConfigs;
import com.rempler.skyseltweaks.common.utils.SkySelConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

@Mod("skyseltweaks")
public class SkySelTweaks {
    private static final String MOD_ID = SkySelConstants.MODID;
    public static CreativeModeTab tab = new CreativeModeTab(MOD_ID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return SkySelItems.CACTUS_FRUIT_NEEDLE.get().getDefaultInstance();
        }
    };
    public static TagKey<Item> KNIFES = ItemTags.create(new ResourceLocation(MOD_ID, "knifes"));
    public static TagKey<Item> INFUSION_STONES = ItemTags.create(new ResourceLocation(MOD_ID, "infusion_stones"));
    public static TagKey<Block> BLOCK_TO_KNIFE = BlockTags.create(new ResourceLocation(MOD_ID, "knifes"));
    private static final IEventBus EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
    public static final Logger LOGGER = LogUtils.getLogger();

    public SkySelTweaks() {
        LOGGER.info("Starting Sky Selection Tweaks");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SkySelConfigs.SPEC, MOD_ID + "-common.toml");
        SkySelItems.register(EVENT_BUS);
        SkySelBlocks.register(EVENT_BUS);
        SkySelBEs.register(EVENT_BUS);
        SkySelMenus.register(EVENT_BUS);
        SkySelRecipes.register(EVENT_BUS);

        MinecraftForge.EVENT_BUS.addListener(EventHandler::onBlockRightClickEvent);
    }
}
