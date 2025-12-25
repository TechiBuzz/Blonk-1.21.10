package com.techibuzz.blonk.item.custom;

import com.techibuzz.blonk.entity.ModEntities;
import com.techibuzz.blonk.entity.custom.Shell;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ShellItem extends Item implements ProjectileItem {
    public ShellItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        return new Shell(ModEntities.SHELL, pos, level);
    }

    public float getDragFactor() {
        return 1.0f;
    }
}
