package com.techibuzz.blonk.block;

import com.techibuzz.blonk.Blonk;
import com.techibuzz.blonk.block.custom.BlonkBlock;
import com.techibuzz.blonk.block.custom.BrokenBlonkBlock;
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
                    .sounds(BlockSoundGroup.IRON)
                    .breakInstantly(),
            true
    );

    public static final Block BLONK = register(
            "blonk",
            BlonkBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.NETHERITE)
                    .requiresTool()
                    .strength(50.0F, 1200.0F),
            true
    );

    public static final Block CHONK = register(
            "chonk",
            BlonkBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.NETHERITE)
                    .requiresTool()
                    .strength(50.0F, 1200.0F),
            true
    );

    public static final Block BROKEN_BLONK = register(
            "broken_blonk",
            BrokenBlonkBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.GRAY)
                    .sounds(BlockSoundGroup.IRON)
                    .requiresTool()
                    .strength(5.0F, 6.0F),
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
