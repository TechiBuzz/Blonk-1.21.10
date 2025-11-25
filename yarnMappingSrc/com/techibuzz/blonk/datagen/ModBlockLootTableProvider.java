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

        addDrop(ModBlocks.BLONK);

        addDrop(ModBlocks.BLACK_BLONK);
        addDrop(ModBlocks.BLUE_BLONK);
        addDrop(ModBlocks.BROWN_BLONK);
        addDrop(ModBlocks.CYAN_BLONK);
        addDrop(ModBlocks.GRAY_BLONK);
        addDrop(ModBlocks.GREEN_BLONK);
        addDrop(ModBlocks.LIGHT_BLUE_BLONK);
        addDrop(ModBlocks.LIGHT_GRAY_BLONK);
        addDrop(ModBlocks.LIME_BLONK);
        addDrop(ModBlocks.MAGENTA_BLONK);
        addDrop(ModBlocks.ORANGE_BLONK);
        addDrop(ModBlocks.PINK_BLONK);
        addDrop(ModBlocks.PURPLE_BLONK);
        addDrop(ModBlocks.RED_BLONK);
        addDrop(ModBlocks.WHITE_BLONK);
        addDrop(ModBlocks.YELLOW_BLONK);

        addDrop(ModBlocks.BROKEN_BLONK);
    }
}
