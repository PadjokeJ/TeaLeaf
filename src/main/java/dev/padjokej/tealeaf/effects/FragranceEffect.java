package dev.padjokej.tealeaf.effects;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class FragranceEffect extends StatusEffect {
    public FragranceEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0);

        this.addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, "dab24cbc-7bff-4ae5-b5f6-2733328e385e",
                        0.15, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                .addAttributeModifier(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, "dab24cbc-7bff-4ae5-b5f6-2733328e385f",
                        0.05, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                .addAttributeModifier(EntityAttributes.GENERIC_SAFE_FALL_DISTANCE, "dab24cbc-7bff-4ae5-b5f6-2733328e3860",
                        0.20, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

}
