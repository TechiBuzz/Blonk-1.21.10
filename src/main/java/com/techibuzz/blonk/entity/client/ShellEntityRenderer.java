package com.techibuzz.blonk.entity.client;

import com.techibuzz.blonk.Blonk;
import com.techibuzz.blonk.entity.custom.ShellEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class ShellEntityRenderer extends EntityRenderer<ShellEntity, ShellEntityRenderState> {
    public static final Identifier TEXTURE = Identifier.of(Blonk.MOD_ID, "textures/entity/shell/shell.png");
    private final ShellEntityModel model;

    public ShellEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new ShellEntityModel(context.getPart(ShellEntityModel.SHELL));
    }


    public void render(ShellEntityRenderState shellEntityRenderState, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, CameraRenderState cameraRenderState) {
        matrixStack.push();

        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(shellEntityRenderState.yaw - 90.F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(shellEntityRenderState.pitch + 90.F));
        orderedRenderCommandQueue.submitModel(this.model, shellEntityRenderState, matrixStack, RenderLayer.getEntitySolid(TEXTURE), shellEntityRenderState.light, OverlayTexture.DEFAULT_UV, shellEntityRenderState.outlineColor, null);

        matrixStack.pop();
        super.render(shellEntityRenderState, matrixStack, orderedRenderCommandQueue, cameraRenderState);
    }

    @Override
    public ShellEntityRenderState createRenderState() {
        return new ShellEntityRenderState();
    }

    @Override
    public void updateRenderState(ShellEntity shellEntity, ShellEntityRenderState shellEntityRenderState, float tickProgress) {
        super.updateRenderState(shellEntity, shellEntityRenderState, tickProgress);
        shellEntityRenderState.yaw = shellEntity.getLerpedYaw(tickProgress);
        shellEntityRenderState.yaw = shellEntity.getLerpedYaw(tickProgress);
    }
}

