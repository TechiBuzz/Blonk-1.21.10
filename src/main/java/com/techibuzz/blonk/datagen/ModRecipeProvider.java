package com.techibuzz.blonk.datagen;

import com.techibuzz.blonk.block.ModBlocks;
import com.techibuzz.blonk.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    private static final List<Item> dyeList = List.of(
            Items.BLACK_DYE,
            Items.BLUE_DYE,
            Items.BROWN_DYE,
            Items.CYAN_DYE,
            Items.GRAY_DYE,
            Items.GREEN_DYE,
            Items.LIGHT_BLUE_DYE,
            Items.LIGHT_GRAY_DYE,
            Items.LIME_DYE,
            Items.MAGENTA_DYE,
            Items.ORANGE_DYE,
            Items.PINK_DYE,
            Items.PURPLE_DYE,
            Items.RED_DYE,
            Items.YELLOW_DYE,
            Items.WHITE_DYE
    );

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                // AMMO RACK
                createShaped(RecipeCategory.COMBAT, ModBlocks.AMMO_RACK)
                        .pattern("SSS")
                        .pattern("SCS")
                        .pattern("SSS")
                        .input('S', ModItems.SHELL)
                        .input('C', Items.IRON_CHAIN)
                        .criterion(hasItem(ModItems.SHELL), conditionsFromItem(ModItems.SHELL))
                        .criterion(hasItem(Items.IRON_CHAIN), conditionsFromItem(Items.IRON_CHAIN))
                        .offerTo(exporter);

                // CASING
                createShaped(RecipeCategory.COMBAT, ModItems.CASING)
                        .pattern("G  ")
                        .pattern("G  ")
                        .pattern("   ")
                        .input('G', Items.GOLD_NUGGET)
                        .criterion(hasItem(Items.GOLD_NUGGET), conditionsFromItem(Items.GOLD_NUGGET))
                        .offerTo(exporter);

                // EXPLOSIVE MIX
                createShaped(RecipeCategory.COMBAT, ModItems.EXPLOSIVE_MIX, 4)
                        .pattern("BG ")
                        .pattern("GB ")
                        .pattern("   ")
                        .input('B', Items.BONE_MEAL)
                        .input('G', Items.GUNPOWDER)
                        .criterion(hasItem(Items.BONE_MEAL), conditionsFromItem(Items.BONE_MEAL))
                        .criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER))
                        .offerTo(exporter);

                // GUN BARREL
                createShaped(RecipeCategory.COMBAT, ModItems.GUN_BARREL)
                    .pattern("IIA")
                    .pattern("   ")
                    .pattern("IIA")
                    .input('I', Items.IRON_INGOT)
                    .input('A', ModItems.METAL_ALLOY)
                    .criterion(hasItem(ModItems.METAL_ALLOY), conditionsFromItem(ModItems.METAL_ALLOY))
                    .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                    .offerTo(exporter);

                // LOADING MECHANISM
                createShaped(RecipeCategory.COMBAT, ModItems.LOADING_MECHANISM)
                        .pattern("   ")
                        .pattern(" PR")
                        .pattern("ICI")
                        .input('P', Items.PISTON)
                        .input('R', Items.REPEATER)
                        .input('I', Items.IRON_INGOT)
                        .input('C', Items.COMPARATOR)
                        .criterion(hasItem(Items.PISTON), conditionsFromItem(Items.PISTON))
                        .criterion(hasItem(Items.REPEATER), conditionsFromItem(Items.REPEATER))
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .criterion(hasItem(Items.COMPARATOR), conditionsFromItem(Items.COMPARATOR))
                        .offerTo(exporter);

                // SHELL
                createShapeless(RecipeCategory.COMBAT, ModItems.SHELL)
                        .input(ModItems.CASING)
                        .input(ModItems.EXPLOSIVE_MIX)
                        .criterion(hasItem(ModItems.CASING), conditionsFromItem(ModItems.CASING))
                        .criterion(hasItem(ModItems.EXPLOSIVE_MIX), conditionsFromItem(ModItems.EXPLOSIVE_MIX))
                        .offerTo(exporter);

                // TRACK
                createShaped(RecipeCategory.COMBAT, ModItems.TRACK)
                        .pattern("   ")
                        .pattern("CCC")
                        .pattern("III")
                        .input('C', Items.COPPER_INGOT)
                        .input('I', Items.IRON_INGOT)
                        .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .offerTo(exporter);

                // BLONK
                createShaped(RecipeCategory.COMBAT, ModBlocks.BLONK)
                        .pattern("MMM")
                        .pattern("GLN")
                        .pattern("TTT")
                        .input('M', ModItems.METAL_ALLOY)
                        .input('G', ModItems.GUN_BARREL)
                        .input('L', ModItems.LOADING_MECHANISM)
                        .input('N', Items.NETHERITE_SCRAP)
                        .input('T', ModItems.TRACK)
                        .criterion(hasItem(ModItems.METAL_ALLOY), conditionsFromItem(ModItems.METAL_ALLOY))
                        .criterion(hasItem(ModItems.GUN_BARREL), conditionsFromItem(ModItems.GUN_BARREL))
                        .criterion(hasItem(Items.NETHERITE_SCRAP), conditionsFromItem(Items.NETHERITE_SCRAP))
                        .criterion(hasItem(ModItems.LOADING_MECHANISM), conditionsFromItem(ModItems.LOADING_MECHANISM))
                        .criterion(hasItem(ModItems.TRACK), conditionsFromItem(ModItems.TRACK))
                        .offerTo(exporter);

                // BLONK FROM BROKEN BLONK
                createShapeless(RecipeCategory.COMBAT, ModBlocks.BLONK)
                        .input(ModBlocks.BROKEN_BLONK)
                        .input(ModItems.SCRAP)
                        .criterion(hasItem(ModBlocks.BROKEN_BLONK.asItem()), conditionsFromItem(ModBlocks.BROKEN_BLONK.asItem()))
                        .criterion(hasItem(ModItems.SCRAP), conditionsFromItem(ModItems.SCRAP))
                        .offerTo(exporter);

                offerDyeableRecipes(
                        dyeList,
                        List.of(ModBlocks.BLONK.asItem()),
                        "wool",
                        RecipeCategory.COMBAT
                );

                // SCRAP SMELTING
                offerBlasting(List.of(ModItems.SCRAP), RecipeCategory.COMBAT, ModItems.METAL_ALLOY, 0.9F, 100, "blonk:metal_alloy");
                offerSmelting(List.of(ModItems.SCRAP), RecipeCategory.COMBAT, ModItems.METAL_ALLOY, 0.9F, 200, "blonk:metal_alloy");
            }
        };
    }

    @Override
    public String getName() {
        return "BlonkModRecipeGenerator";
    }
}
