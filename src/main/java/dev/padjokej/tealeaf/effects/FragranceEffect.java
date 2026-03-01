package dev.padjokej.tealeaf.effects;

import net.minecraft.entity.LivingEntity;
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
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

}
