package com.techibuzz.blonk.item;

import com.techibuzz.blonk.Blonk;
import com.techibuzz.blonk.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroups {
    public static final ResourceKey<CreativeModeTab> BLONK_ITEM_GROUP_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(),
            ResourceLocation.fromNamespaceAndPath("blonk_item_group", Blonk.MOD_ID)
    );
    public static final CreativeModeTab BLONK_ITEM_GROUP = FabricItemGroup.builder()
            .title(Component.translatable("itemGroup.blonk_item_group"))
            .icon(() -> new ItemStack(ModItems.SCRAP))
            .displayItems((displayContext, entries) -> {
                entries.accept(ModItems.CASING);
                entries.accept(ModItems.EXPLOSIVE_MIX);
                entries.accept(ModItems.GUN_BARREL);
                entries.accept(ModItems.METAL_ALLOY);
                entries.accept(ModItems.SCRAP);
                entries.accept(ModItems.SHELL);

                entries.accept(ModBlocks.AMMO_RACK);

                entries.accept(ModBlocks.BLONK);

                entries.accept(ModBlocks.WHITE_BLONK);
                entries.accept(ModBlocks.GRAY_BLONK);
                entries.accept(ModBlocks.LIGHT_GRAY_BLONK);
                entries.accept(ModBlocks.BLACK_BLONK);
                entries.accept(ModBlocks.BROWN_BLONK);
                entries.accept(ModBlocks.RED_BLONK);
                entries.accept(ModBlocks.ORANGE_BLONK);
                entries.accept(ModBlocks.YELLOW_BLONK);
                entries.accept(ModBlocks.LIME_BLONK);
                entries.accept(ModBlocks.GREEN_BLONK);
                entries.accept(ModBlocks.CYAN_BLONK);
                entries.accept(ModBlocks.LIGHT_BLUE_BLONK);
                entries.accept(ModBlocks.BLUE_BLONK);
                entries.accept(ModBlocks.PURPLE_BLONK);
                entries.accept(ModBlocks.MAGENTA_BLONK);
                entries.accept(ModBlocks.PINK_BLONK);

                entries.accept(ModBlocks.BROKEN_BLONK);
            })
            .build();

    public static void registerModItemGroups() {
        Blonk.LOGGER.info("Registering mod item groups for - " + Blonk.MOD_ID);

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, BLONK_ITEM_GROUP_KEY, BLONK_ITEM_GROUP);
    }
}
