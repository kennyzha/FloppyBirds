package com.zhaokenny.floppybird.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zhaokenny.floppybird.FloppyBird;
import com.zhaokenny.floppybird.sprites.Bird;

public class PlayState extends State {
    private Bird bird;

    public PlayState(GameStateManager gsm){
        super(gsm);
        bird = new Bird(50, 300);
        cam.setToOrtho(false, FloppyBird.WIDTH / 2, FloppyBird.HEIGHT / 2);
    }
    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
