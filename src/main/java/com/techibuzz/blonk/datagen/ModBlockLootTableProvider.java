package com.techibuzz.blonk.datagen;

import com.techibuzz.blonk.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {

    public ModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.AMMO_RACK);
        addDrop(ModBlocks.BLONK, ModBlocks.BROKEN_BLONK);
        addDrop(ModBlocks.CHONK, ModBlocks.BROKEN_BLONK);
        addDrop(ModBlocks.BROKEN_BLONK);
    }

}
