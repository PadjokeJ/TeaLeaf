package dev.padjokej.tealeaf.registry;

import dev.padjokej.tealeaf.TeaLeaf;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.logging.Logger;

public class SoundRegistry {
    public static final RegistryEntry<SoundEvent> SNIFFING = register("sniff");

    private static RegistryEntry<SoundEvent> register(String pathName) {
        Identifier id = TeaLeaf.id(pathName);

        SoundEvent soundEvent = SoundEvent.of(id);
        return Registry.registerReference(Registries.SOUND_EVENT, id, soundEvent);
    }

    public static void registerAll() {
        TeaLeaf.LOGGER.info("Registering sounds !");
    }
}
