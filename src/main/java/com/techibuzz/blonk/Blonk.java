package com.techibuzz.blonk;

import com.techibuzz.blonk.block.ModBlockEntities;
import com.techibuzz.blonk.block.ModBlocks;
import com.techibuzz.blonk.entity.ModEntities;
import com.techibuzz.blonk.item.ModItemGroups;
import com.techibuzz.blonk.item.ModItems;
import com.techibuzz.blonk.screen.ModScreens;
import com.techibuzz.blonk.sound.ModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Blonk implements ModInitializer {
    public static final String MOD_ID = "blonk";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModEntities.registerModEntities();
        ModItemGroups.registerModItemGroups();
        ModBlockEntities.registerModBlockEntities();
        ModScreens.registerModScreens();
        ModSounds.registerModSounds();

        // Scrap trade to max level armorer
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 5, factories -> factories.add((entity, random) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 24),
                new ItemStack(ModItems.SCRAP),
                3,
                14,
                0.15f
        )));
    }
}
