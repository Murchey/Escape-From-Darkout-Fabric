package com.git.mur.efdf.efdfItems;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class commonItems {
    //一般物品注册类
    public static final Item STEEL_INGOT = new Item(new FabricItemSettings());
    public static final Item HIGH_TOUGHNESS_STEEL = new Item(new FabricItemSettings());
    public static final Item TOOL_HAMMER = new Item(new FabricItemSettings());//这里的锤子只能用作打制钢锭
    public static void initItems(){
        Registry.register(Registries.ITEM, Identifier.of("efdf","high_toughness_steel"),commonItems.HIGH_TOUGHNESS_STEEL);
        Registry.register(Registries.ITEM, Identifier.of("efdf","steel_ingot"),commonItems.STEEL_INGOT);
        Registry.register(Registries.ITEM, Identifier.of("efdf","tool_hammer"),commonItems.TOOL_HAMMER);
    }
}
