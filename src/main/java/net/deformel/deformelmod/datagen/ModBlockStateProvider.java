package net.deformel.deformelmod.datagen;

import net.deformel.deformelmod.DeformelMod;
import net.deformel.deformelmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, DeformelMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.CRYSTAL_BLOCK);
        blockWithItem(ModBlocks.CRYSTAL_ORE);

        blockWithItem(ModBlocks.SOUND_BLOCK);

        stairsBlock(((StairBlock) ModBlocks.CRYSTAL_STAIRS.get()), blockTexture(ModBlocks.CRYSTAL_BLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.CRYSTAL_SLAB.get()), blockTexture(ModBlocks.CRYSTAL_BLOCK.get()), blockTexture(ModBlocks.CRYSTAL_BLOCK.get()));

        buttonBlock(((ButtonBlock) ModBlocks.CRYSTAL_BUTTON.get()), blockTexture(ModBlocks.CRYSTAL_BLOCK.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.CRYSTAL_PRESSURE_PLATE.get()), blockTexture(ModBlocks.CRYSTAL_BLOCK.get()));

        fenceBlock(((FenceBlock) ModBlocks.CRYSTAL_FENCE.get()), blockTexture(ModBlocks.CRYSTAL_BLOCK.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.CRYSTAL_FENCE_GATE.get()), blockTexture(ModBlocks.CRYSTAL_BLOCK.get()));
        wallBlock(((WallBlock) ModBlocks.CRYSTAL_WALL.get()), blockTexture(ModBlocks.CRYSTAL_BLOCK.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.CRYSTAL_DOOR.get()), modLoc("block/crystal_door_bottom"), modLoc("block/crystal_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.CRYSTAL_TRAPDOOR.get()), modLoc("block/crystal_trapdoor"), true, "cutout");
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
