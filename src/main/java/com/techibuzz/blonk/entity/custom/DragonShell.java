package com.techibuzz.blonk.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.PowerParticleOption;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;

public class DragonShell extends Shell {
    public DragonShell(EntityType<? extends AbstractHurtingProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public DragonShell(EntityType<? extends AbstractHurtingProjectile> entityType, Position position, Level level) {
        super(entityType, position, level);
        this.entityDamage = 16.0f;
    }

    @Override
    public void explode(BlockPos pos, boolean createfire) {
        if (this.level() instanceof ServerLevel serverLevel) {
            AreaEffectCloud areaEffectCloud = new AreaEffectCloud(serverLevel, this.getX(), this.getY(), this.getZ());

            areaEffectCloud.setCustomParticle(PowerParticleOption.create(ParticleTypes.DRAGON_BREATH, 1.0f));
            areaEffectCloud.setRadius(2.0f);
            areaEffectCloud.setDuration(20*8);
            areaEffectCloud.setRadiusPerTick((4.0f - areaEffectCloud.getRadius()) / areaEffectCloud.getDuration());
            areaEffectCloud.setPotionDurationScale(0.25f);
            areaEffectCloud.addEffect(new MobEffectInstance(MobEffects.INSTANT_DAMAGE, 1, 1));

            serverLevel.addFreshEntity(areaEffectCloud);
        }
        this.discard();
    }
}
