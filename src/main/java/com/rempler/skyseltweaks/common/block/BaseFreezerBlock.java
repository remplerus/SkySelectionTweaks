package com.rempler.skyseltweaks.common.block;

import com.rempler.skyseltweaks.common.blockentity.BaseFreezerBlockEntity;
import com.rempler.skyseltweaks.common.init.SkySelBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class BaseFreezerBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
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
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            //this.openContainer(pLevel, pPos, pPlayer);
            return InteractionResult.CONSUME;
        }
    }

    protected void openContainer(Level pLevel, BlockPos pPos, Player pPlayer) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof BaseFreezerBlockEntity) {
            pPlayer.openMenu((MenuProvider)blockentity);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return super.getTicker(pLevel, pState, pBlockEntityType);
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

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
        }

        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pCurrentPos, pNeighborPos);
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
}
