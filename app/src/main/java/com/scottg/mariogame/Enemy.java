package com.scottg.mariogame;

/**
 * Created by Babbas on 6/27/2015.
 */
import android.graphics.Rect;


public class Enemy extends Sprite{

    private int power, centerX, speedX, centerY;
    private Background bg = GameScreen.getBg1();
    private Mario mario = GameScreen.getMario();
    public boolean dead = false;

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    private boolean falling= true;


    public Rect r = new Rect(0, 0, 0, 0);
    public int health = 5;

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    private int moveSpeed = 1;

    // Behavioral Methods
    public void update() {

        if(this.getX() > 400){
            setFalling(false);
            setX(getX() + (bg.getSpeedX() * 2));
        }
        else {
            setX(getX() + moveSpeed + (bg.getSpeedX() * 2));
            r.set(getX(), getY(), getX() + 20, getY() + 20);
            if (falling == false) {
                setY(getY());
            } else {
                setY(getY() + 2);
            }
            checkCollision(r, Mario.rectLeft, Mario.rectRight, Mario.rectTop, Mario.rectBottom);
            setFalling(true);
        }
    }

    private void checkCollision(Rect r, Rect marioLeft, Rect marioRight, Rect marioTop, Rect marioBottom) {
         if(!mario.isInvincible()) {
             if (Rect.intersects(r, marioLeft) || Rect.intersects(r, marioRight) || Rect.intersects(r, marioTop)) {
                 mario.transition = true;
                 mario.hurt = true;

             }
             if (Rect.intersects(r, marioBottom)) {
                 Assets.stomp.play(.85f);
                 mario.setFalling(false);
                 mario.setJumping(true);
                 mario.setJumpHeight(40);
                 setY(1000);
                 mario.setPoints(100);
             }
         }
    }

    public void die() {

    }

    public void attack() {

    }

    public int getPower() {
        return power;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public Background getBg() {
        return bg;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setBg(Background bg) {
        this.bg = bg;
    }

}