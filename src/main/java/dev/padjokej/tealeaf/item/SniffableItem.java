package dev.padjokej.tealeaf.item;

import dev.padjokej.tealeaf.registry.EffectRegistry;
import dev.padjokej.tealeaf.registry.SoundRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.consume.UseAction;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SniffableItem extends ConsumableItem {

    // FIXME check if this produces sound
    public SniffableItem(Settings settings) {
        super(settings);
        settings.component(DataComponentTypes.CONSUMABLE, ConsumableComponents.drink().sound(RegistryEntry.of(SoundRegistry.SNIFFING.get())).build());
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient()) {
            affectConsumer(stack, world, user);
            user.addStatusEffect(new StatusEffectInstance(EffectRegistry.FRAGRANCE, 200, 4));
        }

        ItemStack container = stack.getRecipeRemainder();

        if (stack.get(DataComponentTypes.FOOD) != null) {
            super.finishUsing(stack, world, user);
        } else if (user instanceof PlayerEntity player) {
            player.incrementStat(Stats.USED.getOrCreateStat(this));
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

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack heldStack = user.getStackInHand(hand);
        if (heldStack.get(DataComponentTypes.FOOD) != null) {
            if (user.canConsume(heldStack.get(DataComponentTypes.FOOD).canAlwaysEat())) {
                user.setCurrentHand(hand);

                return ActionResult.CONSUME;
            } else {
                return ActionResult.FAIL;
            }
        }

        return ItemUsage.consumeHeldItem(world, user, hand);
    }
}
