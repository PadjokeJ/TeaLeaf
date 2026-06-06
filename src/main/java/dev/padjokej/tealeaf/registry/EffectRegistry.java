package dev.padjokej.tealeaf.registry;

import dev.padjokej.tealeaf.TeaLeaf;
import dev.padjokej.tealeaf.effects.FragranceEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class EffectRegistry {
    public static final RegistryEntry<StatusEffect> FRAGRANCE = Registry.registerReference(Registries.STATUS_EFFECT,
            TeaLeaf.id("fragrance"), new FragranceEffect());

    public static void registerAll() {
    }
}
