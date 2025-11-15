package com.techibuzz.blonk.item;

import com.techibuzz.blonk.Blonk;
import com.techibuzz.blonk.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final RegistryKey<ItemGroup> BLONK_ITEM_GROUP_KEY = RegistryKey.of(
            Registries.ITEM_GROUP.getKey(),
            Identifier.of("blonk_item_group", Blonk.MOD_ID)
    );
    public static final ItemGroup BLONK_ITEM_GROUP = FabricItemGroup.builder()
            .displayName(Text.translatable("itemGroup.blonk_item_group"))
            .icon(() -> new ItemStack(ModItems.SCRAP))
            .entries((displayContext, entries) -> {
                entries.add(ModItems.CASING);
                entries.add(ModItems.EXPLOSIVE_MIX);
                entries.add(ModItems.GUN_BARREL);
                entries.add(ModItems.LOADING_MECHANISM);
                entries.add(ModItems.METAL_ALLOY);
                entries.add(ModItems.SCRAP);
                entries.add(ModItems.SHELL);
                entries.add(ModItems.TRACK);

                entries.add(ModBlocks.AMMO_RACK);
                entries.add(ModBlocks.CHONK);
                entries.add(ModBlocks.BLONK);
                entries.add(ModBlocks.BROKEN_BLONK);
            })
            .build();

    public static void registerModItemGroups() {
        Blonk.LOGGER.info("Registering mod item groups for - " + Blonk.MOD_ID);

        Registry.register(Registries.ITEM_GROUP, BLONK_ITEM_GROUP_KEY, BLONK_ITEM_GROUP);
    }

}
