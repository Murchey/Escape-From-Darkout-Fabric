package com.git.mur.efdf.efdfThirstBar;

import net.dehydration.api.DrinkEvent;
import net.dehydration.api.DrinkItem;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

public class efdfDrinkItem extends DrinkItem {

    public efdfDrinkItem(Settings settings) {
        super(settings);
    }
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity)user : null;
        if (playerEntity instanceof ServerPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger((ServerPlayerEntity)playerEntity, stack);
        }

        if (playerEntity != null) {
            playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!playerEntity.getAbilities().creativeMode && this.isFood()) {
                ((DrinkEvent)DrinkEvent.EVENT.invoker()).onDrink(stack, playerEntity);
                user.eatFood(world, stack);
            }
        }

        if (playerEntity == null || !playerEntity.getAbilities().creativeMode) {
            if (stack.isEmpty()) {
                return new ItemStack(Items.AIR);
            }

            if (playerEntity != null) {
                playerEntity.getInventory().offerOrDrop(new ItemStack(Items.AIR));
            }
        }

        return stack;
    }
}
