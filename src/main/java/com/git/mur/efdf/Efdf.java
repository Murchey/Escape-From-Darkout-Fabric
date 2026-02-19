package com.git.mur.efdf;

import com.git.mur.efdf.efdfItems.commonItems;
import com.git.mur.efdf.efdfItems.efdfFood;
import net.fabricmc.api.ModInitializer;
import com.git.mur.efdf.itemGroups.commonItemGroup;

import static com.git.mur.efdf.efdfBlocks.commonBlocks.initBlocks;

public class Efdf implements ModInitializer {
    public static final String MODID = "efdf";
    @Override
    public void onInitialize() {
        initBlocks();
        efdfFood.initFood();
        commonItemGroup.initItemGroup();
        commonItems.initItems();
    }
}
