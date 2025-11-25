package com.techibuzz.blonk.block.custom;

import com.mojang.serialization.MapCodec;
import com.techibuzz.blonk.block.ModBlocks;
import com.techibuzz.blonk.block.entity.BlonkBlockEntity;
import com.techibuzz.blonk.entity.ModEntities;
import com.techibuzz.blonk.entity.custom.ShellEntity;
import com.techibuzz.blonk.item.ModItems;
import com.techibuzz.blonk.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class BlonkBlock extends BlockWithEntity {
    public static final EnumProperty<Direction> FACING = Properties.HORIZONTAL_FACING;

    public BlonkBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack itemStack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!(world.getBlockEntity(pos) instanceof BlonkBlockEntity blonkBlockEntity)) {
            return super.onUseWithItem(itemStack, state, world, pos, player, hand, hit);
        }

        if (!world.isClient()) {
            // Ammo Rack Loading
            if (itemStack.isOf(ModBlocks.AMMO_RACK.asItem())) {
                if ((blonkBlockEntity.getAmmoCount() + 8) <= 64) {
                    player.incrementStat(Stats.USED.getOrCreateStat(itemStack.getItem()));

                    ItemStack freshItemStack = itemStack.splitUnlessCreative(1, player);
                    float freshStackCount;
                    if (blonkBlockEntity.getAmmoCount() == 0) {
                        freshStackCount = (float) freshItemStack.getCount() / freshItemStack.getMaxCount();
                    } else {
                        freshStackCount = (float) blonkBlockEntity.getAmmoCount() / blonkBlockEntity.getMaxAmmoCount();
                    }
                    blonkBlockEntity.incrementAmmo();

                    world.playSound(null, pos, ModSounds.BLONK_LOAD, SoundCategory.BLOCKS, 1.0F, 0.7F + 0.5F * freshStackCount);
                    if (world instanceof ServerWorld serverWorld) {
                        serverWorld.spawnParticles(ParticleTypes.DUST_PLUME, pos.getX() + 0.5, pos.getY() + 1.2, pos.getZ() + 0.5, 7, 0.0, 0.0, 0.0, 0.0);
                    }
                } else {
                    world.playSound(null, pos, ModSounds.BLONK_LOAD_FAIL, SoundCategory.BLOCKS, 2.0F, 1.0f);
                }

            } else if (player.getMainHandStack().isEmpty() && blonkBlockEntity.getAmmoCount() > 0) {  // Blonk Shoot
                Vec3d firingPosition = pos.toCenterPos().offset(state.get(FACING), 0.5);
                Vec3i firingVelocity = state.get(FACING).getVector();

                ShellEntity shellEntity = new ShellEntity(ModEntities.SHELL, world);
                ShellEntity.FACING = state.get(FACING);
                shellEntity.setPosition(firingPosition);

                ProjectileEntity.spawnWithVelocity(
                        (world1, shooter, stack) -> shellEntity,
                        (ServerWorld) world,
                        this.asItem().getDefaultStack(),
                        player,
                        firingVelocity.getX(),
                        firingVelocity.getY(),
                        firingVelocity.getZ(),
                        blonkBlockEntity.getFiringPower(),
                        3.5f
                );
                world.playSound(null, pos, ModSounds.BLONK_SHOOT, SoundCategory.BLOCKS, 2.0F, 1.0f);
                world.spawnEntity(new ItemEntity(world, pos.toCenterPos().getX(), pos.toCenterPos().getY() + 0.6, pos.toCenterPos().getZ(), new ItemStack(ModItems.CASING)));

                blonkBlockEntity.decrementAmmo();
            } else {
                world.playSound(null, pos, ModSounds.BLONK_LOAD_FAIL, SoundCategory.BLOCKS, 2.0F, 1.0f);
            }

            blonkBlockEntity.markDirty();
            world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        }

        return ActionResult.SUCCESS;
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
