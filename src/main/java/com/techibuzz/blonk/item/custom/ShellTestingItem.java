package com.techibuzz.blonk.item.custom;

import com.techibuzz.blonk.entity.ModEntities;
import com.techibuzz.blonk.entity.custom.ShellEntity;
import com.techibuzz.blonk.item.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ShellTestingItem extends Item {
    public ShellTestingItem(Properties settings) {
        super(settings);
    }

    @Override
    public @NotNull InteractionResult use(Level level, Player user, InteractionHand hand) {
        if (!level.isClientSide()) {
            Projectile.spawnProjectileFromRotation(
                    (serverLevel, livingEntity, itemStack) -> new ShellEntity(ModEntities.SHELL, user.getEyePosition(), level),
                    (ServerLevel) level,
                    new ItemStack(ModItems.SHELL_TESTING_ITEM),
                    user,
                    0.0F,
                    1.25F,
                    0.0F
            );
        }
        return InteractionResult.SUCCESS;
    }
}
