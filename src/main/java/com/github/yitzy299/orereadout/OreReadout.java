package com.github.yitzy299.orereadout;

import com.google.gson.JsonParser;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OreReadout implements ModInitializer {
    public static Logger LOG = LogManager.getLogger();
    public static Config CONFIG;

    @Override
    public void onInitialize() {
        var configPath = FabricLoader.getInstance().getConfigDir().resolve("ore-readout.json");
        if (!configPath.toFile().exists()) {
            try {
                Files.copy(Objects.requireNonNull(OreReadout.class.getResourceAsStream("/data/ore-readout/default_config.json")), configPath);
                LOG.info("Config file for ore-readout created in config/ore-readout.json");
                readProperties(configPath);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        readProperties(configPath);
    }

    private void readProperties(Path path) {
        List<Action> actions = new ArrayList<>();
        try {
            var array = new JsonParser().parse(Files.readString(path)).getAsJsonArray();
            array.forEach(e -> {
                var o = e.getAsJsonObject();
                var block = new Identifier(o.get("block").getAsString());
                var opChat = o.get("op_chat").getAsBoolean();
                var publicChat = o.get("public_chat").getAsBoolean();
                var console = o.get("log").getAsBoolean();
                var action = new Action(block, opChat, publicChat, console);
                actions.add(action);
            });
            CONFIG = new Config(actions);
        } catch (IOException e) {
            CONFIG = new Config(actions);
        }
    }
}
