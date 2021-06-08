package com.github.yitzy299.orereadout;

import java.util.ArrayList;
import java.util.List;

public class Config {
    public List<Action> actions = new ArrayList<>();

    public Config(List<Action> actions) {
        this.actions = actions;
    }
}
