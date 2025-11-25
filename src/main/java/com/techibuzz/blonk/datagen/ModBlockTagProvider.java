package com.techibuzz.blonk.datagen;

import com.techibuzz.blonk.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void addTags(HolderLookup.Provider wrapperLookup) {
        // PICKAXE-ABLE
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)

                .add(ModBlocks.BLONK)

                .add(ModBlocks.BLACK_BLONK)
                .add(ModBlocks.BLUE_BLONK)
                .add(ModBlocks.BROWN_BLONK)
                .add(ModBlocks.CYAN_BLONK)
                .add(ModBlocks.GRAY_BLONK)
                .add(ModBlocks.GREEN_BLONK)
                .add(ModBlocks.LIGHT_BLUE_BLONK)
                .add(ModBlocks.LIGHT_GRAY_BLONK)
                .add(ModBlocks.LIME_BLONK)
                .add(ModBlocks.MAGENTA_BLONK)
                .add(ModBlocks.ORANGE_BLONK)
                .add(ModBlocks.PINK_BLONK)
                .add(ModBlocks.PURPLE_BLONK)
                .add(ModBlocks.RED_BLONK)
                .add(ModBlocks.WHITE_BLONK)
                .add(ModBlocks.YELLOW_BLONK)

                .add(ModBlocks.BROKEN_BLONK);

        // IRON++
        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.BLONK)

                .add(ModBlocks.BLACK_BLONK)
                .add(ModBlocks.BLUE_BLONK)
                .add(ModBlocks.BROWN_BLONK)
                .add(ModBlocks.CYAN_BLONK)
                .add(ModBlocks.GRAY_BLONK)
                .add(ModBlocks.GREEN_BLONK)
                .add(ModBlocks.LIGHT_BLUE_BLONK)
                .add(ModBlocks.LIGHT_GRAY_BLONK)
                .add(ModBlocks.LIME_BLONK)
                .add(ModBlocks.MAGENTA_BLONK)
                .add(ModBlocks.ORANGE_BLONK)
                .add(ModBlocks.PINK_BLONK)
                .add(ModBlocks.PURPLE_BLONK)
                .add(ModBlocks.RED_BLONK)
                .add(ModBlocks.WHITE_BLONK)
                .add(ModBlocks.YELLOW_BLONK)

                .add(ModBlocks.BROKEN_BLONK);
    }
}
