package com.git.mur.efdf.datagen;

import com.git.mur.efdf.Efdf;
import com.git.mur.efdf.efdfBlocks.commonBlocks;
import com.git.mur.efdf.efdfItems.commonItems;
import com.git.mur.efdf.efdfItems.efdfFood;
import com.git.mur.efdf.efdfItems.efdfOffensiveGrenade;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.*;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
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
        final List<ItemConvertible> IRON_TO_STEEL = Util.make(Lists.newArrayList(),list ->{
           list.add(Items.IRON_INGOT);
           list.add(Items.IRON_BLOCK);
        });
        RecipeProvider.offerSmelting(consumer, IRON_TO_STEEL, RecipeCategory.MISC, commonItems.STEEL_INGOT,0.45f,300,Efdf.MODID);
        RecipeProvider.offerBlasting(consumer, IRON_TO_STEEL, RecipeCategory.MISC, commonItems.STEEL_INGOT,0.40f,250,Efdf.MODID);

        // 1熟牛肉 -> 2牛磺酸结晶
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

        // 3高韧钢 + 1火药 -> 1进攻雷
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, efdfOffensiveGrenade.OFFENSIVE_GRENADE,1)
                .pattern("ABA")
                .pattern(" A ")
                .input('A',commonItems.HIGH_TOUGHNESS_STEEL)
                .input('B',Items.GUNPOWDER)
                .criterion(
                        FabricRecipeProvider.hasItem(Items.GUNPOWDER),
                        FabricRecipeProvider.conditionsFromItem(Items.GUNPOWDER)
                ).offerTo(consumer,new Identifier(Efdf.MODID,"instant_grenade_from_gunpowder"));

        // 2小麦 + 2糖 -> 1压缩饼干
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, efdfFood.COMPRESSED_BISCUITS_ITEM,1)
                .pattern("AB ")
                .pattern("AB ")
                .input('A',Items.WHEAT)
                .input('B',Items.SUGAR)
                .criterion(
                        FabricRecipeProvider.hasItem(Items.SUGAR),
                        FabricRecipeProvider.conditionsFromItem(Items.SUGAR)
                ).offerTo(consumer,new Identifier(Efdf.MODID,"compressed_biscuits_from_sugar_wheat"));

        // 2cooked_beef + 3薄钢板 -> 1红烧牛肉罐头
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, efdfFood.BRAISED_BEEF_CAN_ITEM,1)
                .pattern(" B ")
                .pattern("SBS")
                .pattern(" S ")
                .input('S',commonItems.THIN_STEEL_SHEET)
                .input('B',Items.COOKED_BEEF)
                .criterion(
                        FabricRecipeProvider.hasItem(commonItems.THIN_STEEL_SHEET),
                        FabricRecipeProvider.conditionsFromItem(commonItems.THIN_STEEL_SHEET)
                ).offerTo(consumer, new Identifier(Efdf.MODID,"braised_beef_can_from_cooked_beef"));

        // 1熟牛肉 + 3薄钢板 -> 1红焖牛肉罐头
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, efdfFood.BRAISED_BEEF_CAN_SMALL_ITEM, 1)
                .pattern("SBS")
                .pattern(" S ")
                .input('S', commonItems.THIN_STEEL_SHEET)
                .input('B', Items.COOKED_BEEF)
                .criterion(
                        FabricRecipeProvider.hasItem(commonItems.THIN_STEEL_SHEET),
                        FabricRecipeProvider.conditionsFromItem(commonItems.THIN_STEEL_SHEET)
                ).offerTo(consumer, new Identifier(Efdf.MODID,"braised_beef_can_small_from_cooked_beef"));

        // 1糖 + 1可可豆 + 3玻璃 -> 1甜面酱
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, efdfFood.SWEET_BEAN_SAUCE_ITEM,1)
                .pattern(" S ")
                .pattern("GCG")
                .pattern(" G ")
                .input('S',Items.SUGAR)
                .input('C',Items.COCOA_BEANS)
                .input('G',Items.GLASS)
                .criterion(
                        FabricRecipeProvider.hasItem(Items.GLASS),
                        FabricRecipeProvider.conditionsFromItem(Items.GLASS)
                ).offerTo(consumer, new Identifier(Efdf.MODID,"sweat_bean_sauce_from_cocoa_bean"));

        // 2水玻璃瓶 -> 1高山清泉
        ItemStack waterBottle = new ItemStack(Items.POTION);
        PotionUtil.setPotion(waterBottle, Potions.WATER);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, efdfFood.BOTTLED_WATER_ITEM,1)
                .input(waterBottle.getItem(),2)
                .criterion(
                        FabricRecipeProvider.hasItem(Items.GLASS_BOTTLE),
                        FabricRecipeProvider.conditionsFromItem(Items.GLASS_BOTTLE)
                )
                .offerTo(consumer, new Identifier(Efdf.MODID,"bottled_water_from_water_bottle"));
    }
}
