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

public class NuclearShellModel extends EntityModel<ShellRenderState> {
    public static final ModelLayerLocation NUCLEAR_SHELL = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Blonk.MOD_ID, "nuclear_shell"), "main");

    public NuclearShellModel(ModelPart root) {
        super(root, RenderTypes::entityCutoutNoCull);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition nuclear_shell = partdefinition.addOrReplaceChild("nuclear_shell", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -14.0F, -10.0F, 12.0F, 12.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, -4.0F, 0, (float) -Math.PI, 0));

        nuclear_shell.addOrReplaceChild("outer", CubeListBuilder.create().texOffs(0, 28).addBox(-7.0F, -14.0F, 2.0F, 14.0F, 14.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(44, 28).addBox(-3.0F, -10.0F, -11.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(30, 36).addBox(-7.0F, -14.0F, 6.0F, 14.0F, 14.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}