package dev.padjokej.tealeaf.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.consume.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class DrinkableItem extends ConsumableItem {

    public DrinkableItem(Settings settings) {
        super(settings);
        settings.component(DataComponentTypes.CONSUMABLE, ConsumableComponents.DRINK);
        settings.recipeRemainder(ModItems.TEA_CUP);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
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