package com.git.mur.efdf.efdfBlocks;

import com.git.mur.efdf.Efdf;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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
import net.minecraft.util.math.Direction;

public class efdfFuel{
    public static void fuelInit(){
        Registry.register(Registries.BLOCK, Identifier.of(Efdf.MODID,"high_energy_fuel"),HIGH_ENERGY_FUEL);
        Registry.register(Registries.ITEM,
                Identifier.of(Efdf.MODID,"high_energy_fuel"),
                new BlockItem(HIGH_ENERGY_FUEL,new Item.Settings()));
    }
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final Block HIGH_ENERGY_FUEL = new Block(FabricBlockSettings.copy(Blocks.TNT)){
        {
            this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
        }
        @Override
        protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
            super.appendProperties(builder);
            builder.add(FACING);
        }

        @Override
        public BlockState getPlacementState(ItemPlacementContext ctx) {
            // 放置时朝向玩家（与熔炉一致）
            return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
        }

        @Override
        public BlockState rotate(BlockState state, BlockRotation rotation) {
            return state.with(FACING, rotation.rotate(state.get(FACING)));
        }

        @Override
        public BlockState mirror(BlockState state, BlockMirror mirror) {
            return state.rotate(mirror.getRotation(state.get(FACING)));
        }
    };
}
