package com.git.mur.efdf.itemGroups;

import com.git.mur.efdf.efdfBlocks.efdfFuel;
import com.git.mur.efdf.efdfItems.commonItems;
import com.git.mur.efdf.efdfItems.efdfFood;
import com.git.mur.efdf.efdfItems.efdfOffensiveGrenade;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import com.git.mur.efdf.efdfBlocks.commonBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class commonItemGroup {
    public static final ItemGroup EFDF_ITEM_GROUP =
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(commonBlocks.STEEL_BLOCK))
                    .displayName(Text.translatable("commonItems"))
                    .entries(((displayContext, entries) ->{
                        entries.add(commonBlocks.STEEL_BLOCK);
                        entries.add(commonBlocks.HIGH_TOUGHNESS_STEEL_BLOCK);
                        entries.add(commonItems.STEEL_INGOT);
                        entries.add(commonItems.HIGH_TOUGHNESS_STEEL);
                        entries.add(commonItems.STEEL_BOTTLE);
                        entries.add(commonItems.THIN_STEEL_SHEET);
                        entries.add(efdfFood.TAURINE_DRINK_ITEM);
                        entries.add(efdfFood.TAURINE_CRYSTALS_ITEM);
                        entries.add(efdfFuel.HIGH_ENERGY_FUEL);
                        entries.add(efdfOffensiveGrenade.OFFENSIVE_GRENADE);
                        entries.add(efdfFood.COMPRESSED_BISCUITS_ITEM);
                        entries.add(efdfFood.BRAISED_BEEF_CAN_ITEM);
                        entries.add(efdfFood.BRAISED_BEEF_CAN_SMALL_ITEM);
                        entries.add(efdfFood.SWEET_BEAN_SAUCE_ITEM);
                        entries.add(efdfFood.BOTTLED_WATER_ITEM);
                        entries.add(efdfFood.BOX_MILK_ITEM);
                        entries.add(efdfFood.REDBULL_ITEM);
                    }))
                    .build();
    public static void initItemGroup(){
        Registry.register(Registries.ITEM_GROUP,
                new Identifier("efdf","efdf_group"),
                EFDF_ITEM_GROUP);
    }
}
