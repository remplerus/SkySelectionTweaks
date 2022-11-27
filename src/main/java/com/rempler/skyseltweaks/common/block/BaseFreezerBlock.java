package com.rempler.skyseltweaks.common.block;

import com.rempler.skyseltweaks.SkySelTweaks;
import com.rempler.skyseltweaks.common.block.entity.BaseFreezerBlockEntity;
import com.rempler.skyseltweaks.common.init.SkySelBEs;
import com.rempler.skyseltweaks.common.init.SkySelBlocks;
import com.rempler.skyseltweaks.compat.top.TOPCompat;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("deprecation")
public class BaseFreezerBlock extends BaseEntityBlock implements TOPCompat.ITOPInfoProvider {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final VoxelShape SHAPE = Block.box(2,0,2,14,16,14);

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo info, Player player, Level level, BlockState state, IProbeHitData data) {
        BaseFreezerBlockEntity blockEntity = (BaseFreezerBlockEntity) level.getBlockEntity(data.getPos());
        if (blockEntity == null) {
            return;
        }
        if (!blockEntity.itemHandler.getStackInSlot(0).is(ItemStack.EMPTY.getItem())) {
            ItemStack slot0 = blockEntity.itemHandler.getStackInSlot(0);
            info.text(slot0.getCount() + "x " + new TranslatableComponent(slot0.getDescriptionId()).getString());
        }
        if (!blockEntity.itemHandler.getStackInSlot(1).is(ItemStack.EMPTY.getItem())) {
            ItemStack slot1 = blockEntity.itemHandler.getStackInSlot(1);
            info.text(slot1.getCount() + "x " + new TranslatableComponent(slot1.getDescriptionId()).getString());
        }
        if (blockEntity.getProgress() != 0) {
            List<Component> infoList = blockEntity.getWailaInfo(blockEntity);
            for (Component tooltip : infoList) {
                info.text(tooltip);
            }
        }
    }

    public enum FreezerModelType implements StringRepresentable {
        MINI,
        LARGE_LOWER,
        LARGE_UPPER;

        @Override
        public String getSerializedName() {
            return name().toLowerCase();
        }
    }
    public static final EnumProperty<FreezerModelType> MODEL_TYPE = EnumProperty.create("model", FreezerModelType.class);

    public BaseFreezerBlock(Properties pProperties) {
        super(pProperties);
        this.defaultBlockState().setValue(FACING, Direction.NORTH);
    }

    @Override
    public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @Nullable BlockEntity pBlockEntity, ItemStack pTool) {
        super.playerDestroy(pLevel, pPlayer, pPos, pState, pBlockEntity, pTool);
        if (!pLevel.isClientSide && pBlockEntity instanceof BaseFreezerBlockEntity blockEntity) {
            blockEntity.dropContent();
        }
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            this.openContainer(pLevel, pPos, pPlayer);
            return InteractionResult.CONSUME;
        }
    }

    protected void openContainer(Level pLevel, BlockPos pPos, Player pPlayer) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof BaseFreezerBlockEntity) {
            NetworkHooks.openGui((ServerPlayer) pPlayer, (BaseFreezerBlockEntity)blockentity, pPos);
        } else {
            SkySelTweaks.LOGGER.warn("No Menu found for "+ blockentity.toString() +"!");
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new BaseFreezerBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, SkySelBEs.MINI_FREEZER.get(),
                BaseFreezerBlockEntity::tick);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, MODEL_TYPE);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        BlockPos posBelow = pCurrentPos.below();
        BlockState stateBelow = pLevel.getBlockState(posBelow);
        BlockPos posAbove = pCurrentPos.above();
        BlockState stateAbove = pLevel.getBlockState(posAbove);
        if (stateBelow.getBlock() == SkySelBlocks.IRON_FREEZER.get() && stateBelow.getValue(MODEL_TYPE) == FreezerModelType.MINI) {
            pLevel.setBlock(posBelow, stateBelow.setValue(MODEL_TYPE, FreezerModelType.LARGE_LOWER).setValue(FACING, pState.getValue(FACING)), 3);
            return pState.setValue(MODEL_TYPE, FreezerModelType.LARGE_UPPER);
        }
        else if (stateAbove.getBlock() == SkySelBlocks.IRON_FREEZER.get() && stateAbove.getValue(MODEL_TYPE) == FreezerModelType.MINI) {
            pLevel.setBlock(posAbove, stateAbove.setValue(MODEL_TYPE, FreezerModelType.LARGE_LOWER).setValue(FACING, pState.getValue(FACING)), 3);
            return pState.setValue(MODEL_TYPE, FreezerModelType.LARGE_UPPER);
        } else {
            return pState.setValue(MODEL_TYPE, FreezerModelType.MINI);
        }
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            BlockPos posAbove = pPos.above();
            BlockState stateAbove = pLevel.getBlockState(posAbove);
            BlockPos posBelow = pPos.below();
            BlockState stateBelow = pLevel.getBlockState(posBelow);
            if (stateAbove.getBlock() == SkySelBlocks.IRON_FREEZER.get() && stateAbove.getValue(MODEL_TYPE) == FreezerModelType.LARGE_UPPER) {
                pLevel.setBlock(posAbove, stateAbove.setValue(MODEL_TYPE, FreezerModelType.MINI), 3);
            } else if (stateBelow.getBlock() == SkySelBlocks.IRON_FREEZER.get() && stateBelow.getValue(MODEL_TYPE) == FreezerModelType.LARGE_LOWER) {
                pLevel.setBlock(posBelow, stateBelow.setValue(MODEL_TYPE, FreezerModelType.MINI), 3);
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }
}
