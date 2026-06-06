package dev.padjokej.tealeaf.registry;

import dev.padjokej.tealeaf.TeaLeaf;
import dev.padjokej.tealeaf.effects.FragranceEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class EffectRegistry {
    public static final RegistryEntry<StatusEffect> FRAGRANCE = Registry.registerReference(Registries.STATUS_EFFECT,
            new Identifier(TeaLeaf.MOD_ID, "fragrance"), new FragranceEffect());//("fragrance", new FragranceEffect(.1).addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, "dab24cbc-7bff-4ae5-b5f6-2733328e385e", 0.15, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static void registerAll() {
    }
}
