package com.github.fabricservertools.orereadout.mixin;

import com.github.fabricservertools.orereadout.OreReadout;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class MixinBlockBreak {
    @Inject(method = "onBreak(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/entity/player/PlayerEntity;)V", at = @At("HEAD"))
    public void onBroken(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo ci) {
        Block block = state.getBlock();
        if (block.is(Blocks.COAL_ORE)) {
            print(block, pos, world, player);
        } else if (block.is(Blocks.IRON_ORE)) {
            print(block, pos, world, player);
        } else if (block.is(Blocks.NETHER_GOLD_ORE)) {
        print(block, pos, world, player);
        } else if (block.is(Blocks.NETHER_QUARTZ_ORE)) {
            print(block, pos, world, player);
        }else if (block.is(Blocks.DIAMOND_ORE)) {
            print(block, pos, world, player);
        } else if (block.is(Blocks.EMERALD_ORE)) {
            print(block, pos, world, player);
        } else if (block.is(Blocks.REDSTONE_ORE)) {
            print(block, pos, world, player);
        } else if (block.is(Blocks.LAPIS_ORE)) {
            print(block, pos, world, player);
        } else if (block.is(Blocks.GOLD_ORE)) {
            print(block, pos, world, player);
        }
    }

    private void print(Block block, BlockPos pos, World world, PlayerEntity player) {
        if (OreReadout.sendToChat) {
            player.getServer().getPlayerManager().getPlayerList().forEach(serverPlayerEntity -> {
                if (serverPlayerEntity.hasPermissionLevel(2)) {
                    serverPlayerEntity.sendSystemMessage(new LiteralText(Registry.BLOCK.getId(block) + " was broken by " + player.getName().asString() + " at " + pos.getX() +
                            ", " + pos.getY() + ", " + pos.getZ() + " in " + world.getRegistryKey().getValue()).formatted(Formatting.YELLOW), Util.NIL_UUID);
                }
            });
        }
        if (OreReadout.sendInConsole) {
            OreReadout.LOG.info(Registry.BLOCK.getId(block) + " was broken by " + player.getName().asString() + " at " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + " in " + world.getRegistryKey().getValue());
        }

    }


}
