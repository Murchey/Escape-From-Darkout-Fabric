package com.git.mur.efdf.efdfBlocks;

import com.git.mur.efdf.Efdf;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class efdfFuel {
    public static void fuelInit(){
        Registry.register(Registries.BLOCK, Identifier.of(Efdf.MODID,"high_energy_fuel"),HIGH_ENERGY_FUEL);
        Registry.register(Registries.ITEM, Identifier.of(Efdf.MODID,"high_energy_fuel"),new BlockItem(HIGH_ENERGY_FUEL,new Item.Settings()));
    }
    public static final Block HIGH_ENERGY_FUEL = new Block(FabricBlockSettings.copy(Blocks.TNT));
}
