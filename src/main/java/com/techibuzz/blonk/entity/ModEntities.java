package com.techibuzz.blonk.entity;

import com.techibuzz.blonk.Blonk;
import com.techibuzz.blonk.entity.custom.ShellEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {

    private static final RegistryKey<EntityType<?>> SHELL_KEY = RegistryKey.of(
            RegistryKeys.ENTITY_TYPE,
            Identifier.of(Blonk.MOD_ID, "shell")
    );

    public static final EntityType<ShellEntity> SHELL = Registry.register(
            Registries.ENTITY_TYPE, SHELL_KEY,
            EntityType.Builder.create(ShellEntity::new, SpawnGroup.MISC)
                    .dimensions(4/16F, 4/16F)
                    .build(SHELL_KEY)
    );

    public static void registerModEntities() {
        Blonk.LOGGER.info("Registering mod entities for - " + Blonk.MOD_ID);
    }

}
