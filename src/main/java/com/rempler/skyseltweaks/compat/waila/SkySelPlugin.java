package com.rempler.skyseltweaks.compat.waila;

import com.rempler.skyseltweaks.common.block.BaseFreezerBlock;
import com.rempler.skyseltweaks.common.block.entity.BaseFreezerBlockEntity;
import mcp.mobius.waila.api.IWailaClientRegistration;
import mcp.mobius.waila.api.IWailaCommonRegistration;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.TooltipPosition;
import mcp.mobius.waila.api.WailaPlugin;

@WailaPlugin
public class SkySelPlugin implements IWailaPlugin {

    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(FreezerComponentProvider.INSTANCE, BaseFreezerBlockEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerComponentProvider(FreezerComponentProvider.INSTANCE, TooltipPosition.BODY, BaseFreezerBlock.class);
    }
}
