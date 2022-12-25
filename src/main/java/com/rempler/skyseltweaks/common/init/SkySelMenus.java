package com.rempler.skyseltweaks.common.init;

import com.rempler.skyseltweaks.common.block.container.BaseFreezerMenu;
import com.rempler.skyseltweaks.common.utils.SkySelConstants;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SkySelMenus {
    private static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, SkySelConstants.MODID);
    public static final RegistryObject<MenuType<BaseFreezerMenu>> MINI_FREEZER =
            registerMenuType(BaseFreezerMenu::new, "mini_freezer");
    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> container, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(container));
    }
    public static void register(IEventBus bus) {
        MENUS.register(bus);
    }
}
