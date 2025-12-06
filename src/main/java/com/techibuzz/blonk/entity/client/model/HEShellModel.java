package com.techibuzz.blonk.entity.client.model;

import com.techibuzz.blonk.Blonk;
import com.techibuzz.blonk.entity.client.renderstate.ShellRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class HEShellModel extends EntityModel<ShellRenderState> {
    public static final ModelLayerLocation HE_SHELL = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, "he_shell"), "main");

    public HEShellModel(ModelPart root) {
        super(root, RenderType::entityCutout);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();

		PartDefinition he_shell = modelPartData.addOrReplaceChild("he_shell", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -5.0F, 4.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, (float) -Math.PI, 0.0F));
		PartDefinition fins = he_shell.addOrReplaceChild("fins", CubeListBuilder.create().texOffs(0, 14).addBox(-4.0F, 2.0F, 0.0F, 8.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.rotation(0.0F, 0.0F, 0.0F));
		fins.addOrReplaceChild("vertical_fins_r1", CubeListBuilder.create().texOffs(0, 19).addBox(-8.0F, 0.0F, -2.5F, 8.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 2.5F, 0.0F, 0.0F, -1.5708F));
		
        return LayerDefinition.create(modelData, 32, 32);
	}
}