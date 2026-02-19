package com.git.mur.efdf;

import com.git.mur.efdf.efdfBlocks.commonBlocks;
import com.git.mur.efdf.efdfItems.commonItems;
import net.fabricmc.api.ModInitializer;
import com.git.mur.efdf.itemGroups.commonItemGroup;


public class Efdf implements ModInitializer {
    public static final String MODID = "efdf";
    @Override
    public void onInitialize() {
        //common字段开头的类初始方法包含该包下所有特殊方块和物品的初始方法
        commonBlocks.initBlocks();
        commonItemGroup.initItemGroup();
        commonItems.initItems();
    }
}
