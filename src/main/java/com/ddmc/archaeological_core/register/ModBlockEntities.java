package com.ddmc.archaeological_core.register;

import com.ddmc.archaeological_core.Archaeological_Core;
import com.ddmc.archaeological_core.test.TestBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Archaeological_Core.MODID);

    public static final Supplier<BlockEntityType<TestBlockEntity>> TEST_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("test_block_entity", () -> BlockEntityType.Builder.of(
                    TestBlockEntity::new, ModBlocks.TEST_BLOCK_1.get(), ModBlocks.TEST_BLOCK_2.get()).build(null));
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
