package net.deformel.deformelmod;

import com.mojang.logging.LogUtils;
import jdk.jfr.Category;
import net.deformel.deformelmod.block.ModBlocks;
import net.deformel.deformelmod.entity.ModEntities;
import net.deformel.deformelmod.item.ModCreativeModTabs;
import net.deformel.deformelmod.item.ModItems;
import net.deformel.deformelmod.loot.ModLootModifiers;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;


@Mod(DeformelMod.MOD_ID)
public class DeformelMod {
    // Укажите идентификатор мода в общем месте, чтобы на него можно было ссылаться
    public static final String MOD_ID = "deformelmod";
    // Прямая ссылка на логгер slf4j
    private static final Logger LOGGER = LogUtils.getLogger();

    public DeformelMod(FMLJavaModLoadingContext context)
    {

        IEventBus modEventBus = context.getModEventBus();

        ModItems.register(modEventBus);

        ModCreativeModTabs.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        ModBlocks.register(modEventBus);

        ModEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }


    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Добавить предмет примера блока во вкладку строительных блоков
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.CRYSTAL);
        }
    }

    // Вы можете использовать SubscribeEvent и позволить шине событий обнаруживать методы для вызова
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    // Вы можете использовать EventBusSubscriber для автоматической регистрации всех статических методов в классе, помеченных @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}