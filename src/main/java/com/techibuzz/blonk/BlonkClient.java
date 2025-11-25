package com.techibuzz.blonk;

import com.techibuzz.blonk.entity.ModEntities;
import com.techibuzz.blonk.entity.client.ShellEntityModel;
import com.techibuzz.blonk.entity.client.ShellEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;

@Environment(EnvType.CLIENT)
public class BlonkClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRenderers.register(
                ModEntities.SHELL,
                ShellEntityRenderer::new
        );
        EntityModelLayerRegistry.registerModelLayer(
                ShellEntityModel.SHELL,
                ShellEntityModel::getTexturedModelData
        );
    }
}
