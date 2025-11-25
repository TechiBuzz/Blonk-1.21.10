package com.techibuzz.blonk.entity.custom;

import com.techibuzz.blonk.block.ModBlocks;
import com.techibuzz.blonk.block.entity.BlonkBlockEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class ShellEntity extends ExplosiveProjectileEntity {
    public static Direction FACING = Direction.SOUTH;

    public ShellEntity(EntityType<? extends ExplosiveProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();
        this.setVelocity(this.getVelocity().add(0, -0.005, 0));
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);

        World world = this.getEntityWorld();
        BlockPos pos = blockHitResult.getBlockPos();

        if (!world.isClient()) {
            // Mega explosion if shell directly hits a blonk
            if (world.getBlockEntity(pos) instanceof BlonkBlockEntity) {
                world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 7.0F, true, World.ExplosionSourceType.TNT);

                world.setBlockState(pos, Blocks.AIR.getDefaultState());
                world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModBlocks.BROKEN_BLONK.asItem())));
            } else {
                this.getEntityWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), 3.5F, false, World.ExplosionSourceType.TNT);
            }

            // Replace any blonks with the broken one if in a 3x3 region around explosion
            for (BlockPos blockPos : BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
                if (world.getBlockEntity(blockPos) instanceof BlonkBlockEntity) {
                    world.setBlockState(blockPos, ModBlocks.BROKEN_BLONK.getDefaultState().with(Properties.HORIZONTAL_FACING, world.getBlockState(blockPos).get(Properties.HORIZONTAL_FACING)));
                    world.playSound(null, blockPos, SoundEvents.ENTITY_CREAKING_DEATH, SoundCategory.BLOCKS);

                    world.emitGameEvent(this, GameEvent.BLOCK_CHANGE, blockPos);
                }
            }
        }

        this.discard();
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public boolean isCollidable(@Nullable Entity entity) {
        return false;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }
}
