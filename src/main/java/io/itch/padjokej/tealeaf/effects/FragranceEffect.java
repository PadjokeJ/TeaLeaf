package io.itch.padjokej.tealeaf.effects;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class FragranceEffect extends StatusEffect
{
    private final double modifier;
    public FragranceEffect(double modifier) {
        super(StatusEffectCategory.BENEFICIAL, 0);
        this.modifier = modifier;
    }
    @Override
    public double adjustModifierAmount(int amplifier, EntityAttributeModifier modifier) {
        return this.modifier * amplifier * 0.1f;
    }


    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

}
