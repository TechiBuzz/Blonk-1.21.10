package com.techibuzz.blonk.datagen;

import com.techibuzz.blonk.block.ModBlocks;
import com.techibuzz.blonk.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialBlock(ModBlocks.AMMO_RACK, TexturedModel.CUBE_TOP_BOTTOM);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ModItems.CASING, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.EXPLOSIVE_MIX, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GUN_BARREL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.METAL_ALLOY, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SCRAP, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SHELL, ModelTemplates.FLAT_ITEM);
    }
}
