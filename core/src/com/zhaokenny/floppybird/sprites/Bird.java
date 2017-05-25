package com.zhaokenny.floppybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int movement = 100;
    private static final int GRAVITY = -15;

    private Texture bird;
    private Vector3 position;
    private Vector3 velocity;

    public Bird(int x, int y){
        bird = new Texture("bird.png");
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
    }

    public void update(float dt){
        if(position.y > 0){
            velocity.add(0, GRAVITY, 0);
        }

        velocity.scl(dt);
        position.add(movement * dt, velocity.y, 0);

        if(position.y < 0){
            position.y = 0;
        }
        velocity.scl(1 / dt);
    }

    public Texture getTexture() {
        return bird;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public void jump(){
        velocity.y = 250;
    }
}
