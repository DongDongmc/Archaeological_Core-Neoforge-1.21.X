package com.ddmc.archaeological_core.register;

import com.ddmc.archaeological_core.Archaeological_Core;
import com.ddmc.archaeological_core.test.TestBlock;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {

    public  static  final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Archaeological_Core.MODID);
    public static final DeferredBlock<Block> TEST_BLOCK_1 = registerBlock("test_block_1",
            () -> new TestBlock(Blocks.SAND, SoundEvents.BRUSH_SAND,
                    SoundEvents.BRUSH_SAND_COMPLETED, BlockBehaviour.Properties.of().noLootTable(),
                    BuiltInLootTables.DESERT_WELL_ARCHAEOLOGY,1));
    public static final DeferredBlock<Block> TEST_BLOCK_2 = registerBlock("test_block_2",
            () -> new TestBlock(Blocks.SAND, SoundEvents.BRUSH_SAND,
                    SoundEvents.BRUSH_SAND_COMPLETED, BlockBehaviour.Properties.of().noLootTable(),
                    BuiltInLootTables.DESERT_WELL_ARCHAEOLOGY,2));
    private  static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> block1 = BLOCKS.register(name, block);
        registerBlockItem(name, block1);
        return block1;
    }
    private static <T extends  Block> void registerBlockItem(String name, DeferredBlock<T> block1) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block1.get(), new Item.Properties()));
    }
    public static void  register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
