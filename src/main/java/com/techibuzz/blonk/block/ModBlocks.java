package com.techibuzz.blonk.block;

import com.techibuzz.blonk.Blonk;
import com.techibuzz.blonk.block.custom.BlonkBlock;
import com.techibuzz.blonk.block.custom.BrokenBlonkBlock;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.DamageResistant;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class ModBlocks {
    public static final Block AMMO_RACK = register(
            "ammo_rack",
            Block::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_YELLOW)
                    .sound(SoundType.IRON)
                    .instabreak(),
            true,
            new Item.Properties().fireResistant()
    );

    public static final Block BLONK = register(
            "blonk",
            BlonkBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Properties().fireResistant()
    );

    public static final Block BLACK_BLONK = register(
            "black_blonk",
            BlonkBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Properties().fireResistant()
    );

    public static final Block BLUE_BLONK = register(
            "blue_blonk",
            BlonkBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Properties().fireResistant()
    );

    public static final Block BROWN_BLONK = register(
            "brown_blonk",
            BlonkBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Properties().fireResistant()
    );

    public static final Block CYAN_BLONK = register(
            "cyan_blonk",
            BlonkBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Properties().fireResistant()
    );

    public static final Block GRAY_BLONK = register(
            "gray_blonk",
            BlonkBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Properties().fireResistant()
    );

    public static final Block GREEN_BLONK = register(
            "green_blonk",
            BlonkBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Properties().fireResistant()
    );

    public static final Block LIGHT_BLUE_BLONK = register(
            "light_blue_blonk",
            BlonkBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Properties().fireResistant()
    );

    public static final Block LIGHT_GRAY_BLONK = register(
            "light_gray_blonk",
            BlonkBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Properties().fireResistant()
    );

    public static final Block LIME_BLONK = register(
            "lime_blonk",
            BlonkBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Properties().fireResistant()
    );

    public static final Block MAGENTA_BLONK = register(
            "magenta_blonk",
            BlonkBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Properties().fireResistant()
    );

    public static final Block ORANGE_BLONK = register(
            "orange_blonk",
            BlonkBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Properties().fireResistant()
    );

    public static final Block PINK_BLONK = register(
            "pink_blonk",
            BlonkBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Properties().fireResistant()
    );

    public static final Block PURPLE_BLONK = register(
            "purple_blonk",
            BlonkBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Properties().fireResistant()
    );

    public static final Block RED_BLONK = register(
            "red_blonk",
            BlonkBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Properties().fireResistant()
    );

    public static final Block WHITE_BLONK = register(
            "white_blonk",
            BlonkBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Properties().fireResistant()
    );

    public static final Block YELLOW_BLONK = register(
            "yellow_blonk",
            BlonkBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(50.0F, 1200.0F),
            true,
            new Item.Properties().fireResistant()
    );

    public static final Block BROKEN_BLONK = register(
            "broken_blonk",
            BrokenBlonkBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GRAY)
                    .sound(SoundType.IRON)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F),
            true,
            new Item.Properties().component(DataComponents.DAMAGE_RESISTANT, new DamageResistant(DamageTypeTags.IS_EXPLOSION)).fireResistant()
    );

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem, Item.Properties customItemSettings) {
        ResourceKey<Block> blockKey = ResourceKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, name));
        Block block = blockFactory.apply(settings.setId(blockKey));

        // Blocks with items
        if (shouldRegisterItem) {
            ResourceKey<Item> itemKey =  ResourceKey.create(BuiltInRegistries.ITEM.key(), ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, name));

            BlockItem blockItem = new BlockItem(block, customItemSettings.setId(itemKey).useBlockDescriptionPrefix());

            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    public static void registerModBlocks() {
        Blonk.LOGGER.info("Registering Blocks for - " + Blonk.MOD_ID);
    }
}
