package com.git.mur.efdf.datagen;

import com.git.mur.efdf.Efdf;
import com.git.mur.efdf.efdfBlocks.commonBlocks;
import com.git.mur.efdf.efdfItems.commonItems;
import com.git.mur.efdf.efdfItems.efdfFood;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
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
                ).offerTo(consumer,new Identifier(Efdf.MODID,"steel_block_from_steel_ingot"));
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
                ).offerTo(consumer,new Identifier(Efdf.MODID,"steel_ingot_from_block"));
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
                .offerTo(consumer,new Identifier(Efdf.MODID,"high_toughness_block_from_ingot"));

        // 1高韧钢块 -> 9高韧钢（无序合成）
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, commonItems.HIGH_TOUGHNESS_STEEL, 9)
                .input(commonBlocks.HIGH_TOUGHNESS_STEEL_BLOCK)
                .criterion(
                        FabricRecipeProvider.hasItem(commonBlocks.HIGH_TOUGHNESS_STEEL_BLOCK),  // 改为检查方块
                        FabricRecipeProvider.conditionsFromItem(commonBlocks.HIGH_TOUGHNESS_STEEL_BLOCK)
                )
                .offerTo(consumer,new Identifier(Efdf.MODID,"high_toughness_from_block"));
        // 1木棍+1铁锭 -> 1工具锤
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, commonItems.TOOL_HAMMER,1)
                .pattern(" X ")
                .pattern(" A ")
                .input('X', Items.IRON_INGOT)
                .input('A', Items.STICK)
                .criterion(
                        FabricRecipeProvider.hasItem(commonItems.STEEL_INGOT),
                        FabricRecipeProvider.conditionsFromItem(commonItems.STEEL_INGOT)
                ).offerTo(consumer,new Identifier(Efdf.MODID,"tool_hammer_from_make"));
        // 1工具锤+1钢锭 -> 1高韧铁锭
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, commonItems.HIGH_TOUGHNESS_STEEL,1)
                .pattern("AB ")
                .input('A', commonItems.STEEL_INGOT)
                .input('B', commonItems.TOOL_HAMMER)
                .criterion(
                        FabricRecipeProvider.hasItem(commonItems.STEEL_INGOT),
                        FabricRecipeProvider.conditionsFromItem(commonItems.STEEL_INGOT)
                ).offerTo(consumer,new Identifier(Efdf.MODID,"high_toughness_steel_from_make"));
        // 1铁锭 -> 1钢锭
        final List<ItemConvertible> IRON_INGOT_TO_STEEL_INGOT = Util.make(Lists.newArrayList(),list ->{
           list.add(Items.IRON_INGOT);
        });
        RecipeProvider.offerSmelting(consumer, IRON_INGOT_TO_STEEL_INGOT, RecipeCategory.MISC, commonItems.STEEL_INGOT,0.45f,300,Efdf.MODID);
        RecipeProvider.offerBlasting(consumer, IRON_INGOT_TO_STEEL_INGOT, RecipeCategory.MISC, commonItems.STEEL_INGOT,0.40f,250,Efdf.MODID);
        // 1熟牛肉 -> 2牛磺酸结晶
        final List<ItemConvertible> STEAK_TO_TAURINE_CRYSTALS = Util.make(Lists.newArrayList(),list->{
            list.add(Items.COOKED_BEEF);
        });
        CookingRecipeJsonBuilder.createSmoking(
                Ingredient.ofItems(Items.COOKED_BEEF),
                RecipeCategory.MISC,
                efdfFood.TAURINE_CRYSTALS_ITEM,
                0.35f,
                100).criterion(
                    FabricRecipeProvider.hasItem(Items.COOKED_BEEF),
                    FabricRecipeProvider.conditionsFromItem(Items.COOKED_BEEF)
                ).offerTo(consumer,new Identifier(Efdf.MODID,"taurine_crystals_from_smoking"));
        CookingRecipeJsonBuilder.createSmelting(
                Ingredient.ofItems(Items.COOKED_BEEF),
                RecipeCategory.MISC,
                efdfFood.TAURINE_CRYSTALS_ITEM,
                0.35f,
                200).criterion(
                FabricRecipeProvider.hasItem(Items.COOKED_BEEF),
                FabricRecipeProvider.conditionsFromItem(Items.COOKED_BEEF)
        ).offerTo(consumer,new Identifier(Efdf.MODID,"taurine_crystals_from_smelting"));
        // 1牛磺酸结晶 + 3薄钢板 -> 2牛磺酸饮料
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,efdfFood.TAURINE_DRINK_ITEM,2)
                        .pattern("BAB")
                        .pattern(" B ")
                        .input('A',efdfFood.TAURINE_CRYSTALS_ITEM)
                        .input('B',commonItems.THIN_STEEL_SHEET)
                        .criterion(
                                FabricRecipeProvider.hasItem(Items.COOKED_BEEF),
                                FabricRecipeProvider.conditionsFromItem(Items.COOKED_BEEF)
                        ).offerTo(consumer,new Identifier(Efdf.MODID,"taurine_drink_from_thin_steel_sheet"));
        // 1钢锭 -> 6薄钢板
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, commonItems.THIN_STEEL_SHEET,6)
                .input(commonItems.STEEL_INGOT,1)
                .criterion(
                        FabricRecipeProvider.hasItem(commonItems.STEEL_INGOT),
                        FabricRecipeProvider.conditionsFromItem(commonItems.STEEL_INGOT)
                ).offerTo(consumer, new Identifier(Efdf.MODID,"thin_steel_sheet_from_steel_ingot"));
        // 3钢锭 -> 1钢瓶
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,commonItems.STEEL_BOTTLE,1)
                .pattern("X X")
                .pattern(" X ")
                .input('X',commonItems.THIN_STEEL_SHEET)
                .criterion(
                        FabricRecipeProvider.hasItem(commonItems.THIN_STEEL_SHEET),
                        FabricRecipeProvider.conditionsFromItem(commonItems.THIN_STEEL_SHEET)
                ).offerTo(consumer,new Identifier(Efdf.MODID,"steel_bottle_from_thin_steel_sheet"));
        // 1钢瓶 + 1牛磺酸结晶 -> 2牛磺酸饮料
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,efdfFood.TAURINE_DRINK_ITEM,2)
                .pattern("AB")
                .input('A',commonItems.STEEL_BOTTLE)
                .input('B',efdfFood.TAURINE_CRYSTALS_ITEM)
                .criterion(
                        FabricRecipeProvider.hasItem(commonItems.STEEL_BOTTLE),
                        FabricRecipeProvider.conditionsFromItem(commonItems.STEEL_BOTTLE)
                ).offerTo(consumer,new Identifier(Efdf.MODID,"taurine_drink_from_steel_bottle"));
    }
}
