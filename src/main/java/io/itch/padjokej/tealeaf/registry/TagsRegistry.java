package io.itch.padjokej.tealeaf.registry;
import io.itch.padjokej.tealeaf.TeaLeaf;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.impl.tag.convention.TagRegistration;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

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