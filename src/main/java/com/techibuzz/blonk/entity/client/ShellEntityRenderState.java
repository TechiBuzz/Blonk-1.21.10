package com.techibuzz.blonk.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.EntityRenderState;

@Environment(EnvType.CLIENT)
public class ShellEntityRenderState extends EntityRenderState {
    public float yaw;
    public float pitch;
}
