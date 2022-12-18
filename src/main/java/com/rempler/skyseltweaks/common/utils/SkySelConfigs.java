package com.rempler.skyseltweaks.common.utils;

import net.minecraftforge.common.ForgeConfigSpec;

public class SkySelConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public final static ForgeConfigSpec.IntValue INFUSION_STONE;
    public final static ForgeConfigSpec.IntValue RED_INFUSION_STONE;
    public final static ForgeConfigSpec.IntValue CACTUS_KNIFE;
    public final static ForgeConfigSpec.IntValue WOODEN_KNIFE;
    public final static ForgeConfigSpec.IntValue STONE_KNIFE;
    public final static ForgeConfigSpec.IntValue IRON_KNIFE;
    public final static ForgeConfigSpec.IntValue GOLD_KNIFE;
    public final static ForgeConfigSpec.IntValue DIAMOND_KNIFE;
    public final static ForgeConfigSpec.IntValue EMERALD_KNIFE;
    public final static ForgeConfigSpec.IntValue NETHERITE_KNIFE;
    public final static ForgeConfigSpec.DoubleValue HEAVY_SNOWBALL_DAMAGE;
    public final static ForgeConfigSpec.BooleanValue HURT_PLAYERS;

    static {
        BUILDER.push("Knife Configs");
            BUILDER.push("Durability");
            CACTUS_KNIFE = BUILDER.comment("Cactus Knife")
                    .defineInRange("cactus_knife", 3, 0, Integer.MAX_VALUE);
            WOODEN_KNIFE = BUILDER.comment("Wooden Knife")
                    .defineInRange("wooden_knife", 6, 0, Integer.MAX_VALUE);
            STONE_KNIFE = BUILDER.comment("Stone Knife")
                    .defineInRange("stone_knife", 12, 0, Integer.MAX_VALUE);
            IRON_KNIFE = BUILDER.comment("Iron Knife")
                    .defineInRange("iron_knife", 18, 0, Integer.MAX_VALUE);
            GOLD_KNIFE = BUILDER.comment("Golden Knife")
                    .defineInRange("gold_knife", 12, 0, Integer.MAX_VALUE);
            DIAMOND_KNIFE = BUILDER.comment("Diamond Knife")
                    .defineInRange("diamond_knife", 36, 0, Integer.MAX_VALUE);
            EMERALD_KNIFE = BUILDER.comment("Emerald Knife")
                    .defineInRange("emerald_knife", 54, 0, Integer.MAX_VALUE);
            NETHERITE_KNIFE = BUILDER.comment("Netherite Knife")
                    .defineInRange("netherite_knife", 108, 0, Integer.MAX_VALUE);
            BUILDER.pop();
        BUILDER.pop();
        BUILDER.push("Infusion Stone Configs").push("Durability");
            INFUSION_STONE = BUILDER.comment("Infusion Stone")
                    .defineInRange("infusion_stone", 96, 0, Integer.MAX_VALUE);
            RED_INFUSION_STONE = BUILDER.comment("Red Infusion Stone")
                    .defineInRange("red_infusion_stone", 96, 0, Integer.MAX_VALUE);
        BUILDER.pop().pop();
        BUILDER.push("Snowball").push("Damage");
            HEAVY_SNOWBALL_DAMAGE = BUILDER.comment("Damage of Heavy Snowballs")
                    .defineInRange("heavy_snowball_damage", 7F, 0F, Float.MAX_VALUE);
            HURT_PLAYERS = BUILDER.comment("Should heavy snowballs hurt players?")
                    .define("hurt_players", false);
        BUILDER.pop().pop();
        SPEC = BUILDER.build();
    }
}
