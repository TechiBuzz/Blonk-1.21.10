package com.techibuzz.blonk.entity.client.model;

import com.techibuzz.blonk.Blonk;
import com.techibuzz.blonk.entity.client.renderstate.ShellRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;

public class SmokeShellModel extends EntityModel<ShellRenderState> {
    public static final ModelLayerLocation SMOKE_SHELL = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Blonk.MOD_ID, "smoke_shell"), "main");


    public SmokeShellModel(ModelPart root) {
        super(root, RenderTypes::entitySolid);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();

        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild("smoke_shell", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -4.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0,0,-2F,0.0F,0, 0.0F));

        return LayerDefinition.create(modelData, 32, 32);
    }
}