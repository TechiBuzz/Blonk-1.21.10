package com.techibuzz.blonk.item;

import com.techibuzz.blonk.Blonk;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {

    public static final TagKey<Item> SHELLS = bind("shells");

    private static TagKey<Item> bind(String name) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Blonk.MOD_ID, name));
    }

    public static void registerModItems() {
        Blonk.LOGGER.info("Registering ItemTags for - " + Blonk.MOD_ID);
    }
}
