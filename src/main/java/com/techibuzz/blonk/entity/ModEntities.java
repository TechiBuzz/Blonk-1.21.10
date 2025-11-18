package com.techibuzz.blonk.entity;

import com.techibuzz.blonk.Blonk;
import com.techibuzz.blonk.entity.custom.ShellProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final RegistryKey<EntityType<?>> SHELL_KEY = RegistryKey.of(Registries.ENTITY_TYPE.getKey(), Identifier.of(Blonk.MOD_ID, "shell"));
    public static final EntityType<ShellProjectileEntity> SHELL = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Blonk.MOD_ID, "shell"),
            EntityType.Builder.create(ShellProjectileEntity::new, SpawnGroup.MISC).dimensions(4/16f, 7/16f).build(SHELL_KEY)
    );

    public static void registerModEntities() {
        Blonk.LOGGER.info("Registering mod entities for - " + Blonk.MOD_ID);
    }

}
