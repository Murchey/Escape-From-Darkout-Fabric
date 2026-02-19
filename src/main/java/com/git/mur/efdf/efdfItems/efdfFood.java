package com.git.mur.efdf.efdfItems;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class efdfFood {

    public static final FoodComponent TAURINE_DRINK = (new FoodComponent.Builder()).hunger(5).saturationModifier(0.5f).build();
    public static final Item TAURINE_DRINK_ITEM = new Item(new Item.Settings().food(TAURINE_DRINK).maxCount(12));
    public static void initFood(){
        Registry.register(Registries.ITEM, Identifier.of("efdf","taurine_drink"),TAURINE_DRINK_ITEM);
    }

}
