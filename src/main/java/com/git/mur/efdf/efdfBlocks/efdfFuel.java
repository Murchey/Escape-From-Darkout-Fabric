package com.git.mur.efdf.efdfBlocks;

import com.git.mur.efdf.Efdf;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class efdfFuel extends Block{
    public efdfFuel(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING,Direction.NORTH));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)){
            case SOUTH -> SHAPE_S;
            case NORTH -> SHAPE_N;
            case WEST -> SHAPE_W;
            default -> SHAPE_E;
        };
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING,ctx.getHorizontalPlayerFacing().getOpposite());
    }

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    private static final VoxelShape SHAPE_N =Block.createCuboidShape(1, 0, 1, 15, 11, 15);
    private static final VoxelShape SHAPE_S =Block.createCuboidShape(1, 0, 1, 15, 11, 4);
    private static final VoxelShape SHAPE_E =Block.createCuboidShape(1, 0, 1, 4, 11, 15);
    private static final VoxelShape SHAPE_W =Block.createCuboidShape(12, 0, 1, 15, 11, 15);
    public static final Block HIGH_ENERGY_FUEL = new efdfFuel(FabricBlockSettings.copy(Blocks.TNT));
    public static void fuelInit(){
        Registry.register(Registries.BLOCK, Identifier.of(Efdf.MODID,"high_energy_fuel"),HIGH_ENERGY_FUEL);
        Registry.register(Registries.ITEM,
                Identifier.of(Efdf.MODID,"high_energy_fuel"),
                new BlockItem(HIGH_ENERGY_FUEL,new Item.Settings()));
        FuelRegistry.INSTANCE.add(HIGH_ENERGY_FUEL,20*80*9);//相当于一个煤炭块的燃烧时间
    }

}
