package com.zhaokenny.floppybird.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zhaokenny.floppybird.FloppyBird;

public class PlayState extends State {
    private Texture bird;

    public PlayState(GameStateManager gsm){
        super(gsm);
        bird = new Texture("bird.png");
        cam.setToOrtho(false, FloppyBird.WIDTH / 2, FloppyBird.HEIGHT / 2);
    }
    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bird,50,50);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
