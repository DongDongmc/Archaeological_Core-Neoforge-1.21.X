package com.ddmc.archaeological_core.test;

import com.ddmc.archaeological_core.api.BaseBrushableBlockEntity;
import com.ddmc.archaeological_core.register.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TestBlockEntity extends BaseBrushableBlockEntity {

    public TestBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.TEST_BLOCK_ENTITY.get(), pPos, pBlockState);
    }
}
