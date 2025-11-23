package com.techibuzz.blonk.sound;

import com.techibuzz.blonk.Blonk;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent BLONK_SHOOT = registerSound("blonk_shoot");

    private static SoundEvent registerSound(String name) {
        Identifier id = Identifier.of(Blonk.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerModSounds() {
        Blonk.LOGGER.info("Registering mod sounds for - " + Blonk.MOD_ID);
    }
}
