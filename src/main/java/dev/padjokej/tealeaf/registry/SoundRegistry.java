package dev.padjokej.tealeaf.registry;

import dev.padjokej.tealeaf.TeaLeaf;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;

public enum SoundRegistry {
    SNIFFING("sniff");

    private final String pathName;
    private final SoundEvent soundEvent;

    SoundRegistry(String pathName) {
        this.pathName = pathName;
        this.soundEvent = SoundEvent.of(TeaLeaf.id(this.pathName));
    }

    public static void registerAll() {
        for (SoundRegistry value : values()) {
            Registry.register(Registries.SOUND_EVENT, TeaLeaf.id(value.pathName), value.soundEvent);
        }
    }

    public SoundEvent get() {
        return soundEvent;
    }
}
