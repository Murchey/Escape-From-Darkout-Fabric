package com.git.mur.efdf.datagen;

import com.git.mur.efdf.efdfBlocks.commonBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import com.git.mur.efdf.efdfItems.commonItems;
import net.minecraft.data.client.Models;

public class efdfModelsProvider extends FabricModelProvider {
    public efdfModelsProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(commonBlocks.STEEL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(commonBlocks.HIGH_TOUGHNESS_STEEL_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(commonItems.STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(commonItems.HIGH_TOUGHNESS_STEEL, Models.GENERATED);
    }
}
