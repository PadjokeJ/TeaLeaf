package io.itch.padjokej.tealeaf.item;

import io.itch.padjokej.tealeaf.registry.EffectRegistry;
import io.itch.padjokej.tealeaf.registry.SoundRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class SniffableItem extends ConsumableItem
{

    public SniffableItem(Settings settings) {
        super(settings);

    }
    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient()) {
            affectConsumer(stack, world, user);
            user.addStatusEffect(new StatusEffectInstance(
                    EffectRegistry.FRAGRANCE.get(),
                    200, 4));

        }

        ItemStack container = stack.getRecipeRemainder();

        if (stack.isFood()) {
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
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack heldStack = user.getStackInHand(hand);
        if (heldStack.isFood()) {
            if (user.canConsume(heldStack.getItem().getFoodComponent().isAlwaysEdible())) {
                user.setCurrentHand(hand);

                return TypedActionResult.consume(heldStack);
            } else {
                return TypedActionResult.fail(heldStack);
            }
        }

        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public SoundEvent getDrinkSound() {
        return SoundRegistry.SNIFFING.get();
    }

    @Override
    public SoundEvent getEatSound() {
        return SoundRegistry.SNIFFING.get();
    }


}
