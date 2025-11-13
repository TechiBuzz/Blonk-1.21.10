package com.techibuzz.blonk.datagen;

import com.techibuzz.blonk.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.CASING, Models.GENERATED);
        itemModelGenerator.register(ModItems.EXPLOSIVE_MIX, Models.GENERATED);
        itemModelGenerator.register(ModItems.GUN_BARREL, Models.GENERATED);
        itemModelGenerator.register(ModItems.LOADING_MECHANISM, Models.GENERATED);
        itemModelGenerator.register(ModItems.METAL_ALLOY, Models.GENERATED);
        itemModelGenerator.register(ModItems.SCRAP, Models.GENERATED);
        itemModelGenerator.register(ModItems.SHELL, Models.GENERATED);
        itemModelGenerator.register(ModItems.TRACK, Models.GENERATED);
    }
}
