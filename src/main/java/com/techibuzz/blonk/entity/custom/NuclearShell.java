package com.techibuzz.blonk.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;

public class NuclearShell extends Shell {
    public NuclearShell(EntityType<? extends AbstractHurtingProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public NuclearShell(EntityType<? extends AbstractHurtingProjectile> entityType, Position position, Level level) {
        super(entityType, position, level);

        this.explosionPower = 128.0f;
        this.blonkExplosionScalingFactor = 20.0f;
        this.entityDamage = 200.0f;

        this.gravity = 0.002f;
    }

    @Override
    public void explode(BlockPos pos, boolean createFire) {
        super.explode(pos, true);
    }
}
