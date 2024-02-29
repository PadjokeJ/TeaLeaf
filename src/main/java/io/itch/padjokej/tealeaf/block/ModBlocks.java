package io.itch.padjokej.tealeaf.block;

import io.itch.padjokej.tealeaf.TeaLeaf;
import io.itch.padjokej.tealeaf.block.teapot.TeaPotBlock;
import io.itch.padjokej.tealeaf.item.ModItemGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block TEAPOT_BLOCK = registerBlock("teapot",
            new TeaPotBlock(FabricBlockSettings.of(Material.METAL)
                    .strength(4f).requiresTool().nonOpaque()), ModItemGroup.TEALEAF);
    private static Block registerBlock(String name, Block block, ItemGroup group)
    {
        registerBlockItems(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(TeaLeaf.MOD_ID, name), block);
    }
    private static Item registerBlockItems(String name, Block block, ItemGroup group)
    {
        return Registry.register(Registry.ITEM, new Identifier(TeaLeaf.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }

    public static void registerModBlocks()
    {
        TeaLeaf.LOGGER.debug("Registering mod blocks for " + TeaLeaf.MOD_ID);
    }
}
