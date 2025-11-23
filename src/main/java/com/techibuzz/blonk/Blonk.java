package com.techibuzz.blonk;

import com.techibuzz.blonk.block.ModBlockEntities;
import com.techibuzz.blonk.block.ModBlocks;
import com.techibuzz.blonk.entity.ModEntities;
import com.techibuzz.blonk.item.ModItemGroups;
import com.techibuzz.blonk.item.ModItems;
import com.techibuzz.blonk.sound.ModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Blonk implements ModInitializer {

    public static final String MOD_ID = "blonk";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModBlockEntities.registerModBlockEntities();
        ModEntities.registerModEntities();
        ModItemGroups.registerModItemGroups();
        ModSounds.registerModSounds();

        // Scrap trade to max level armorer
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 5, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 24),
                    new ItemStack(ModItems.SCRAP),
                    3,
                    14,
                    0.15f
            ));
        });
    }
}
