package com.techibuzz.blonk.sound;

import com.techibuzz.blonk.Blonk;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {
    public static final SoundEvent BLONK_LOAD = registerSound("blonk_load");
    public static final SoundEvent BLONK_LOAD_FAIL = registerSound("blonk_load_fail");
    public static final SoundEvent BLONK_SHOOT = registerSound("blonk_shoot");
    public static final SoundEvent BLONK_REPAIR = registerSound("blonk_repair");

    private static SoundEvent registerSound(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void registerModSounds() {
        Blonk.LOGGER.info("Registering Sounds for - " + Blonk.MOD_ID);
    }
}
