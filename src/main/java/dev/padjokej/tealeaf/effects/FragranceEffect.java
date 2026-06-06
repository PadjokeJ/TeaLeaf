package dev.padjokej.tealeaf.effects;

import dev.padjokej.tealeaf.TeaLeaf;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class FragranceEffect extends StatusEffect {
    public FragranceEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xD3AF37);

        this.addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, TeaLeaf.id("speed"),
                        0.15, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                .addAttributeModifier(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, TeaLeaf.id("kb-res"),
                        0.05, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                .addAttributeModifier(EntityAttributes.GENERIC_SAFE_FALL_DISTANCE, TeaLeaf.id("fall-distance"),
                        0.20, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

}
