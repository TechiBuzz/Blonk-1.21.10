package com.techibuzz.blonk.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;

public class HeatShell extends Shell {
    public HeatShell(EntityType<? extends AbstractHurtingProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public HeatShell(EntityType<? extends AbstractHurtingProjectile> entityType, Position position, Level level) {
        super(entityType, position, level);

        this.explosionPower = 8.0f;
        this.blonkExplosionScalingFactor = 5.0f;
        this.entityDamage = 40.0f;

        this.gravity = 0.0035f;
    }

    @Override
    public void explode(BlockPos pos, boolean createFire) {
        super.explode(pos, true);
    }
}
