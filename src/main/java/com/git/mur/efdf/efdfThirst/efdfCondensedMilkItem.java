package com.git.mur.efdf.efdfThirst;

import net.dehydration.access.ThirstManagerAccess;
import net.dehydration.api.DrinkEvent;
import net.dehydration.api.DrinkItem;
import net.dehydration.thirst.ThirstManager;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class efdfCondensedMilkItem extends DrinkItem {
    public efdfCondensedMilkItem(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.EAT;
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
        if (playerEntity != null) {
            ThirstManager manager = ((ThirstManagerAccess) playerEntity).getThirstManager();
            manager.setThirstLevel(manager.getThirstLevel()-9);
        }

        return stack;
    }
}
