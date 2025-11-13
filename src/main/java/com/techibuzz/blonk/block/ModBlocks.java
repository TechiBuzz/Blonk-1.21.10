package com.techibuzz.blonk.block;

import com.techibuzz.blonk.Blonk;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {

    public static final Block AMMO_RACK = register(
            "ammo_rack",
            Block::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.YELLOW)
                    .sounds(BlockSoundGroup.CHAIN)
                    .breakInstantly(),
            true
    );

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        RegistryKey<Block> blockKey = RegistryKey.of(Registries.BLOCK.getKey(), Identifier.of(Blonk.MOD_ID, name));
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        // Blocks with items
        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey =  RegistryKey.of(Registries.ITEM.getKey(), Identifier.of(Blonk.MOD_ID, name));

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey());
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    public static void registerModBlocks() {
        Blonk.LOGGER.info("Registering mod blocks for - " + Blonk.MOD_ID);
    }
}
