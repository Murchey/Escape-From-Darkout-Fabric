package com.git.mur.efdf.efdfItems;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class efdfFood {
    //效果：力量3 速度2 持续300秒
    public static final FoodComponent TAURINE_DRINK = (new FoodComponent.Builder()).hunger(7).saturationModifier(5.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20*300, 2),1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20*300, 1),1.0f)
            .build();
    public static final Item TAURINE_DRINK_ITEM = new Item(new Item.Settings().food(TAURINE_DRINK).maxCount(12));
    //效果：力量1 速度1 持续200秒
    public static final FoodComponent TAURINE_CRYSTALS = (new FoodComponent.Builder()).hunger(3).saturationModifier(3.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED,20*200,0),0.75f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,20*200,0),0.75f)
            .build();
    public static final Item TAURINE_CRYSTALS_ITEM = new Item(new Item.Settings().food(TAURINE_CRYSTALS).maxCount(64));
    public static void initFood(){
        Registry.register(Registries.ITEM, Identifier.of("efdf","taurine_drink"),TAURINE_DRINK_ITEM);
        Registry.register(Registries.ITEM, Identifier.of("efdf","taurine_crystals"),TAURINE_CRYSTALS_ITEM);
    }

}
