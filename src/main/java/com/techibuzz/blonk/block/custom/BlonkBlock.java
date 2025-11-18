package com.techibuzz.blonk.block.custom;

import com.mojang.serialization.MapCodec;
import com.techibuzz.blonk.block.ModBlocks;
import com.techibuzz.blonk.block.entity.BlonkBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class BlonkBlock extends BlockWithEntity {

    public static final EnumProperty<Direction> FACING = HorizontalFacingBlock.FACING;

    public BlonkBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack itemStack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!(world.getBlockEntity(pos) instanceof BlonkBlockEntity blonkBlockEntity)) {
            return super.onUseWithItem(itemStack, state, world, pos, player, hand, hit);
        }

        // Armor
        if (world.isClient()) {
            return ActionResult.SUCCESS;
        } else {
            ItemStack blockEntityStack = blonkBlockEntity.getStack();
            if (!itemStack.isEmpty()
                && itemStack.getItem().equals(ModBlocks.AMMO_RACK.asItem())
                && (blockEntityStack.isEmpty() || ItemStack.areItemsAndComponentsEqual(blockEntityStack, itemStack)
                && blockEntityStack.getCount() < blonkBlockEntity.getMaxStackSize())
            ) {
                player.incrementStat(Stats.USED.getOrCreateStat(itemStack.getItem()));

                ItemStack freshItemStack = itemStack.splitUnlessCreative(1, player);
                float freshStackCount;
                if (blonkBlockEntity.isEmpty()) {
                    blonkBlockEntity.setStack(freshItemStack);
                    freshStackCount = (float) freshItemStack.getCount() / freshItemStack.getMaxCount();
                } else {
                    blockEntityStack.increment(1);
                    freshStackCount = (float) blockEntityStack.getCount() / blockEntityStack.getMaxCount();
                }

                world.playSound(null, pos, SoundEvents.BLOCK_DECORATED_POT_INSERT, SoundCategory.BLOCKS, 1.0F, 0.7F + 0.5F * freshStackCount);
                if (world instanceof ServerWorld serverWorld) {
                    serverWorld.spawnParticles(ParticleTypes.DUST_PLUME, pos.getX() + 0.5, pos.getY() + 1.2, pos.getZ() + 0.5, 7, 0.0, 0.0, 0.0, 0.0);
                }

                blonkBlockEntity.markDirty();
                world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                return ActionResult.SUCCESS;
            } else {
                return  ActionResult.PASS;
            }
        }
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BlonkBlockEntity(pos, state);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(BlonkBlock::new);
    }

    @Override
    public Item asItem() {
        return super.asItem();
    }
}
