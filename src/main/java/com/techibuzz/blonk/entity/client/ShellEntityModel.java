package com.techibuzz.blonk.entity.client;

import com.techibuzz.blonk.Blonk;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class ShellEntityModel extends EntityModel<ShellEntityRenderState> {
    public static final ModelLayerLocation SHELL = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, "shell"), "main");

    public ShellEntityModel(ModelPart root) {
        super(root, RenderType::entitySolid);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();

        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild("shell", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -3.5F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)),
                PartPose.rotation(0, (float) -Math.PI, 0));

        return LayerDefinition.create(modelData, 32, 32);
    }
}