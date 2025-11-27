package com.techibuzz.blonk.entity;

import com.techibuzz.blonk.Blonk;
import com.techibuzz.blonk.entity.custom.ShellEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    private static final ResourceKey<EntityType<?>> SHELL_KEY = ResourceKey.create(
            Registries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, "shell")
    );

    public static final EntityType<ShellEntity> SHELL = Registry.register(
            BuiltInRegistries.ENTITY_TYPE, SHELL_KEY,
            EntityType.Builder.<ShellEntity>of(ShellEntity::new, MobCategory.MISC)
                    .sized(4/16F, 4/16F)
                    .build(SHELL_KEY)
    );

    public static void registerModEntities() {
        Blonk.LOGGER.info("Registering Entities for - " + Blonk.MOD_ID);
    }
}
