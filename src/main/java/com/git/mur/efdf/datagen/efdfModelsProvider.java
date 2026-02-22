package com.git.mur.efdf.datagen;

import com.git.mur.efdf.efdfBlocks.commonBlocks;
import com.git.mur.efdf.efdfBlocks.efdfFuel;
import com.git.mur.efdf.efdfItems.efdfFood;
import com.git.mur.efdf.efdfItems.efdfOffensiveGrenade;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import com.git.mur.efdf.efdfItems.commonItems;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;

public class efdfModelsProvider extends FabricModelProvider {
    public efdfModelsProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(commonBlocks.STEEL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(commonBlocks.HIGH_TOUGHNESS_STEEL_BLOCK);
        //blockStateModelGenerator.registerSimpleState(efdfFuel.HIGH_ENERGY_FUEL);//只创建简单的方块状态
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(commonItems.STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(commonItems.HIGH_TOUGHNESS_STEEL, Models.GENERATED);
        itemModelGenerator.register(commonItems.TOOL_HAMMER, Models.GENERATED);
        itemModelGenerator.register(efdfFood.TAURINE_DRINK_ITEM, Models.GENERATED);
        itemModelGenerator.register(commonItems.THIN_STEEL_SHEET, Models.GENERATED);
        itemModelGenerator.register(commonItems.STEEL_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(efdfFood.TAURINE_CRYSTALS_ITEM, Models.GENERATED);
        itemModelGenerator.register(efdfFuel.HIGH_ENERGY_FUEL.asItem(), Models.GENERATED);
        itemModelGenerator.register(efdfOffensiveGrenade.OFFENSIVE_GRENADE, Models.GENERATED);
        itemModelGenerator.register(efdfFood.COMPRESSED_BISCUITS_ITEM, Models.GENERATED);
        itemModelGenerator.register(efdfFood.BRAISED_BEEF_CAN_ITEM, Models.GENERATED);
        itemModelGenerator.register(efdfFood.BRAISED_BEEF_CAN_SMALL_ITEM, Models.GENERATED);
        itemModelGenerator.register(efdfFood.SWEET_BEAN_SAUCE_ITEM,Models.GENERATED);
        itemModelGenerator.register(efdfFood.BOTTLED_WATER_ITEM,Models.GENERATED);
        itemModelGenerator.register(efdfFood.BOX_MILK_ITEM,Models.GENERATED);
        itemModelGenerator.register(efdfFood.REDBULL_ITEM,Models.GENERATED);
    }
}
