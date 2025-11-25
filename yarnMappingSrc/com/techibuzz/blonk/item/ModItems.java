package com.techibuzz.blonk.item;

import com.techibuzz.blonk.Blonk;
import com.techibuzz.blonk.item.custom.ShellTestingItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    public static final Item CASING = registerItem(
            "casing",
            Item::new,
            new Item.Settings()
    );

    public static final Item EXPLOSIVE_MIX = registerItem(
            "explosive_mix",
            Item::new,
            new Item.Settings()
    );

    public static final Item GUN_BARREL = registerItem(
            "gun_barrel",
            Item::new,
            new Item.Settings()
    );

    public static final Item METAL_ALLOY = registerItem(
            "metal_alloy",
            Item::new,
            new Item.Settings()
    );

    public static final Item SCRAP = registerItem(
            "scrap",
            Item::new,
            new Item.Settings()
    );

    public static final Item SHELL = registerItem(
            "shell",
            Item::new,
            new Item.Settings()
    );

    public static final Item SHELL_TESTING_ITEM = registerItem(
            "shell_testing_item",
            ShellTestingItem::new,
            new Item.Settings()
    );


    private static Item registerItem(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Blonk.MOD_ID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        return Registry.register(Registries.ITEM, itemKey, item);
    }

    public static void registerModItems() {
        Blonk.LOGGER.info("Registering mod items for - " + Blonk.MOD_ID);
    }
}
