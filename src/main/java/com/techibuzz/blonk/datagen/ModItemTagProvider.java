package com.techibuzz.blonk.datagen;

import com.techibuzz.blonk.item.ModItemTags;
import com.techibuzz.blonk.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(ModItemTags.SHELLS)
                .add(ModItems.SHELL)
                .add(ModItems.DRAGON_SHELL)
                .add(ModItems.HE_SHELL)
                .add(ModItems.HEAT_SHELL)
                .add(ModItems.NUCLEAR_SHELL)
                .add(ModItems.SMOKE_SHELL);
    }
}
