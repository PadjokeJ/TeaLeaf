package dev.padjokej.tealeaf.item;

import dev.padjokej.tealeaf.TeaLeaf;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
public class ModItems
{
    public static final Item TEA_CUP = registerItem("tea_cup",
            new Item(new FabricItemSettings()));

    public static final Item SPRUCE_TEALEAF = registerItem("spruce_tea_leaf",
            new SniffableItem(new FabricItemSettings()));
    public static final Item OAK_TEALEAF = registerItem("oak_tea_leaf",
            new SniffableItem(new FabricItemSettings()));
    public static final Item ACACIA_TEALEAF = registerItem("acacia_tea_leaf",
            new SniffableItem(new FabricItemSettings()));
    public static final Item JUNGLE_TEALEAF = registerItem("jungle_tea_leaf",
            new SniffableItem(new FabricItemSettings()));
    public static final Item BIRCH_TEALEAF = registerItem("birch_tea_leaf",
            new SniffableItem(new FabricItemSettings()));
    public static final Item DARK_OAK_TEALEAF = registerItem("dark_oak_tea_leaf",
            new SniffableItem(new FabricItemSettings()));
    public static final Item MANGROVE_OAK_TEALEAF = registerItem("mangrove_tea_leaf",
            new SniffableItem(new FabricItemSettings()));

    public static final Item SPRUCE_TEA = registerItem("spruce_tea",
            new DrinkableItem(new FabricItemSettings().recipeRemainder(ModItems.TEA_CUP)));
    public static final Item OAK_TEA = registerItem("oak_tea",
            new DrinkableItem(new FabricItemSettings().recipeRemainder(ModItems.TEA_CUP)));
    public static final Item ACACIA_TEA = registerItem("acacia_tea",
            new DrinkableItem(new FabricItemSettings().recipeRemainder(ModItems.TEA_CUP)));
    public static final Item JUNGLE_TEA = registerItem("jungle_tea",
            new DrinkableItem(new FabricItemSettings().recipeRemainder(ModItems.TEA_CUP)));
    public static final Item BIRCH_TEA = registerItem("birch_tea",
            new DrinkableItem(new FabricItemSettings().recipeRemainder(ModItems.TEA_CUP)));
    public static final Item DARK_OAK_TEA = registerItem("dark_oak_tea",
            new DrinkableItem(new FabricItemSettings().recipeRemainder(ModItems.TEA_CUP)));
    public static final Item MANGROVE_OAK_TEA = registerItem("mangrove_tea",
            new DrinkableItem(new FabricItemSettings().recipeRemainder(ModItems.TEA_CUP)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(TeaLeaf.MOD_ID,name), item);
    }

    public static void addItemsToGroup() {
        addToItemGroup(ModItemGroup.TEALEAF, TEA_CUP);

        addToItemGroup(ModItemGroup.TEALEAF, SPRUCE_TEALEAF);
        addToItemGroup(ModItemGroup.TEALEAF, OAK_TEALEAF);
        addToItemGroup(ModItemGroup.TEALEAF, ACACIA_TEALEAF);
        addToItemGroup(ModItemGroup.TEALEAF, JUNGLE_TEALEAF);
        addToItemGroup(ModItemGroup.TEALEAF, BIRCH_TEALEAF);
        addToItemGroup(ModItemGroup.TEALEAF, DARK_OAK_TEALEAF);
        addToItemGroup(ModItemGroup.TEALEAF, MANGROVE_OAK_TEALEAF);

        addToItemGroup(ItemGroups.INGREDIENTS, SPRUCE_TEALEAF);
        addToItemGroup(ItemGroups.INGREDIENTS, OAK_TEALEAF);
        addToItemGroup(ItemGroups.INGREDIENTS, ACACIA_TEALEAF);
        addToItemGroup(ItemGroups.INGREDIENTS, JUNGLE_TEALEAF);
        addToItemGroup(ItemGroups.INGREDIENTS, BIRCH_TEALEAF);
        addToItemGroup(ItemGroups.INGREDIENTS, DARK_OAK_TEALEAF);
        addToItemGroup(ItemGroups.INGREDIENTS, MANGROVE_OAK_TEALEAF);

        addToItemGroup(ModItemGroup.TEALEAF, SPRUCE_TEA);
        addToItemGroup(ModItemGroup.TEALEAF, OAK_TEA);
        addToItemGroup(ModItemGroup.TEALEAF, ACACIA_TEA);
        addToItemGroup(ModItemGroup.TEALEAF, JUNGLE_TEA);
        addToItemGroup(ModItemGroup.TEALEAF, BIRCH_TEA);
        addToItemGroup(ModItemGroup.TEALEAF, DARK_OAK_TEA);
        addToItemGroup(ModItemGroup.TEALEAF, MANGROVE_OAK_TEA);
    }

    public static void addToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries ->
                entries.add(item));
    }
    public static void registerModItems() {
        TeaLeaf.LOGGER.debug("Registering Mod Items for TeaLeaf!");

        addItemsToGroup();
    }
}
