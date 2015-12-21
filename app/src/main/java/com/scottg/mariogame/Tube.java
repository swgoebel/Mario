package com.scottg.mariogame;

import android.graphics.Rect;

import com.scottg.framework.Image;

import java.util.ArrayList;

/**
 * Created by Babbas on 9/25/2015.
 */
public class Tube extends Sprite {
    private Rect rect = new Rect(0,0,0,0);
    private Mario mario = GameScreen.getMario();
    private Background bg = GameScreen.getBg1();
    public ArrayList items = GameScreen.getItems();
    public ArrayList goombas = GameScreen.getGoomba();
    public Item item1;
    public Goomba goomba1;


    private Image tubeImage;


    public Tube(int x, int y){
        setX(x);
        setY(y);
        tubeImage = Assets.tube;

    }

    public void update(){
        setX(getX()+(bg.getSpeedX()*2));
        rect.set(getX(), getY(), getX() + 32, getY() + 64);
        checkVerticalCollision(rect, mario.rectBottom);
        checkSideCollision(rect, mario.rectLeft, mario.rectRight);
        for(int i =0; i<items.size();i++){
            item1 = (Item) items.get(i);

            checkItemCollision(item1.rect, rect);
        }
        if(mario.getX()<400){
        for(int i =0; i<goombas.size();i++){
            goomba1 = (Goomba) goombas.get(i);

            checkEnemyCollision(goomba1.r, rect);
        }
        }
    }

    private void checkEnemyCollision(Rect rect, Rect r) {

        if(Rect.intersects(rect, r)){
            goomba1.setMoveSpeed(goomba1.getMoveSpeed() * -1);
        }
    }

    private void checkItemCollision(Rect itemrect, Rect rect) {
        if(Rect.intersects(itemrect, rect)){
            item1.setMoveSpeed(item1.getMoveSpeed()*-1);
        }
    }


    private void checkVerticalCollision(Rect rect, Rect rectBottom) {
        if(Rect.intersects(rect, rectBottom)){
            mario.setFalling(false);
            mario.setJumping(false);

            mario.setJumpHeight(0);
            mario.setY((mario.getY()));
        }
    }

    private void checkSideCollision(Rect rect, Rect rectLeft, Rect rectRight) {
        if(Rect.intersects(rect, rectLeft) || Rect.intersects(rect, rectRight)){
            if(Rect.intersects(rectRight, rect)){
                mario.setX(rect.left - 17);
                mario.arbitrary=rect.left-17;
            }
            if(Rect.intersects(rectLeft, rect)){
                mario.setX(rect.right + 1);
                mario.arbitrary=rect.right+17;
            }
        }
    }
    public void setTubeImage(Image tubeImage) {
        this.tubeImage = tubeImage;
    }

    public Image getTubeImage() {
        return tubeImage;
    }

}
