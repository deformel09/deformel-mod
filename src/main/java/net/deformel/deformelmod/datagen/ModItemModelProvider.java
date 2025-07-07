package net.deformel.deformelmod.datagen;

import net.deformel.deformelmod.DeformelMod;
import net.deformel.deformelmod.block.ModBlocks;
import net.deformel.deformelmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {

    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1f);
        trimMaterials.put(TrimMaterials.IRON, 0.2f);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3f);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4f);
        trimMaterials.put(TrimMaterials.COPPER, 0.5f);
        trimMaterials.put(TrimMaterials.GOLD, 0.6f);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7f);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8f);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9f);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0f);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, DeformelMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.CRYSTAL);

        simpleItem(ModItems.METAL_DETECTOR);
        simpleItem(ModItems.STRAWBERRY);
        simpleItem(ModItems.PINE_CONE);

        simpleBlockItem(ModBlocks.CRYSTAL_DOOR);

        fenceItem(ModBlocks.CRYSTAL_FENCE, ModBlocks.CRYSTAL_BLOCK);
        buttonItem(ModBlocks.CRYSTAL_BUTTON, ModBlocks.CRYSTAL_BLOCK);
        wallItem(ModBlocks.CRYSTAL_WALL, ModBlocks.CRYSTAL_BLOCK);

        evenSimplerBlockItem(ModBlocks.CRYSTAL_STAIRS);
        evenSimplerBlockItem(ModBlocks.CRYSTAL_SLAB);
        evenSimplerBlockItem(ModBlocks.CRYSTAL_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBlocks.CRYSTAL_FENCE_GATE);

        trapdoorItem(ModBlocks.CRYSTAL_TRAPDOOR);

        handheldItem(ModItems.CRYSTAL_AXE);
        handheldItem(ModItems.CRYSTAL_PICKAXE);
        handheldItem(ModItems.CRYSTAL_SWORD);
        handheldItem(ModItems.CRYSTAL_HOE);
        handheldItem(ModItems.CRYSTAL_SHOVEL);

        trimmedArmorItem(ModItems.CRYSTAL_HELMET);
        trimmedArmorItem(ModItems.CRYSTAL_CHESTPLATE);
        trimmedArmorItem(ModItems.CRYSTAL_LEGGINGS);
        trimmedArmorItem(ModItems.CRYSTAL_BOOTS);
    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = DeformelMod.MOD_ID; // Change this to your mod id
        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {
                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();
                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };
                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.fromNamespaceAndPath(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.withDefaultNamespace(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.fromNamespaceAndPath(MOD_ID, currentTrimName);
                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");
                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);
                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(DeformelMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(DeformelMod.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }
    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(DeformelMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(DeformelMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", ResourceLocation.fromNamespaceAndPath(DeformelMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(DeformelMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(DeformelMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
