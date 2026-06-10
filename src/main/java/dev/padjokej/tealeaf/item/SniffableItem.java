package dev.padjokej.tealeaf.item;

import net.minecraft.item.Item;

public class SniffableItem extends Item {

    public SniffableItem(Settings settings) {
        super(settings
                .food(ModFoodComponents.FRAGRANCE_PROVIDER, ModFoodComponents.SNIFFING_LEAF));
    }
}
