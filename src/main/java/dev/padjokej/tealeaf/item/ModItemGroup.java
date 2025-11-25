package dev.padjokej.tealeaf.item;

import dev.padjokej.tealeaf.TeaLeaf;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static ItemGroup TEALEAF;

    public static void registerItemGroup() {
        TEALEAF = FabricItemGroup.builder(new Identifier(TeaLeaf.MOD_ID, "tealeaf"))
                .displayName(Text.translatable("itemGroup.tealeaf.tealeaf"))
                .icon(() -> new ItemStack(ModItems.OAK_TEALEAF)).build();
    }
}
