package net.deformel.deformelmod.entity.custom;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class CrystalSnowball extends Snowball {

    private Vec3 startPos;
    private double maxRange = 20.0;
    private Vec3 initialVelocity;

    public CrystalSnowball(EntityType<? extends Snowball> entityType, Level level) {
        super(entityType, level);
        this.startPos = this.position();
        this.setNoGravity(true); // Отключаем гравитацию
    }

    public CrystalSnowball(Level level, LivingEntity shooter) {
        super(level, shooter);
        this.startPos = this.position();
        this.setNoGravity(true); // Отключаем гравитацию
    }

    public void setMaxRange(double range) {
        this.maxRange = range;
    }

    @Override
    public void setDeltaMovement(Vec3 deltaMovement) {
        super.setDeltaMovement(deltaMovement);
        this.initialVelocity = deltaMovement; // Сохраняем начальную скорость
    }

    @Override
    public void tick() {
        // Сохраняем постоянную скорость полета
        if (this.initialVelocity != null) {
            this.setDeltaMovement(this.initialVelocity);
        }

        super.tick();

        // Проверяем, не превысил ли снежок максимальную дальность
        if (this.startPos != null && this.position().distanceTo(this.startPos) > this.maxRange) {
            this.discard(); // Удаляем снежок, если он пролетел слишком далеко
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();

        // Наносим 6 единиц урона (3 сердца)
        if (entity instanceof LivingEntity) {
            entity.hurt(this.damageSources().thrown(this, this.getOwner()), 6.0F);
        }

        super.onHitEntity(result);
    }

    @Override
    protected float getGravity() {
        return 0.0F; // Полностью отключаем гравитацию
    }
}