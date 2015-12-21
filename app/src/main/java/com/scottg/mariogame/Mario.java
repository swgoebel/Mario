package com.scottg.mariogame;

import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by Babbas on 6/30/2015.
 */


public class Mario extends Sprite {
    private int MOVESPEED = 2;
    private int JUMPSPEED = -5;
    public int jumpHeight, arbitrary, arbitrary2,arbitrary3 = 0;
    private int speedY = 3;

    boolean up, down, left, right, fire; //basic controls

    boolean lookRight;
    private int speedX;

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins += coins;
    }

    private int coins = 0;
    boolean falling = true;
    boolean topContact;
    boolean bottomContact;
    boolean rightContact;
    boolean leftContact;
    boolean jumpSound = false;

    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    boolean invincible = false;
    boolean jump;
    boolean big;
    boolean transition;
    boolean hurt;

    public boolean isDuck() {
        return duck;
    }

    public void setDuck(boolean duck) {
        this.duck = duck;
    }

    public int getMOVESPEED() {
        return MOVESPEED;
    }

    boolean duck = false;
    private String playerState;
    private int lives = 3;
    private int walkingAnimation;

    public int getInvincibleCount() {
        return invincibleCount;
    }

    public void setInvincibleCount(int invincibleCount) {
        this.invincibleCount = invincibleCount;
    }

    private int invincibleCount = 0;
    public static Rect rectBottom = new Rect(0, 0, 0, 0);
    public static Rect rectTop= new Rect(0,0,0,0);
    public static Rect rectRight= new Rect(0,0,0,0);
    public static Rect rectLeft= new Rect(0,0,0,0);
    public static Rect yellowRed = new Rect(0,0,0,0);
    private ArrayList tileArray = new ArrayList();
    private ArrayList itemArray = new ArrayList();
    public Item item;
    public ArrayList items = GameScreen.getItems();
    private int points = 0;
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points += points;
    }


    public Mario() {
        setX(200);
        setY(175);
    }

    public void jump() {
       if(getFalling()==false){    setJumping(true);}
    }

    public void setFalling(boolean a){
        this.falling = a;
        if(falling==true){speedY = 3;}
        else {speedY = 0;}
    }
    public boolean getBig() {
        return big;
    }

    public void setBig(Boolean big) { this.big = big;}

    public void moveRight() {
        if (right == true) {
            speedX = MOVESPEED;
        }
    }

    public void moveLeft() {
        if (left == true) {
            speedX = -MOVESPEED;
        }
    }
    public void setRight(boolean moving) {
        this.right = moving;
    }

    public void setLeft(boolean moving) {
        this.left = moving;
    }

    public boolean getRight() {
        return right;
    }

    public boolean getLeft() {
        return left;
    }

    public void stopRight() {
        setRight(false);
        stop();
    }

    public void stopLeft() {
        setLeft(false);
        stop();
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public boolean getFalling(){return falling;}
    public Boolean getJumping(){return jump;}

    public void stop() {
        if (right == false && left == false) {
            speedX = 0;
        }

        if (right == true && left == false) {
            moveRight();
        }

    }



    public void update() {
    //    setCenterX();
     //   setCenterY();

        if(getJumping()==true&&getY()>5){
            if(getJumpHeight()<=80){
                setSpeedY(-4);
                setJumpHeight(getJumpHeight()+4);
            }
            else{
                setFalling(true);
            }
           if(!jumpSound) {
               Assets.smallJump.play(0.85f);
            jumpSound = true;
           }
        }
        if(invincible==true){
            if(invincibleCount < 2500){
                invincible = true;
                invincibleCount+=10;
            }
            else{
                invincible = false;
                invincibleCount=0;
            }
        }
        x = getX() + speedX;
        y = getY() + speedY;
        for(int i =0; i<items.size();i++){
            item = (Item) items.get(i);

         checkItemCollision(item.rect, rectLeft, rectRight, rectBottom,rectTop);
        }
        if(getBig()){
            if(isDuck()){
                rectBottom.set(getX()+2, getY()+14, getX()+14, getY()+16);
                rectTop.set(getX()+2, getY(), getX()+14, getY()+2);
                rectLeft.set(getX(), getY()+4, getX()+1, getY()+13);
                rectRight.set(getX()+15, getY()+4,getX()+16,getY()+13);
                yellowRed.set(getX() - 64, getY() - 64, getX() + 80, getY() + 80);
            }
            rectBottom.set(getX()+2, getY()+30, getX()+14, getY()+33);
            rectTop.set(getX()+2, getY(), getX()+14, getY()+2);
            rectLeft.set(getX(), getY()+4, getX()+1, getY()+29);
            rectRight.set(getX()+15, getY()+4,getX()+16,getY()+29);
            yellowRed.set(getX() - 64, getY() - 64, getX() + 80, getY() + 80);}
        else{
            rectBottom.set(getX()+2, getY()+15, getX()+14, getY()+18);
            rectTop.set(getX()+2, getY(), getX()+14, getY()+2);
            rectLeft.set(getX(), getY()+4, getX()+1, getY()+13);
            rectRight.set(getX()+15, getY()+4,getX()+16,getY()+13);
            yellowRed.set(getX() - 64, getY() - 64, getX() + 80, getY() + 80);
        }
        if(getY() >10 && getJumping()==false){
            setFalling(true);
        }

    }

    private void checkItemCollision(Rect rect, Rect rectLeft, Rect rectRight, Rect rectBottom, Rect rectTop) {
        if(Rect.intersects(rect,rectLeft) || Rect.intersects(rect,rectRight) || Rect.intersects(rect,rectBottom) || Rect.intersects(rect,rectTop)){
            if(item.isRise()==false){
            items.remove(item);
                if(getBig()==false){
                 //   GameScreen.GameState state = GameScreen.GameState.Transition;
                    transition = true;
                    hurt = false;
                }
            setPoints(100);}
        }
    }


    public void setJumping(boolean jumping) {
        this.jump = jumping;
    }
}