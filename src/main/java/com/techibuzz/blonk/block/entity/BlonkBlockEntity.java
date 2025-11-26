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
    private int ammo;
    private float firing_power = 1.5f;

    private NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

    public BlonkBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLONK_BLOCK_ENTITY, pos, state);
    }

    @Override
    protected void saveAdditional(ValueOutput view) {
        super.saveAdditional(view);

        view.putInt("ammo_count", this.ammo);
        view.putFloat("firing_power", this.firing_power);

        ContainerHelper.saveAllItems(view, this.items);
    }

    @Override
    protected void loadAdditional(ValueInput view) {
        super.loadAdditional(view);

        this.ammo = view.read("ammo_count", Codec.INT).orElse(0);
        this.firing_power = view.read("firing_power", Codec.FLOAT).orElse(1.5f);

        ContainerHelper.loadAllItems(view, this.items);
    }

    protected @NotNull Component getDefaultName() {
        return Component.translatable("screen.blonk.blonk");
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
    protected @NotNull AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        return new BlonkMenu(containerId, inventory);
    }

    @Override
    public int getContainerSize() {
        return 1;
    }

    public int getAmmoCount() { return  this.ammo; }

    public void incrementAmmo() { this.ammo += 8; }

    public void decrementAmmo() { this.ammo--; }

    public int getMaxAmmoCount() { return 64; }

    public float getFiringPower() { return firing_power; }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.saveWithoutMetadata(registries);
    }
}
