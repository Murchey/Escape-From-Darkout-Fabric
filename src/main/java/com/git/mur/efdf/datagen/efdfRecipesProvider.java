package com.git.mur.efdf.datagen;

import com.git.mur.efdf.efdfBlocks.commonBlocks;
import com.git.mur.efdf.efdfItems.commonItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.function.Consumer;

public class efdfRecipesProvider extends FabricRecipeProvider {
    public efdfRecipesProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> consumer) {
        // 9钢锭 -> 1钢块
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, commonBlocks.STEEL_BLOCK)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .input('X',commonItems.STEEL_INGOT)
                .criterion(
                        FabricRecipeProvider.hasItem(commonItems.STEEL_INGOT),
                        FabricRecipeProvider.conditionsFromItem(commonItems.STEEL_INGOT)
                ).offerTo(consumer);
        // 1钢块 -> 9钢锭
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, commonItems.STEEL_INGOT,9)
                .input(commonBlocks.STEEL_BLOCK)
                .criterion(
                        FabricRecipeProvider.hasItem(commonItems.STEEL_INGOT),
                        FabricRecipeProvider.conditionsFromItem(commonItems.STEEL_INGOT)
                )
                .criterion(
                        FabricRecipeProvider.hasItem(commonBlocks.STEEL_BLOCK),
                        FabricRecipeProvider.conditionsFromItem(commonBlocks.STEEL_BLOCK)
                ).offerTo(consumer);
// 9高韧钢 -> 1高韧钢块（有序合成）
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, commonBlocks.HIGH_TOUGHNESS_STEEL_BLOCK)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .input('X', commonItems.HIGH_TOUGHNESS_STEEL)
                .criterion(
                        FabricRecipeProvider.hasItem(commonItems.HIGH_TOUGHNESS_STEEL),
                        FabricRecipeProvider.conditionsFromItem(commonItems.HIGH_TOUGHNESS_STEEL)
                )
                .offerTo(consumer,new Identifier("efdf","high_toughness_block_from_ingot"));

// 1高韧钢块 -> 9高韧钢（无序合成）
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, commonItems.HIGH_TOUGHNESS_STEEL, 9)
                .input(commonBlocks.HIGH_TOUGHNESS_STEEL_BLOCK)
                .criterion(
                        FabricRecipeProvider.hasItem(commonBlocks.HIGH_TOUGHNESS_STEEL_BLOCK),  // 改为检查方块
                        FabricRecipeProvider.conditionsFromItem(commonBlocks.HIGH_TOUGHNESS_STEEL_BLOCK)
                )
                .offerTo(consumer,new Identifier("efdf","high_toughness_from_block"));
        // 1木棍+1铁锭 -> 1工具锤
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, commonItems.TOOL_HAMMER,1)
                .pattern(" X ")
                .pattern(" A ")
                .input('X', Items.IRON_INGOT)
                .input('A', Items.STICK)
                .criterion(
                        FabricRecipeProvider.hasItem(commonItems.STEEL_INGOT),
                        FabricRecipeProvider.conditionsFromItem(commonItems.STEEL_INGOT)
                ).offerTo(consumer);
        // 1工具锤+1钢锭 -> 1高韧铁锭
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, commonItems.HIGH_TOUGHNESS_STEEL,1)
                .pattern("AB ")
                .input('A', commonItems.STEEL_INGOT)
                .input('B', commonItems.TOOL_HAMMER)
                .criterion(
                        FabricRecipeProvider.hasItem(commonItems.STEEL_INGOT),
                        FabricRecipeProvider.conditionsFromItem(commonItems.STEEL_INGOT)
                ).offerTo(consumer,new Identifier("efdf","high_toughness_steel_from_make"));
        // 1铁锭 -> 1钢锭
        final List<ItemConvertible> IRON_INGOT_TO_STEEL_INGOT = Util.make(Lists.newArrayList(),list ->{
           list.add(Items.IRON_INGOT);
        });
        RecipeProvider.offerSmelting(consumer, IRON_INGOT_TO_STEEL_INGOT, RecipeCategory.MISC, commonItems.STEEL_INGOT,0.45f,300,"efdf");
        RecipeProvider.offerBlasting(consumer, IRON_INGOT_TO_STEEL_INGOT, RecipeCategory.MISC, commonItems.STEEL_INGOT,0.45f,250,"efdf");
        // 1钢锭 -> 6薄钢板
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, commonItems.THIN_STEEL_SHEET,6)
                .input(commonItems.STEEL_INGOT,1)
                .criterion(
                        FabricRecipeProvider.hasItem(commonItems.STEEL_INGOT),
                        FabricRecipeProvider.conditionsFromItem(commonItems.STEEL_INGOT)
                ).offerTo(consumer, new Identifier("efdf","thin_steel_sheet_from_steel_ingot"));
    }
}
