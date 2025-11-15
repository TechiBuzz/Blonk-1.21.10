package com.techibuzz.blonk.block.entity;

import com.mojang.serialization.Codec;
import com.techibuzz.blonk.block.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.LootableInventory;
import net.minecraft.inventory.SingleStackInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BlonkBlockEntity extends BlockEntity implements LootableInventory, SingleStackInventory.SingleStackBlockEntityInventory {
    public int color = 0xFFFFFF;
    private ItemStack stack = ItemStack.EMPTY;

    public BlonkBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLONK_BLOCK_ENTITY, pos, state);
    }

    public void shitHue(int newColor) {
        color = ColorHelper.average(newColor, color);
        this.updateListeners(world);
    }

    private void updateListeners(World world) {
        if (world != null) {
            world.updateListeners(pos, getCachedState(), getCachedState(), 0);
        }
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);

        // Tinting
        color = view.read("color", Codec.INT).orElse(0xFFFFFF);
        this.updateListeners(world);

        // Shell thing
        if (!this.readLootTable(view)) {
            this.stack = view.read("item", ItemStack.CODEC).orElse(ItemStack.EMPTY);
        } else {
            this.stack = ItemStack.EMPTY;
        }

    }

    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);

        view.putInt("color", color);

        if (!this.writeLootTable(view) && !this.stack.isEmpty()) {
            view.put("item", ItemStack.CODEC, this.stack);
        }
    }

    @Override
    public @Nullable Object getRenderData() {
        return color;
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registries) {
        return createNbt(registries);
    }

    @Override
    public @Nullable RegistryKey<LootTable> getLootTable() {
        return null;
    }

    @Override
    public void setLootTable(@Nullable RegistryKey<LootTable> lootTable) {

    }

    @Override
    public long getLootTableSeed() {
        return 0;
    }

    @Override
    public void setLootTableSeed(long lootTableSeed) {

    }

    @Override
    public BlockEntity asBlockEntity() {
        return this;
    }

    @Override
    public ItemStack getStack() {
        this.generateLoot(null);
        return this.stack;
    }

    @Override
    public void setStack(ItemStack stack) {
        this.generateLoot(null);
        this.stack = stack;
    }

    public int getMaxStackSize() {
        return 8;
    }
}
