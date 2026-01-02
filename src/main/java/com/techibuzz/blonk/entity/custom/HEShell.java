package com.techibuzz.blonk.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.hurtingprojectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class HEShell extends Shell {
    public HEShell(EntityType<? extends @NotNull AbstractHurtingProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public HEShell(EntityType<? extends @NotNull AbstractHurtingProjectile> entityType, Position position, Level level) {
        super(entityType, position, level);

        this.explosionPower = 3.25f;
        this.blonkExplosionScalingFactor = 3.25f;
        this.entityDamage = 20.0f;
    }

    @Override
    public void explode(BlockPos pos, boolean createFire) {
        super.explode(pos, false);
    }
}
