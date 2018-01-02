package com.alessio.game;

import com.alessio.engine.Application;

public class Game extends Application {
    public Game() {
        super();
    }

    @Override
    public void init() {
        super.init();
        addLayer(new GameLayer());
        start();
    }
}
