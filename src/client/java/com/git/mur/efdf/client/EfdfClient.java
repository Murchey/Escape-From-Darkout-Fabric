package com.git.mur.efdf;

import com.git.mur.efdf.efdfItems.efdfOffensiveGrenade;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class EfdfClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // 注册手榴弹实体的渲染器
        EntityRendererRegistry.register(
                efdfOffensiveGrenade.INSTANT_GRENADE_PROJECTILE,
                FlyingItemEntityRenderer::new
        );
    }
}