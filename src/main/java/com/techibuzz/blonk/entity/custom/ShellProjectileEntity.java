package com.techibuzz.blonk.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class ShellProjectileEntity extends ProjectileEntity {

    public ShellProjectileEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getEntityWorld().isClient()) {
            this.getEntityWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), 10, false, World.ExplosionSourceType.TRIGGER);
            this.discard();
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    public boolean shouldRender(double distance) {
        return true;
    }

}
