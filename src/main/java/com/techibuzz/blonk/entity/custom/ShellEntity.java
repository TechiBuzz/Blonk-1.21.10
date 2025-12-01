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
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class ShellEntity extends AbstractHurtingProjectile {
    public ShellEntity(EntityType<? extends AbstractHurtingProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public ShellEntity(EntityType<? extends AbstractHurtingProjectile> entityType, Position position, Level level) {
        super(entityType, position.x(), position.y(), position.z(), level);
    }

    @Override
    public void tick() {
        super.tick();
        this.applyGravity();
        this.setXRot(this.getXRot());
        this.setYRot(this.getYRot(0));
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (this.level() instanceof ServerLevel serverLevel) {
            Entity hitEntity = result.getEntity();
            Entity owner = this.getOwner();
            DamageSource damageSource = this.damageSources().explosion(owner, hitEntity);
            hitEntity.hurtServer(serverLevel, damageSource, 6.0F);
            EnchantmentHelper.doPostAttackEffects(serverLevel, hitEntity, damageSource);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);

        Level level = this.level();
        if (!level.isClientSide()) {
            BlockPos pos = blockHitResult.getBlockPos();
            // Mega explosion if shell directly hits a blonk
            if (level.getBlockEntity(pos) instanceof BlonkBlockEntity) {
                level.explode(this, this.getX(), this.getY(), this.getZ(), 7.0F, true, Level.ExplosionInteraction.MOB);

                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModBlocks.BROKEN_BLONK.asItem())));
            } else {
                level.explode(this, this.getX(), this.getY(), this.getZ(), 3.5F, Level.ExplosionInteraction.MOB);
            }

            // Replace any blonks with the broken one if in a 3x3 region around explosion
            for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
                if (level.getBlockEntity(blockPos) instanceof BlonkBlockEntity) {
                    level.setBlockAndUpdate(blockPos, ModBlocks.BROKEN_BLONK.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, level.getBlockState(blockPos).getValue(BlockStateProperties.HORIZONTAL_FACING)));
                    level.playSound(null, blockPos, SoundEvents.CREAKING_DEATH, SoundSource.BLOCKS);

                    level.gameEvent(this, GameEvent.BLOCK_CHANGE, blockPos);
                }
            }
            this.discard();
        }
    }

    @Override
    protected void applyGravity() {
        this.setDeltaMovement(this.getDeltaMovement().subtract(0.0F, 0.005F, 0.0F));
    }

    @Override
    public boolean isOnFire() {
        return false;
    }
}
