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

import java.util.function.BiConsumer;
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
import net.minecraft.world.level.Explosion;
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
    protected @NotNull InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!(world.getBlockEntity(pos) instanceof BlonkBlockEntity blonkBlockEntity)) {
            return super.useItemOn(itemStack, state, world, pos, player, hand, hit);
        }

        if (!world.isClientSide()) {
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

                    world.playSound(null, pos, ModSounds.BLONK_LOAD, SoundSource.BLOCKS, 1.0F, 0.7F + 0.5F * freshStackCount);
                    if (world instanceof ServerLevel serverWorld) {
                        serverWorld.sendParticles(ParticleTypes.DUST_PLUME, pos.getX() + 0.5, pos.getY() + 1.2, pos.getZ() + 0.5, 7, 0.0, 0.0, 0.0, 0.0);
                    }
                } else {
                    world.playSound(null, pos, ModSounds.BLONK_LOAD_FAIL, SoundSource.BLOCKS, 2.0F, 1.0f);
                }

            } else if (player.getMainHandItem().isEmpty() && blonkBlockEntity.getAmmoCount() > 0) {  // Blonk Shoot
                Vec3 firingPosition = pos.getCenter().relative(state.getValue(FACING), 0.5);
                Vec3i firingVelocity = state.getValue(FACING).getUnitVec3i();

                ShellEntity shellEntity = new ShellEntity(ModEntities.SHELL, world);
                ShellEntity.FACING = state.getValue(FACING);
                shellEntity.setPos(firingPosition);

                Projectile.spawnProjectileUsingShoot(
                        (world1, shooter, stack) -> shellEntity,
                        (ServerLevel) world,
                        this.asItem().getDefaultInstance(),
                        player,
                        firingVelocity.getX(),
                        firingVelocity.getY(),
                        firingVelocity.getZ(),
                        blonkBlockEntity.getFiringPower(),
                        3.0f
                );
                world.playSound(null, pos, ModSounds.BLONK_SHOOT, SoundSource.BLOCKS, 2.0F, 1.0f);
                world.addFreshEntity(new ItemEntity(world, pos.getCenter().x(), pos.getCenter().y() + 0.6, pos.getCenter().z(), new ItemStack(ModItems.CASING)));

                blonkBlockEntity.decrementAmmo();
            } else {
                world.playSound(null, pos, ModSounds.BLONK_LOAD_FAIL, SoundSource.BLOCKS, 2.0F, 1.0f);
            }

            blonkBlockEntity.setChanged();
            world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    protected void onExplosionHit(BlockState state, ServerLevel world, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> stackMerger) {
        super.onExplosionHit(state, world, pos, explosion, stackMerger);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlonkBlockEntity(pos, state);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(BlonkBlock::new);
    }

    @Override
    public Item asItem() {
        return super.asItem();
    }
}
