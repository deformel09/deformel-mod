package net.deformel.deformelmod.datagen;

import net.deformel.deformelmod.DeformelMod;
import net.deformel.deformelmod.block.ModBlocks;
import net.deformel.deformelmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, DeformelMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
                .add(ModBlocks.CRYSTAL_ORE.get()).addTag(Tags.Blocks.ORES);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.CRYSTAL_BLOCK.get(),
                        ModBlocks.CRYSTAL_ORE.get(),
                        ModBlocks.SOUND_BLOCK.get()

                );

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.CRYSTAL_BLOCK.get(),
                        ModBlocks.CRYSTAL_ORE.get(),
                        ModBlocks.SOUND_BLOCK.get()

                );
        this.tag(BlockTags.FENCES)
                .add(ModBlocks.CRYSTAL_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.CRYSTAL_FENCE_GATE.get());
        this.tag(BlockTags.WALLS)
                .add(ModBlocks.CRYSTAL_WALL.get());
    }
}
