package com.ddmc.archaeological_core.api;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootTable;
import javax.annotation.Nullable;

public abstract class BaseBrushableBlock extends BaseEntityBlock {

    private static final IntegerProperty DUSTED = BlockStateProperties.DUSTED;
    private static final IntegerProperty BRUSH_LEVEL = IntegerProperty.create("brush_level", 1, 4);

    public static final int TICK_DELAY = 2;
    private final Block turnsInto;
    private final SoundEvent brushSound;
    private final SoundEvent brushCompletedSound;
    @Nullable
    private final ResourceKey<LootTable> lootTable;
    private final int brushLevel;

    public BaseBrushableBlock(
            Block turnsInto,
            SoundEvent brushSound,
            SoundEvent brushCompletedSound,
            Properties properties,
            @Nullable ResourceKey<LootTable> lootTable,
            int brushLevel
    ) {
        super(properties);
        this.turnsInto = turnsInto;
        this.brushSound = brushSound;
        this.brushCompletedSound = brushCompletedSound;
        this.lootTable = lootTable;
        this.brushLevel = brushLevel;
        this.registerDefaultState(this.stateDefinition.any().setValue(DUSTED, 0).setValue(BRUSH_LEVEL, brushLevel));

    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(DUSTED, BRUSH_LEVEL);
    }
    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }
    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        pLevel.scheduleTick(pPos, this, 2);
    }
    @Override
    public BlockState updateShape(
            BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos
    ) {
        pLevel.scheduleTick(pPos, this, 2);
        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);
    }
    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.getBlockEntity(pPos) instanceof BaseBrushableBlockEntity brushableblockentity) {
            brushableblockentity.setLootTable(this.lootTable, pPos.asLong());
            brushableblockentity.setBrushLevel(this.brushLevel);
            brushableblockentity.checkReset();
        }
    }
    @Override
    public abstract BlockEntity newBlockEntity(BlockPos pPos, BlockState pState);

    public Block getTurnsInto() {
        return this.turnsInto;
    }
}
