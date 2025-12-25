package com.techibuzz.blonk.screen.custom;

import com.techibuzz.blonk.Blonk;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CyclingSlotBackground;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;

public class BlonkScreen extends AbstractContainerScreen<BlonkMenu> {
    private static final ResourceLocation BLONK_GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, "textures/gui/container/blonk.png");

    private static final ResourceLocation SHELL_SPRITE = ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, "container/slot/shell");
    private static final ResourceLocation DRAGON_SHELL_SPRITE = ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, "container/slot/dragon_shell");
    private static final ResourceLocation HE_SHELL_SPRITE = ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, "container/slot/he_shell");
    private static final ResourceLocation HEAT_SHELL_SPRITE = ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, "container/slot/heat_shell");
    private static final ResourceLocation NUCLEAR_SHELL_SPRITE = ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, "container/slot/nuclear_shell");
    private static final ResourceLocation SMOKE_SHELL_SPRITE = ResourceLocation.fromNamespaceAndPath(Blonk.MOD_ID, "container/slot/smoke_shell");
    private static final List<ResourceLocation> EMPTY_SLOT_SHELLS = List.of(
            SHELL_SPRITE, DRAGON_SHELL_SPRITE, HE_SHELL_SPRITE, HEAT_SHELL_SPRITE, NUCLEAR_SHELL_SPRITE, SMOKE_SHELL_SPRITE
    );

    private final CyclingSlotBackground templateIcon = new CyclingSlotBackground(0);

    public BlonkScreen(BlonkMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        this.templateIcon.tick(EMPTY_SLOT_SHELLS);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;

        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BLONK_GUI_TEXTURE, i, j, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
        this.templateIcon.render(this.menu, guiGraphics, partialTick, i, j);
//        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, SHELL_SPRITE, i + 80, j + 35, 16, 16, ARGB.white(1.0F));
    }
}
