package com.techibuzz.blonk.block.custom;

import com.mojang.serialization.MapCodec;
import com.techibuzz.blonk.block.ModBlocks;
import com.techibuzz.blonk.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
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
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            if (stack.isOf(ModItems.SCRAP)) {
                player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
                stack.decrementUnlessCreative(1, player);

                world.setBlockState(pos, ModBlocks.BLONK.getDefaultState().with(Properties.HORIZONTAL_FACING, state.get(FACING)));
                world.playSound(null, pos, SoundEvents.BLOCK_WET_SPONGE_DRIES, SoundCategory.BLOCKS, 1.0f, 1.0f);

                if (world instanceof ServerWorld serverWorld) {
                    Random random = world.getRandom();
                    serverWorld.spawnParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, (double)pos.getX() + (double)0.5F + random.nextDouble() / (double)3.0F * (double)(random.nextBoolean() ? 1 : -1), (double)pos.getY() + random.nextDouble() + random.nextDouble(), (double)pos.getZ() + (double)0.5F + random.nextDouble() / (double)3.0F * (double)(random.nextBoolean() ? 1 : -1), 10,0.001F, 0.2d, 0.001F, 0.02F);
                }
            }
        }
        return ActionResult.SUCCESS;
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
