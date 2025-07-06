package net.deformel.deformelmod.item;

import net.deformel.deformelmod.DeformelMod;
import net.deformel.deformelmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DeformelMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> DEFORMEL_TAB = CREATIVE_MODE_TABS.register("deformel_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CRYSTAL.get()))
            .title(Component.translatable("creativetab.deformel_tab"))
            .displayItems((pPatameters, pOutput) -> {

                pOutput.accept(ModItems.CRYSTAL.get());
                pOutput.accept(ModItems.METAL_DETECTOR.get());
                pOutput.accept(ModItems.STRAWBERRY.get());
                pOutput.accept(ModItems.PINE_CONE.get());

                pOutput.accept(ModItems.CRYSTAL_STAFF.get());
                pOutput.accept(ModItems.CRYSTAL_SWORD.get());
                pOutput.accept(ModItems.CRYSTAL_SHOVEL.get());
                pOutput.accept(ModItems.CRYSTAL_AXE.get());
                pOutput.accept(ModItems.CRYSTAL_PICKAXE.get());
                pOutput.accept(ModItems.CRYSTAL_HOE.get());


                pOutput.accept(ModBlocks.SOUND_BLOCK.get());
                pOutput.accept(ModBlocks.CRYSTAL_BLOCK.get());
                pOutput.accept(ModBlocks.CRYSTAL_ORE.get());

                pOutput.accept(ModBlocks.CRYSTAL_DOOR.get());
                pOutput.accept(ModBlocks.CRYSTAL_STAIRS.get());
                pOutput.accept(ModBlocks.CRYSTAL_FENCE_GATE.get());
                pOutput.accept(ModBlocks.CRYSTAL_TRAPDOOR.get());
                pOutput.accept(ModBlocks.CRYSTAL_PRESSURE_PLATE.get());
                pOutput.accept(ModBlocks.CRYSTAL_SLAB.get());
                pOutput.accept(ModBlocks.CRYSTAL_WALL.get());
                pOutput.accept(ModBlocks.CRYSTAL_BUTTON.get());
                pOutput.accept(ModBlocks.CRYSTAL_FENCE.get());



            })
            .build());


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
