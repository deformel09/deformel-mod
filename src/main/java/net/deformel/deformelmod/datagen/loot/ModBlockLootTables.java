package net.deformel.deformelmod.datagen.loot;

import net.deformel.deformelmod.block.ModBlocks;
import net.deformel.deformelmod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.CRYSTAL_BLOCK.get());
        this.dropSelf(ModBlocks.SOUND_BLOCK.get());

        this.dropSelf(ModBlocks.CRYSTAL_FENCE.get());
        this.dropSelf(ModBlocks.CRYSTAL_BUTTON.get());
        this.dropSelf(ModBlocks.CRYSTAL_STAIRS.get());
        this.dropSelf(ModBlocks.CRYSTAL_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.CRYSTAL_TRAPDOOR.get());
        this.dropSelf(ModBlocks.CRYSTAL_FENCE_GATE.get());
        this.dropSelf(ModBlocks.CRYSTAL_WALL.get());

        this.add(ModBlocks.CRYSTAL_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.CRYSTAL_SLAB.get()));

        this.add(ModBlocks.CRYSTAL_DOOR.get(),
                block -> createDoorTable(ModBlocks.CRYSTAL_DOOR.get()));


        this.add(ModBlocks.CRYSTAL_ORE.get(),
                block -> createRedstoneOreDrops(ModBlocks.CRYSTAL_ORE.get(), ModItems.CRYSTAL.get()));
    }
    protected LootTable.Builder createRedstoneOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 5.0F)))
                    .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
