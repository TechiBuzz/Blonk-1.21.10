package com.techibuzz.blonk.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;

public class SmokeShell extends Shell {
    public SmokeShell(EntityType<? extends AbstractHurtingProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public SmokeShell(EntityType<? extends AbstractHurtingProjectile> entityType, Position position, Level level) {
        super(entityType, position, level);
    }

    @Override
    public void explode(BlockPos pos) {
        super.explode(pos);
    }
}
