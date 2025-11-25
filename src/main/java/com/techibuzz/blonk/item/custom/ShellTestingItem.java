package com.techibuzz.blonk.item.custom;

import com.techibuzz.blonk.entity.ModEntities;
import com.techibuzz.blonk.entity.custom.ShellEntity;
import com.techibuzz.blonk.item.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ShellTestingItem extends Item {
    public ShellTestingItem(Properties settings) {
        super(settings);
    }

    @Override
    public @NotNull InteractionResult use(Level world, Player user, InteractionHand hand) {
        if (!world.isClientSide()) {
            ShellEntity shellEntity = new ShellEntity(ModEntities.SHELL, world);
            shellEntity.setPos(user.getEyePosition());

            Projectile.spawnProjectileFromRotation(
                    (world1, shooter, stack) -> shellEntity,
                    (ServerLevel) world,
                    new ItemStack(ModItems.SHELL_TESTING_ITEM),
                    user,
                    0.0F,
                    1.5F,
                    0.0F
            );
        }
        return InteractionResult.SUCCESS;
    }
}
