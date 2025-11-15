package com.techibuzz.blonk.block;

import com.techibuzz.blonk.Blonk;
import com.techibuzz.blonk.block.entity.BlonkBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static final BlockEntityType<BlonkBlockEntity> BLONK_BLOCK_ENTITY = register(
            "blonk",
            BlonkBlockEntity::new,
            ModBlocks.BLONK,
            ModBlocks.CHONK
    );

    private static <T extends BlockEntity> BlockEntityType<T> register(String name, FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory, Block... blocks) {
        return Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                Identifier.of(Blonk.MOD_ID, name),
                FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build()
        );
    }

    public static void registerModBlockEntities() {
        Blonk.LOGGER.info("Registering mod block entities for - " + Blonk.MOD_ID);
    }

}
