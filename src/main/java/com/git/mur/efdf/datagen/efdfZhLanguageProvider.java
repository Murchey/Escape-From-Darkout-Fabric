package com.git.mur.efdf.datagen;

import com.git.mur.efdf.efdfItems.commonItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import static com.git.mur.efdf.efdfBlocks.commonBlocks.HIGH_TOUGHNESS_STEEL_BLOCK;
import static com.git.mur.efdf.efdfBlocks.commonBlocks.STEEL_BLOCK;

public class efdfZhLanguageProvider extends FabricLanguageProvider {
    public efdfZhLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput,"zh_cn");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(STEEL_BLOCK,"钢铁块");
        translationBuilder.add(HIGH_TOUGHNESS_STEEL_BLOCK,"高韧钢铁块");
        translationBuilder.add(commonItems.STEEL_INGOT,"钢锭");
        translationBuilder.add(commonItems.HIGH_TOUGHNESS_STEEL,"高韧钢");
        translationBuilder.add(commonItems.TOOL_HAMMER,"工具锤");
    }
}
