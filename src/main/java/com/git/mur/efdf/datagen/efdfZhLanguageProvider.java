package com.git.mur.efdf.datagen;

import com.git.mur.efdf.efdfBlocks.efdfFuel;
import com.git.mur.efdf.efdfItems.commonItems;
import com.git.mur.efdf.efdfItems.efdfFood;
import com.git.mur.efdf.efdfItems.efdfOffensiveGrenade;
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
        translationBuilder.add(efdfFood.TAURINE_DRINK_ITEM,"牛磺酸饮料");
        translationBuilder.add(commonItems.THIN_STEEL_SHEET,"薄钢板");
        translationBuilder.add(efdfFood.TAURINE_CRYSTALS_ITEM,"牛磺酸结晶");
        translationBuilder.add(commonItems.STEEL_BOTTLE,"钢制瓶");
        translationBuilder.add(efdfFuel.HIGH_ENERGY_FUEL,"高能燃料");
        translationBuilder.add(efdfOffensiveGrenade.OFFENSIVE_GRENADE,"进攻雷");
        translationBuilder.add(efdfFood.COMPRESSED_BISCUITS_ITEM,"压缩饼干");
        translationBuilder.add(efdfFood.BRAISED_BEEF_CAN_ITEM,"红烧牛肉罐头");
        translationBuilder.add(efdfFood.BRAISED_BEEF_CAN_SMALL_ITEM,"红焖牛肉罐头");
        translationBuilder.add(efdfFood.SWEET_BEAN_SAUCE_ITEM,"甜面酱");
        translationBuilder.add(efdfFood.BOTTLED_WATER_ITEM,"高山清泉");
        translationBuilder.add(efdfFood.BOX_MILK_ITEM,"金丝雀牛奶");
        translationBuilder.add(efdfFood.REDBULL_ITEM,"红阳运动饮料");
        translationBuilder.add(efdfFood.CONDENSED_MILK_ITEM,"炼乳");
        translationBuilder.add(efdfFood.CONDENSED_MILK_CAN_ITEM,"炼乳罐头");
    }
}
