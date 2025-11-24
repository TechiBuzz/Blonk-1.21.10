package com.techibuzz.blonk.entity.client;

import com.techibuzz.blonk.Blonk;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ShellEntityModel extends EntityModel<ShellEntityRenderState> {
    public static final EntityModelLayer SHELL = new EntityModelLayer(Identifier.of(Blonk.MOD_ID, "shell"), "main");

    public ShellEntityModel(ModelPart root) {
        super(root, RenderLayer::getEntitySolid);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();

        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("shell", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, 0.0F, -3.5F, 4.0F, 4.0F, 7.0F, new Dilation(0.0F)),
                ModelTransform.rotation(0, (float) -Math.PI, 0));

        return TexturedModelData.of(modelData, 32, 32);
    }
}