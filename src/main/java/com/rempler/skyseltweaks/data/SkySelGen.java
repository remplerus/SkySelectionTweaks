package com.rempler.skyseltweaks.data;

import com.rempler.skyseltweaks.SkySelTweaks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = SkySelTweaks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SkySelGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeClient()) {
            generator.addProvider(new SkySelLangGen(generator, "en_us"));
        }
        if (event.includeServer()) {
            generator.addProvider(new SkySelItemTagGen(generator, event.getExistingFileHelper()));
            generator.addProvider(new SkySelBlockTagGen(generator, event.getExistingFileHelper()));
            generator.addProvider(new SkySelRecipeGen(generator));
            generator.addProvider(new SkySelLootTablesGen(generator));
        }
    }
}
