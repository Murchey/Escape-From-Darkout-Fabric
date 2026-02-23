package com.git.mur.efdf.efdfThirst;

import net.dehydration.api.DrinkEvent;
import net.dehydration.api.DrinkItem;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

public class efdfDrinkItem extends DrinkItem {
    private final Item container;//返回容器
    public efdfDrinkItem(Settings settings, Item container) {
        super(settings);
        this.container=container;
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
                ((DrinkEvent) DrinkEvent.EVENT.invoker()).onDrink(stack, playerEntity);
                user.eatFood(world, stack);
            }
        }

        if (playerEntity == null || !playerEntity.getAbilities().creativeMode) {
            if (stack.isEmpty()) {
                return new ItemStack(container);
            }

            if (playerEntity != null) {
                playerEntity.getInventory().offerOrDrop(new ItemStack(container));
            }
        }

        return stack;
    }
}
