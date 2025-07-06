package net.deformel.deformelmod.datagen;

import net.deformel.deformelmod.DeformelMod;
import net.deformel.deformelmod.block.ModBlocks;
import net.deformel.deformelmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItemModelProvider extends ItemModelProvider {
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
