package io.itch.padjokej.tealeaf.registry;

import io.itch.padjokej.tealeaf.TeaLeaf;
import io.itch.padjokej.tealeaf.effects.FragranceEffect;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum EffectRegistry
{
    FRAGRANCE("fragrance", new FragranceEffect(.1)
            .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, "dab24cbc-7bff-4ae5-b5f6-2733328e385e", 0.0, EntityAttributeModifier.Operation.ADDITION));

    private final String pathName;
    private final StatusEffect effect;
    EffectRegistry(String pathName, StatusEffect effect)
    {
        this.pathName = pathName;
        this.effect = effect;
    }
    public static void registerAll() {
        for (EffectRegistry value : values()) {
            Registry.register(Registry.STATUS_EFFECT,
                    new Identifier(TeaLeaf.MOD_ID, value.pathName), value.effect);
        }
    }
    public StatusEffect get() {
        return effect;
    }
}
