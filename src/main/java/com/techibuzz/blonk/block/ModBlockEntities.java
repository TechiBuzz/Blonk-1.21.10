package com.techibuzz.blonk.block;

import com.techibuzz.blonk.Blonk;
import com.techibuzz.blonk.block.entity.BlonkBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {

    public static final BlockEntityType<BlonkBlockEntity> BLONK_BLOCK_ENTITY = register(
            "blonk",
            BlonkBlockEntity::new,
            ModBlocks.BLONK,

            ModBlocks.BLACK_BLONK,
            ModBlocks.BLUE_BLONK,
            ModBlocks.BROWN_BLONK,
            ModBlocks.CYAN_BLONK,
            ModBlocks.GRAY_BLONK,
            ModBlocks.GREEN_BLONK,
            ModBlocks.LIGHT_BLUE_BLONK,
            ModBlocks.LIGHT_GRAY_BLONK,
            ModBlocks.LIME_BLONK,
            ModBlocks.MAGENTA_BLONK,
            ModBlocks.ORANGE_BLONK,
            ModBlocks.PINK_BLONK,
            ModBlocks.PURPLE_BLONK,
            ModBlocks.RED_BLONK,
            ModBlocks.WHITE_BLONK,
            ModBlocks.YELLOW_BLONK
    );

    private static <T extends BlockEntity> BlockEntityType<T> register(String name, FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory, Block... blocks) {
        return Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, name),
                FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build()
        );
    }

    public static void registerModBlockEntities() {
        Blonk.LOGGER.info("Registering Block Entities for - " + Blonk.MOD_ID);
    }

}
