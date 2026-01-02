package com.techibuzz.blonk.entity.custom;

import com.techibuzz.blonk.block.ModBlocks;
import com.techibuzz.blonk.block.entity.BlonkBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.hurtingprojectile.AbstractHurtingProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class Shell extends net.minecraft.world.entity.projectile.hurtingprojectile.AbstractHurtingProjectile {
    protected float explosionPower = 2f;
    protected float blonkExplosionScalingFactor = 2f;
    protected float entityDamage = 16.0f;
    protected float gravity = 0.005f;

    public Shell(EntityType<? extends @NotNull AbstractHurtingProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public Shell(EntityType<? extends @NotNull AbstractHurtingProjectile> entityType, Position position, Level level) {
        super(entityType, position.x(), position.y(), position.z(), level);
    }

    @Override
    public void tick() {
        super.tick();
        this.applyGravity();
        // Fix rotation after spawn
        this.setXRot(this.getXRot());
        this.setYRot(this.getYRot(0));
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        super.onHitEntity(result);
        if (this.level() instanceof ServerLevel serverLevel) {
            Entity hitEntity = result.getEntity();
            Entity owner = this.getOwner();

            DamageSource damageSource = this.damageSources().explosion(owner, hitEntity);
            hitEntity.hurtServer(serverLevel, damageSource, this.entityDamage);
            EnchantmentHelper.doPostAttackEffects(serverLevel, hitEntity, damageSource);

            this.explode(hitEntity.getOnPos(), false);
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        this.explode(blockHitResult.getBlockPos(), false);
    }

    public void explode(BlockPos pos, boolean createFire) {
        if (this.level() instanceof ServerLevel serverLevel) {
            // Bigger explosion if shell directly hits a blonk
            if (serverLevel.getBlockEntity(pos) instanceof BlonkBlockEntity) {
                serverLevel.explode(this, this.getX(), this.getY(), this.getZ(), this.explosionPower * this.blonkExplosionScalingFactor, true, Level.ExplosionInteraction.MOB);

                serverLevel.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                serverLevel.addFreshEntity(new ItemEntity(serverLevel, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModBlocks.BROKEN_BLONK.asItem())));
            } else {
                serverLevel.explode(this, this.getX(), this.getY(), this.getZ(), this.explosionPower, createFire, Level.ExplosionInteraction.MOB);
            }
            // Replace any blonks with the broken one if in a 3x3 region around explosion
            for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
                if (serverLevel.getBlockEntity(blockPos) instanceof BlonkBlockEntity) {
                    serverLevel.setBlockAndUpdate(blockPos, ModBlocks.BROKEN_BLONK.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, serverLevel.getBlockState(blockPos).getValue(BlockStateProperties.HORIZONTAL_FACING)));
                    serverLevel.playSound(null, blockPos, SoundEvents.CREAKING_DEATH, SoundSource.BLOCKS);

                    serverLevel.gameEvent(this, GameEvent.BLOCK_CHANGE, blockPos);
                }
            }
        }
        this.discard();
    }

    @Override
    protected void applyGravity() {
        this.setDeltaMovement(this.getDeltaMovement().subtract(0.0F, this.gravity, 0.0F));
    }

    @Override
    public boolean isOnFire() {
        return false;
    }
}
