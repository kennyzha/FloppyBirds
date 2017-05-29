package com.zhaokenny.floppybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    private Texture topTube;
    private Texture bottomTube;
    private Vector2 posTopTube;
    private Vector2 posBotTube;

    private Random random;
    private static final int FLUCTUATION = 150;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 50;
    public static final int TUBE_WIDTH = 52;

    private Rectangle topBounds;
    private Rectangle botBounds;

    public Tube(float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        random = new Random();

        posTopTube = new Vector2(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        topBounds = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        botBounds = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public void reposition(float x){
        posTopTube.set(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        topBounds.setPosition(posTopTube.x, posTopTube.y);
        botBounds.setPosition(posBotTube.x, posTopTube.y);
    }

    public boolean isColliding(Rectangle player){
        return  player.overlaps(botBounds) || player.overlaps(topBounds);
    }

    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }
}
