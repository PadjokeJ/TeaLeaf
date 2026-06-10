package dev.padjokej.tealeaf.item;

import dev.padjokej.tealeaf.registry.EffectRegistry;
import dev.padjokej.tealeaf.registry.SoundRegistry;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.UseAction;

public class ModFoodComponents {
    public static final FoodComponent FRAGRANCE_PROVIDER = new FoodComponent.Builder().nutrition(0).saturationModifier(0).alwaysEdible().build();

    public static final ConsumableComponent SNIFFING_LEAF = ConsumableComponent.builder()
            .consumeSeconds(1.6F)
            .useAction(UseAction.EAT)
            .sound(SoundRegistry.SNIFFING)
            .consumeParticles(false)
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(EffectRegistry.FRAGRANCE, 200, 4)))
            .build();

    public static final ConsumableComponent DRINKING_TEA = ConsumableComponents.drink()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(EffectRegistry.FRAGRANCE, 6000, 2)))
            .build();
}
