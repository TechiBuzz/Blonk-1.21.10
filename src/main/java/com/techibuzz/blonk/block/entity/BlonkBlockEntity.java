package com.techibuzz.blonk.block.entity;

import com.techibuzz.blonk.block.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;

public class BlonkBlockEntity extends BlockEntity {
    private int ammoRackCount = 0;

    public BlonkBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLONK_BLOCK_ENTITY, pos, state);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registries) {
        return createNbt(registries);
    }

    public void addAmmoRack() {
        this.ammoRackCount++;
    }

    public int getAmmoRackCount() {
        return this.ammoRackCount;
    }

    public int getMaxAmmoRackCount() {
        return 8;
    }
}
