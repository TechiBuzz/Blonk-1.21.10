package com.techibuzz.blonk.entity.custom;

import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;

public class HEShell extends Shell {
    public HEShell(EntityType<? extends AbstractHurtingProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public HEShell(EntityType<? extends AbstractHurtingProjectile> entityType, Position position, Level level) {
        super(entityType, position, level);
    }
}
