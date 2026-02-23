package com.git.mur.efdf.mixin;

import com.git.mur.efdf.efdfItems.efdfFood;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlockEntity.class)
public class efdfFurnaceEvents {

    @Inject(method = "craftRecipe", at = @At("TAIL"))
    private static void returnEmptyBucketAfterSmeltMilk(DynamicRegistryManager registryManager,
                                                        Recipe<?> recipe,
                                                        DefaultedList<ItemStack> slots,
                                                        int count,
                                                        CallbackInfoReturnable<Boolean> cir){
        if (cir.getReturnValue() &&
                recipe.getOutput(registryManager).isOf(efdfFood.CONDENSED_MILK_ITEM)){
                slots.set(0,Items.BUCKET.getDefaultStack());
        }
    }
}
