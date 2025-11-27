package com.techibuzz.blonk.screen;

import com.techibuzz.blonk.Blonk;
import com.techibuzz.blonk.screen.custom.BlonkMenu;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class ModScreens {

    public static final MenuType<BlonkMenu> BLONK_SCREEN_HANDLER = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, "blonk_screen"),
            new MenuType<>(BlonkMenu::new, FeatureFlags.VANILLA_SET)
    );

    public static void registerModScreens() {
        Blonk.LOGGER.info("Registering Screens for - " + Blonk.MOD_ID);
    }
}
