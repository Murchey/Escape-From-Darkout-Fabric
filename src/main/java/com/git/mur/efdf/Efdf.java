package com.git.mur.efdf;

import net.fabricmc.api.ModInitializer;
import com.git.mur.efdf.itemGroups.commonItemGroup;
import static com.git.mur.efdf.efdfBlocks.commonBlocks.initBlocks;

public class Efdf implements ModInitializer {
    public static final String MODID = "efdf";
    @Override
    public void onInitialize() {
        initBlocks();
        commonItemGroup.initItemGroup();
    }
}
