package com.techibuzz.blonk.block;

import com.techibuzz.blonk.Blonk;
import com.techibuzz.blonk.block.custom.BlonkBlock;
import com.techibuzz.blonk.block.custom.BrokenBlonkBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DamageResistantComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.DamageTypeTags;
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
            true,
            new Item.Settings().fireproof()
    );

    public static final Block BLONK = register(
            "blonk",
            BlonkBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.NETHERITE)
                    .requiresTool()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Settings().fireproof()
    );

    public static final Block BLACK_BLONK = register(
            "black_blonk",
            BlonkBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.NETHERITE)
                    .requiresTool()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Settings().fireproof()
    );

    public static final Block BLUE_BLONK = register(
            "blue_blonk",
            BlonkBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.NETHERITE)
                    .requiresTool()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Settings().fireproof()
    );

    public static final Block BROWN_BLONK = register(
            "brown_blonk",
            BlonkBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.NETHERITE)
                    .requiresTool()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Settings().fireproof()
    );

    public static final Block CYAN_BLONK = register(
            "cyan_blonk",
            BlonkBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.NETHERITE)
                    .requiresTool()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Settings().fireproof()
    );

    public static final Block GRAY_BLONK = register(
            "gray_blonk",
            BlonkBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.NETHERITE)
                    .requiresTool()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Settings().fireproof()
    );

    public static final Block GREEN_BLONK = register(
            "green_blonk",
            BlonkBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.NETHERITE)
                    .requiresTool()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Settings().fireproof()
    );

    public static final Block LIGHT_BLUE_BLONK = register(
            "light_blue_blonk",
            BlonkBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.NETHERITE)
                    .requiresTool()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Settings().fireproof()
    );

    public static final Block LIGHT_GRAY_BLONK = register(
            "light_gray_blonk",
            BlonkBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.NETHERITE)
                    .requiresTool()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Settings().fireproof()
    );

    public static final Block LIME_BLONK = register(
            "lime_blonk",
            BlonkBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.NETHERITE)
                    .requiresTool()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Settings().fireproof()
    );

    public static final Block MAGENTA_BLONK = register(
            "magenta_blonk",
            BlonkBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.NETHERITE)
                    .requiresTool()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Settings().fireproof()
    );

    public static final Block ORANGE_BLONK = register(
            "orange_blonk",
            BlonkBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.NETHERITE)
                    .requiresTool()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Settings().fireproof()
    );

    public static final Block PINK_BLONK = register(
            "pink_blonk",
            BlonkBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.NETHERITE)
                    .requiresTool()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Settings().fireproof()
    );

    public static final Block PURPLE_BLONK = register(
            "purple_blonk",
            BlonkBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.NETHERITE)
                    .requiresTool()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Settings().fireproof()
    );

    public static final Block RED_BLONK = register(
            "red_blonk",
            BlonkBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.NETHERITE)
                    .requiresTool()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Settings().fireproof()
    );

    public static final Block WHITE_BLONK = register(
            "white_blonk",
            BlonkBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.NETHERITE)
                    .requiresTool()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Settings().fireproof()
    );

    public static final Block YELLOW_BLONK = register(
            "yellow_blonk",
            BlonkBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .sounds(BlockSoundGroup.NETHERITE)
                    .requiresTool()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Settings().fireproof()
    );

    public static final Block BROKEN_BLONK = register(
            "broken_blonk",
            BrokenBlonkBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.GRAY)
                    .sounds(BlockSoundGroup.IRON)
                    .requiresTool()
                    .strength(5.0F, 6.0F),
            true,
            new Item.Settings()
                    .fireproof()
                    .component(DataComponentTypes.DAMAGE_RESISTANT, new DamageResistantComponent(DamageTypeTags.IS_EXPLOSION))
    );

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem, Item.Settings customItemSettings) {
        RegistryKey<Block> blockKey = RegistryKey.of(Registries.BLOCK.getKey(), Identifier.of(Blonk.MOD_ID, name));
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        // Blocks with items
        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey =  RegistryKey.of(Registries.ITEM.getKey(), Identifier.of(Blonk.MOD_ID, name));

            BlockItem blockItem = new BlockItem(block, customItemSettings.registryKey(itemKey).useBlockPrefixedTranslationKey());

            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    public static void registerModBlocks() {
        Blonk.LOGGER.info("Registering mod blocks for - " + Blonk.MOD_ID);
    }
}
