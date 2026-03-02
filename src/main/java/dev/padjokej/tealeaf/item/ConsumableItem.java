package dev.padjokej.tealeaf.item;

import dev.padjokej.tealeaf.registry.EffectRegistry;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.component.DataComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class ConsumableItem extends Item {

    public static final int BRIEF_DURATION = 600;    // 30 seconds
    public static final int SHORT_DURATION = 1200;    // 1 minute
    public static final int MEDIUM_DURATION = 3600;    // 3 minutes
    public static final int LONG_DURATION = 6000;    // 5 minutes

    private static final MutableText NO_EFFECTS = (Text.translatable("effect.none")).formatted(Formatting.GRAY);

    private final boolean hasFoodEffectTooltip;
    private final boolean hasCustomTooltip;

    public ConsumableItem(Settings settings) {
        super(settings);
        this.hasFoodEffectTooltip = false;
        this.hasCustomTooltip = false;
    }

    public ConsumableItem(Settings settings, boolean hasFoodEffectTooltip) {
        super(settings);
        this.hasFoodEffectTooltip = hasFoodEffectTooltip;
        this.hasCustomTooltip = false;
    }

    public ConsumableItem(Settings settings, boolean hasFoodEffectTooltip, boolean hasCustomTooltip) {
        super(settings);
        this.hasFoodEffectTooltip = hasFoodEffectTooltip;
        this.hasCustomTooltip = hasCustomTooltip;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient()) {
            affectConsumer(stack, world, user);
        }

        ItemStack container = stack.getRecipeRemainder();

        if (stack.get(DataComponentTypes.FOOD) != null) {
            super.finishUsing(stack, world, user);
        } else if (user instanceof PlayerEntity player) {
            if (player instanceof ServerPlayerEntity serverPlayer) {
                Criteria.CONSUME_ITEM.trigger(serverPlayer, stack);
            }
            player.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!player.getAbilities().creativeMode) {
                stack.decrement(1);
            }
        }

        if (stack.isEmpty()) {
            return container;
        } else {
            if (user instanceof PlayerEntity player && !player.getAbilities().creativeMode && !player.getInventory().insertStack(container)) {
                player.dropItem(container, false);
            }

            return stack;
        }
    }

    public void affectConsumer(ItemStack stack, World world, LivingEntity user) {
        // Do nothing for basic consumable item
    }

}