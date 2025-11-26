package com.techibuzz.blonk.block.custom;

import com.mojang.serialization.MapCodec;
import com.techibuzz.blonk.block.ModBlocks;
import com.techibuzz.blonk.block.entity.BlonkBlockEntity;
import com.techibuzz.blonk.entity.ModEntities;
import com.techibuzz.blonk.entity.custom.ShellEntity;
import com.techibuzz.blonk.item.ModItems;
import com.techibuzz.blonk.sound.ModSounds;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Orientation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
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
    protected @NotNull InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!(level.getBlockEntity(pos) instanceof BlonkBlockEntity blonkBlockEntity)) {
            return super.useItemOn(itemStack, state, level, pos, player, hand, hit);
        }

        if (!level.isClientSide()) {
            if (itemStack.isEmpty()) { // Shoot Shell
                shootShell(state, pos, level, player);
            } else if (itemStack.is(ModBlocks.AMMO_RACK.asItem()) && (blonkBlockEntity.getAmmoCount() + 8) <= 64) { // Load Blonk
                player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));

                ItemStack freshItemStack = itemStack.consumeAndReturn(1, player);
                float freshStackCount = blonkBlockEntity.getAmmoCount() == 0
                        ? (float) freshItemStack.getCount() / freshItemStack.getMaxStackSize()
                        : (float) blonkBlockEntity.getAmmoCount() / blonkBlockEntity.getMaxAmmoCount();

                blonkBlockEntity.incrementAmmo();
                blonkBlockEntity.setChanged();
                level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);

                level.playSound(null, pos, ModSounds.BLONK_LOAD, SoundSource.BLOCKS, 1.0F, 0.7F + 0.5F * freshStackCount);
                if (level instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.DUST_PLUME, pos.getX() + 0.5, pos.getY() + 1.2, pos.getZ() + 0.5, 7, 0.0, 0.0, 0.0, 0.0);
                }
            } else {
                level.playSound(null, pos, ModSounds.BLONK_LOAD_FAIL, SoundSource.BLOCKS, 2.0F, 1.0f);
            }
        }

        return InteractionResult.SUCCESS;
    }

    private void shootShell(BlockState state, BlockPos pos, Level level, Player player) {
        if (!(level.getBlockEntity(pos) instanceof BlonkBlockEntity blonkBlockEntity)) {
            return;
        }
        if (!(blonkBlockEntity.getAmmoCount() > 0)) {
            level.playSound(null, pos, ModSounds.BLONK_LOAD_FAIL, SoundSource.BLOCKS, 2.0F, 1.0f);
            return;
        }

        Vec3 firingPosition = pos.getCenter().relative(state.getValue(FACING), 0.5);
        Vec3i firingVelocity = state.getValue(FACING).getUnitVec3i();

        ShellEntity shellEntity = new ShellEntity(ModEntities.SHELL, level);
        ShellEntity.FACING = state.getValue(FACING);
        shellEntity.setPos(firingPosition);

        Projectile.spawnProjectileUsingShoot(
                (level1, shooter, stack) -> shellEntity,
                (ServerLevel) level,
                this.asItem().getDefaultInstance(),
                player,
                firingVelocity.getX(),
                firingVelocity.getY(),
                firingVelocity.getZ(),
                blonkBlockEntity.getFiringPower(),
                3.0f
        );
        level.playSound(null, pos, ModSounds.BLONK_SHOOT, SoundSource.BLOCKS, 2.0F, 1.0f);
        level.addFreshEntity(new ItemEntity(level, pos.getCenter().x(), pos.getCenter().y() + 0.6, pos.getCenter().z(), new ItemStack(ModItems.CASING)));

        blonkBlockEntity.decrementAmmo();
        blonkBlockEntity.setChanged();

        level.gameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean movedByPiston) {
        boolean bl = level.hasNeighborSignal(pos) || level.hasNeighborSignal(pos.above());
        boolean bl2 = state.getValue(TRIGGERED);
        if (bl && !bl2) {
            level.scheduleTick(pos, this, 4);
            level.setBlock(pos, state.setValue(TRIGGERED, true), 2);
        } else if (!bl && bl2) {
            level.setBlock(pos, state.setValue(TRIGGERED, false), 2);
        }
    }

    @Override
    protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel level, BlockPos pos, boolean movedByPiston) {
        Containers.updateNeighboursAfterDestroy(state, level, pos);
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
