package dev.padjokej.tealeaf.item;

import dev.padjokej.tealeaf.TeaLeaf;
import dev.padjokej.tealeaf.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static ItemGroup TEALEAF = Registry.register(Registries.ITEM_GROUP, new Identifier(TeaLeaf.MOD_ID, "tealeaf"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup.tealeaf.tealeaf"))
                    .icon(() -> new ItemStack(ModItems.OAK_TEALEAF)).entries((displayContext, entries) -> {
                        entries.add(ModItems.ACACIA_TEA);
                        entries.add(ModItems.BIRCH_TEA);
                        entries.add(ModItems.DARK_OAK_TEA);
                        entries.add(ModItems.JUNGLE_TEA);
                        entries.add(ModItems.MANGROVE_TEA);
                        entries.add(ModItems.OAK_TEA);
                        entries.add(ModItems.SPRUCE_TEA);

                        entries.add(ModItems.ACACIA_TEALEAF);
                        entries.add(ModItems.BIRCH_TEALEAF);
                        entries.add(ModItems.DARK_OAK_TEALEAF);
                        entries.add(ModItems.JUNGLE_TEALEAF);
                        entries.add(ModItems.MANGROVE_TEALEAF);
                        entries.add(ModItems.OAK_TEALEAF);
                        entries.add(ModItems.SPRUCE_TEALEAF);

                        entries.add(ModItems.TEA_CUP);

                        entries.add(ModBlocks.TEAPOT_BLOCK);
                    }).build());

    public static void registerItemGroup() {

    }
}
