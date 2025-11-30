package com.techibuzz.blonk.datagen;

import com.techibuzz.blonk.block.ModBlocks;
import com.techibuzz.blonk.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
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

    private static final List<Item> blonkList = List.of(
            ModBlocks.BLACK_BLONK.asItem(),
            ModBlocks.BLUE_BLONK.asItem(),
            ModBlocks.BROWN_BLONK.asItem(),
            ModBlocks.CYAN_BLONK.asItem(),
            ModBlocks.GRAY_BLONK.asItem(),
            ModBlocks.GREEN_BLONK.asItem(),
            ModBlocks.LIGHT_BLUE_BLONK.asItem(),
            ModBlocks.LIGHT_GRAY_BLONK.asItem(),
            ModBlocks.LIME_BLONK.asItem(),
            ModBlocks.MAGENTA_BLONK.asItem(),
            ModBlocks.ORANGE_BLONK.asItem(),
            ModBlocks.PINK_BLONK.asItem(),
            ModBlocks.PURPLE_BLONK.asItem(),
            ModBlocks.RED_BLONK.asItem(),
            ModBlocks.WHITE_BLONK.asItem(),
            ModBlocks.YELLOW_BLONK.asItem()
    );

    @Override
    protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                // AMMO RACK
                nineBlockStorageRecipesRecipesWithCustomUnpacking(
                        RecipeCategory.COMBAT, ModItems.SHELL, RecipeCategory.COMBAT, ModBlocks.AMMO_RACK, "shell_from_ammo_rack", "shell"
                );

                // CASING
                shaped(RecipeCategory.COMBAT, ModItems.CASING, 4)
                        .pattern("G  ")
                        .pattern("G  ")
                        .pattern("   ")
                        .define('G', Items.GOLD_INGOT)
                        .unlockedBy(getHasName(Items.GOLD_NUGGET), has(Items.GOLD_NUGGET))
                        .save(output);

                // EXPLOSIVE MIX
                shaped(RecipeCategory.COMBAT, ModItems.EXPLOSIVE_MIX, 4)
                        .pattern("BG ")
                        .pattern("GB ")
                        .pattern("   ")
                        .define('B', Items.BONE_MEAL)
                        .define('G', Items.GUNPOWDER)
                        .unlockedBy(getHasName(Items.BONE_MEAL), has(Items.BONE_MEAL))
                        .unlockedBy(getHasName(Items.GUNPOWDER), has(Items.GUNPOWDER))
                        .save(output);

                // GUN BARREL
                shaped(RecipeCategory.COMBAT, ModItems.GUN_BARREL)
                    .pattern("IIA")
                    .pattern("   ")
                    .pattern("IIA")
                    .define('I', Items.IRON_INGOT)
                    .define('A', ModItems.METAL_ALLOY)
                    .unlockedBy(getHasName(ModItems.METAL_ALLOY), has(ModItems.METAL_ALLOY))
                    .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                    .save(output);

                // SHELL
                shapeless(RecipeCategory.COMBAT, ModItems.SHELL)
                        .requires(ModItems.CASING)
                        .requires(ModItems.EXPLOSIVE_MIX)
                        .unlockedBy(getHasName(ModItems.CASING), has(ModItems.CASING))
                        .unlockedBy(getHasName(ModItems.EXPLOSIVE_MIX), has(ModItems.EXPLOSIVE_MIX))
                        .save(output);

                // BLONK
                shaped(RecipeCategory.COMBAT, ModBlocks.BLONK)
                        .pattern("MMM")
                        .pattern("GPN")
                        .pattern("KKK")
                        .define('M', ModItems.METAL_ALLOY)
                        .define('G', ModItems.GUN_BARREL)
                        .define('P', Blocks.PISTON)
                        .define('N', Items.NETHERITE_SCRAP)
                        .define('K', Blocks.DRIED_KELP_BLOCK)
                        .unlockedBy(getHasName(ModItems.METAL_ALLOY), has(ModItems.METAL_ALLOY))
                        .unlockedBy(getHasName(ModItems.GUN_BARREL), has(ModItems.GUN_BARREL))
                        .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP))
                        .save(output);

                // DYE-ABLE BLONKS
                colorWithDye(
                        dyeList,
                        blonkList,
                        ModBlocks.BLONK.asItem(),
                        "blonk",
                        RecipeCategory.COMBAT
                );

                // SCRAP -> METAL ALLOY SMELT
                oreBlasting(List.of(ModItems.SCRAP), RecipeCategory.COMBAT, ModItems.METAL_ALLOY, 0.9F, 100, "blonk:metal_alloy");
                oreSmelting(List.of(ModItems.SCRAP), RecipeCategory.COMBAT, ModItems.METAL_ALLOY, 0.9F, 200, "blonk:metal_alloy");

                // CASING -> GOLD NUGGET SMELT
                oreBlasting(List.of(ModItems.CASING), RecipeCategory.COMBAT, Items.GOLD_NUGGET, 0.1F, 100, "blonk:casing");
                oreSmelting(List.of(ModItems.CASING), RecipeCategory.COMBAT, Items.GOLD_NUGGET, 0.1F, 200, "blonk:casing");
            }
        };
    }

    @Override
    public @NotNull String getName() {
        return "BlonkModRecipeGenerator";
    }
}
