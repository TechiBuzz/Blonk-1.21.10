package com.techibuzz.blonk.block.custom;

import com.mojang.serialization.MapCodec;
import com.techibuzz.blonk.block.ModBlocks;
import com.techibuzz.blonk.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BrokenBlonkBlock extends HorizontalDirectionalBlock {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

    public BrokenBlonkBlock(Properties settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection());
    }

    @Override
    protected @NotNull InteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide()) {
            if (stack.is(ModItems.SCRAP)) {
                player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
                stack.consume(1, player);

                world.setBlockAndUpdate(pos, ModBlocks.BLONK.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, state.getValue(FACING)));
                world.playSound(null, pos, SoundEvents.WET_SPONGE_DRIES, SoundSource.BLOCKS, 1.0f, 1.0f);

                if (world instanceof ServerLevel serverWorld) {
                    RandomSource random = world.getRandom();
                    serverWorld.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, (double)pos.getX() + (double)0.5F + random.nextDouble() / (double)3.0F * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + random.nextDouble() + random.nextDouble(), (double)pos.getZ() + (double)0.5F + random.nextDouble() / (double)3.0F * (double)(random.nextBoolean() ? 1 : -1), 10,0.001F, 0.2d, 0.001F, 0.02F);
                    serverWorld.sendParticles(ParticleTypes.HAPPY_VILLAGER, (double)pos.getX() + (double)0.5F + random.nextDouble() / (double)3.0F * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + random.nextDouble() + random.nextDouble(), (double)pos.getZ() + (double)0.5F + random.nextDouble() / (double)3.0F * (double)(random.nextBoolean() ? 1 : -1), 10,0.02F, 0.1d, 0.02F, 0.01F);
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return simpleCodec(BrokenBlonkBlock::new);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
