package net.deformel.deformelmod.entity;

import net.deformel.deformelmod.DeformelMod;
import net.deformel.deformelmod.entity.custom.CrystalSnowball;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DeformelMod.MOD_ID);

    public static final RegistryObject<EntityType<CrystalSnowball>> CRYSTAL_SNOWBALL =
            ENTITY_TYPES.register("crystal_snowball",
                    () -> EntityType.Builder.<CrystalSnowball>of(CrystalSnowball::new, MobCategory.MISC)
                            .sized(0.25F, 0.25F)
                            .clientTrackingRange(4)
                            .updateInterval(10)
                            .build("crystal_snowball"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}