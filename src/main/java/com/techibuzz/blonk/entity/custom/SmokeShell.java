package com.techibuzz.blonk.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;

public class SmokeShell extends Shell {
    public SmokeShell(EntityType<? extends AbstractHurtingProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public SmokeShell(EntityType<? extends AbstractHurtingProjectile> entityType, Position position, Level level) {
        super(entityType, position, level);
        this.entityDamage = 14.0f;
    }

    @Override
    public void explode(BlockPos pos, boolean createFire) {
        if (this.level() instanceof ServerLevel serverLevel) {
            RandomSource randomSource = level().random;
            for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-3, 1, -3), pos.offset(3, 1, 3))) {
                serverLevel.sendParticles(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, true, true, blockPos.getX() + randomSource.nextFloat(), blockPos.getY(), blockPos.getZ() + randomSource.nextFloat(), 80 ,0.2f + randomSource.nextFloat(), 0.3F + randomSource.nextFloat(), 0.2F+ randomSource.nextFloat(), 0.02f);
            }
        }
        this.discard();
    }
}