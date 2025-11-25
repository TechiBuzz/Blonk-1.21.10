package com.techibuzz.blonk.entity.custom;

import com.techibuzz.blonk.block.ModBlocks;
import com.techibuzz.blonk.block.entity.BlonkBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class ShellEntity extends AbstractHurtingProjectile {
    public static Direction FACING = Direction.SOUTH;

    public ShellEntity(EntityType<? extends AbstractHurtingProjectile> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().add(0, -0.005, 0));
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
        }

        this.discard();
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

}
