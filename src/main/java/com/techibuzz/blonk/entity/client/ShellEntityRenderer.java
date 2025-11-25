package com.techibuzz.blonk.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.techibuzz.blonk.Blonk;
import com.techibuzz.blonk.entity.custom.ShellEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class ShellEntityRenderer extends net.minecraft.client.renderer.entity.EntityRenderer<ShellEntity, ShellEntityRenderState> {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, "textures/entity/shell/shell.png");
    private final ShellEntityModel model;

    public ShellEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ShellEntityModel(context.bakeLayer(ShellEntityModel.SHELL));
    }

    @Override
    public void submit(ShellEntityRenderState shellEntityRenderState, PoseStack matrixStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        matrixStack.pushPose();

        matrixStack.mulPose(Axis.YP.rotationDegrees(shellEntityRenderState.yaw));
        matrixStack.mulPose(Axis.XP.rotationDegrees(shellEntityRenderState.pitch));

        submitNodeCollector.submitModel(this.model, shellEntityRenderState, matrixStack, RenderType.entitySolid(TEXTURE), shellEntityRenderState.lightCoords, OverlayTexture.NO_OVERLAY, shellEntityRenderState.outlineColor, null);

        matrixStack.popPose();

        super.submit(shellEntityRenderState, matrixStack, submitNodeCollector, cameraRenderState);
    }

    @Override
    public @NotNull ShellEntityRenderState createRenderState() {
        return new ShellEntityRenderState();
    }

    @Override
    public void extractRenderState(ShellEntity shellEntity, ShellEntityRenderState shellEntityRenderState, float tickProgress) {
        super.extractRenderState(shellEntity, shellEntityRenderState, tickProgress);
        shellEntityRenderState.yaw = switch (ShellEntity.FACING) {
            case NORTH -> 180;
            case EAST -> 90;
            case WEST -> -90;
            default -> 0;
        };
        shellEntityRenderState.pitch = shellEntity.getXRot();
    }
}

