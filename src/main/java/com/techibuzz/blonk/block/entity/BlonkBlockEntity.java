package com.techibuzz.blonk.block.entity;

import com.mojang.serialization.Codec;
import com.techibuzz.blonk.block.ModBlockEntities;
import com.techibuzz.blonk.screen.custom.BlonkMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlonkBlockEntity extends BaseContainerBlockEntity {
    private float firing_power = 1.5f;

    private NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

    public BlonkBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLONK_BLOCK_ENTITY, pos, state);
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new BlonkMenu(containerId, inventory, this);
    }

    @Override
    protected void saveAdditional(ValueOutput view) {
        super.saveAdditional(view);

        view.putFloat("firing_power", this.firing_power);
        ContainerHelper.saveAllItems(view, this.items);
    }

    @Override
    protected void loadAdditional(ValueInput view) {
        super.loadAdditional(view);

        this.firing_power = view.read("firing_power", Codec.FLOAT).orElse(1.5f);
        ContainerHelper.loadAllItems(view, this.items);
    }

    @Override
    protected @NotNull NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    public int getContainerSize() {
        return 1;
    }

    public float getFiringPower() {
        return this.firing_power;
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.saveWithoutMetadata(registries);
    }

    protected @NotNull Component getDefaultName() {
        return Component.translatable("screen.blonk.blonk");
    }
}
