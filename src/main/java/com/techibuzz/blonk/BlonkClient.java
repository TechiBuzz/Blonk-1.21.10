package com.techibuzz.blonk;

import com.techibuzz.blonk.entity.ModEntities;
import com.techibuzz.blonk.entity.client.model.*;
import com.techibuzz.blonk.entity.client.renderer.ShellRenderer;
import com.techibuzz.blonk.screen.ModScreens;
import com.techibuzz.blonk.screen.custom.BlonkScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class BlonkClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRenderers.register(ModEntities.SHELL, context -> new ShellRenderer(context, new ShellModel(context.bakeLayer(ShellModel.SHELL)), ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, "textures/entity/shell/shell.png")));
        EntityModelLayerRegistry.registerModelLayer(ShellModel.SHELL, ShellModel::getTexturedModelData);

        EntityRenderers.register(ModEntities.DRAGON_SHELL, context -> new ShellRenderer(context, new DragonShellModel(context.bakeLayer(DragonShellModel.DRAGON_SHELL)), ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, "textures/entity/shell/dragon_shell.png")));
        EntityModelLayerRegistry.registerModelLayer(DragonShellModel.DRAGON_SHELL, DragonShellModel::getTexturedModelData);

        EntityRenderers.register(ModEntities.HEAT_HELL, context -> new ShellRenderer(context, new HeatShellModel(context.bakeLayer(HeatShellModel.HEAT_SHELL)), ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, "textures/entity/shell/heat_shell.png")));
        EntityModelLayerRegistry.registerModelLayer(HeatShellModel.HEAT_SHELL, HeatShellModel::getTexturedModelData);

        EntityRenderers.register(ModEntities.HE_SHELL, context -> new ShellRenderer(context, new HEShellModel(context.bakeLayer(HEShellModel.HE_SHELL)), ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, "textures/entity/shell/he_shell.png")));
        EntityModelLayerRegistry.registerModelLayer(HEShellModel.HE_SHELL, HEShellModel::getTexturedModelData);

        EntityRenderers.register(ModEntities.NUCLEAR_SHELL, context -> new ShellRenderer(context, new NuclearShellModel(context.bakeLayer(NuclearShellModel.NUCLEAR_SHELL)), ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, "textures/entity/shell/nuclear_shell.png")));
        EntityModelLayerRegistry.registerModelLayer(NuclearShellModel.NUCLEAR_SHELL, NuclearShellModel::getTexturedModelData);

        EntityRenderers.register(ModEntities.SMOKE_SHELL,context -> new ShellRenderer(context, new SmokeShellModel(context.bakeLayer(SmokeShellModel.SMOKE_SHELL)), ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, "textures/entity/shell/smoke_shell.png")));
        EntityModelLayerRegistry.registerModelLayer(SmokeShellModel.SMOKE_SHELL, SmokeShellModel::getTexturedModelData);

        MenuScreens.register(ModScreens.BLONK_SCREEN_HANDLER, BlonkScreen::new);
    }
}
