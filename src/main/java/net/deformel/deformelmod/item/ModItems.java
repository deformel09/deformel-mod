package net.deformel.deformelmod.item;

import net.deformel.deformelmod.DeformelMod;
import net.deformel.deformelmod.item.custom.CrystalStaff;
import net.deformel.deformelmod.item.custom.FuelItem;
import net.deformel.deformelmod.item.custom.MetalDetectorItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DeformelMod.MOD_ID);
    public static final RegistryObject<Item> CRYSTAL = ITEMS.register("crystal",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));
    public static final RegistryObject<Item> CRYSTAL_STAFF = ITEMS.register("crystal_staff",
            () -> new CrystalStaff(new Item.Properties().stacksTo(1).durability(32)));
    public static final RegistryObject<Item> PINE_CONE = ITEMS.register("pine_cone",
            () -> new FuelItem(new Item.Properties(), 400));

    public static final RegistryObject<Item> CRYSTAL_SWORD = ITEMS.register("crystal_sword",
            () -> new SwordItem(ModToolsTiers.CRYSTAL, 4, 2, new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_PICKAXE = ITEMS.register("crystal_pickaxe",
            () -> new PickaxeItem(ModToolsTiers.CRYSTAL, 1, 1, new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_AXE = ITEMS.register("crystal_axe",
            () -> new AxeItem(ModToolsTiers.CRYSTAL, 7, 1, new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_SHOVEL = ITEMS.register("crystal_shovel",
            () -> new ShovelItem(ModToolsTiers.CRYSTAL, 0, 0    , new Item.Properties()));

    public static final RegistryObject<Item> CRYSTAL_HOE = ITEMS.register("crystal_hoe",
            () -> new HoeItem(ModToolsTiers.CRYSTAL, 0, 0, new Item.Properties()));
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
