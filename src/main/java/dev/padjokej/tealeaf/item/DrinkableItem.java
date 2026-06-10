package dev.padjokej.tealeaf.item;

import net.minecraft.item.Item;

public class DrinkableItem extends Item {
    public DrinkableItem(Settings settings) {
        super(settings
                .food(ModFoodComponents.FRAGRANCE_PROVIDER, ModFoodComponents.DRINKING_TEA)
                .useRemainder(ModItems.TEA_CUP)
                .recipeRemainder(ModItems.TEA_CUP));
    }
}