package net.deformel.deformelmod.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.deformel.deformelmod.entity.custom.CrystalSnowball;

public class CrystalStaff extends Item {

    private static final int COOLDOWN_TICKS = 20; // 1 секунда = 20 тиков

    public CrystalStaff(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        // Проверяем кулдаун
        if (player.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.fail(itemStack);
        }

        if (!level.isClientSide) {
            // Создаем кастомный снежок
            CrystalSnowball snowball = new CrystalSnowball(level, player);

            // Устанавливаем позицию снежка
            snowball.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());

            // Устанавливаем направление полета
            Vec3 lookDirection = player.getLookAngle();
            double speed = 2.0;
            snowball.setDeltaMovement(lookDirection.x * speed, lookDirection.y * speed, lookDirection.z * speed);

            // Устанавливаем максимальную дальность полета (20 блоков)
            snowball.setMaxRange(20);

            // Добавляем снежок в мир
            level.addFreshEntity(snowball);

            // Наносим урон предмету (1 единица за использование)
            itemStack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));

            // Устанавливаем кулдаун на 1 секунду
            player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
        }

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }
}