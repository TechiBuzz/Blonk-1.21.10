package com.techibuzz.blonk.client;

import com.techibuzz.blonk.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.render.BlockRenderLayer;

@Environment(EnvType.CLIENT)
public class BlonkClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(ModBlocks.BLONK, BlockRenderLayer.CUTOUT);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) ->
                world != null && world.getBlockEntityRenderData(pos) instanceof Integer integer
                        ? integer
                        : 0xFFFFFF, ModBlocks.BLONK
        );

    }
}
