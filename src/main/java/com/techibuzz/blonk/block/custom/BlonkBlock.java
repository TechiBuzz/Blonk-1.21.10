package com.techibuzz.blonk.block.custom;

import com.mojang.serialization.MapCodec;
import com.techibuzz.blonk.block.entity.BlonkBlockEntity;
import com.techibuzz.blonk.item.ModItemTags;
import com.techibuzz.blonk.item.ModItems;
import com.techibuzz.blonk.item.custom.ShellItem;
import com.techibuzz.blonk.sound.ModSounds;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Orientation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class BlonkBlock extends BaseEntityBlock {
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty TRIGGERED = BlockStateProperties.TRIGGERED;

    public BlonkBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(TRIGGERED)) {
            shootShell(state, pos, level, null);
        }
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if ((level.getBlockEntity(pos) instanceof BlonkBlockEntity blonkBlockEntity)) {
            if (!level.isClientSide()) {
                if (player.isShiftKeyDown()) {
                    player.openMenu(blonkBlockEntity);
                } else {
                    shootShell(state, pos, level, player);
                }
            }
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.PASS;
        }
    }

    @Override
    protected @NotNull InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.getBlockEntity(pos) instanceof BlonkBlockEntity blonkBlockEntity) {
            if (level.isClientSide()) {
                return InteractionResult.SUCCESS;
            } else {
                ItemStack itemStack = blonkBlockEntity.getItem(0);
                if (stack.is(ModItemTags.SHELLS) && (itemStack.isEmpty() || ItemStack.isSameItemSameComponents(itemStack, stack) && itemStack.getCount() < itemStack.getMaxStackSize())) {
                    player.awardStat(Stats.ITEM_USED.get(stack.getItem()));

                    ItemStack freshItemStack = stack.consumeAndReturn(1, player);
                    float freshStackCount;
                    if (blonkBlockEntity.getItem(0).isEmpty()) {
                        blonkBlockEntity.setItem(0, freshItemStack);
                        freshStackCount = (float) freshItemStack.getCount() / freshItemStack.getMaxStackSize();
                    } else {
                        itemStack.grow(1);
                        freshStackCount = (float) blonkBlockEntity.getItem(0).getCount() / blonkBlockEntity.getItem(0).getMaxStackSize();
                    }

                    level.playSound(null, pos, ModSounds.BLONK_LOAD, SoundSource.BLOCKS, 1.0F, 0.7F + 0.5F * freshStackCount);
                    if (level instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(ParticleTypes.DUST_PLUME, pos.getX() + 0.5, pos.getY() + 1.2, pos.getZ() + 0.5, 7, 0.0, 0.0, 0.0, 0.0);
                    }

                    blonkBlockEntity.setChanged();
                    level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                    return InteractionResult.CONSUME;
                } else {
                    return InteractionResult.TRY_WITH_EMPTY_HAND;
                }
            }
        } else {
            return InteractionResult.PASS;
        }
    }

    private void shootShell(BlockState state, BlockPos pos, Level level, Player player) {
        if (!(level.getBlockEntity(pos) instanceof BlonkBlockEntity blonkBlockEntity)) {
            return;
        }
        if (blonkBlockEntity.getItem(0).isEmpty()) {
            level.playSound(null, pos, ModSounds.BLONK_LOAD_FAIL, SoundSource.BLOCKS, 2.0F, 1.0f);
            return;
        }

        Vec3 position = pos.getCenter().relative(state.getValue(FACING), 0.7);
        Direction direction = state.getValue(FACING);

        ShellItem shellItem = (ShellItem) blonkBlockEntity.getItem(0).getItem();
        Projectile.spawnProjectileUsingShoot(
                (serverLevel, livingEntity, itemStack) -> shellItem.asProjectile(level, position, itemStack, direction),
                (ServerLevel) level,
                this.asItem().getDefaultInstance(),
                player,
                direction.getStepX(),
                direction.getStepY(),
                direction.getStepZ(),
                blonkBlockEntity.getFiringPower(),
                3.0f
        );

        level.playSound(null, pos, ModSounds.BLONK_SHOOT, SoundSource.BLOCKS, 2.0F, 1.0f);
        level.addFreshEntity(new ItemEntity(level, pos.getCenter().x(), pos.getCenter().y() + 0.6, pos.getCenter().z(), new ItemStack(ModItems.CASING)));

        blonkBlockEntity.getItem(0).shrink(1);

        blonkBlockEntity.setChanged();
        level.gameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean movedByPiston) {
        boolean redstoneNearby = level.hasNeighborSignal(pos) || level.hasNeighborSignal(pos.above());
        boolean isTriggered = state.getValue(TRIGGERED);
        if (redstoneNearby && !isTriggered) {
            level.scheduleTick(pos, this, 4);
            level.setBlock(pos, state.setValue(TRIGGERED, true), 2);
        } else if (!redstoneNearby && isTriggered) {
            level.setBlock(pos, state.setValue(TRIGGERED, false), 2);
        }
    }

    @Override
    protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel level, BlockPos pos, boolean movedByPiston) {
        Containers.updateNeighboursAfterDestroy(state, level, pos);
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos, Direction direction) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlonkBlockEntity(pos, state);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite()).setValue(TRIGGERED, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TRIGGERED);
    }

    @Override
    public @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(BlonkBlock::new);
    }

    @Override
    public @NotNull Item asItem() {
        return super.asItem();
    }
}
