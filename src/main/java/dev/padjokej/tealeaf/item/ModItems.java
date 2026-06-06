package dev.padjokej.tealeaf.item;

import dev.padjokej.tealeaf.TeaLeaf;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    public static final Item TEA_CUP = registerItem("tea_cup",
            Item::new, new Item.Settings());

    public static final Item SPRUCE_TEALEAF = registerItem("spruce_tea_leaf",
            SniffableItem::new, new Item.Settings());
    public static final Item OAK_TEALEAF = registerItem("oak_tea_leaf",
            SniffableItem::new, new Item.Settings());
    public static final Item ACACIA_TEALEAF = registerItem("acacia_tea_leaf",
            SniffableItem::new, new Item.Settings());
    public static final Item JUNGLE_TEALEAF = registerItem("jungle_tea_leaf",
            SniffableItem::new, new Item.Settings());
    public static final Item BIRCH_TEALEAF = registerItem("birch_tea_leaf",
            SniffableItem::new, new Item.Settings());
    public static final Item DARK_OAK_TEALEAF = registerItem("dark_oak_tea_leaf",
            SniffableItem::new, new Item.Settings());
    public static final Item MANGROVE_TEALEAF = registerItem("mangrove_tea_leaf",
            SniffableItem::new, new Item.Settings());

    public static final Item SPRUCE_TEA = registerItem("spruce_tea",
            DrinkableItem::new, new Item.Settings());
    public static final Item OAK_TEA = registerItem("oak_tea",
            DrinkableItem::new, new Item.Settings());
    public static final Item ACACIA_TEA = registerItem("acacia_tea",
            DrinkableItem::new, new Item.Settings());
    public static final Item JUNGLE_TEA = registerItem("jungle_tea",
            DrinkableItem::new, new Item.Settings());
    public static final Item BIRCH_TEA = registerItem("birch_tea",
            DrinkableItem::new, new Item.Settings());
    public static final Item DARK_OAK_TEA = registerItem("dark_oak_tea",
            DrinkableItem::new, new Item.Settings());
    public static final Item MANGROVE_TEA = registerItem("mangrove_tea",
            DrinkableItem::new, new Item.Settings());

    private static Item registerItem(String name, Function<Item.Settings, Item> itemFunction, Item.Settings settings) {
        Identifier id = TeaLeaf.id(name);
        RegistryKey<Item> k = RegistryKey.of(RegistryKeys.ITEM, id);

        settings.registryKey(k);

        return Registry.register(Registries.ITEM, id, itemFunction.apply(settings));
    }

    public static void registerModItems() {
        TeaLeaf.LOGGER.debug("Registering Mod Items for TeaLeaf!");
    }
}
