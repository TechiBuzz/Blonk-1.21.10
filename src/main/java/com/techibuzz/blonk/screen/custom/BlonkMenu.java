package com.techibuzz.blonk.screen.custom;

import com.techibuzz.blonk.screen.ModScreens;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BlonkMenu extends AbstractContainerMenu {
    private final Container blonkInventory;

    public BlonkMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, new SimpleContainer(1));
    }

    public BlonkMenu(int containerId, Inventory playerInventory, Container container) {
        super(ModScreens.BLONK_SCREEN_HANDLER, containerId);

        this.blonkInventory = container;
        container.startOpen(playerInventory.player);
        this.addSlot(new Slot(container, 0, 80, 35));

        this.addStandardInventorySlots(playerInventory, 8, 84);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack originalStack = slot.getItem();
            newStack = originalStack.copy();
            if (index < this.blonkInventory.getContainerSize()) {
                if (!this.moveItemStackTo(originalStack, this.blonkInventory.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(originalStack,  0, this.blonkInventory.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return newStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.blonkInventory.stillValid(player);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.blonkInventory.stopOpen(player);
    }
}
