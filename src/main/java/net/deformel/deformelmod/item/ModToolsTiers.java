package net.deformel.deformelmod.item;

import net.deformel.deformelmod.DeformelMod;
import net.deformel.deformelmod.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolsTiers {
    public static final Tier CRYSTAL = TierSortingRegistry.registerTier(
            new ForgeTier(5, 1500, 5f, 4f, 25,
                    ModTags.Blocks.NEEDS_CRYSTAL_TOOL, ()-> Ingredient.of(ModItems.CRYSTAL.get())),
            ResourceLocation.fromNamespaceAndPath(DeformelMod.MOD_ID, "crystal"), List.of(Tiers.NETHERITE), List.of());
}
