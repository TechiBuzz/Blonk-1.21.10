package com.techibuzz.blonk.item.custom;

import com.techibuzz.blonk.entity.ModEntities;
import com.techibuzz.blonk.entity.custom.ShellEntity;
import com.techibuzz.blonk.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ShellTestingItem extends Item {

    public ShellTestingItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            ShellEntity shellEntity = new ShellEntity(ModEntities.SHELL, world);
            shellEntity.setPosition(user.getEyePos());
            shellEntity.rotate(90, true, 10, true);

            ProjectileEntity.spawnWithVelocity(
                    (world1, shooter, stack) -> shellEntity,
                    (ServerWorld) world,
                    new ItemStack(ModItems.SHELL_TESTING_ITEM),
                    user,
                    0.0F,
                    1.5F,
                    0.0F
            );

        }
        return ActionResult.SUCCESS;
    }

}
