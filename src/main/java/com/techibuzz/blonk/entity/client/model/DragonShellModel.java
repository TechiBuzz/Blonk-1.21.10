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
import org.jetbrains.annotations.NotNull;

public class DragonShellModel extends EntityModel<@NotNull ShellRenderState> {
    public static final ModelLayerLocation DRAGON_SHELL = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Blonk.MOD_ID, "dragon_shell"), "main");

    public DragonShellModel(ModelPart root) {
        super(root, RenderTypes::entityCutoutNoCull);
	}

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition dragon_shell = partdefinition.addOrReplaceChild("dragon_shell", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -7.0F, 4.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0,4,-5.0F,0.0F, (float) -Math.PI, (float) (Math.PI)));
        PartDefinition bone2 = dragon_shell.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 18).addBox(-5.0F, 2.0F, -4.0F, 10.0F, 0.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        bone2.addOrReplaceChild("vertical_fins_r1", CubeListBuilder.create().texOffs(0, 29).addBox(-5.0F, 0.0F, -5.5F, 10.0F, 0.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 1.5F, 0.0F, 0.0F, -1.5708F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}