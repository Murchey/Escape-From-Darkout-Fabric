package com.git.mur.efdf.efdfBlocks;


import com.git.mur.efdf.Efdf;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class commonBlocks {
    // 一般方块注册类
    public static void initBlocks(){
        efdfFuel.fuelInit();

        Registry.register(Registries.BLOCK, Identifier.of(Efdf.MODID,"steel_block"),STEEL_BLOCK);
        Registry.register(Registries.ITEM, Identifier.of(Efdf.MODID,"steel_block"),new BlockItem(STEEL_BLOCK,new Item.Settings()));
        Registry.register(Registries.BLOCK, Identifier.of(Efdf.MODID,"high_toughness_steel_block"),HIGH_TOUGHNESS_STEEL_BLOCK);
        Registry.register(Registries.ITEM, Identifier.of(Efdf.MODID,"high_toughness_steel_block"),new BlockItem(HIGH_TOUGHNESS_STEEL_BLOCK,new Item.Settings()));
    }

    public static final Block STEEL_BLOCK = new Block(FabricBlockSettings.create().strength(5.5f));
    public static final Block HIGH_TOUGHNESS_STEEL_BLOCK = new Block(FabricBlockSettings.create().strength(6.0f));


}
