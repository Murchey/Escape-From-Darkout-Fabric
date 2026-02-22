package com.git.mur.efdf.efdfItems;

import com.git.mur.efdf.Efdf;
import com.git.mur.efdf.efdfThirst.efdfDrinkItem;
import com.git.mur.efdf.efdfThirst.efdfCompressedBiscuits;
import com.git.mur.efdf.efdfThirst.efdfSweetBeanSauce;
import net.dehydration.api.DrinkItem;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class efdfFood {
    //牛磺酸饮料，效果：力量3 速度2 持续300秒
    public static final FoodComponent TAURINE_DRINK = (new FoodComponent.Builder()).hunger(6).saturationModifier(5.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20*300, 2,false,false,true),1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20*300, 1,false,false,true),1.0f)
            .alwaysEdible()
            .build();
    public static final Item TAURINE_DRINK_ITEM = new efdfDrinkItem(new Item.Settings().food(TAURINE_DRINK).maxCount(12));

    //牛磺酸结晶，效果：力量1 速度1 持续200秒
    public static final FoodComponent TAURINE_CRYSTALS = (new FoodComponent.Builder()).hunger(2).saturationModifier(3.5f).alwaysEdible()
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED,20*200,0,false,false,true),0.75f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,20*200,0,false,false,true),0.75f)
            .build();
    public static final Item TAURINE_CRYSTALS_ITEM = new Item(new Item.Settings().food(TAURINE_CRYSTALS).maxCount(64));

    //压缩饼干，无效果，回复12饥饿值,掉水
    public static final FoodComponent COMPRESSED_BISCUITS = (new FoodComponent.Builder()).hunger(12).saturationModifier(2.75f).alwaysEdible().build();
    public static final Item COMPRESSED_BISCUITS_ITEM = new efdfCompressedBiscuits(new Item.Settings().food(COMPRESSED_BISCUITS).maxCount(12));

    //红烧牛肉罐头，力量1，持续60秒，回复16饥饿值（两块熟牛肉）
    public static final FoodComponent BRAISED_BEEF_CAN = (new FoodComponent.Builder()).hunger(16).saturationModifier(20f).alwaysEdible()
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,20*60,0,false,false,true),1.0f).build();
    public static final Item BRAISED_BEEF_CAN_ITEM = new Item(new Item.Settings().food(BRAISED_BEEF_CAN));

    //红焖牛肉罐头，力量1，持续45秒，回复8饥饿值
    public static final FoodComponent BRAISED_BEEF_CAN_SMALL = (new FoodComponent.Builder()).hunger(8).saturationModifier(12f).alwaysEdible()
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20*45,0,false,false,true),1.0f).build();
    public static final Item BRAISED_BEEF_CAN_SMALL_ITEM = new Item(new Item.Settings().food(BRAISED_BEEF_CAN_SMALL));

    //甜面酱，口渴1，5秒，口渴-16
    public static final FoodComponent SWEET_BEAN_SAUCE = (new FoodComponent.Builder()).hunger(3).saturationModifier(2f).alwaysEdible().build();
    public static final Item SWEET_BEAN_SAUCE_ITEM = new efdfSweetBeanSauce(new Item.Settings().food(SWEET_BEAN_SAUCE).maxCount(16));

    //高山清泉，口渴+12
    public static final FoodComponent BOTTLED_WATER = (new FoodComponent.Builder()).hunger(0).alwaysEdible().saturationModifier(0f).build();
    public static final Item BOTTLED_WATER_ITEM = new efdfDrinkItem(new Item.Settings().food(BOTTLED_WATER).maxCount(16));

    //牛奶，口渴+3
    public static final FoodComponent BOX_MILK = (new FoodComponent.Builder()).hunger(3).saturationModifier(0.8f).alwaysEdible().build();
    public static final Item BOX_MILK_ITEM = new efdfDrinkItem(new Item.Settings().food(BOX_MILK));

    public static void foodInit(){
        Registry.register(Registries.ITEM, Identifier.of(Efdf.MODID,"taurine_drink"),TAURINE_DRINK_ITEM);
        Registry.register(Registries.ITEM, Identifier.of(Efdf.MODID,"taurine_crystals"),TAURINE_CRYSTALS_ITEM);
        Registry.register(Registries.ITEM, Identifier.of(Efdf.MODID,"compressed_biscuits"),COMPRESSED_BISCUITS_ITEM);
        Registry.register(Registries.ITEM, Identifier.of(Efdf.MODID,"braised_beef_can"),BRAISED_BEEF_CAN_ITEM);
        Registry.register(Registries.ITEM, Identifier.of(Efdf.MODID,"braised_beef_can_small"),BRAISED_BEEF_CAN_SMALL_ITEM);
        Registry.register(Registries.ITEM, Identifier.of(Efdf.MODID,"sweet_bean_sauce"),SWEET_BEAN_SAUCE_ITEM);
        Registry.register(Registries.ITEM, Identifier.of(Efdf.MODID,"bottled_water"),BOTTLED_WATER_ITEM);
        Registry.register(Registries.ITEM, Identifier.of(Efdf.MODID,"box_milk"),BOX_MILK_ITEM);
    }

}
