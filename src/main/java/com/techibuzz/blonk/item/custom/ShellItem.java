package com.techibuzz.blonk.item.custom;

import com.techibuzz.blonk.entity.custom.ShellEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ShellItem extends Item implements ProjectileItem {
    private final EntityType<ShellEntity> shellEntityType;

    public ShellItem(Properties properties, EntityType<ShellEntity> shellEntityType) {
        super(properties);
        this.shellEntityType = shellEntityType;
    }

    @Override
    public @NotNull Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        return new ShellEntity(this.shellEntityType, pos, level);
    }
}
