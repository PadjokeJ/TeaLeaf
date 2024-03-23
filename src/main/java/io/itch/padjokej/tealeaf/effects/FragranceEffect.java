package io.itch.padjokej.tealeaf.effects;

import io.itch.padjokej.tealeaf.TeaLeaf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

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
