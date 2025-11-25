package com.techibuzz.blonk.block.entity;

import com.mojang.serialization.Codec;
import com.techibuzz.blonk.block.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;

public class BlonkBlockEntity extends BlockEntity {
    private int ammo;
    private float firing_power = 1.5f;

    public BlonkBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLONK_BLOCK_ENTITY, pos, state);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registries) {
        return createNbt(registries);
    }

    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);

        view.putInt("ammo_count", ammo);
        view.putFloat("firing_power", firing_power);
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);

        ammo = view.read("ammo_count", Codec.INT).orElse(0);
        firing_power = view.read("firing_power", Codec.FLOAT).orElse(1.5f);
    }

    public int getAmmoCount() { return  this.ammo; }

    public void incrementAmmo() { this.ammo += 8; }

    public void decrementAmmo() { this.ammo--; }

    public int getMaxAmmoCount() { return 64; }

    public float getFiringPower() { return firing_power; }
}
