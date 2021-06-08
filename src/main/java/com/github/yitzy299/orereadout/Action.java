package com.github.yitzy299.orereadout;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Action {
    private Block block;

    public boolean isPublicChat() {
        return publicChat;
    }

    public boolean isOpChat() {
        return opChat;
    }

    public boolean isConsole() {
        return console;
    }

    private boolean publicChat;
    private boolean opChat;
    private boolean console;

    public Action(Identifier blockId, boolean op, boolean publicChat, boolean console) {
        this.block = Registry.BLOCK.get(blockId);
        this.publicChat = publicChat;
        this.opChat = op;
        this.console = console;
    }

    public Block getBlock() {
        return this.block;
    }
}
