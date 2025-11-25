package dev.padjokej.tealeaf.registry;
import dev.padjokej.tealeaf.TeaLeaf;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

@SuppressWarnings("unused")
public class TagsRegistry {

    public static final TagKey<Block> HEAT_SOURCES = create("heat_sources", Registry.BLOCK_KEY);
    public static final TagKey<Item> TEA_LEAVES = create("tealeaves", Registry.ITEM_KEY);


    private static <E> TagKey<E> create(String pathName, RegistryKey<Registry<E>> registry) {
        return TagKey.of(registry, new Identifier(TeaLeaf.MOD_ID, pathName));
    }

    private TagsRegistry() throws InstantiationException
    {
        throw new InstantiationException("Constant class cannot be instantiate");
    }

}