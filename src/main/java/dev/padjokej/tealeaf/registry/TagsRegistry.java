package dev.padjokej.tealeaf.registry;
import dev.padjokej.tealeaf.TeaLeaf;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;

import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

@SuppressWarnings("unused")
public class TagsRegistry {

    public static final TagKey<Block> HEAT_SOURCES = create("heat_sources", RegistryKeys.BLOCK);
    public static final TagKey<Item> TEA_LEAVES = create("tealeaves", RegistryKeys.ITEM);


    private static <E> TagKey<E> create(String pathName, RegistryKey<Registry<E>> registry) {
        return TagKey.of(registry, new Identifier(TeaLeaf.MOD_ID, pathName));
    }

    private TagsRegistry() throws InstantiationException
    {
        throw new InstantiationException("Constant class cannot be instantiate");
    }

}