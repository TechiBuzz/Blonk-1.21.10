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
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
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
            if (world.getBlockEntity(pos) instanceof BlonkBlockEntity) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
                world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModBlocks.BROKEN_BLONK.asItem())));

                this.getEntityWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), 6.0F, true, World.ExplosionSourceType.BLOCK);
            } else {
                this.getEntityWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), 3.0F, false, World.ExplosionSourceType.BLOCK);
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
