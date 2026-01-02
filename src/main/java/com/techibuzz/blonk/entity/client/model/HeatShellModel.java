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

public class HeatShellModel extends EntityModel<ShellRenderState> {
    public static final ModelLayerLocation HEAT_SHELL = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Blonk.MOD_ID, "heat_shell"), "main");

    public HeatShellModel(ModelPart root) {
		super(root, RenderTypes::entityCutoutNoCull);
	}
    
	public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition heat_shell = partdefinition.addOrReplaceChild("heat_shell", CubeListBuilder.create().texOffs(0, 12).addBox(-8.75F, -3.0F, -3.0F, 12.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -5.8F, 0.0F, (float) Math.PI/2, 0.0F));

        PartDefinition fins = heat_shell.addOrReplaceChild("fins", CubeListBuilder.create(), PartPose.offset(2.75F, 0.0F, 0.0F));
        fins.addOrReplaceChild("fin2_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -6.0F, 0.0F, 9.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));
        fins.addOrReplaceChild("fin1_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -6.0F, 0.0F, 9.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}