package com.techibuzz.blonk.entity.custom;

import com.techibuzz.blonk.block.ModBlockEntities;
import com.techibuzz.blonk.block.ModBlocks;
import com.techibuzz.blonk.block.entity.BlonkBlockEntity;
import com.techibuzz.blonk.entity.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ShellEntity extends ExplosiveProjectileEntity {
    public ShellEntity(EntityType<? extends ExplosiveProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();
        this.setVelocity(this.getVelocity().add(0, -0.005, 0));
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);

        World world = this.getEntityWorld();
        if (!world.isClient()) {
            this.getEntityWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), 2.5F, false, World.ExplosionSourceType.BLOCK);
        }
        this.discard();
    }

    @Override
    protected void onBlockCollision(BlockState state) {
        super.onBlockCollision(state);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);

        BlockPos pos = blockHitResult.getBlockPos();
        if (this.getEntityWorld().getBlockEntity(pos) instanceof BlonkBlockEntity) {
            this.getEntityWorld().setBlockState(pos, ModBlocks.BROKEN_BLONK.getDefaultState().with(Properties.HORIZONTAL_FACING, this.getEntityWorld().getBlockState(pos).get(Properties.HORIZONTAL_FACING)));
        }
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
