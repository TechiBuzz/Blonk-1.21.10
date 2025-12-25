package com.techibuzz.blonk.entity.client.renderstate;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

@Environment(EnvType.CLIENT)
public class ShellRenderState extends EntityRenderState {
    public float xRot;
    public float yRot;
}
