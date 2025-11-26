package com.techibuzz.blonk.item;

import com.techibuzz.blonk.Blonk;
import com.techibuzz.blonk.item.custom.ShellTestingItem;
import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItems {
    public static final Item CASING = registerItem(
            "casing",
            Item::new,
            new Item.Properties()
    );

    public static final Item EXPLOSIVE_MIX = registerItem(
            "explosive_mix",
            Item::new,
            new Item.Properties()
    );

    public static final Item GUN_BARREL = registerItem(
            "gun_barrel",
            Item::new,
            new Item.Properties()
    );

    public static final Item METAL_ALLOY = registerItem(
            "metal_alloy",
            Item::new,
            new Item.Properties()
    );

    public static final Item SCRAP = registerItem(
            "scrap",
            Item::new,
            new Item.Properties()
    );

    public static final Item SHELL = registerItem(
            "shell",
            Item::new,
            new Item.Properties()
    );

    public static final Item SHELL_TESTING_ITEM = registerItem(
            "shell_testing_item",
            ShellTestingItem::new,
            new Item.Properties()
    );


    private static Item registerItem(String name, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, name));
        Item item = itemFactory.apply(settings.setId(itemKey));

        return Registry.register(BuiltInRegistries.ITEM, itemKey, item);
    }

    public static void registerModItems() {
        Blonk.LOGGER.info("Registering Items for - " + Blonk.MOD_ID);
    }
}
