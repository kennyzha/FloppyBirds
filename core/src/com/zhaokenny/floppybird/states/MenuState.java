package com.zhaokenny.floppybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zhaokenny.floppybird.*;

public class MenuState extends State {
    private Texture background;
    private Texture playButton;


    public MenuState(com.zhaokenny.floppybird.GameStateManager gsm){
        super(gsm);
        background = new Texture("bg.png");
        playButton = new Texture("playBtn.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, FloppyBird.WIDTH, FloppyBird.HEIGHT);
        sb.draw(playButton, (FloppyBird.WIDTH / 2) - (playButton.getWidth() / 2), FloppyBird.HEIGHT / 2  );
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
}
