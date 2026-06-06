package dev.padjokej.tealeaf.block;

import dev.padjokej.tealeaf.TeaLeaf;
import dev.padjokej.tealeaf.block.teapot.TeaPotBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block TEAPOT_BLOCK = registerBlock("teapot",
            new TeaPotBlock(AbstractBlock.Settings.create().mapColor(MapColor.IRON_GRAY).sounds(BlockSoundGroup.METAL)
                    .strength(1f).nonOpaque().registryKey(RegistryKey.of(RegistryKeys.BLOCK, TeaLeaf.id("teapot")))));

    private static Block registerBlock(String name, Block block) {
        registerBlockItems(name, block);
        return Registry.register(Registries.BLOCK, TeaLeaf.id(name), block);
    }

    private static Item registerBlockItems(String name, Block block) {
        Identifier id = TeaLeaf.id(name);
        Item item = Registry.register(Registries.ITEM, id,
                new BlockItem(block, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, id))));
        return item;
    }

    public static void registerModBlocks() {
        TeaLeaf.LOGGER.debug("Registering mod blocks for " + TeaLeaf.MOD_ID);
    }
}
