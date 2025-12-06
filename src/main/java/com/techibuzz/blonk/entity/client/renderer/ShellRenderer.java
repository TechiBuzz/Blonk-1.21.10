package com.techibuzz.blonk.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.techibuzz.blonk.entity.client.renderstate.ShellRenderState;
import com.techibuzz.blonk.entity.custom.Shell;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class ShellRenderer extends EntityRenderer<Shell, ShellRenderState> {
    private final EntityModel<ShellRenderState> model;
    private final ResourceLocation texture;

    public ShellRenderer(EntityRendererProvider.Context context, EntityModel<ShellRenderState> model, ResourceLocation texture) {
        super(context);
        this.model = model;
        this.texture = texture;
    }

    @Override
    public void submit(ShellRenderState shellRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(shellRenderState.yRot));
        poseStack.mulPose(Axis.XP.rotationDegrees(shellRenderState.xRot));

        submitNodeCollector.submitModel(this.model, shellRenderState, poseStack, RenderType.entityCutoutNoCull(this.texture), shellRenderState.lightCoords, OverlayTexture.NO_OVERLAY, shellRenderState.outlineColor, null);

        poseStack.popPose();
        super.submit(shellRenderState, poseStack, submitNodeCollector, cameraRenderState);
    }

    @Override
    public @NotNull ShellRenderState createRenderState() {
        return new ShellRenderState();
    }

    @Override
    public void extractRenderState(Shell shell, ShellRenderState shellRenderState, float tickProgress) {
        super.extractRenderState(shell, shellRenderState, tickProgress);
        shellRenderState.yRot = shell.getYRot(tickProgress);
        shellRenderState.xRot = shell.getXRot(tickProgress);
    }
}

