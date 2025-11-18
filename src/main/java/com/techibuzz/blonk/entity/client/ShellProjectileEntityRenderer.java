package com.techibuzz.blonk.entity.client;

import com.techibuzz.blonk.entity.custom.ShellProjectileEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.ProjectileEntityRenderState;

@Environment(EnvType.CLIENT)
public class ShellProjectileEntityRenderer extends EntityRenderer<ShellProjectileEntity, EntityRenderState> {
    public ShellProjectileEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public EntityRenderState createRenderState() {
        return new ProjectileEntityRenderState();
    }
}