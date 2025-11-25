package com.techibuzz.blonk.datagen;

import com.techibuzz.blonk.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    public ModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.AMMO_RACK);

        dropSelf(ModBlocks.BLONK);

        dropSelf(ModBlocks.BLACK_BLONK);
        dropSelf(ModBlocks.BLUE_BLONK);
        dropSelf(ModBlocks.BROWN_BLONK);
        dropSelf(ModBlocks.CYAN_BLONK);
        dropSelf(ModBlocks.GRAY_BLONK);
        dropSelf(ModBlocks.GREEN_BLONK);
        dropSelf(ModBlocks.LIGHT_BLUE_BLONK);
        dropSelf(ModBlocks.LIGHT_GRAY_BLONK);
        dropSelf(ModBlocks.LIME_BLONK);
        dropSelf(ModBlocks.MAGENTA_BLONK);
        dropSelf(ModBlocks.ORANGE_BLONK);
        dropSelf(ModBlocks.PINK_BLONK);
        dropSelf(ModBlocks.PURPLE_BLONK);
        dropSelf(ModBlocks.RED_BLONK);
        dropSelf(ModBlocks.WHITE_BLONK);
        dropSelf(ModBlocks.YELLOW_BLONK);

        dropSelf(ModBlocks.BROKEN_BLONK);
    }
}
