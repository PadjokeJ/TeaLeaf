package dev.padjokej.tealeaf.registry;

import dev.padjokej.tealeaf.TeaLeaf;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public enum SoundRegistry
{
    SNIFFING("sniff");

    private final String pathName;
    private final SoundEvent soundEvent;

    SoundRegistry(String pathName) {
        this.pathName = pathName;
        this.soundEvent = SoundEvent.of(new Identifier(TeaLeaf.MOD_ID, this.pathName));
    }

    public static void registerAll() {
        for (SoundRegistry value : values()) {
            Registry.register(Registries.SOUND_EVENT, new Identifier(TeaLeaf.MOD_ID, value.pathName), value.soundEvent);
        }
    }

    public SoundEvent get() {
        return soundEvent;
    }
}
