
package com.techibuzz.blonk.entity.client;

import com.techibuzz.blonk.Blonk;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.state.ProjectileEntityRenderState;
import net.minecraft.util.Identifier;

public class ShellProjectileModel extends EntityModel<ProjectileEntityRenderState> {

    public static final EntityModelLayer SHELL = new EntityModelLayer(Identifier.of(Blonk.MOD_ID, "shell"), "main");
    private final ModelPart shell;

    public ShellProjectileModel(ModelPart root) {
        super(root);
        this.shell = root.getChild("shell");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData shell = modelPartData.addChild("shell", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -7.0F, -2.0F, 4.0F, 7.0F, 4.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 24.0F, 0.0F));

        return TexturedModelData.of(modelData, 16, 16);
    }


}