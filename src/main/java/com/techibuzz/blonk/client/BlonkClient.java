package com.techibuzz.blonk.client;

import com.techibuzz.blonk.entity.ModEntities;
import com.techibuzz.blonk.entity.client.ShellProjectileModel;
import com.techibuzz.blonk.entity.client.ShellProjectileEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.EntityRendererFactories;

@Environment(EnvType.CLIENT)
public class BlonkClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ShellProjectileModel.SHELL, ShellProjectileModel::getTexturedModelData);
        EntityRendererFactories.register(ModEntities.SHELL, ShellProjectileEntityRenderer::new);
    }

}
