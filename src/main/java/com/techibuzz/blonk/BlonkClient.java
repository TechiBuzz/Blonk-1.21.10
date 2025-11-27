package com.techibuzz.blonk;

import com.techibuzz.blonk.entity.ModEntities;
import com.techibuzz.blonk.entity.client.ShellEntityModel;
import com.techibuzz.blonk.entity.client.ShellEntityRenderer;
import com.techibuzz.blonk.screen.ModScreens;
import com.techibuzz.blonk.screen.custom.BlonkScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
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

        MenuScreens.register(ModScreens.BLONK_SCREEN_HANDLER, BlonkScreen::new);
    }
}
