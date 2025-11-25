package com.techibuzz.blonk.block.custom;

import com.mojang.serialization.MapCodec;
import com.techibuzz.blonk.block.ModBlocks;
import com.techibuzz.blonk.block.entity.BlonkBlockEntity;
import com.techibuzz.blonk.entity.ModEntities;
import com.techibuzz.blonk.entity.custom.ShellEntity;
import com.techibuzz.blonk.item.ModItems;
import com.techibuzz.blonk.sound.ModSounds;
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

    public BlonkBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected @NotNull InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!(level.getBlockEntity(pos) instanceof BlonkBlockEntity blonkBlockEntity)) {
            return super.useItemOn(itemStack, state, level, pos, player, hand, hit);
        }

        if (!level.isClientSide()) {
            // Ammo Rack Loading
            if (itemStack.is(ModBlocks.AMMO_RACK.asItem())) {
                if ((blonkBlockEntity.getAmmoCount() + 8) <= 64) {
                    player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));

                    ItemStack freshItemStack = itemStack.consumeAndReturn(1, player);
                    float freshStackCount;
                    if (blonkBlockEntity.getAmmoCount() == 0) {
                        freshStackCount = (float) freshItemStack.getCount() / freshItemStack.getMaxStackSize();
                    } else {
                        freshStackCount = (float) blonkBlockEntity.getAmmoCount() / blonkBlockEntity.getMaxAmmoCount();
                    }
                    blonkBlockEntity.incrementAmmo();

                    level.playSound(null, pos, ModSounds.BLONK_LOAD, SoundSource.BLOCKS, 1.0F, 0.7F + 0.5F * freshStackCount);
                    if (level instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(ParticleTypes.DUST_PLUME, pos.getX() + 0.5, pos.getY() + 1.2, pos.getZ() + 0.5, 7, 0.0, 0.0, 0.0, 0.0);
                    }
                } else {
                    level.playSound(null, pos, ModSounds.BLONK_LOAD_FAIL, SoundSource.BLOCKS, 2.0F, 1.0f);
                }

            } else if (player.getMainHandItem().isEmpty() && blonkBlockEntity.getAmmoCount() > 0) {  // Blonk Shoot
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
            } else {
                level.playSound(null, pos, ModSounds.BLONK_LOAD_FAIL, SoundSource.BLOCKS, 2.0F, 1.0f);
            }

            blonkBlockEntity.setChanged();
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlonkBlockEntity(pos, state);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
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
