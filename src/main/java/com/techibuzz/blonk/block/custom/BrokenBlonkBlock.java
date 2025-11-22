package com.techibuzz.blonk.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

public class BrokenBlonkBlock extends HorizontalFacingBlock {

    public static final EnumProperty<Direction> FACING = Properties.HORIZONTAL_FACING;

    public BrokenBlonkBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing());
    }

    @Override
    public MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return createCodec(BrokenBlonkBlock::new);
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

}
