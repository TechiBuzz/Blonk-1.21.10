package com.techibuzz.blonk.block.entity;

import com.mojang.serialization.Codec;
import com.techibuzz.blonk.block.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;

public class BlonkBlockEntity extends BlockEntity {
    private int ammo;
    private float firing_power = 1.5f;

    public BlonkBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLONK_BLOCK_ENTITY, pos, state);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    protected void saveAdditional(ValueOutput view) {
        super.saveAdditional(view);

        view.putInt("ammo_count", ammo);
        view.putFloat("firing_power", firing_power);
    }

    @Override
    protected void loadAdditional(ValueInput view) {
        super.loadAdditional(view);

        ammo = view.read("ammo_count", Codec.INT).orElse(0);
        firing_power = view.read("firing_power", Codec.FLOAT).orElse(1.5f);
    }

    public int getAmmoCount() { return  this.ammo; }

    public void incrementAmmo() { this.ammo += 8; }

    public void decrementAmmo() { this.ammo--; }

    public int getMaxAmmoCount() { return 64; }

    public float getFiringPower() { return firing_power; }
}
