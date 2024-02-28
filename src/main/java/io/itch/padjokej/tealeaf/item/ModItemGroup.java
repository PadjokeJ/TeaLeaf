package io.itch.padjokej.tealeaf.item;

import io.itch.padjokej.tealeaf.TeaLeaf;
import io.itch.padjokej.tealeaf.item.ModItemGroup;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup TEALEAF = FabricItemGroupBuilder.build(
            new Identifier(TeaLeaf.MOD_ID, "tealeaf"), () -> new ItemStack(ModItems.SPRUCE_TEALEAF));
}
