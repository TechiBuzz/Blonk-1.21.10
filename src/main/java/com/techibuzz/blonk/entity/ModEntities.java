package com.techibuzz.blonk.entity;

import com.techibuzz.blonk.Blonk;
import com.techibuzz.blonk.entity.custom.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.jetbrains.annotations.NotNull;

public class ModEntities {
    private static final ResourceKey<@NotNull EntityType<?>> SHELL_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Blonk.MOD_ID, "shell"));
    public static final EntityType<@NotNull Shell> SHELL = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, SHELL_KEY,
            EntityType.Builder.<Shell>of(Shell::new, MobCategory.MISC).sized(4/16F, 4/16F).build(SHELL_KEY)
    );

    private static final ResourceKey<@NotNull EntityType<?>> DRAGON_SHELL_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Blonk.MOD_ID, "dragon_shell"));
    public static final EntityType<@NotNull Shell> DRAGON_SHELL = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, DRAGON_SHELL_KEY,
            EntityType.Builder.<Shell>of(DragonShell::new, MobCategory.MISC).sized(4/16F, 4/16F).build(DRAGON_SHELL_KEY)
    );
    private static final ResourceKey<@NotNull EntityType<?>> HEAT_SHELL_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Blonk.MOD_ID, "heat_shell"));
    public static final EntityType<@NotNull Shell> HEAT_SHELL = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, HEAT_SHELL_KEY,
            EntityType.Builder.<Shell>of(HeatShell::new, MobCategory.MISC).sized(6/16F, 6/16F).build(HEAT_SHELL_KEY)
    );

    private static final ResourceKey<@NotNull EntityType<?>> HE_SHELL_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Blonk.MOD_ID, "he_shell"));
    public static final EntityType<@NotNull Shell> HE_SHELL = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, HE_SHELL_KEY,
            EntityType.Builder.<Shell>of(HEShell::new, MobCategory.MISC).sized(4/16F, 4/16F).build(HE_SHELL_KEY)
    );

    private static final ResourceKey<@NotNull EntityType<?>> NUCLEAR_SHELL_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Blonk.MOD_ID, "nuclear_shell"));
    public static final EntityType<@NotNull Shell> NUCLEAR_SHELL = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, NUCLEAR_SHELL_KEY,
            EntityType.Builder.<Shell>of(NuclearShell::new, MobCategory.MISC).sized(12/16F, 12/16F).build(NUCLEAR_SHELL_KEY)
    );

    private static final ResourceKey<@NotNull EntityType<?>> SMOKE_SHELL_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Blonk.MOD_ID, "smoke_shell"));
    public static final EntityType<@NotNull Shell> SMOKE_SHELL = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, SMOKE_SHELL_KEY,
            EntityType.Builder.<Shell>of(SmokeShell::new, MobCategory.MISC).sized(4/16F, 4/16F).build(SMOKE_SHELL_KEY)
    );

    public static void registerModEntities() {
        Blonk.LOGGER.info("Registering Entities for - " + Blonk.MOD_ID);
    }
}
