package com.techibuzz.blonk.item.custom;

import com.techibuzz.blonk.entity.ModEntities;
import com.techibuzz.blonk.entity.custom.DragonShell;
import com.techibuzz.blonk.entity.custom.Shell;
import com.techibuzz.blonk.entity.custom.SmokeShell;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class SmokeShellItem extends Item implements ProjectileItem {
    public SmokeShellItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        return new SmokeShell(ModEntities.SMOKE_SHELL, pos, level);
    }
}
