package com.zhaokenny.floppybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int movement = 100;
    private static final int GRAVITY = -15;

    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Texture animationTexture;

    public Bird(int x, int y){
        animationTexture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(animationTexture), 3, 0.5f);
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        bounds = new Rectangle(x, y, animationTexture.getWidth() / 3, animationTexture.getHeight());
    }

    public void update(float dt){
        birdAnimation.update(dt);

        if(position.y > 0){
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(movement * dt, velocity.y, 0);

        if(position.y < 0){
            position.y = 0;
        }
        velocity.scl(1 / dt);

        bounds.setPosition(position.x, position.y);
    }

    public void incrementAnimationFrame(){
        birdAnimation.incrementFrame();
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public Vector3 getPosition() {
        return position;
    }

    public void jump(){
        velocity.y = 250;
    }

    public Rectangle getBounds(){ return bounds; }

    public void dispose(){
        animationTexture.dispose();
    }
}
