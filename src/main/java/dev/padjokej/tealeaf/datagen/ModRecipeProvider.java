package dev.padjokej.tealeaf.datagen;

import dev.padjokej.tealeaf.block.ModBlocks;
import dev.padjokej.tealeaf.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                offerReversibleCompactingRecipes(RecipeCategory.MISC, ModItems.ACACIA_TEALEAF, RecipeCategory.MISC, Items.ACACIA_LEAVES);
                offerReversibleCompactingRecipes(RecipeCategory.MISC, ModItems.BIRCH_TEALEAF, RecipeCategory.MISC, Items.BIRCH_LEAVES);
                offerReversibleCompactingRecipes(RecipeCategory.MISC, ModItems.CHERRY_TEALEAF, RecipeCategory.MISC, Items.CHERRY_LEAVES);
                offerReversibleCompactingRecipes(RecipeCategory.MISC, ModItems.DARK_OAK_TEALEAF, RecipeCategory.MISC, Items.DARK_OAK_LEAVES);
                offerReversibleCompactingRecipes(RecipeCategory.MISC, ModItems.JUNGLE_TEALEAF, RecipeCategory.MISC, Items.JUNGLE_LEAVES);
                offerReversibleCompactingRecipes(RecipeCategory.MISC, ModItems.MANGROVE_TEALEAF, RecipeCategory.MISC, Items.MANGROVE_LEAVES);
                offerReversibleCompactingRecipes(RecipeCategory.MISC, ModItems.OAK_TEALEAF, RecipeCategory.MISC, Items.OAK_LEAVES);
                offerReversibleCompactingRecipes(RecipeCategory.MISC, ModItems.SPRUCE_TEALEAF, RecipeCategory.MISC, Items.SPRUCE_LEAVES);
                offerReversibleCompactingRecipes(RecipeCategory.MISC, ModItems.PALE_OAK_TEALEAF, RecipeCategory.MISC, Items.PALE_OAK_LEAVES);
                
                createShaped(RecipeCategory.MISC, ModItems.TEA_CUP, 4)
                        .criterion(hasItem(Items.CLAY_BALL), conditionsFromItem(Items.CLAY_BALL))
                        .pattern("# #")
                        .pattern(" # ")
                        .input('#', Items.CLAY_BALL)
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModBlocks.TEAPOT_BLOCK)
                        .criterion(hasItem(Items.CLAY_BALL), conditionsFromItem(Items.CLAY_BALL))
                        .pattern(" # ")
                        .pattern("# #")
                        .pattern("###")
                        .input('#', Items.CLAY_BALL)
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "Tealeaf recipes";
    }
}
