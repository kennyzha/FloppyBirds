package com.zhaokenny.floppybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.zhaokenny.floppybird.*;
import com.zhaokenny.floppybird.sprites.Bird;
import com.zhaokenny.floppybird.sprites.Tube;

public class PlayState extends State {
    private static final int TUBE_COUNT = 4;
    private static final int TUBE_SPACING = 125;
    private static final int GROUND_Y_OFFSET = -75;

    private Bird bird;
    private Texture bg;
    private Texture ground;

    private Array<Tube> tubes;
    private Vector2 groundPos1;
    private Vector2 groundPos2;

    public PlayState(com.zhaokenny.floppybird.GameStateManager gsm){
        super(gsm);
        bird = new Bird(50, 300);
        cam.setToOrtho(false, FloppyBird.WIDTH / 2, FloppyBird.HEIGHT / 2);
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");

        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2( (cam.position.x - (cam.viewportWidth / 2)) + ground.getWidth(), GROUND_Y_OFFSET);

        tubes = new Array<Tube>();
        for(int i = 1 ; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }
    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
            bird.incrementAnimationFrame();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        updateGround();
        cam.position.x = bird.getPosition().x + 80;
        cam.update();

        for(Tube tube : tubes){
            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if(tube.isColliding(bird.getBounds())){
                gsm.set(new PlayState(gsm));
                break;
            }
        }

        if(bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);

        for(Tube tube : tubes){
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();

        for(Tube tube : tubes){
            tube.dispose();
        }
    }

    private void updateGround(){
        if(cam.position.x - cam.viewportWidth / 2 > groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if(cam.position.x - cam.viewportWidth / 2 > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
