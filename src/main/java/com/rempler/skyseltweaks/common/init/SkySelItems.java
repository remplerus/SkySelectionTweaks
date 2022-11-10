package com.rempler.skyseltweaks.common.init;

import com.rempler.skyseltweaks.SkySelTweaks;
import com.rempler.skyseltweaks.common.item.InfusionStoneItem;
import com.rempler.skyseltweaks.common.item.KnifeItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class SkySelItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SkySelTweaks.MOD_ID);
    private static final Item.Properties properties = new Item.Properties().tab(SkySelTweaks.tab);
    public static final RegistryObject<Item> CACTUS_FRUIT_NEEDLE = ITEMS.register("cactus_fruit_needle", () -> new BlockItem(SkySelBlocks.CACTUS_FRUIT_NEEDLE.get(), properties));
    public static final RegistryObject<Item> MINI_FREEZER = ITEMS.register("mini_freezer", () -> new BlockItem(SkySelBlocks.MINI_FREEZER.get(), properties));
    public static final RegistryObject<Item> IRON_FREEZER = ITEMS.register("iron_freezer", () -> new BlockItem(SkySelBlocks.IRON_FREEZER.get(), properties));
    public static final RegistryObject<Item> HEAVY_SNOW = ITEMS.register("heavy_snow", () -> new BlockItem(SkySelBlocks.HEAVY_SNOW.get(), properties));
    public static final RegistryObject<Item> HEAVY_SNOWBALL = ITEMS.register("heavy_snowball", () -> new Item(properties));
    public static final RegistryObject<Item> CACTUS_FRUIT = ITEMS.register("cactus_fruit", () -> new Item(new Item.Properties().tab(SkySelTweaks.tab).food(
            new FoodProperties.Builder().nutrition(4).saturationMod(0.6f).alwaysEat().fast().build())));
    public static final RegistryObject<Item> CACTUS_NEEDLE = ITEMS.register("cactus_needle", () -> new Item(properties));
    public static final RegistryObject<Item> CACTUS_KNIFE = ITEMS.register("cactus_knife", () -> new KnifeItem(properties, 8, ""));
    public static final RegistryObject<Item> WOODEN_KNIFE = ITEMS.register("wooden_knife", () -> new KnifeItem(properties,24, ""));
    public static final RegistryObject<Item> STONE_KNIFE = ITEMS.register("stone_knife", () -> new KnifeItem(properties, 72, ""));
    public static final RegistryObject<Item> IRON_KNIFE = ITEMS.register("iron_knife", () -> new KnifeItem(properties, 216, ""));
    public static final RegistryObject<Item> GOLDEN_KNIFE = ITEMS.register("golden_knife", () -> new KnifeItem(properties, 96, "special"));
    public static final RegistryObject<Item> DIAMOND_KNIFE = ITEMS.register("diamond_knife", () -> new KnifeItem(properties, 512, ""));
    public static final RegistryObject<Item> EMERALD_KNIFE = ITEMS.register("emerald_knife", () -> new KnifeItem(properties, 1024, ""));
    public static final RegistryObject<Item> NETHERITE_KNIFE = ITEMS.register("netherite_knife", () -> new KnifeItem(properties, 8192, ""));
    public static final RegistryObject<Item> INFUSION_STONE = ITEMS.register("infusion_stone", () -> new InfusionStoneItem(properties, 96));
    public static final RegistryObject<Item> RED_INFUSION_STONE = ITEMS.register("red_infusion_stone", () -> new InfusionStoneItem(properties, 96));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
