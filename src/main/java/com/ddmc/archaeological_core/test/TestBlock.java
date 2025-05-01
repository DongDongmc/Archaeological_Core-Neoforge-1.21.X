package com.ddmc.archaeological_core.test;

import com.ddmc.archaeological_core.api.BaseBrushableBlock;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootTable;

import javax.annotation.Nullable;

public  class TestBlock extends BaseBrushableBlock {

    public static final MapCodec<BrushableBlock> CODEC = RecordCodecBuilder.mapCodec(
            p_344647_ -> p_344647_.group(
                            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("turns_into").forGetter(BrushableBlock::getTurnsInto),
                            BuiltInRegistries.SOUND_EVENT.byNameCodec().fieldOf("brush_sound").forGetter(BrushableBlock::getBrushSound),
                            BuiltInRegistries.SOUND_EVENT.byNameCodec().fieldOf("brush_comleted_sound").forGetter(BrushableBlock::getBrushCompletedSound),
                            propertiesCodec()
                    )
                    .apply(p_344647_, BrushableBlock::new)
    );
    @Override
    public MapCodec<BrushableBlock> codec() {
        return CODEC;
    }
    public TestBlock(Block block, SoundEvent soundEvent1, SoundEvent soundEvent2, Properties properties,
                     @Nullable ResourceKey<LootTable> lootTable, int brushLevel ) {
        super(block, soundEvent1, soundEvent2, properties, lootTable,  brushLevel);
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TestBlockEntity(pos, state);
    }
}
